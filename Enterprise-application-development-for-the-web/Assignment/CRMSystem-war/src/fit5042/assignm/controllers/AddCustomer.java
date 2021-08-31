package fit5042.assignm.controllers;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import fit5042.assignm.mbeans.CustomerManagedBean;


import javax.faces.bean.ManagedProperty;

/**
*
* 
*/
@RequestScoped
@Named("addCustomer")
public class AddCustomer {
	@ManagedProperty(value="#{customerManagedBean}") 
	CustomerManagedBean customerManagedBean;
    
    private boolean showForm = true;

    private Customer customer;
    
    CustomerApplication app;
    
    public void setCustomer(Customer customer){
        this.customer = customer;
    }
    
    public Customer getCustomer(){
        return customer;
    }
    
    public boolean isShowForm() {
        return showForm;
    }

    public AddCustomer() 
    {
        ELContext context
                = FacesContext.getCurrentInstance().getELContext();

        app  = (CustomerApplication) FacesContext.getCurrentInstance()
                        .getApplication()
                        .getELResolver()
                        .getValue(context, null, "customerApplication");
        
        //instantiate propertyManagedBean
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        customerManagedBean = (CustomerManagedBean) FacesContext.getCurrentInstance().getApplication()
        .getELResolver().getValue(elContext, null, "customerManagedBean");
    }

    public void addCustomer(Customer localCustomer) {
        //this is the local property, not the entity
       try
       {
            //add this property to db via EJB
    	   customerManagedBean.addCustomer(localCustomer);

            //refresh the list in PropertyApplication bean
            app.searchAll();
            //updatePropertyListInPropertyApplicationBean();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Customer has been added succesfully"));
            //refresh the property list in propertyApplication bean
       }
       catch (Exception ex)
       {
           
       }
        showForm = true;
    }

}
