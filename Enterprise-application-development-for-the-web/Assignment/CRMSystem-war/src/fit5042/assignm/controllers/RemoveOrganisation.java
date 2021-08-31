package fit5042.assignm.controllers;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import fit5042.assignm.mbeans.CustomerManagedBean;
import javax.faces.bean.ManagedProperty;


@RequestScoped
@Named("removeOrganisation")
public class RemoveOrganisation {
	
	 @ManagedProperty(value = "#{customerManagedBean}")
	    CustomerManagedBean customerManagedBean;

	    private boolean showForm = true;
	    
	    

	    CustomerApplication app;
	    
	    public boolean isShowForm() {
	        return showForm;
	    }


	    public RemoveOrganisation() {
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
	    
	    public void removeOrganisation(int oganisationId) {
	        try {
	            //remove this property from db via EJB
	        	
	        	customerManagedBean.removeOrganisation(oganisationId);
	            //refresh the list in PropertyApplication bean
	            app.searchAllOrganisation();

	            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Organisation has been deleted succesfully"));
	        } catch (Exception ex) {

	        }
	        showForm = true;

	    }
}
