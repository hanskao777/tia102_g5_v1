package hibernate.util.CompositeQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.tia102g5.news.model.News;
import com.tia102g5.administrator.model.Administrator;

public class HibernateUtil_CompositeQuery_News3 {

    public static Predicate get_aPredicate_For_AnyDB(CriteriaBuilder builder, Root<News> root, String columnName, String value) {

        Predicate predicate = null;

        if ("newsID".equals(columnName) || "newsStatus".equals(columnName)) // 用於Integer
            predicate = builder.equal(root.get(columnName), Integer.valueOf(value));

        else if ("newsTitle".equals(columnName) || "newsContent".equals(columnName)) // 用於varchar
            predicate = builder.like(root.get(columnName), "%" + value + "%");

        else if ("newsCreateTime".equals(columnName)) // 用於date
            predicate = builder.equal(root.get(columnName), java.sql.Timestamp.valueOf(value));

        else if ("administratorID".equals(columnName)) {
            Administrator administrator = new Administrator();
            administrator.setAdministratorID(Integer.valueOf(value));
            predicate = builder.equal(root.get("administrator"), administrator);
        }

        return predicate;
    }

    @SuppressWarnings("unchecked")
    public static List<News> getAllC(Map<String, String[]> map, Session session) {

        Transaction tx = session.beginTransaction();
        List<News> list = null;
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<News> criteriaQuery = builder.createQuery(News.class);
            Root<News> root = criteriaQuery.from(News.class);

            List<Predicate> predicateList = new ArrayList<Predicate>();

            Set<String> keys = map.keySet();
            int count = 0;
            for (String key : keys) {
                String value = map.get(key)[0];
                if (value != null && value.trim().length() != 0 && !"action".equals(key)) {
                    count++;
                    predicateList.add(get_aPredicate_For_AnyDB(builder, root, key, value.trim()));
                    System.out.println("有送出查詢資料的欄位數count = " + count);
                }
            }
            System.out.println("predicateList.size()="+predicateList.size());
            criteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
            criteriaQuery.orderBy(builder.asc(root.get("newsID")));
            Query query = session.createQuery(criteriaQuery);
            list = query.getResultList();

            tx.commit();
        } catch (RuntimeException ex) {
            if (tx != null)
                tx.rollback();
            throw ex;
        } finally {
            session.close();
        }

        return list;
    }
}