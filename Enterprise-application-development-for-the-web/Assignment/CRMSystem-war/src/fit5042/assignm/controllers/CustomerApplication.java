package fit5042.assignm.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;

import fit5042.assignm.mbeans.CustomerContactManagedBean;
import fit5042.assignm.mbeans.CustomerManagedBean;
import javax.inject.Named;
import fit5042.assignm.repository.entities.Customer;
import fit5042.assignm.repository.entities.CustomerContact;
import fit5042.assignm.repository.entities.Organisation;
import fit5042.assignm.repository.entities.Users;

import javax.el.ELContext;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

/**
 * The class is a demonstration of how the application scope works. You can
 * change this scope.
 *
 * 
 */
@Named(value = "customerApplication")
@ApplicationScoped

public class CustomerApplication {
	 //dependency injection of managed bean here so that we can use its methods
    @ManagedProperty(value = "#{customerManagedBean}")    
    CustomerManagedBean customerManagedBean;
  

    private ArrayList<Customer> customers;
	  private ArrayList<Users> users;
	  private ArrayList<Organisation> organisations;
	  private ArrayList<CustomerContact> contacts;

    
    
    private boolean showForm = true;
    
    public boolean isShowForm() {
        return showForm;
    }
    
 // Add some customer and customerContact data from db to app 
    public CustomerApplication() throws Exception {
        customers = new ArrayList<>();
        contacts = new ArrayList<>();
        users = new ArrayList<>();     
        organisations = new ArrayList<>();


        //instantiate customerManagedBean
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        customerManagedBean = (CustomerManagedBean) FacesContext.getCurrentInstance().getApplication()
                .getELResolver().getValue(elContext, null, "customerManagedBean");

        //get properties from db 
        updateCustomerList();
        updateUserList();
        updateOrganisationList() ;

        
        
    }
    
 
    public ArrayList<Users> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<Users> users) {
		this.users = users;
	}
    
    public ArrayList<Customer> getCustomers() {
        return customers;
    }

   
    
    private void setCustomers(ArrayList<Customer> newCustomers) {
        this.customers = newCustomers;
    }
    
   
	public ArrayList<Organisation> getOrganisation() {
		return organisations;
	}

	public void setOrganisation(ArrayList<Organisation> organisations) {
		this.organisations = organisations;
	}
	
    public ArrayList<CustomerContact> getContacts() {
		return contacts;
	}

	public void setContacts(ArrayList<CustomerContact> contacts) {
		this.contacts = contacts;
	}
	
  //when loading, and after adding or deleting, the property list needs to be refreshed
    //this method is for that purpose
    public void updateCustomerList() {
        if (customers != null && customers.size() > 0)
        {
            
        }
        else
        {
        	customers.clear();

            for (fit5042.assignm.repository.entities.Customer customer : customerManagedBean.getAllCustomers())
            {
            	customers.add(customer);
            }

            setCustomers(customers);
        }
    }
    
	public void setOrganisations(ArrayList<Organisation> organisations) {
		this.organisations = organisations;
	}
    

 	
	public void updateOrganisationList() {
        if (organisations != null && organisations.size() > 0)
        {
            
        }
        else
        {
        	organisations.clear();

        	 for (fit5042.assignm.repository.entities.Organisation organisation : customerManagedBean.getAllOrganisation())
	            {
        		 organisations.add(organisation);
	            }

        	 setOrganisations(organisations);

            
        }
    }
    
    public void searchCustomerById(int customerId)
    {
    	customers.clear();
        
    	customers.add(customerManagedBean.searchCustomerById(customerId));
    }
    
   
    
    public void searchAll()
    {
        customers.clear();
        
        for (fit5042.assignm.repository.entities.Customer customer : customerManagedBean.getAllCustomers())
        {
        	customers.add(customer);
        }
        
        setCustomers(customers);
    }
    
    public Customer getCustomerById(int customerId) {
    	Customer customer = new Customer();
    	for(Customer cust:getCustomers()) {
    		if (cust.getCustomerId() == customerId) {
    			return cust;
    		}
    	}
		return customer;
    }
   
	public void searchAllUsers()
    {
		 users.clear();

            for (fit5042.assignm.repository.entities.Users user : customerManagedBean.getAllUsers())
            {
            	users.add(user);
            }

            setUsers(users);
    }
    
	public void updateUserList() {
        if (users != null && users.size() > 0)
        {
            
        }
        else
        {
            users.clear();

            for (fit5042.assignm.repository.entities.Users user : customerManagedBean.getAllUsers())
            {
            	users.add(user);
            }

            setUsers(users);
        }
    }
	
    public void searchCustomerByCEOAndState(String ceo, String state) {
    	customers.clear();
    	Set<Customer> filterCustomers = customerManagedBean.searchCustomerByCEOAndState(ceo, state);
    	
    	for(Customer customer:filterCustomers) 
    	{
    		
    		customers.add(customer);
;	    	}
    }

	public void searchAllOrganisation()
    {
		organisations.clear();

            for (fit5042.assignm.repository.entities.Organisation organisation : customerManagedBean.getAllOrganisation())
            {
            	organisations.add(organisation);
            }

            setOrganisation(organisations);
    }
	

}
