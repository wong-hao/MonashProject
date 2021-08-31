package fit5042.assignm.mbeans;

import fit5042.assignm.repository.CustomerRepository;
import fit5042.assignm.repository.entities.Organisation;
import fit5042.assignm.repository.entities.Users;
import fit5042.assignm.repository.entities.CustomerContact;
import fit5042.assignm.repository.entities.Groups;
import fit5042.assignm.repository.entities.Address;
import fit5042.assignm.repository.entities.Customer;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.sun.istack.NotNull;

/**
*
* 
*/
@ManagedBean(name = "customerManagedBean")
@SessionScoped

public class CustomerManagedBean implements Serializable {
	 @EJB
	 CustomerRepository customerRepository;
	 
	 @NotNull 
     String streetNumber;
	 
	 @NotNull
     String streetAddress;
	 
	 @NotNull
     String suburb;
	 
	 @NotNull
     String postcode;
	 
	 @NotNull
     String state;
        
	 /**
	     * Creates a new instance of CustomerManagedBean
	     */
	    public CustomerManagedBean() {
	    }

	    public List<Customer> getAllCustomers() {
	        try {
	            List<Customer> customers = customerRepository.getAllCustomers();
	            return customers;
	        } catch (Exception ex) {
	            Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        return null;
	    }

	    
	    /**
	     * Search a customer by Id
	     */
	    public Customer searchCustomerById(int id) {
	        try {
	            return customerRepository.searchCustomerId(id);
	        } catch (Exception ex) {
	            Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
	        }

	        return null;
	    }
	    public void removeCustomer(int customerId) {
	        try {
	            customerRepository.removeCustomer(customerId);
	        } catch (Exception ex) {
	            Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
	        }
	    }

	    public void editCustomer(Customer customer) {
	        try {

	            customerRepository.editCustomer(customer);

	            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Customer has been updated succesfully"));
	        } catch (Exception ex) {
	            Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
	        }
	    }
	    
	    public void addCustomer(fit5042.assignm.controllers.Customer localCustomer) {
	        //convert this newProperty which is the local property to entity property
	        Customer customer = convertCustomerToEntity(localCustomer);

	        Users user = new Users();
	        user = localCustomer.getUser();
	        if (user.getUserId() == 0) {
	        	user = null;
	        }
	        
	        customer.setUser(user);
	        
	        Organisation organisation = new Organisation();
	        organisation = localCustomer.getOrganisation();
	        
	        if (organisation.getOrganisationId() == 0) {
	        	organisation = null;
	        }
	        
	        customer.setOrganisation(organisation);
	        
	        try {
	            customerRepository.addCustomer(customer);
	        } catch (Exception ex) {
	            Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
	        }
	    }
	    
	    private Customer convertCustomerToEntity(fit5042.assignm.controllers.Customer localCustomer) {
	        Customer customer = new Customer(); //entity
	      
	        
		    streetNumber = localCustomer.getStreetNumber();
	        streetAddress = localCustomer.getStreetAddress();
	        suburb = localCustomer.getSuburb();
	        postcode = localCustomer.getPostcode();
	        state = localCustomer.getState();
	        
		    Address address = new Address(streetNumber,streetAddress,suburb,postcode,state);
		    
	        customer.setCustomerId(localCustomer.getCustomerId());
	        customer.setAddress(address);
	        customer.setCompanyName(localCustomer.getCompanyName());
	        customer.setOfficephone(localCustomer.getOfficephone());
	        customer.setRegisterDate(localCustomer.getRegisterDate());
	        customer.setaBN(localCustomer.getaBN());
	        customer.setEmail(localCustomer.getEmail());
	        customer.setCEO(localCustomer.getCEO());
	        
	        return customer;
	        
	        
	    }
	    
	 	public Users searchUserByName(String userName) {
	        try {
	            Users user = customerRepository.searchUserByName(userName);
	            return user;
	        } catch (Exception ex) {
	            Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        return null;
	    }
	    
		   public Set<Customer> searchCustomerByUser(Users user) {
		        try {
		            return customerRepository.searchCustomerByUser(user);
		        } catch (Exception ex) {
		            Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
		        }

		        return null;
		    }
		   
		   public Set<CustomerContact> searchContactByCustomer(Customer customer) {
		        try {
		            return customerRepository.searchContactByCustomer(customer);
		        } catch (Exception ex) {
		            Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
		        }

		        return null;
		    }
		   
		   public void addUserCustomer(fit5042.assignm.controllers.Customer localCustomer,fit5042.assignm.repository.entities.Users user) {
		        //convert this newProperty which is the local property to entity property
		        Customer customer = convertCustomerToEntity(localCustomer);
		        customer.setUser(user);
		        Organisation organisation = new Organisation();
		        organisation = localCustomer.getOrganisation();
		        
		        if (organisation.getOrganisationId() == 0) {
		        	organisation = null;
		        }
		        
		        customer.setOrganisation(organisation);;
		        
		        try {
		            customerRepository.addCustomer(customer);
		            
		        } catch (Exception ex) {
		            Logger.getLogger(CustomerRepository.class.getName()).log(Level.SEVERE, null, ex);
		        }
		        
		 
		    }
		   
		 	public List<Users> getAllUsers() {
		        try {
		            List<Users> users = customerRepository.getAllUsers();
		            return users;
		        } catch (Exception ex) {
		            Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
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
			   
		    public void editUser(Users user,String localPassword) {
		    	String password = localPassword;
		    	
		    	try {
		    		String hashPassword = convertPasswordToSha256(password);
		    		user.setPassword(hashPassword);

		            customerRepository.editUser(user);

		            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Password has been updated succesfully"));
		        } catch (Exception ex) {
		            Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
		        }
		    }
		    
			   private Users convertUserToEntity(fit5042.assignm.controllers.User localUser) {
				   	Users user = new Users(); //entity
			        
				   	String name = localUser.getUsername();
				   	String password = localUser.getPassword();
				   	String hashPassword = "";
				   	
				   	try {
			    		hashPassword = convertPasswordToSha256(password);
			    
			        } catch (Exception ex) {
			        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Password 256 error"));
			        }
			       
				   	user.setUsername(name);
				   	user.setPassword(hashPassword);

				   	Set<Customer> customers = new HashSet<>();
				   	user.setCustomers(customers);
				   	
				   	return user;
			    }
			   
		    
			   public void addUser(fit5042.assignm.controllers.User localUser) {
				   Users user = convertUserToEntity(localUser);
				   Groups group = new Groups();
				   group.setUsername(user.getUsername());
				   group.setGroupname("user");
				   try {
					   customerRepository.addUser(user);
					   customerRepository.addGroup(group);
					   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User has been added succesfully"));
				   }catch (Exception ex) {
					   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("This user name is existing,please enter other user name"));
			            Logger.getLogger(CustomerRepository.class.getName()).log(Level.SEVERE, null, ex);
			        }
				   
			   }
			   
			   public void removeUser(int userId) {
			        try {
			        	customerRepository.removeUser(userId);
			 
			        } catch (Exception ex) {
			            Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
			        }
			    }
			   
			   public Set<Customer> searchCustomerByCEOAndState(String ceo, String state){
				   
				   Set<Customer> filterCustomer = new HashSet<>();
				   try {
					   for (Customer customer: customerRepository.getAllCustomers())
					   {
						   if(customer.getCEO().equals(ceo) && customer.getAddress().getState().equals(state)) 
						   {
							   filterCustomer.add(customer);
						   }
					   }
					   return filterCustomer;
				   }
				   catch(Exception ex){
					   
				   }
				   
				   return null;
			   }
			   
			 	public List<Organisation> getAllOrganisation() {
			        try {
			            List<Organisation> organisations = customerRepository.getAllOrganisation();
			            return organisations;
			        } catch (Exception ex) {
			            Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
			        }
			        return null;
			    }

			    public void editOrganisation(Organisation organisation ) {
			        try {

			        	String typename = organisation.getTypeName();
			        	if(typename.equals("Hospital") || 
				        		typename.equals("Medical Clinic") || 
				        		typename.equals("Specialist Office") ||
				        		typename.equals("Nursing Home") ||
				        		typename.equals("Dental Office") ||
				        		typename.equals("Diagnostics & Laboratory") ||
				        		typename.equals("Orthodontics Office") ||
				        		typename.equals("Natural Therapy") ) 
				        	{
				        	 customerRepository.editOrganisation(organisation);

					            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Organisation has been updated succesfully"));
				        	}
				        	
				        	else {
				        		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Organisation Type is illegal"));
				        	}

			        } catch (Exception ex) {
			            Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
			        }
			    }
			    
				   public void addOrganisation(fit5042.assignm.controllers.Organisation localOrganisation) {
						
					   Organisation organisation = new Organisation();
					   organisation.setTypeName(localOrganisation.getTypeName());
					   
					   try {
						   customerRepository.addOrganisation(organisation);
					   
					   }catch (Exception ex) {
				            Logger.getLogger(CustomerRepository.class.getName()).log(Level.SEVERE, null, ex);
				        }
					   
				   }
				   
				   public void removeOrganisation(int organisationId) {
				        try {
				        	customerRepository.removeOrganisation(organisationId);
				 
				        } catch (Exception ex) {
				            Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
				        }
				    }
}
