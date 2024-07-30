package com.tia102g5.partnermember.model;

import java.util.List;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("partnermemberService")
public class PartnerMemberService {
	
	@Autowired
	PartnerMemberRepository repository;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	public void addPartnerMember (PartnerMember partnermember) {
		repository.save(partnermember);
	}
	
	public void updatePartnerMember (PartnerMember partnermember) {
		repository.save(partnermember);
	}
	
	public void deletePartnerMember(Integer partnerID) {
		if(repository.existsById(partnerID))
			repository.deleteBypartnerID(partnerID);
//			repository.deleteById(partnerID);
	}
	
	public PartnerMember getOnePartnerMember(Integer partnerID) {
		Optional<PartnerMember> optional = repository.findById(partnerID);
//		return optional.get();
		return optional.orElse(null); // public T orElse(T other) : 如果值存在就回傳其值，否則回傳other的值
	}
	
	public List<PartnerMember> getAll(){
		return repository.findAll();
	}

}
