package hibernate.util.CompositeQuery;

//import hibernate.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.tia102g5.activity.model.Activity;
import com.tia102g5.partnermember.model.PartnerMemberService;
import com.tia102g5.venue.model.Venue;


public class HibernateUtil_CompositeQuery_Activity {

	public static Predicate get_aPredicate_For_AnyDB(CriteriaBuilder builder, Root<Activity> root, String columnName, String value) {

		Predicate predicate = null;

		if ("activityTag".equals(columnName)) { // 用於varchar
			predicate = builder.like(root.get(columnName), "%" + value + "%");
		}else if ("venueID".equals(columnName)) {
			Venue venue = new Venue();
			venue.setVenueID(Integer.valueOf(value));
			predicate = builder.equal(root.get("venue"), venue);}
//		}else if ("searchBar".equals(columnName)) {
//			
//			predicate = builder.like(root.get("activityID"), "%" + value + "%");
//			
//			String partnerName = new PartnerMemberService().getOnePartnerMember(Integer.valueOf(value)).getPartnerName();
//			predicate = builder.like(root.get("partnerName"), "%" + partnerName + "%");
//			
//			
//			predicate = builder.like(root.get("venueTimeSlotDate"), "%" + value + "%");
//		}
		return predicate;
	}

	@SuppressWarnings("unchecked")
	public static List<Activity> getAllC(Map<String, String[]> map, Session session) {

//		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		List<Activity> list = null;
		try {
			// 【●創建 CriteriaBuilder】
			CriteriaBuilder builder = session.getCriteriaBuilder();
			// 【●創建 CriteriaQuery】
			CriteriaQuery<Activity> criteriaQuery = builder.createQuery(Activity.class);
			// 【●創建 Root】
			Root<Activity> root = criteriaQuery.from(Activity.class);

			List<Predicate> predicateList = new ArrayList<Predicate>();
			
			Set<String> keys = map.keySet();
			int count = 0;
			for (String key : keys) {
				for(int i = 0; i < map.get(key).length; i++) {
					String value = map.get(key)[i];
					if (value != null && value.trim().length() != 0 && !"action".equals(key)) {
						count++;
						predicateList.add(get_aPredicate_For_AnyDB(builder, root, key, value.trim()));
						System.out.println("有送出查詢資料的欄位數count = " + count);
					}
				}
			}
			System.out.println("predicateList.size()="+predicateList.size());
			criteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
			criteriaQuery.orderBy(builder.asc(root.get("activityID")));
			// 【●最後完成創建 javax.persistence.Query●】
			Query query = session.createQuery(criteriaQuery);
			list = query.getResultList();

			tx.commit();
		} catch (RuntimeException ex) {
			if (tx != null)
				tx.rollback();
			throw ex; // System.out.println(ex.getMessage());
		} finally {
			session.close();
			// HibernateUtil.getSessionFactory().close();
		}

		return list;
	}
}
