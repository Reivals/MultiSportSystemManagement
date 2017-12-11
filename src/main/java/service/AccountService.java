package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.AccountDao;
import model.Account;

@Service
public class AccountService {
	
	@Autowired
	private AccountDao accountDao;
	
	
	public void createAccount(Account account)
	{
		accountDao.insertAccount(account);
	}
	public List<Account> getListOfAccounts()
	{
		
		return null;//return (List<Account>) accountDao.findAll();
	}
	public Account searchForAccountById(int id)
	{
		//return accountDao.findOne(id);
		return null;
	}
	
	public Account searchForAccountByLoginAndPassword(String login, String password)
	{
		return accountDao.searchAccountByLoginAndPassword(login,password);
	}


}
