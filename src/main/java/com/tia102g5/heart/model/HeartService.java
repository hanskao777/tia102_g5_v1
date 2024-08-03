package com.tia102g5.heart.model;

import java.util.List;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("heartService")
public class HeartService {

	@Autowired
	HeartRepository repository;
	
	@Autowired
    private SessionFactory sessionFactory;

	public void addHeart(Heart heart) {
		repository.save(heart);
	}

	public void updateHeart(Heart heart) {
		repository.save(heart);
	}

	public void deleteHeart(Integer heart) {
		if (repository.existsById(heart))
			repository.deleteByHeartID(heart);
//		    repository.deleteById(heart);
	}

	public Heart getOneHeart(Integer heartID) {
		Optional<Heart> optional = repository.findById(heartID);
//		return optional.get();
		return optional.orElse(null);  // public T orElse(T other) : 如果值存在就回傳其值，否則回傳other的值
	}

	public List<Heart> getAll() {
		return repository.findAll();
	}

//	public List<Board> getAll(Map<String, String[]> map) {
//		return HibernateUtil_CompositeQuery_Board3.getAllC(map,sessionFactory.openSession());
//	}

}