package com.tia102g5.message.model;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hibernate.util.CompositeQuery.HibernateUtil_CompositeQuery_Article3;


@Service("messageService")
public class MessageService {

	@Autowired
	MessageRepository repository;
	
	@Autowired
    private SessionFactory sessionFactory;

	public Message addMessage(Message message) {
        String formattedContent = formatMessageContent(message.getMessageContent());// 在保存之前處理換行符
        message.setMessageContent(formattedContent);
	    return repository.save(message); //返回保存後的實體
	}

	public Message updateMessage(Message message) {
		return repository.save(message);
	}

	public void deleteMessage(Integer message) {
		if (repository.existsById(message))
			repository.deleteByMessageID(message);
//		    repository.deleteById(message);
	}

	public Message getOneMessage(Integer messageID) {
		Optional<Message> optional = repository.findById(messageID);
//		return optional.get();
		return optional.orElse(null);  // public T orElse(T other) : 如果值存在就回傳其值，否則回傳other的值
	}

	public List<Message> getAll() {
		return repository.findAll();
	}
	
    public List<Message> getMessagesByArticleID(Integer articleID) {
        List<Message> messages = repository.findByArticle_ArticleID(articleID);
        return messages;
    }


   //處理留言換行內容
    private String formatMessageContent(String content) {
        if (content == null) {
            return "";
        }
        return content.replace("\n", "<br>");
    }
	
//	public List<Board> getAll(Map<String, String[]> map) {
//		return HibernateUtil_CompositeQuery_Board3.getAllC(map,sessionFactory.openSession());
//	}

}