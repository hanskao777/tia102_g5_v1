package com.tia102g5.venuerental.model;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hibernate.util.CompositeQuery.HibernateUtil_CompositeQuery_Article3;

@Service("venueRentalService")
public class VenueRentalService {
	@Autowired
	VenueRentalRepository venueRentalRepository;

	@Autowired
	private SessionFactory sessionFactory;

	public void addVenueRental(VenueRental venueRental) {
		venueRentalRepository.save(venueRental);
	}

	public void updateVenueRental(VenueRental venueRental) {
		venueRentalRepository.save(venueRental);
	}

	public void deleteVenueRental(Integer venueRentalID) {// 刪不掉，寫開心的
		if (venueRentalRepository.existsById(venueRentalID))
			venueRentalRepository.deleteById(venueRentalID);
	}

	public VenueRental getOneVenueRental(Integer venueRentalID) {
		Optional<VenueRental> optional = venueRentalRepository.findById(venueRentalID);
		return optional.orElse(null);
	}

	public List<VenueRental> getAll() {
		return venueRentalRepository.findAll();
	}
	
	public List<VenueRental> findByPartnerMemberPartnerID(Integer partnerID) {
        return venueRentalRepository.findByPartnerMemberPartnerID(partnerID);
    }


//	public List<VenueRental> getAll(Map<String,String[]> map){//複合查詢，暫時先不做
//		return HibernateUtil_CompositeQuery_VenueRental.getAllC(map,sessionFactory.openSession());
//	}
}
