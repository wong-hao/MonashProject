package fit5042.assignm.controllers;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import fit5042.assignm.mbeans.CustomerManagedBean;

import javax.faces.bean.ManagedProperty;

@RequestScoped
@Named("addUser")
public class AddUser {
	
	 @ManagedProperty(value = "#{customerManagedBean}")
	 CustomerManagedBean customerManagedBean;

	    private boolean showForm = true;
	    
	    CustomerApplication app;
	    
	    public boolean isShowForm() {
	        return showForm;
	    }
	    
	    public AddUser() {
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
	    
	    public void addUser(User localUser) {
	        //this is the local property, not the entity
	        try {
	            //add this property to db via EJB
	        	customerManagedBean.addUser(localUser);

	            //refresh the list in PropertyApplication bean
	        	app.searchAllUsers();
	            
	        } catch (Exception ex) {

	        }
	        showForm = true;
	    }

}
