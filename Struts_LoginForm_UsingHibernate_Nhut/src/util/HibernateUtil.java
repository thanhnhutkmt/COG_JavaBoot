/**
 * 
 */
package util;

import org.hibernate.*;
import org.hibernate.cfg.*;
/**
 * @author java_dev
 *
 */
public class HibernateUtil {
	private static SessionFactory sessionFactory;
	
	static {
		try {
			sessionFactory = new Configuration()
				.configure("hibernate.cfg.xml").buildSessionFactory();
		} catch(Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError();
		}	
	}
	
	public static SessionFactory getSessionFactory() {	
		return sessionFactory;
	}
}
