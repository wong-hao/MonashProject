package fit5042.assignm.repository.entities;



import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * 
 */
@Entity
@Table(name = "CUSTOMER_CONTACT")
@NamedQueries({ @NamedQuery(name = CustomerContact.GET_ALL_CONTACT_QUERY_NAME, query = "SELECT a FROM CustomerContact a order by a.customerContactId desc") })
public class CustomerContact implements Serializable {

	public static final String GET_ALL_CONTACT_QUERY_NAME = "CustomerContact.getAll";

	private int customerContactId;
	private String contactName;
	private int contactPhone;
	private String contactEmail;
	private String position;
	private String gender;


	private Customer customer;
	
	public CustomerContact() {
    }


	public CustomerContact(int customerContactId, String contactName, int contactPhone, String contactEmail, String position,
			String gender, Customer customer, Set<String> tags) {
		this.customerContactId = customerContactId;
		this.contactName = contactName;
		this.contactPhone = contactPhone;
		this.contactEmail = contactEmail;
		this.position = position;
		this.gender = gender;
		this.customer = customer;
	}

    
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "customerContact_id")
    public int getCustomerContactId() {
		return customerContactId;
	}


	public void setCustomerContactId(int customerContactId) {
		this.customerContactId = customerContactId;
	}

	@NotNull(message = "Contact email should not be null")
	public String getContactName() {
		return contactName;
	}

    
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

   
	@Column(name = "contact_phone_number")
	public int getContactPhone() {
		return contactPhone;
	}


	public void setContactPhone(int contactPhone) {
		this.contactPhone = contactPhone;
	}

	@NotNull(message = "Contact email should not be empty") 
	public String getContactEmail() {
		return contactEmail;
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

    
	@ManyToOne
	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	@Override
	public String toString() {
		return "CustomerContact [customerContactId=" + customerContactId + ", contactName=" + contactName
				+ ", contactPhone=" + contactPhone + ", contactEmail=" + contactEmail + ", position=" + position + ", gender="
				+ gender + ", customer=" + customer + "]";
	}
	

}
		

   
	
	


	


	