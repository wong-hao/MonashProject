package fit5042.assignm.repository.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="Administrator")
@NamedQueries({
    @NamedQuery(name = Admin.GET_ALL_ADMIN, query = "SELECT n FROM Admin n")})
public class Admin extends Users implements Serializable{

	private static final long serialVersionUID = 1L;
	public static final String GET_ALL_ADMIN = "Admin.getAll";
	//private int adminId;
	private String adminAddress;
	private int adminPhone;
	private Set<Users> users;
    
	public Admin() {
	}

	public String getAdminAddress() {
		return adminAddress;
	}

	public void setAdminAddress(String adminAddress) {
		this.adminAddress = adminAddress;
	}

	public int getAdminPhone() {
		return adminPhone;
	}

	public void setAdminPhone(int adminPhone) {
		this.adminPhone = adminPhone;
	}

	public Set<Users> getUsers() {
		return users;
	}

	public void setUsers(Set<Users> users) {
		this.users = users;
	}


}
