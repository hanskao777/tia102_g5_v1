package com.tia102g5.generalmember.model;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import HibernateUtil.HibernateUtil_CompositeQuery_GeneralMember;

@Service("generalmemberService")
public class GeneralMemberService {

	@Autowired
	GeneralMemberRepository repository;

	@Autowired
	private SessionFactory sessionFactory;

	public void addGeneralMember(GeneralMember generalMember) {
		repository.save(generalMember);
	}

	public void updateGeneralMember(GeneralMember generalMember) {
		repository.save(generalMember);
	}

	public void deleteGeneralMember(Integer memberID) {
		if (repository.existsById(memberID))
			repository.deleteByMemberID(memberID);
//			repository.deleteById(memberID);
	}
	
	public GeneralMember getOneGeneralMember(Integer memberID) {
		Optional<GeneralMember> optional = repository.findById(memberID);
//		return optional.get();
		return optional.orElse(null); // public T orElse(T other) : 如果值存在就回傳其值，否則回傳other的值
	}
	
	public List<GeneralMember> getAll(){
		return repository.findAll();
	}
	
	public List<GeneralMember> getAll(Map<String, String[]>map){
		return HibernateUtil_CompositeQuery_GeneralMember.getAllC(map,sessionFactory.openSession());
	}
}
