package fit5042.assignm.controllers;

import fit5042.assignm.repository.entities.Address;
import fit5042.assignm.repository.entities.CustomerContact;
import fit5042.assignm.repository.entities.Organisation;
import fit5042.assignm.repository.entities.Users;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.Column;

import java.sql.Date;

/**
 *
 * 
 */
@RequestScoped
@Named(value = "customer")
public class Customer implements Serializable  {
	private int customerId;
	private Address address;
	private String companyName;
    private int officephone;
    private String registerDate;
    private String aBN;
    private String email;
    private String CEO;
    
    private Set<CustomerContact> customerContacts;
    
    private Organisation organisation;
    private Users user;

    private String organisationType;
    private String organisationDesc;
    
    private String streetNumber;
    private String streetAddress;
    private String suburb;
    private String postcode;
    private String state;

    private Set<fit5042.assignm.repository.entities.Customer> customers;

    public Customer() {
        
    }

    //non-defaut constructor
    public Customer(int customerId, Address address, String companyName, int officephone, String registerDate, String aBN,
			String email, String CEO, Users user, Organisation organisation,Set<CustomerContact> customerContacts) {
		this.customerId = customerId;
		this.address = address;		
		this.companyName = companyName;
		this.officephone = officephone;
		this.registerDate = registerDate;
		this.aBN = aBN;
		this.email = email;
		this.CEO = CEO;
		this.user = user;
		this.organisation = organisation;
		this.customerContacts = customerContacts;
	
	}
    

	

	public int getCustomerId() {
		return customerId;
	}
	
	

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public String getCompanyName() {
		return companyName;
	}
	
	public void setCompanyName(String companyName) {
		this.companyName= companyName;
	}

	public int getOfficephone() {
		return officephone;
	}

	public void setOfficephone(int officephone) {
		this.officephone = officephone;
	}

	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

	public String getaBN() {
		return aBN;
	}

	public void setaBN(String aBN) {
		this.aBN = aBN;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCEO() {
		return CEO;
	}

	public void setCEO(String CEO) {
		this.CEO = CEO;
	}

	public Organisation getOrganisation() {
		return organisation;
	}

	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}

	public String getOrganisationType() {
		return organisationType;
	}

	public void setOrganisationType(String organisationType) {
		this.organisationType = organisationType;
	}

	public String getOrganisationDesc() {
		return organisationDesc;
	}

	public void setOrganisationDesc(String organisationDesc) {
		this.organisationDesc = organisationDesc;
	}

	public Set<fit5042.assignm.repository.entities.Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(Set<fit5042.assignm.repository.entities.Customer> customers) {
		this.customers = customers;
	}

	public Set<CustomerContact> getCustomerContacts() {
		return customerContacts;
	}

	public void setCustomerContacts(Set<CustomerContact> customerContacts) {
		this.customerContacts = customerContacts;
	}

	
	public Users getUser() {
		return user;
	}


	public void setUser(Users user) {
		this.user = user;
	}
	
    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
	
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", address=" + address + ", companyName=" + companyName
				+ ", officephone=" + officephone + ", registerDate=" + registerDate + ", aBN=" + aBN + ", email="
				+ email + ", CEO=" + CEO + ", user" + user + ", customerContacts=" + customerContacts + ", organisation=" + organisation + "]";
	}

   

}
