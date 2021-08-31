package fit5042.assignm.controllers.user;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import fit5042.assignm.controllers.Customer;
import fit5042.assignm.mbeans.CustomerManagedBean;



import javax.faces.bean.ManagedProperty;

/**
*
* 
*/
@RequestScoped
@Named("userremoveCustomer")
public class UserRemoveCustomer {
	    @ManagedProperty(value = "#{customerManagedBean}")
	    CustomerManagedBean customerManagedBean;

	    private boolean showForm = true;

	    //private final ArrayList<fit5042.assigmn.repository.entities.Customer> customers;
	    private Customer customer;

		UserController app2;

	    public void setCustomer(Customer customer) {
	        this.customer = customer;
	    }

	    public Customer getCustomer() {
	        return customer;
	    }

	    public boolean isShowForm() {
	        return showForm;
	    }

	    public UserRemoveCustomer() {
	        ELContext context
	                = FacesContext.getCurrentInstance().getELContext();

	        app2 = (UserController) FacesContext.getCurrentInstance()
	                .getApplication()
	                .getELResolver()
	                .getValue(context, null, "userController");

	        app2.updateCustomerList();

	        //instantiate customerManagedBean
	        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
	        customerManagedBean = (CustomerManagedBean) FacesContext.getCurrentInstance().getApplication()
	                .getELResolver().getValue(elContext, null, "customerManagedBean");
	    }

	    /**
	     * @param customer id
	     */
	    public void removeCustomer(int customerId) {
	        try {
	            //remove this customer from db via EJB
	        	customerManagedBean.removeCustomer(customerId);

	            //refresh the list in CustomerApplication bean
	        	app2.updateCustomerList(app2.getUser());

	            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Customer has been deleted succesfully"));
	        } catch (Exception ex) {

	        }
	        showForm = true;

	    }

}