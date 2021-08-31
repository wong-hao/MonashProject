package fit5042.assignm.repository.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;;



/**
 *
 * 
 */
@Entity
@Table(name = "CUSTOMER")
@NamedQueries({@NamedQuery(name = Customer.GET_ALL_CUSTOMERS_QUERY_NAME, query = "SELECT c FROM Customer c order by c.customerId desc")})
public class Customer implements Serializable {
    
    public static final String GET_ALL_CUSTOMERS_QUERY_NAME = "Customer.getAll";
    
    private Set<CustomerContact> contacts;

    
	private int customerId;
	private String companyName;
    private int officephone;
    private Address address;
    private String registerDate;
    private String aBN;
    private String email;
    private String CEO;
    private Organisation organisation;
    
    
    private List<CustomerContact> customerContacts;
    private Users user;

    public Customer() {
    }

    public Customer(int customerId, String companyName, int officephone, Address address, String registerDate,
			String aBN, String email, String CEO,Users user, Organisation organisation,List<CustomerContact> customerContacts) {
		this.customerId = customerId;
		this.companyName = companyName;
		this.officephone = officephone;
		this.address = address;
		this.registerDate = registerDate;
		this.aBN = aBN;
		this.email = email;
		this.CEO =CEO;
		this.user = user;
		this.organisation = organisation;
		this.customerContacts = customerContacts;
	}

    
  


	@Id
    @GeneratedValue
    @Column(name = "customer_id")
	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	@NotNull(message = "Company name should not be empty")
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Column(name = "office_phone")
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
	
	
	@Column(name = "ABN")
	public String getaBN() {
		return aBN;
	}

	public void setaBN(String aBN) {
		this.aBN = aBN;
	}

	@Column(name = "office_email")
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
 
	@ManyToOne
	public Organisation getOrganisation() {
		return organisation;
	}


	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}
	
	//enforce the relationship between a property and its contact person using annotation(s). Each property has one and only one contact person. Each contact person might be responsible for zero to many properties
    @OneToMany(mappedBy = "customer",cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    public Set<CustomerContact> getContacts() {
		return contacts;
	}
    
	public void setContacts(Set<CustomerContact> contacts) {
		this.contacts = contacts;
	}
    
	@OneToMany(mappedBy = "customer",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public List<CustomerContact> getCustomerContacts() {
		return customerContacts;
	}

	public void setCustomerContacts(List<CustomerContact> customerContacts) {
		this.customerContacts = customerContacts;
	}
	
	
	@ManyToOne
	public Users getUser() {
		return user;
	}


	public void setUser(Users user) {
		this.user = user;
	}
	
	@Embedded
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", companyName=" + companyName + ", officephone=" + officephone
				+ ", address=" + address + ", regist_date=" + registerDate + ", aBN=" + aBN + ", email=" + email
				+ ", CEO=" + CEO + ", user" + user + ", organisation=" + organisation + "]";
	}
	
	
    



}   