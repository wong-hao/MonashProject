package fit5042.assignm.controllers;


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
import fit5042.assignm.repository.entities.Users;



@FacesConverter(forClass = fit5042.assignm.repository.entities.Users.class, value = "user")
public class UserConverter implements Converter {
	
	
	@ManagedProperty(value = "#{customerManagedBean}")
    CustomerManagedBean customerManagedBean;
	    int number;
	
	    public List<Users> userDB;
	    
	    public UserConverter() {
	        try {
	            //instantiate propertyManagedBean
	            ELContext elContext = FacesContext.getCurrentInstance().getELContext();
	            customerManagedBean = (CustomerManagedBean) FacesContext.getCurrentInstance().getApplication()
	                    .getELResolver().getValue(elContext, null, "customerManagedBean");

	            userDB = customerManagedBean.getAllUsers();
	        } catch (Exception ex) {

	        }
	    }
	    
	    
	

	@Override
	public Users getAsObject(FacesContext context, UIComponent component, String submittedValue) {
		 if (submittedValue.trim().equals("")) {
	            return null;
	        } else {
	            try {
	               number = Integer.parseInt(submittedValue);

	                for (Users c : userDB) {
	                    if (c.getUserId() == number) {
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
	            return String.valueOf(((Users) value).getUserId());
	        }
	}
	
	
	

}