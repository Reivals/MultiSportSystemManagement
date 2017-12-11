package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.sun.media.jfxmedia.logging.Logger;

import model.Account;

@Repository
@Transactional
public class AccountDao {

	@PersistenceContext
	private EntityManager manager;
	
	public Account searchAccountByLoginAndPassword(String login,String password) {
	
		Query query = manager.createQuery("SELECT a FROM Account AS a WHERE a.login = :login AND a.password = :password",Account.class).setParameter("login",login)
				.setParameter("password", password);

			try
			{
				Account acc = (Account) query.getSingleResult();
				return acc;
			}
			catch(Exception exc)
			{
				return null;
			}
			
	}
	
	public void insertAccount(Account acc)
	{
		manager.persist(acc);
	}





}
