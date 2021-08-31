package fit5042.assignm.mbeans;

import fit5042.assignm.repository.entities.Address;
import fit5042.assignm.repository.entities.Customer;
import fit5042.assignm.repository.CustomerContactRepository;
import fit5042.assignm.repository.entities.Customer;
import fit5042.assignm.repository.entities.CustomerContact;
import fit5042.assignm.repository.entities.Organisation;
import fit5042.assignm.repository.entities.Users;

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

import com.sun.istack.NotNull;

/**
*
* 
* 
*/
@ManagedBean(name = "customerContactManagedBean")
@SessionScoped

public class CustomerContactManagedBean implements Serializable {
	@EJB
	CustomerContactRepository customerContactRepository;
	
	@NotNull
	String CEO;
	
	private int selectedId;

    public int getSelectedId() {
        return selectedId;
    }

    public void setSelectedId(int selectedId) {
        this.selectedId = selectedId;
    }

	/**
     * Creates a new instance of CustomerContactManagedBean
     */
	 public CustomerContactManagedBean() {
	    }

	public List<CustomerContact> getAllCustomerContacts() {
		try {
			List<CustomerContact> customerContacts = customerContactRepository.getAllCustomerContacts();
			return customerContacts;
		} catch (Exception ex) {
			Logger.getLogger(CustomerContactManagedBean.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

	public void addCustomerContact(CustomerContact customerContact) {
		try {
			customerContactRepository.addCustomerContact(customerContact);
		} catch (Exception ex) {
			Logger.getLogger(CustomerContactManagedBean.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * Search a customerContact by Id
	 */
	public CustomerContact searchCustomerContactById(int id) {
		try {
			return customerContactRepository.searchCustomerContactId(id);
		} catch (Exception ex) {
			Logger.getLogger(CustomerContactManagedBean.class.getName()).log(Level.SEVERE, null, ex);
		}

		return null;
	}

	public List<CustomerContact> searchCustomerContactByCustomerId(int customerId) {
		try {
            //retrieve contact person by id
            for (Customer customer : customerContactRepository.getAllCustomers()) {
                if (customer.getCustomerId() == customerId) {
                    return customerContactRepository.searchCustomerContactByCustomer(customer);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(CustomerContactManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

	
	public List<Customer> getAllCustomers() throws Exception {
		try {
			return customerContactRepository.getAllCustomers();
		} catch (Exception ex) {
			Logger.getLogger(CustomerContactManagedBean.class.getName()).log(Level.SEVERE, null, ex);
		}

		return null;
	}

	public void removeCustomerContact(int customerContactId) {
		try {
			customerContactRepository.removeCustomerContact(customerContactId);
		} catch (Exception ex) {
			Logger.getLogger(CustomerContactManagedBean.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void editCustomerContact(CustomerContact customerContact) {
		try {
			
			int id = customerContact.getCustomer().getCustomerId();
			Customer customer = customerContact.getCustomer();
			customer.setCustomerId(id);
			customerContact.setCustomer(customer);
			
			customerContactRepository.editCustomerContact(customerContact);

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("CustomerContact has been updated succesfully"));
		} catch (Exception ex) {
			Logger.getLogger(CustomerContactManagedBean.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	
	   public void addCustomerContact(Customer customer,fit5042.assignm.controllers.CustomerContact localCustomerContact) {
		   CustomerContact customerContact = convertCustomerContactToEntity(customer,localCustomerContact);
		   try {
			   customerContactRepository.addCustomerContact(customerContact);
		   
		   }catch (Exception ex) {
	            Logger.getLogger(CustomerContactManagedBean.class.getName()).log(Level.SEVERE, null, ex);
	        }
		   
	   }
	
	private CustomerContact convertCustomerContactToEntity(Customer customer, fit5042.assignm.controllers.CustomerContact localCustomerContact) {
		CustomerContact customerContact = new CustomerContact(); //entity
        int customerId = localCustomerContact.getCustomerId();
        String companyName = localCustomerContact.getCompanyName();
        int officephone = localCustomerContact.getOfficephone();
        String registerDate = localCustomerContact.getRegisterDate();
        String aBN = localCustomerContact.getaBN();
        String email = localCustomerContact.getEmail();
        CEO = localCustomerContact.getCEO();
        Users user = localCustomerContact.getUser();
        
        Address address = localCustomerContact.getAddress();

        
        
        
                    
        customerContact.setCustomerContactId(localCustomerContact.getCustomerContactId());
        customerContact.setContactName(localCustomerContact.getContactName());
        customerContact.setContactPhone(localCustomerContact.getContactPhone());
        customerContact.setContactEmail(localCustomerContact.getContactEmail());
        customerContact.setPosition(localCustomerContact.getPosition());
        customerContact.setGender(localCustomerContact.getGender());
        
        if (customer.getCustomerId() == 0)
            customer = null;
        customerContact.setCustomer(customer);
        return customerContact;
        
        
    }
    

	

	

}
