package fit5042.assignm.controllers.user;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import fit5042.assignm.controllers.Customer;
import fit5042.assignm.mbeans.CustomerManagedBean;
import fit5042.assignm.repository.entities.Users;

import javax.faces.bean.ManagedProperty;

@RequestScoped
@Named("addUserCustomer")
public class AddCustomer {
	 @ManagedProperty(value = "#{customerManagedBean}")
	 CustomerManagedBean customerManagedBean;

	    private boolean showForm = true;

	    private Customer customer;

	    UserController app;

	    public void setCustomer(Customer customer) {
	        this.customer = customer;
	    }

	    public Customer getCustomer() {
	        return customer;
	    }

	    public boolean isShowForm() {
	        return showForm;
	    }

	    public AddCustomer() {
	        ELContext context
	                = FacesContext.getCurrentInstance().getELContext();

	        app = (UserController) FacesContext.getCurrentInstance()
	                .getApplication()
	                .getELResolver()
	                .getValue(context, null, "userController");

	        //instantiate propertyManagedBean
	        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
	        customerManagedBean = (CustomerManagedBean) FacesContext.getCurrentInstance().getApplication()
	                .getELResolver().getValue(elContext, null, "customerManagedBean");
	    }

	    public void addUserCustomer(Customer localCustomer,Users user) {
	        //this is the local property, not the entity
	        try {
	            //add this property to db via EJB
	        	customerManagedBean.addUserCustomer(localCustomer,user);

	            //refresh the list in PropertyApplication bean
	        	app.updateCustomerList(user);
	            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Customer has been added succesfully"));
	        } catch (Exception ex) {

	        }
	        showForm = true;
	    }

}