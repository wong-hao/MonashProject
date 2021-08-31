package fit5042.assignm.repository.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Organisation")
@NamedQueries({
    @NamedQuery(name = Organisation.GET_ALL_QUERY_NAME, query = "SELECT i FROM Organisation i")})
public class Organisation implements Serializable {
	
	public static final String GET_ALL_QUERY_NAME = "Organisation.getAll";
	
	private int organisationId;
	private String typeName;
	
	private Set<Customer> customers;
	
	public Organisation() {
		
	}

	public Organisation(int organisationId, String typeName, Set<Customer> customers) {
		super();
		this.organisationId = organisationId;
		this.typeName = typeName;
		this.customers = new HashSet<>();
	}

	@Id
    @GeneratedValue
	public int getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(int organisationId) {
		this.organisationId = organisationId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@OneToMany(mappedBy = "organisation")
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
		result = prime * result + organisationId;
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
		Organisation other = (Organisation) obj;
		if (organisationId != other.organisationId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Organisation [organisationId=" + organisationId + ", typeName=" + typeName + ", customers=" + customers + "]";
	}


	

	
}