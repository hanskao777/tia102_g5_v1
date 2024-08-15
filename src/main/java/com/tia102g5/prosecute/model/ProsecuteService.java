package com.tia102g5.prosecute.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service("prosecuteService")
public class ProsecuteService {
	private static final String PROSECUTE_HASH_KEY = "prosecute:";
	private static final String REPORTED_ARTICLES_SET = "reported:articles";
	private static final String REPORTED_MESSAGES_SET = "reported:messages";

	@Autowired
	ProsecuteRepository repository;

	@Autowired
	private StringRedisTemplate redisTemplate;

	@Transactional
	public void prosecuteContent(Prosecute prosecute) {
	    if (prosecute == null) {
	        throw new IllegalArgumentException("檢舉對象不能為空");
	    }
	    if (prosecute.getGeneralMember() == null || prosecute.getGeneralMember().getMemberID() == null) {
	        throw new IllegalArgumentException("檢舉者信息不完整");
	    }
	    // 檢查是檢舉文章還是留言
	       if (prosecute.getArticle() != null) {
	            if (prosecute.getArticle().getArticleID() == null) {
	                throw new IllegalArgumentException("被檢舉文章信息不完整");
	            }
	            if (isArticleReported(prosecute.getArticle().getArticleID())) {
	                throw new IllegalStateException("此文章已被檢舉");
	            }
	        } else if (prosecute.getMessage() != null) {
	            if (prosecute.getMessage().getMessageID() == null) {
	                throw new IllegalArgumentException("被檢舉留言信息不完整");
	            }
	            if (isMessageReported(prosecute.getMessage().getMessageID())) {
	                throw new IllegalStateException("此留言已被檢舉");
	            }
	        } else {
	            throw new IllegalArgumentException("必須指定檢舉的文章或留言");
	        }


		// 設置檢舉創建時間
	    if (prosecute.getProsecuteCreateTime() == null) {
	        prosecute.setProsecuteCreateTime(new Date(System.currentTimeMillis()));
	    }
	    
		try {
			repository.save(prosecute);
		} catch (DataIntegrityViolationException e) {
			throw new RuntimeException("檢舉保存失敗", e);
		}

		String prosecuteKey = PROSECUTE_HASH_KEY + prosecute.getProsecuteID();
		redisTemplate.opsForHash().putAll(prosecuteKey, convertProsecuteToMap(prosecute));

		// 更新 Redis 集合
	    if (prosecute.getArticle() != null) {
	        redisTemplate.opsForSet().add(REPORTED_ARTICLES_SET, prosecute.getArticle().getArticleID().toString());
	    } else if (prosecute.getMessage() != null) {
	        redisTemplate.opsForSet().add(REPORTED_MESSAGES_SET, prosecute.getMessage().getMessageID().toString());
	    }
	}

	// 被檢舉的是文章
	public boolean isArticleReported(Integer articleID) {
		return redisTemplate.opsForSet().isMember(REPORTED_ARTICLES_SET, articleID.toString());
	}

	// 被檢舉的是留言
	public boolean isMessageReported(Integer messageID) {
		return redisTemplate.opsForSet().isMember(REPORTED_MESSAGES_SET, messageID.toString());
	}

	private Map<String, String> convertProsecuteToMap(Prosecute prosecute) {
		Map<String, String> map = new HashMap<>();
		map.put("prosecuteID", prosecute.getProsecuteID().toString());
		map.put("memberID", prosecute.getGeneralMember().getMemberID().toString());
		map.put("prosecuteReason", prosecute.getProsecuteReason());
		map.put("prosecuteStatus", prosecute.getProsecuteStatus().toString());

		if (prosecute.getArticle() != null) {
			map.put("articleID", prosecute.getArticle().getArticleID().toString());
		}
		if (prosecute.getMessage() != null) {
			map.put("messageID", prosecute.getMessage().getMessageID().toString());
		}
		return map;
	}

//	public void addProsecute(Prosecute prosecute) {
//		repository.save(prosecute);
//	}
//
//	public void updateProsecute(Prosecute prosecute) {
//		repository.save(prosecute);
//	}
//
//	public void deleteProsecute(Integer prosecute) {
//		if (repository.existsById(prosecute))
//			repository.deleteByProsecuteID(prosecute);
////		    repository.deleteById(prosecute);
//	}
//
//	public Prosecute getOneProsecute(Integer prosecuteID) {
//		Optional<Prosecute> optional = repository.findById(prosecuteID);
////		return optional.get();
//		return optional.orElse(null);  // public T orElse(T other) : 如果值存在就回傳其值，否則回傳other的值
//	}
//
//	public List<Prosecute> getAll() {
//		return repository.findAll();
//	}
//
////	public List<Prosecute> getAll(Map<String, String[]> map) {
////		return HibernateUtil_CompositeQuery_Prosecute3.getAllC(map,sessionFactory.openSession());
////	}

}