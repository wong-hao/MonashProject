package fit5042.assignm.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;

import fit5042.assignm.mbeans.CustomerContactManagedBean;

import javax.inject.Named;

import fit5042.assignm.repository.entities.CustomerContact;

import javax.el.ELContext;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;


@Named(value = "contactApplication")
@ApplicationScoped
public class ContactApplication {
	@ManagedProperty(value = "#{customerContactManagedBean}")
    CustomerContactManagedBean customerContactManagedBean;
    private ArrayList<CustomerContact> customerContacts;
    
   
    
   
    private boolean showForm = true;
    
    public boolean isShowForm() {
        return showForm;
    }
    
 // Add some customer and customerContact data from db to app 
    public ContactApplication() throws Exception {
       
        customerContacts = new ArrayList<>();
        
        
        
        //instantiate customerId
       
        // Assign customer based on the id provided 
        
      //instantiate customerContactManagedBean
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        customerContactManagedBean = (CustomerContactManagedBean) FacesContext.getCurrentInstance().getApplication()
                .getELResolver().getValue(elContext, null, "customerContactManagedBean");

        //get customerContacts from db 
        updateCustomerContactList();
      
        
    }
    
    public ArrayList<CustomerContact> getCustomerContacts() {
        return customerContacts;
    }
    
    private void setCustomerContacts(ArrayList<CustomerContact> newCustomerContacts) {
        this.customerContacts = newCustomerContacts;
    }
    
    public void updateCustomerContactList() {
        if (customerContacts != null && customerContacts.size() > 0)
        {
            
        }
        else
        {
        	customerContacts.clear();
        	
        	List<CustomerContact> contacts = customerContactManagedBean.getAllCustomerContacts();

            for (CustomerContact customerContact : contacts)
            {
            	customerContacts.add(customerContact);
            }

            setCustomerContacts(customerContacts);
        }
    }
    
    public void searchCustomerContactById(int customerContactId)
    {
    	customerContacts.clear();
        
    	customerContacts.add(customerContactManagedBean.searchCustomerContactById(customerContactId));
    }
    
    public void searchCustomerContactByCustomerId(int customerId) {
    	customerContacts.clear();
        List<CustomerContact> customerContactsByCustomer = customerContactManagedBean.searchCustomerContactByCustomerId(customerId);
        for (CustomerContact customerContacteach: customerContactsByCustomer) {
        	customerContacts.add(customerContacteach);
        }
       

    }
    
    public void searchAll()
    {
        customerContacts.clear();
        
        for (fit5042.assignm.repository.entities.CustomerContact customerContact : customerContactManagedBean.getAllCustomerContacts())
        {
        	customerContacts.add(customerContact);
        }
        
        setCustomerContacts(customerContacts);
    }

	
	

}
