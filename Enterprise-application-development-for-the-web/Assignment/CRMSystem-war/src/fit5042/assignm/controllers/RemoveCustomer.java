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
@Named("removeCustomer")
public class RemoveCustomer {
	    @ManagedProperty(value = "#{customerManagedBean}")
	    CustomerManagedBean customerManagedBean;

	    private boolean showForm = true;

	    //private final ArrayList<fit5042.assigmn.repository.entities.Customer> customers;
	    private Customer customer;

	    CustomerApplication app;

	    public void setCustomer(Customer customer) {
	        this.customer = customer;
	    }

	    public Customer getCustomer() {
	        return customer;
	    }

	    public boolean isShowForm() {
	        return showForm;
	    }

	    public RemoveCustomer() {
	        ELContext context
	                = FacesContext.getCurrentInstance().getELContext();

	        app = (CustomerApplication) FacesContext.getCurrentInstance()
	                .getApplication()
	                .getELResolver()
	                .getValue(context, null, "customerApplication");

	        app.updateCustomerList();

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
	             app.searchAll();

	            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Customer has been deleted succesfully"));
	        } catch (Exception ex) {

	        }
	        showForm = true;

	    }

}
