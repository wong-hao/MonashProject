package fit5042.assignm.repository.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Named;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

@NamedQueries({
    @NamedQuery(name = Users.GET_ALL_QUERY_NAME, query = "SELECT u FROM Users u")})
public class Users implements Serializable {
	
	 public static final String GET_ALL_QUERY_NAME = "Users.getAll";
	 
	 private int userId;
	 private String username;
	 private String password;
	 
	 private Set<Customer> customers;
	 
	 public Users() {
	    }

	public Users(int userId, String username, String password, Set<Customer> customers) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.customers = new HashSet<>();
	}

	@Id
    @GeneratedValue
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	@Column(nullable = false,unique=true)
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

	@OneToMany(mappedBy = "user",cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
	public Set<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(Set<Customer> customers) {
		this.customers = customers;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + userId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Users other = (Users) obj;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Users [userId=" + userId + ", username=" + username + ", password=" + password + ", customers="
				+ customers + "]";
	}
	
	
	 
	 

}
