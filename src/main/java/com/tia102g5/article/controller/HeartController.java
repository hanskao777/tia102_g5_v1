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

import com.tia102g5.heart.model.HeartService;

@Controller
@RequestMapping("/heart")
public class HeartController {
	
	@Autowired
	HeartService heartSvc;	
		
	
	/*檢查會員是否對此文章按過讚*/
    @GetMapping("/status/{articleID}/{memberID}")
    public ResponseEntity<Boolean> getHeartStatus(
            @PathVariable Integer articleID,
            @PathVariable Integer memberID) {
        boolean isLiked = heartSvc.isArticleLikedByMember(articleID, memberID);
        return ResponseEntity.ok(isLiked);
    }
	
	
	/*切換文章的按讚狀態*/
    @PostMapping("/toggle")
    public ResponseEntity<Boolean> toggleHeart(@RequestParam Integer memberID, @RequestParam Integer articleID) {
    	// 這裡添加身份驗證檢查
    	boolean isLiked = heartSvc.toggleHeart(memberID, articleID);
        return ResponseEntity.ok(isLiked);
    }
    
    /*獲取特定文章的按讚數*/
    @GetMapping("/count/{articleID}")
    public ResponseEntity<Long> getHeartCount(@PathVariable Integer articleID) {
        Long count = heartSvc.getHeartCount(articleID);
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
