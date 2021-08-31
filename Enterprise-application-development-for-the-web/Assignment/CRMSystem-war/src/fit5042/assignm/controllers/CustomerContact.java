package fit5042.assignm.controllers;

import fit5042.assignm.repository.entities.Address;
import fit5042.assignm.repository.entities.Customer;
import fit5042.assignm.repository.entities.Organisation;
import fit5042.assignm.repository.entities.Users;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.sql.Date;

/**
 *
 * 
 */
@RequestScoped
@Named(value = "customerContact")
public class CustomerContact implements Serializable{
	private int customerContactId;	
	private String contactName;
	private int contactPhone;
	private String contactEmail;
	private String position;
	private String gender;


	private Customer customer;
	private int customerId;
	private Address address;
	private String companyName;
    private int officephone;
    private String registerDate;
    private String aBN;
    private String email;
    private String CEO;
    private Organisation organisation;
    private Users user;
    
    private String organisationType;
    private String organisationDesc;
	
	private Set<fit5042.assignm.repository.entities.CustomerContact> customerContacts;
	
	public CustomerContact() {
		
	}
	
	public CustomerContact(int customerContactId, String contactName, int contactPhone, String contactEmail, String position,
			String gender, Customer customer){
		
		this.customerContactId = customerContactId;
		this.contactName = contactName;
		this.contactPhone = contactPhone;
		this.contactEmail = contactEmail;
		this.position = position;
		this.gender = gender;
		this.customer = customer;
		
		
	}

	
	public int getCustomerContactId() {
		return customerContactId;
	}

	public void setCustomerContactId(int customerContactId) {
		this.customerContactId = customerContactId;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public int getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(int contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getContactEmail() {
		return email;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public Set<fit5042.assignm.repository.entities.CustomerContact> getCustomerContacts() {
		return customerContacts;
	}

	public void setCustomerContacts(Set<fit5042.assignm.repository.entities.CustomerContact> customerContacts) {
		this.customerContacts = customerContacts;
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
	
	public Users getUser() {
		return user;
	}


	public void setUser(Users user) {
		this.user = user;
	}
	
	@Override
	public String toString() {
		return "CustomerContact [customerContactId=" + customerContactId + ", contactName=" + contactName
				+ ", contactPhone=" + contactPhone + ", contactEmail=" + contactEmail + ", position=" + position + ", gender="
				+ gender + ", customer=" + customer + "]";
	}



}
