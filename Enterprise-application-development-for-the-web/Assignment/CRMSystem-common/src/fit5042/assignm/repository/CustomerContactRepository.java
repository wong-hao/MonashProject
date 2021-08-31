package fit5042.assignm.repository;

import fit5042.assignm.repository.entities.CustomerContact;
import fit5042.assignm.repository.entities.Customer;
import java.util.List;
import java.util.Set;
import javax.ejb.Remote;

/**
 * @autor Shuang Xu
 */
@Remote
public interface CustomerContactRepository {

    /**
     * Add the customerContact being passed as parameter into the repository
     *
     * @param customerContact - the customerContact to add
     */
    public void addCustomerContact(CustomerContact customerContact) throws Exception;

    /**
     * Search for a property by its property ID
     *
     * @param id - the CustomerContactId of the property to search for
     * @return the customerContact found
     */
    public CustomerContact searchCustomerContactId(int id) throws Exception;

    /**
     * Return all the customerContacts in the repository
     *
     * @return all the customerContact in the repository
     */
    public List<CustomerContact> getAllCustomerContacts() throws Exception;
    
    /**
     * Search for properties by their contact person
     *
     * @param contactPerson - the contact person that is responsible for the properties
     * @return the properties found
     */
    public List<CustomerContact> searchCustomerContactByCustomer(Customer customer) throws Exception;
   
        
    /**
     * Return all the customers in the repository
     *
     * @return all the customers people in the repository
     */
    public List<Customer> getAllCustomers() throws Exception;
    
    /**
     * Remove the customerContact, whose customerContact ID matches the one being passed as parameter, from the repository
     *
     * @param customerContactId - the ID of the customerContact to remove
     */
    public void removeCustomerContact(int customerContactId) throws Exception;
    
    /**
     * Update a customerContact in the repository
     *
     * @param customerContact - the updated information regarding a property
     */
    public void editCustomerContact(CustomerContact customerContact) throws Exception;
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}