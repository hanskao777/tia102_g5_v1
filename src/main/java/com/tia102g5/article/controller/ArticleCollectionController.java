package com.tia102g5.article.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tia102g5.articleCollection.model.ArticleCollectionService;

@Controller
@RequestMapping("/articleCollection")
public class ArticleCollectionController {
	
	@Autowired
	ArticleCollectionService articleCollectionSvc;	
		
	
	/*檢查會員是否收藏此文章*/
   @GetMapping("/status/{articleID}/{memberID}")
    public ResponseEntity<Boolean> getCollectionStatus(
            @PathVariable Integer articleID,
            @PathVariable Integer memberID) {
        boolean isCollected = articleCollectionSvc.isArticleCollectedByMember(articleID, memberID);
        return ResponseEntity.ok(isCollected);
    }
	
	//切換文章的收藏狀態
    @PostMapping("/toggle")
    public ResponseEntity<Boolean> toggleArticleCollection(@RequestParam Integer memberID, @RequestParam Integer articleID) {
    	// 這裡添加身份驗證檢查
    	boolean isCollected = articleCollectionSvc.toggleArticleCollection(memberID, articleID);
        return ResponseEntity.ok(isCollected);
    }
    
    //獲取特定文章的收藏數
    @GetMapping("/count/{articleID}")
    public ResponseEntity<Long> getArticleCollectionCount(@PathVariable Integer articleID) {
        Long count = articleCollectionSvc.getArticleCollectionCount(articleID);
        return ResponseEntity.ok(count);
    }

	 

    
    //Redis 相關錯誤處理
    @ExceptionHandler(RedisConnectionFailureException.class)
    public ResponseEntity<String> handleRedisConnectionFailureException(RedisConnectionFailureException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Redis 連接失敗: " + e.getMessage());
    }
	
    //member或article相關錯誤
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body("參數錯誤: " + e.getMessage());
    }
	
    //未預期的異常處理
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("發生未預期的錯誤: " + e.getMessage());
    }

}
