package com.tia102g5.activitycollection.model;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tia102g5.activity.model.ActivityRepository;

public interface ActivityCollectionRepository extends JpaRepository<ActivityRepository, Integer>{

}
