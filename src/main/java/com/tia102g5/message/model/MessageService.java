package com.tia102g5.message.model;

import java.util.List;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("messageService")
public class MessageService {

	@Autowired
	MessageRepository repository;
	
	@Autowired
    private SessionFactory sessionFactory;

	public void addMessage(Message message) {
		repository.save(message);
	}

	public void updateMessage(Message message) {
		repository.save(message);
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

//	public List<Board> getAll(Map<String, String[]> map) {
//		return HibernateUtil_CompositeQuery_Board3.getAllC(map,sessionFactory.openSession());
//	}

}