package fit5042.assignm.controllers;

import java.util.ArrayList;
import javax.el.ELContext;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import fit5042.assignm.mbeans.CustomerManagedBean;
import fit5042.assignm.repository.entities.CustomerContact;
import fit5042.assignm.repository.entities.Customer;

@Named(value = "adminUserController")
@ApplicationScoped
public class UserController {

	@ManagedProperty(value = "#{customerManagedBean}")
    CustomerManagedBean customerManagedBean;
	
	//index
	private int userId;
	private boolean showForm = true;
	private String password;
	
	 CustomerApplication app;
	
	private fit5042.assignm.repository.entities.Users user;


	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public UserController() {
	
	
   	 ELContext context
     = FacesContext.getCurrentInstance().getELContext();
   	 
   	 app = (CustomerApplication) FacesContext.getCurrentInstance()
             .getApplication()
             .getELResolver()
             .getValue(context, null, "customerApplication");
        
   	userId= Integer.valueOf(FacesContext.getCurrentInstance()
            .getExternalContext()
            .getRequestParameterMap()
            .get("userID"));
   	
   	user = getUser();

	}
	
	 public fit5042.assignm.repository.entities.Users getUser() {
	        if (user == null) {
	            // Get application context bean PropertyApplication 
	           
	            // -1 to propertyId since we +1 in JSF (to always have positive property id!) 
	            return app.getUsers().get(--userId); //this propertyId is the index, don't confuse with the Property Id
	        }
	        return user;
	        
	        
	    }
	

}
