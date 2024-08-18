package com.tia102g5.commodity.model;

import com.tia102g5.activity.model.Activity;
import com.tia102g5.activity.model.ActivityRepository;
import com.tia102g5.activityPicture.model.ActivityPicture;
import com.tia102g5.partnermember.model.PartnerMember;
import com.tia102g5.partnermember.model.PartnerMemberRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;

//import hibernate.util.CompositeQuery.HibernateUtil_CompositeQuery_Commodity3;

import java.util.*;

import com.tia102g5.commoditypicture.model.CommodityPicture;

@Service("commodityService")
public class CommodityService {

    @Autowired
    CommodityRepository repository;

    @Autowired
    ActivityRepository activityRepository;
    
    @Autowired
    private SessionFactory sessionFactory;

    public void addCommodity(Commodity commodity) {
        repository.save(commodity);
    }

    public void updateCommodity(Commodity commodity) {
        repository.save(commodity);
    }

    public void deleteCommodity(Integer commodityID) {
        if (repository.existsById(commodityID))
            repository.deleteByCommodityID(commodityID);
    }

    public Commodity getOneCommodity(Integer commodityID) {
        Optional<Commodity> optional = repository.findById(commodityID);
        return optional.orElse(null);
        
    }
    
    public Activity getActivityById(Integer id) {
        return repository.findById(id)
                .map(Commodity::getActivity)
                .orElse(null);
    }

    public List<Commodity> getAll() {
        return repository.findAll();
    }
    
//    public List<Activity> getAllactivity() {
//        return activityRepository.findAll();
//    }

    public Page<Commodity> getCommoditiesByActivityPaginated(Integer activityID, Pageable pageable) {
        return repository.findByActivity_ActivityID(activityID, pageable);
    }
    
    public Page<Activity> getAllActivitiesPaginated(Pageable pageable) {
        return activityRepository.findAll(pageable);
    }
    
  public List<Commodity> getCommoditiesByActivity(Integer activityID) {
  return repository.findByActivity_ActivityID(activityID);
}
  
//  public Page<Activity> getActivity(Integer activityID,PageRequest pageRequest){
//	  return repository.findActivity(activityID, pageRequest);
//  }
//    
  public Optional<Commodity> getActivityByCommodityId(Integer commodityId) {
	    Commodity commodity = repository.findById(commodityId).orElseThrow();
	    return repository.findById(commodity.getActivity().getActivityID());
	}


//    複合查詢
//    public List<Commodity> getAll(Map<String, String[]> map) {
//        return HibernateUtil_CompositeQuery_Commodity3.getAllC(map, sessionFactory.openSession());
//    }

    public Set<CommodityPicture> getCommodityPicturesByCommodityID(Integer commodityID) {
        return getOneCommodity(commodityID).getCommodityPictures();
    }

    public List<Activity> getAllActivities() {
        return repository.findAllDistinctActivities();
    }
    
//    public List<Activity> getAllActivitiesPicture() {
//        return repository.findAllDistinctActivitiesPicture();
//    }


//    public List<Activity> getActivitiesByPartner(Integer partnerID){
//        return repository.findByPartnerMember_PartnerID(partnerID);
//    }

    public List<Activity> getActivitiesByPartnerMember(Integer partnerMemberID) {
        List<Activity> activitiesFromCommodities = repository.findActivitiesByPartnerMemberID(partnerMemberID);
        List<Activity> allActivities = repository.findAllActivitiesByPartnerMemberID(partnerMemberID);

        // 合併兩個列表並去重
        Set<Activity> uniqueActivities = new HashSet<>(activitiesFromCommodities);
        uniqueActivities.addAll(allActivities);

        return new ArrayList<>(uniqueActivities);

//        return repository.findActivitiesByPartnerMemberID(partnerMemberID);
    }




//    public List<Activity> getActivitiesByPartnerMember(Integer partnerID) {
//        return repository.findByPartnerMember_PartnerMemberID(partnerID);
//    }

//    public void addCommodity(Commodity commodity, Integer activityID, Integer partnerID) {
//        Activity activity = ActivityRepository.findById(activityID).orElseThrow(() -> new RuntimeException("Activity not found"));
//        PartnerMember partnerMember = PartnerMemberRepository.findById(partnerID).orElseThrow(() -> new RuntimeException("PartnerMember not found"));
//
//        commodity.setActivity(activity);
//        commodity.setPartnerMember(partnerMember);
//
//        repository.save(commodity);
//    }

    // 可以根據需求添加其他方法，例如：
    // public List<Commodity> getCommoditiesByStatus(Integer status) {
    //     return repository.findByCommodityStatus(status);
    // }

    // public List<Commodity> getCommoditiesByPartner(Integer partnerID) {
    //     return repository.findByPartnerMember_PartnerID(partnerID);
    // }
}