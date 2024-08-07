package com.tia102g5.activity.model;

import java.util.List;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("activityService")
public class ActivityService {

	@Autowired
	ActivityRepository repository;
	
//	@Autowired
//	private SessionFactory sessionFactory;
	
	//新增
	public void addActivity(Activity activity) {
		repository.save(activity);
	}
	
	//修改
	public void updateActivity(Activity activity) {
		repository.save(activity);
	}
	
	//刪除
	public void deleteActivity(Integer activityID) {
		if(repository.existsById(activityID)) {
			repository.deleteByActivityID(activityID);
		}
	}
	
	//查詢 (單一)
	public Activity getOneActivity(Integer activityID) {
		Optional<Activity> optional = repository.findById(activityID);
		
		return optional.orElse(null);
	}
	
	//查詢 (全部)
	public List<Activity> getAll(){
		return repository.findAll();
	}
}
