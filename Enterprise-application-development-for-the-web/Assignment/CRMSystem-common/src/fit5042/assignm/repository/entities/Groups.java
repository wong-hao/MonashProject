package fit5042.assignm.repository.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import javax.persistence.Table;


@Entity
@Table(name = "Groups")
@NamedQueries({
    @NamedQuery(name = Groups.GET_ALL_QUERY_NAME, query = "SELECT g FROM Groups g")})
public class Groups implements Serializable{
	
	public static final String GET_ALL_QUERY_NAME = "Groups.getAll";
	
	int id;
	String username;
	String groupname;
	
	public Groups() {}
	
	

	public Groups(int id, String username, String groupname) {
		super();
		this.id = id;
		this.username = username;
		this.groupname = groupname;
	}


	@Id
    @GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((groupname == null) ? 0 : groupname.hashCode());
		result = prime * result + id;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		Groups other = (Groups) obj;
		if (groupname == null) {
			if (other.groupname != null)
				return false;
		} else if (!groupname.equals(other.groupname))
			return false;
		if (id != other.id)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "Groups [id=" + id + ", username=" + username + ", groupname=" + groupname + "]";
	}
	
	
	
	

}
