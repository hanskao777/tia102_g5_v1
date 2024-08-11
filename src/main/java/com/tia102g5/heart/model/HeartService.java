package com.tia102g5.heart.model;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.tia102g5.article.model.Article;
import com.tia102g5.generalmember.model.GeneralMember;

@Service("heartService")
public class HeartService {

	@Autowired
	HeartRepository repository;

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	//會員是否按讚
	public boolean isArticleLikedByMember(Integer articleID, Integer memberID) {
	    return !repository.findByMemberAndArticle(memberID, articleID).isEmpty();
	}
	
	
	// 確保文章按讚統計的一致性和安全性，同時提高程式碼的可維護性
	private static final String HEART_COUNT_KEY = "article:heart:count:";

	public void addHeart(Heart heart) {
		repository.save(heart);
		incrementRedisHeartCount(heart.getArticle().getArticleID());
	}

//更新最終確定不需要就刪除
//	public void updateHeart(Heart heart) {
//		repository.save(heart);
//	}

	public void deleteHeart(Integer heartID) {
		Heart heart = repository.findByHeartID(heartID)
				.orElseThrow(() -> new IllegalArgumentException("heartID " + heartID + " not found"));// Optional
																										// 類和lambda
																										// 表達式的組合
		repository.deleteByHeartID(heartID);
		decrementRedisHeartCount(heart.getArticle().getArticleID());
	}

	public Heart getOneHeart(Integer heartID) {
		return repository.findByHeartID(heartID)
				.orElseThrow(() -> new IllegalArgumentException("heartID " + heartID + " not found"));
	}

	public List<Heart> getAll() {
		return repository.findAll();
	}

	// 檢查是否按讚過決定按讚或取消按讚
	public boolean toggleHeart(Integer memberID, Integer articleID) {
		List<Heart> hearts = repository.findByMemberAndArticle(memberID, articleID);

		if (hearts.isEmpty()) {
			Heart heart = new Heart();

			GeneralMember generalmember = new GeneralMember();
			generalmember.setMemberID(memberID);

			Article article = new Article();
			article.setArticleID(articleID);

			heart.setGeneralMember(generalmember);
			heart.setArticle(article);

			addHeart(heart);
			return true;
		} else {
			deleteHeart(hearts.get(hearts.size() - 1).getHeartID());
			return false;
		}
	}

	// 獲取特定文章的按讚數
	public Long getHeartCount(Integer articleID) {
		String key = HEART_COUNT_KEY + articleID;
		String count = redisTemplate.opsForValue().get(key);
		return count != null ? Long.parseLong(count) : 0L;
	}

	// 增加特定文章的按讚統計
	private void incrementRedisHeartCount(Integer articleID) {
		String key = HEART_COUNT_KEY + articleID;
		redisTemplate.opsForValue().increment(key);
	}

	// 減少特定文章的按讚統計
	private void decrementRedisHeartCount(Integer articleID) {
		String key = HEART_COUNT_KEY + articleID;
		redisTemplate.opsForValue().decrement(key);
	}
}
