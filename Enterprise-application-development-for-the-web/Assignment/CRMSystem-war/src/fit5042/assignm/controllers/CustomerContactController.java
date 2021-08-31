package fit5042.assignm.controllers;

import java.io.Serializable;

import javax.el.ELContext;
import javax.inject.Named;

import fit5042.assignm.controllers.ContactApplication;
import fit5042.assignm.repository.entities.CustomerContact;

import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;


/**
*
* 
*/
@Named(value = "customerContactController")
@RequestScoped
public class CustomerContactController{
	private int customerContactIndex; //this customerIndex is the index

    public int getCustomerContactIndex() {
        return customerContactIndex;
    }

    public void setCustomerContactIndex(int customerContactIndex) {
        this.customerContactIndex = customerContactIndex;
    }
    private fit5042.assignm.repository.entities.CustomerContact customerContact;

    public CustomerContactController() {
        // Assign customer identifier via GET param 
        //this customerIndex is the index, don't confuse with the customer Id
    	customerContactIndex = Integer.valueOf(FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("customerContactIndex"));
        // Assign customer based on the id provided 
        customerContact = getCustomerContact();
    }

    public fit5042.assignm.repository.entities.CustomerContact getCustomerContact() {
        if (customerContact == null) {
            // Get application context bean CustomerApplication 
            ELContext context
                    = FacesContext.getCurrentInstance().getELContext();
            ContactApplication app
                    = (ContactApplication) FacesContext.getCurrentInstance()
                            .getApplication()
                            .getELResolver()
                            .getValue(context, null, "contactApplication");
            // -1 to customerId since we +1 in JSF (to always have positive customer index!) 
            return app.getCustomerContacts().get(--customerContactIndex); //this customer index is the index, don't confuse with the Customer Id
        }
       
        return customerContact;
    }


}
