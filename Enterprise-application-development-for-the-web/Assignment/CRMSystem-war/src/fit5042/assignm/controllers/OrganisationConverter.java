package fit5042.assignm.controllers;

import fit5042.assignm.repository.entities.Organisation;
import fit5042.assignm.repository.entities.Users;

import java.util.ArrayList;
import java.util.List;
import javax.el.ELContext;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import fit5042.assignm.mbeans.CustomerManagedBean;

@FacesConverter(forClass = fit5042.assignm.repository.entities.Organisation.class, value = "organisation")
public class OrganisationConverter implements Converter {
	
	
	@ManagedProperty(value = "#{customerManagedBean}")
    CustomerManagedBean customerManagedBean;
	    int number;
	
	    public List<Organisation> organisationDB;
	    
	    public OrganisationConverter() {
	    	
	        try {
	            //instantiate propertyManagedBean
	            ELContext elContext = FacesContext.getCurrentInstance().getELContext();
	            customerManagedBean = (CustomerManagedBean) FacesContext.getCurrentInstance().getApplication()
	                    .getELResolver().getValue(elContext, null, "customerManagedBean");

	            organisationDB = customerManagedBean.getAllOrganisation();
	        } catch (Exception ex) {

	        }
	    }
	
	
		@Override
		public Organisation getAsObject(FacesContext context, UIComponent component, String submittedValue) {
			 if (submittedValue.trim().equals("")) {
		            return null;
		        } else {
		            try {
		               number = Integer.parseInt(submittedValue);

		                for (Organisation c : organisationDB) {
		                    if (c.getOrganisationId() == number) {
		                        return c;
		                    }
		                }

		            } catch (NumberFormatException exception) {
		                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid user"));
		            }
		        }

		        return null;
		}

		@Override
		public String getAsString(FacesContext context, UIComponent component, Object value) {
			 if (value == null || value.equals("")) {
		            return "";
		        } else {
		            return String.valueOf(((Organisation) value).getOrganisationId());
		        }
		}

}
