package fit5042.assignm.controllers;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import fit5042.assignm.repository.entities.Customer;

@RequestScoped
@Named(value = "organisation")
public class Organisation {

	private int organisationId;
	@NotNull
	private String typeName;
	
	private Set<Customer> customers;
	
	public Organisation() {}

	public Organisation(int industryId, String typeName, Set<Customer> customers) {
		super();
		this.organisationId = industryId;
		this.typeName = typeName;
		this.customers = customers;
	}

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

	public Set<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(Set<Customer> customers) {
		this.customers = customers;
	}

	@Override
	public String toString() {
		return "Industry [organisationId=" + organisationId + ", typeName=" + typeName + ", customers=" + customers + "]";
	}
	
	
			
	
}
