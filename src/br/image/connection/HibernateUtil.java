package br.image.connection;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil {
	private static SessionFactory sessionFactory = null;
	
	static {
		try {
			sessionFactory = new AnnotationConfiguration()
						.configure()
						.buildSessionFactory();
		} catch (Throwable e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError();
		}
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}