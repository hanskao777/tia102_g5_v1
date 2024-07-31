package com.tia102g5.articleImg.model;

import java.util.List;

import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import com.util.HibernateUtil;


@Transactional
public class ArticleImgService {

	private ArticleImgDAO dao;

	public ArticleImgService() {
		dao = new ArticleImgDAOImpl();
	}

	public ArticleImg addArticleImg(Integer articleID, byte[] articlePic) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			
			ArticleImg articleImg = new ArticleImg();
			articleImg.setArticleID(articleID);
			articleImg.setArticlePic(articlePic);

			System.out.println("Article ID: " + articleID);
			System.out.println("Article Pic Size: " + (articlePic != null ? articlePic.length : 0));
			
			dao.insert(articleImg);

			session.getTransaction().commit();
			return articleImg;
			
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		}

	}

	public ArticleImg updateArticleImg(Integer articleImgID, Integer articleID,byte[] articlePic) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			
			ArticleImg articleImg = new ArticleImg();
			articleImg.setArticleImgID(articleImgID);
			articleImg.setArticleID(articleID);
			articleImg.setArticlePic(articlePic);

			dao.update(articleImg);
			
			session.getTransaction().commit();
			return articleImg;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		}
	}

	public void deleteArticle(Integer articleImgID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			
			dao.delete(articleImgID);
			
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return;
		}
	}

	public List<ArticleImg> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<ArticleImg> list = dao.getAll();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		}
	}

	public ArticleImg getOneArticleImg(Integer articleImgID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
			session.beginTransaction();
			ArticleImg articleImg = dao.getById(articleImgID);
			session.getTransaction().commit();
			return articleImg;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		}
	}
	


//	public Set<Member> getMemberByMemberID(Integer memberID) {
//		return dao.getMemberByMemberID(memberID);
//	}
}
