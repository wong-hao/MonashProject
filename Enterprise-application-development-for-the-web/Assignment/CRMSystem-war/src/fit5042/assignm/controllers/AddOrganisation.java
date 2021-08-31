package fit5042.assignm.controllers;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import fit5042.assignm.mbeans.CustomerManagedBean;

import javax.faces.bean.ManagedProperty;

@RequestScoped
@Named("addOrganisation")
public class AddOrganisation {

	
	
	 @ManagedProperty(value = "#{customerManagedBean}")
	 CustomerManagedBean customerManagedBean;

	    private boolean showForm = true;
	    
	    CustomerApplication app;
	    
	    public boolean isShowForm() {
	        return showForm;
	    }
	    
	    
	    public AddOrganisation() {
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
	    
	    public void addOrganisation(Organisation localOrganisation) {
	        //this is the local property, not the entity
	        try {
	            //add this property to db via EJB
	        	String typename = localOrganisation.getTypeName() ;
	        	
	        	if(typename.equals("Hospital") || 
	        		typename.equals("Medical Clinic") || 
	        		typename.equals("Specialist Office") ||
	        		typename.equals("Nursing Home") ||
	        		typename.equals("Dental Office") ||
	        		typename.equals("Diagnostics & Laboratory") ||
	        		typename.equals("Orthodontics Office") ||
	        		typename.equals("Natural Therapy") ) 
	        	{
	              	customerManagedBean.addOrganisation(localOrganisation);

		            //refresh the list in PropertyApplication bean
		        	app.searchAllOrganisation();
		            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Organisation has been added succesfully"));
	        	}
	        	
	        	else {
	        		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Organisation Type is illegal"));
	        	}
	  
	        } catch (Exception ex) {

	        }
	        showForm = true;
	    }
}
