package fit5042.assignm.controllers;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import fit5042.assignm.repository.entities.Customer;

@RequestScoped
@Named(value = "user")
public class User {
	
	private int userId;
	@NotNull(message = "Username can not be empty")
    private String username;
	@NotNull(message = "Username can not be empty")
	 @Pattern(regexp="((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{5,20})", message = "Length of Password must be between 5-20, Contains at least 1 digit,lower case,uppercase")
    private String password;
    
    private Set<Customer> customers;
    
    
    public User() {}

	public User(int userId, String username, String password, Set<Customer> customers) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.customers = customers;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(Set<Customer> customers) {
		this.customers = customers;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", customers="
				+ customers + "]";
	}
    
	
    
    

}