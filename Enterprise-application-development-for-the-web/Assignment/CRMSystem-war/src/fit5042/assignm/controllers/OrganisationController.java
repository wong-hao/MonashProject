package fit5042.assignm.controllers;

import java.util.ArrayList;
import javax.el.ELContext;

import fit5042.assignm.mbeans.CustomerManagedBean;
import fit5042.assignm.repository.entities.CustomerContact;
import fit5042.assignm.repository.entities.Customer;

import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;


@Named(value = "organisationController")
@RequestScoped
public class OrganisationController {

	 @ManagedProperty(value = "#{customerManagedBean}")
	    CustomerManagedBean customerManagedBean;
	 
	 	private boolean showForm = true;
	 	
	 	private int organisationId;//index
	 	
	 	CustomerApplication app;



		public CustomerApplication getApp() {
			return app;
		}

		public void setApp(CustomerApplication app) {
			this.app = app;
		}
	 	
		private fit5042.assignm.repository.entities.Organisation organisation;
		
		public OrganisationController() {
	        // Assign property identifier via GET param 
	        //this propertyID is the index, don't confuse with the Property Id
	    	 ELContext context
	         = FacesContext.getCurrentInstance().getELContext();
	 app = (CustomerApplication) FacesContext.getCurrentInstance()
	                 .getApplication()
	                 .getELResolver()
	                 .getValue(context, null, "customerApplication");
	    	
	 organisationId = Integer.valueOf(FacesContext.getCurrentInstance()
	                .getExternalContext()
	                .getRequestParameterMap()
	                .get("organisationID"));
	        // Assign property based on the id provided 
	 organisation = getOrganisation();
	     
	       
	    }
		
		 public fit5042.assignm.repository.entities.Organisation getOrganisation() {
		        if (organisation == null) {
		            // Get application context bean PropertyApplication 
		           
		            // -1 to propertyId since we +1 in JSF (to always have positive property id!) 
		            return app.getOrganisation().get(--organisationId); //this propertyId is the index, don't confuse with the Property Id
		        }
		        return organisation;
		        
		        
		    }

		public int getOrganisationId() {
			return organisationId;
		}

		public void setOrganisationId(int organisationId) {
			this.organisationId = organisationId;
		}
	 
}

