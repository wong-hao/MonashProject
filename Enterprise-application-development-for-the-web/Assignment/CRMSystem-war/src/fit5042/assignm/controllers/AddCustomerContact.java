package fit5042.assignm.controllers;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import fit5042.assignm.mbeans.CustomerContactManagedBean;
import fit5042.assignm.mbeans.CustomerManagedBean;
import fit5042.assignm.repository.entities.Customer;
import fit5042.assignm.repository.entities.CustomerContact;

import javax.faces.bean.ManagedProperty;


/**
*
* 
*/
@RequestScoped
@Named("AddCustomerContact")
public class AddCustomerContact {
	@ManagedProperty(value="#{customerContactManagedBean}") 
	CustomerContactManagedBean customerContactManagedBean;
    
    private boolean showForm = true;

    private CustomerContact customerContact;
    
    
    private Customer customer;
    
    private int customerId;
    
    ContactApplication app;
    
    CustomerApplication custApp;
    
    public void setCustomerContact(CustomerContact customerContact){
        this.customerContact = customerContact;
    }
    
    public CustomerContact getCustomerContact(){
        return customerContact;
    }
    
    public boolean isShowForm() {
        return showForm;
    }

    public AddCustomerContact() 
    {
        ELContext context
                = FacesContext.getCurrentInstance().getELContext();

        app  = (ContactApplication) FacesContext.getCurrentInstance()
                        .getApplication()
                        .getELResolver()
                        .getValue(context, null, "contactApplication");
        
        custApp  = (CustomerApplication) FacesContext.getCurrentInstance()
                .getApplication()
                .getELResolver()
                .getValue(context, null, "customerApplication");
        
        //instantiate customerContactManagedBean
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        customerContactManagedBean = (CustomerContactManagedBean) FacesContext.getCurrentInstance().getApplication()
        .getELResolver().getValue(elContext, null, "customerContactManagedBean");
         
        
        //instantiate customerContact
        customerContact = new CustomerContact();
        
        //instantiate customerId and customer
        customerId = getCustomerId();
        //customerId = Integer.valueOf(
		//		FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("customerId"));
        
       
    }

    public void addCustomerContactInfor() {
        //this is the local property, not the entity
       try{
    	   
    	   setCustomer(custApp.getCustomerById(customerId));
    	   customerContact.setCustomer(getCustomer());
            //add this property to db via EJB
    	   customerContactManagedBean.addCustomerContact(customerContact);

            //refresh the list in PropertyApplication bean
            app.searchAll();
            //updatePropertyListInPropertyApplicationBean();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Customer Contact has been added succesfully"));
            //refresh the property list in propertyApplication bean
       }
       catch (Exception ex)
       {
           
       }
        showForm = true;
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

	
}
