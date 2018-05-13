/**
 * 
 */
package model;

import java.util.List;
import org.hibernate.*;

import entities.Account;
import util.HibernateUtil;

/**
 * @author java_dev
 *
 */
public class AccountModel {
	public List<Account> getAccounts() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("FROM Account");
			List<Account> lstAccounts = query.list();
			transaction.commit();
			return lstAccounts;
		} catch(HibernateException e) {
			if (!(transaction == null)) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return null;
	}
}
