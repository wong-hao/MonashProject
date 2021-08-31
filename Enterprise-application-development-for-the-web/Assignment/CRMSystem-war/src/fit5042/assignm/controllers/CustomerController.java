package fit5042.assignm.controllers;

import java.io.Serializable;
import java.util.Set;

import javax.el.ELContext;
import javax.inject.Named;

import fit5042.assignm.controllers.CustomerApplication;
import fit5042.assignm.repository.entities.Organisation;

import fit5042.assignm.repository.entities.CustomerContact;
import fit5042.assignm.repository.entities.Customer;

import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;


/**
*
* 
*/
@RequestScoped
@Named(value = "customerController")
public class CustomerController{
	private int customerIndex; //this customerIndex is the index
	
	

    public int getCustomerIndex() {
        return customerIndex;
    }

    public void setCustomerIndex(int customerIndex) {
        this.customerIndex = customerIndex;
    }
    private Customer customer;
    
   
    
    

    public CustomerController() {
        // Assign customer identifier via GET param 
        //this customerIndex is the index, don't confuse with the customer Id
    	customerIndex = Integer.valueOf(FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("customerIndex"));
        // Assign customer based on the id provided 
    	customer = getCustomer();
    	
        
    }

    public Customer getCustomer() {
        if (customer == null) {
            // Get application context bean CustomerApplication 
        	
            ELContext context
                    = FacesContext.getCurrentInstance().getELContext();
            CustomerApplication app
                    = (CustomerApplication) FacesContext.getCurrentInstance()
                            .getApplication()
                            .getELResolver()
                            .getValue(context, null, "customerApplication");
            // -1 to customerId since we +1 in JSF (to always have positive customer index!) 
            return app.getCustomers().get(--customerIndex); //this customer index is the index, don't confuse with the Customer Id
        }
        return customer;
    }
    
    public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	
    
    
   

}
