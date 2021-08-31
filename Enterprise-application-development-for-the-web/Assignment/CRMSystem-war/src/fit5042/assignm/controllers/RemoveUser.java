package fit5042.assignm.controllers;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import fit5042.assignm.mbeans.CustomerManagedBean;
import javax.faces.bean.ManagedProperty;


@RequestScoped
@Named("removeUser")
public class RemoveUser {
	
	 @ManagedProperty(value = "#{customerManagedBean}")
	    CustomerManagedBean customerManagedBean;

	    private boolean showForm = true;
	    
	    

	    CustomerApplication app;
	    
	    public boolean isShowForm() {
	        return showForm;
	    }


	    public RemoveUser() {
	        ELContext context
	                = FacesContext.getCurrentInstance().getELContext();

	        app = (CustomerApplication) FacesContext.getCurrentInstance()
	                .getApplication()
	                .getELResolver()
	                .getValue(context, null, "customerApplication");

	        

	        //instantiate propertyManagedBean
	        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
	        customerManagedBean = (CustomerManagedBean) FacesContext.getCurrentInstance().getApplication()
	                .getELResolver().getValue(elContext, null, "customerManagedBean");
	    }
	    
	    public void removeUser(int userId) {
	        try {
	            //remove this property from db via EJB
	        	
	        	customerManagedBean.removeUser(userId);
	            //refresh the list in PropertyApplication bean
	            app.searchAllUsers();

	            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Property has been deleted succesfully"));
	        } catch (Exception ex) {

	        }
	        showForm = true;

	    }
}
