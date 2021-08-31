package fit5042.assignm.controllers.user;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Set;

import javax.el.ELContext;

import javax.inject.Named;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import fit5042.assignm.*;
import fit5042.assignm.repository.entities.Customer;
import fit5042.assignm.mbeans.CustomerManagedBean;
import fit5042.assignm.repository.entities.CustomerContact;


@Named(value = "userController")
@ApplicationScoped
public class UserController {

	@ManagedProperty(value = "#{customerManagedBean}")
    CustomerManagedBean customerManagedBean;
	
	private String userName;
	private boolean showForm = true;
	private String password;
	private String checkPassword;
	
	
	private fit5042.assignm.repository.entities.Users user;
	private ArrayList<Customer> customerList;
	private ArrayList<CustomerContact> contactList;
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	
	
	public String getCheckPassword() {
		return checkPassword;
	}

	public void setCheckPassword(String checkPassword) {
		this.checkPassword = checkPassword;
	}

	public ArrayList<Customer> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(ArrayList<Customer> customerList) {
		this.customerList = customerList;
	}
	

	public ArrayList<CustomerContact> getContactList() {
		return contactList;
	}

	public void setContactList(ArrayList<CustomerContact> contactList) {
		this.contactList = contactList;
	}

	public fit5042.assignm.repository.entities.Users getUser() {
		return user;
	}

	public void setUser(fit5042.assignm.repository.entities.Users user) {
		this.user = user;
	}

	public UserController() {
		customerList = new ArrayList<>();
		contactList = 	new ArrayList<>();
		user = new fit5042.assignm.repository.entities.Users();
		this.userName = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        customerManagedBean = (CustomerManagedBean) FacesContext.getCurrentInstance().getApplication()
                .getELResolver().getValue(elContext, null, "customerManagedBean");
        
      
        user = getUser(this.userName);
        updateCustomerList(user);
        

	}
	
	public fit5042.assignm.repository.entities.Users getUser(String userName) {
		
		fit5042.assignm.repository.entities.Users tempUser = customerManagedBean.searchUserByName(userName);
			
		return tempUser;
		
		
	}
	
	public void updateCustomerList(fit5042.assignm.repository.entities.Users user) {
       
        	customerList.clear();

            for (fit5042.assignm.repository.entities.Customer customer : customerManagedBean.searchCustomerByUser(user))
            {
            	customerList.add(customer);
            }

            setCustomerList(customerList);
    
	
	}	
	
    public void searchAll()
    {
    	customerList.clear();
        
        for (fit5042.assignm.repository.entities.Customer customer : customerManagedBean.getAllCustomers())
        {
        	customerList.add(customer);
        }
        
        setCustomerList(customerList);
    }
    
    public void searchCustomerById(int customerId)
    {
    	customerList.clear();
        
    	customerList.add(customerManagedBean.searchCustomerById(customerId));
    }
  
    
    public void searchCustomerByCEOAndState(String ceo, String state) {
    	customerList.clear();
    	Set<Customer> filterCustomers = customerManagedBean.searchCustomerByCEOAndState(ceo, state);
    	
    	for(Customer customer:filterCustomers) 
    	{
    		
    		customerList.add(customer);
;	    	}
    }
    
    
    public void updateCustomerList() {
        if (customerList != null && customerList.size() > 0)
        {
            
        }
        else
        {
        	customerList.clear();

            for (fit5042.assignm.repository.entities.Customer customer : customerManagedBean.getAllCustomers())
            {
            	customerList.add(customer);
            }

            setCustomerList(customerList);
        }
    }
	
	
	  public void searchContactsByCustomer(Customer customer) {
	    	contactList.clear();

	        for (fit5042.assignm.repository.entities.CustomerContact customercontact : customerManagedBean.searchContactByCustomer(customer)) {
	        	contactList.add(customercontact);
	        }

	        setContactList(contactList);
	    }
	  
	 
}
