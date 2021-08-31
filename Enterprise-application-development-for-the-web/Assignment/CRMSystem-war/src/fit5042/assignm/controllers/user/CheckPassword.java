package fit5042.assignm.controllers.user;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import javax.el.ELContext;

import fit5042.assignm.mbeans.CustomerManagedBean;
import fit5042.assignm.repository.entities.CustomerContact;
import fit5042.assignm.repository.entities.Customer;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;

@RequestScoped
@Named("checkPassword")

public class CheckPassword implements Serializable {
	
	 @ManagedProperty(value = "#{customerManagedBean}")
	    CustomerManagedBean customerManagedBean;
	
	 private boolean showForm = true;
	 
	private String checkPsw;
	private fit5042.assignm.repository.entities.Users user;


	public String getCheckPsw() {
		return checkPsw;
	}

	public void setCheckPsw(String checkPsw) {
		this.checkPsw = checkPsw;
	}
	
	public fit5042.assignm.repository.entities.Users getUser() {
		return user;
	}

	public void setUser(fit5042.assignm.repository.entities.Users user) {
		this.user = user;
	}
	
	 public CheckPassword() {
	
		 user = new fit5042.assignm.repository.entities.Users();
		
		 ELContext elContext = FacesContext.getCurrentInstance().getELContext();
	        customerManagedBean = (CustomerManagedBean) FacesContext.getCurrentInstance().getApplication()
	                .getELResolver().getValue(elContext, null, "customerManagedBean");
	        
	        
	        user = getUser(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
	}

	 
		public fit5042.assignm.repository.entities.Users getUser(String userName) {
			
			fit5042.assignm.repository.entities.Users tempUser = customerManagedBean.searchUserByName(userName);
				
			return tempUser;
			
			
		}
	 
	 
	 
	public String checkPasswordCorrect() {
		    String Password = user.getPassword();
		  try {
		  String shaCheckPassword = convertPasswordToSha256(checkPsw);
		  if( Password.equals(shaCheckPassword) ) {
			
			  return "Correct password input";
		  }
		  else {
			 
			  return "InCorrect password input";
		  }
		  
		  
		  }
		  catch(Exception ex) {
			  
		  }
		  return null;
	  }
	
	  public String convertPasswordToSha256(String password) throws NoSuchAlgorithmException {
		   MessageDigest md = MessageDigest.getInstance("SHA-256");
		   md.update(password.getBytes());
		   
		   byte[] digest = md.digest();
		   StringBuffer sb = new StringBuffer();
		   for(byte b : digest) {
			   sb.append(String.format("%02x", b & 0xff));
		   }
		   
		   return sb.toString();
	   }

}
