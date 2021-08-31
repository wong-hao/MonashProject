package fit5042.assignm.controllers;
import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import fit5042.assignm.mbeans.CustomerContactManagedBean;
import fit5042.assignm.repository.entities.CustomerContact;
import javax.faces.bean.ManagedProperty;

/**
*
* 
*/
@RequestScoped
@Named("removeCustomerContact")
public class RemoveCustomerContact {
	@ManagedProperty(value = "#{CustomerContactManagedBean}")
	CustomerContactManagedBean customerContactManagedBean;

	private boolean showForm = true;
	private CustomerContact customerContact;
	ContactApplication app;

	public void setCustomerContact(CustomerContact customerContact) {
		this.customerContact = customerContact;
	}

	public CustomerContact getCustomerContact() {
		return customerContact;
	}

	public boolean isShowForm() {
		return showForm;
	}

	public RemoveCustomerContact() {
	        ELContext context
	                = FacesContext.getCurrentInstance().getELContext();

	        app = (ContactApplication) FacesContext.getCurrentInstance()
	                        .getApplication()
	                        .getELResolver()
	                        .getValue(context, null, "contactApplication");
	        
	        app.updateCustomerContactList();
	        
	        //instantiate propertyManagedBean
	        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
	        customerContactManagedBean = (CustomerContactManagedBean) FacesContext.getCurrentInstance().getApplication()
	        .getELResolver().getValue(elContext, null, "customerContactManagedBean");
	    }

	/**
	 * @param property Id
	 */
	public void removeCustomerContact(int customerContactId) {
		try {
			// remove this property from db via EJB
			customerContactManagedBean.removeCustomerContact(customerContactId);

			// refresh the list in PropertyApplication bean
			app.searchAll();

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Customer Contact has been deleted succesfully"));
		} catch (Exception ex) {

		}
		showForm = true;

	}

}
