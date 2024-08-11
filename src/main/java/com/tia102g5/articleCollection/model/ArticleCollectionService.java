package com.tia102g5.articleCollection.model;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.tia102g5.article.model.Article;
import com.tia102g5.generalmember.model.GeneralMember;

@Service("articleCollectionService")
public class ArticleCollectionService {


	@Autowired
	ArticleCollectionRepository repository;

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	//測試會員收藏 測試之後刪掉
	public boolean isArticleCollectionByMember(Integer articleID, Integer memberID) {
	    return !repository.findByMemberAndArticle(memberID, articleID).isEmpty();
	}
	
	
	// 確保文章收藏統計的一致性和安全性，同時提高程式碼的可維護性
	private static final String ARTICLECOLLECTION_COUNT_KEY = "article:collection:count:";

	public void addArticleCollection(ArticleCollection articleCollection) {
		repository.save(articleCollection);
		incrementRedisArticleCollectionCount(articleCollection.getArticle().getArticleID());
	}

//更新最終確定不需要就刪除
//	public void updateArticleCollection(ArticleCollection articleCollection) {
//		repository.save(articleCollection);
//	}

	public void deleteArticleCollection(Integer articleCollectionID) {
		ArticleCollection articleCollection = repository.findByArticleCollectionID(articleCollectionID)
				.orElseThrow(() -> new IllegalArgumentException("articleCollectionID " + articleCollectionID + " not found"));
				// Optional類和lambda表達式的組合
		
		repository.deleteByArticleCollectionID(articleCollectionID);
		decrementRedisArticleCollectionCount(articleCollection.getArticle().getArticleID());
	}

	public ArticleCollection getOneArticleCollection(Integer articleCollectionID) {
		return repository.findByArticleCollectionID(articleCollectionID)
				.orElseThrow(() -> new IllegalArgumentException("articleCollectionID " + articleCollectionID + " not found"));
	}

	public List<ArticleCollection> getAll() {
		return repository.findAll();
	}
	
	/*檢查收藏狀態*/
    public boolean isArticleCollectedByMember(Integer articleID, Integer memberID) {
        List<ArticleCollection> articleCollections = repository.findByMemberAndArticle(memberID, articleID);
        return !articleCollections.isEmpty();
    }

	/*檢查是否收藏過決定收藏或取消收藏*/
	public boolean toggleArticleCollection(Integer memberID, Integer articleID) {
		List<ArticleCollection> articleCollections = repository.findByMemberAndArticle(memberID, articleID);

		if (articleCollections.isEmpty()) {
			ArticleCollection articleCollection = new ArticleCollection();

			GeneralMember generalmember = new GeneralMember();
			generalmember.setMemberID(memberID);

			Article article = new Article();
			article.setArticleID(articleID);

			articleCollection.setGeneralMember(generalmember);
			articleCollection.setArticle(article);

			addArticleCollection(articleCollection);
			return true;
		} else {
			deleteArticleCollection(articleCollections.get(articleCollections.size() - 1).getArticleCollectionID());
			return false;
		}
	}

	// 獲取特定文章的收藏數
	public Long getArticleCollectionCount(Integer articleID) {
		String key = ARTICLECOLLECTION_COUNT_KEY + articleID;
		String count = redisTemplate.opsForValue().get(key);
		return count != null ? Long.parseLong(count) : 0L;
	}

	// 增加特定文章的收藏統計
	private void incrementRedisArticleCollectionCount(Integer articleID) {
		String key = ARTICLECOLLECTION_COUNT_KEY + articleID;
		redisTemplate.opsForValue().increment(key);
	}

	// 減少特定文章的收藏統計
	private void decrementRedisArticleCollectionCount(Integer articleID) {
		String key = ARTICLECOLLECTION_COUNT_KEY + articleID;
		redisTemplate.opsForValue().decrement(key);
	}
	
}
