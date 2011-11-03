package br.imagem.dao;


import org.hibernate.Session;
import org.hibernate.Transaction;

import br.image.connection.HibernateUtil;
import br.image.entity.Book;

public class BookDAO {
	
	public Book save(Book book) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			session.save(book);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
		return book;
	}
	
	public Book load(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Book book = null;
		try {
			 book = (Book)session.load(Book.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return book;
	}
}