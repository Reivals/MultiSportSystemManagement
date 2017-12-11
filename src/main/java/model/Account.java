package model;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Account {
	
	@Id
	@GeneratedValue
	@Column (name = "Id")
	private int id;
	@Column(name = "Login")
	private String login;
	@Column (name = "Password")
	private String password;
	//@CollectionTable(name="Months", joinColumns=@JoinColumn(name="example_id"))
	
	public Account(String login, String password) {

		this.login = login;
		this.password = password;
	}
	public Account() {}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
	

}
