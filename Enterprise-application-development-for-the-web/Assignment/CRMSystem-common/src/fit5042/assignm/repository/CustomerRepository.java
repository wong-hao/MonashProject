package fit5042.assignm.repository;

import fit5042.assignm.repository.entities.CustomerContact;
import fit5042.assignm.repository.entities.Groups;
import fit5042.assignm.repository.entities.Organisation;
import fit5042.assignm.repository.entities.Users;
import fit5042.assignm.repository.entities.Customer;
import java.util.List;
import java.util.Set;
import javax.ejb.Remote;

/**
 * @autor Shuang Xu
 */
@Remote
public interface CustomerRepository {
	
	/**
     * Add the customer being passed as parameter into the repository
     *
     * @param customer - the customer to add
     */
    public void addCustomer(Customer customer) throws Exception;

    /**
     * Search for a customer by its customer ID
     *
     * @param id - the customerId of the customer to search for
     * @return the customer found
     */
    public Customer searchCustomerId(int id) throws Exception;
    
    /**
     * Remove the customer, whose customerID matches the one being passed as parameter, from the repository
     *
     * @param customerId - the ID of the customer to remove
     */
    public void removeCustomer(int customerId) throws Exception;
    
    /**
     * Update a customer in the repository
     *
     * @param customer - the updated information regarding a property
     */
    public void editCustomer(Customer customer) throws Exception;
    
	/**
	 * Return all the customers in the repository
	 *
	 * @return all the customers people in the repository
	 */
	public List<Customer> getAllCustomers() throws Exception;
	
    Set<CustomerContact> searchContactByCustomer(Customer customer) throws Exception;

    Set<Customer> searchCustomerByUser(Users user) throws Exception;

    public List<Users> getAllUsers() throws Exception;

    public Users searchUserByName(String userName) throws Exception;
    
    public void editUser(Users user) throws Exception;
    
    public int getMaxGroupId()throws Exception;
    
    public void addUser(Users user) throws Exception;
    
    public void addGroup(Groups group) throws Exception;

    public void removeUser(int userId) throws Exception;
    
    public List<Organisation> getAllOrganisation() throws Exception;
    
    public void editOrganisation(Organisation organisation) throws Exception;

    public void addOrganisation(Organisation organisation) throws Exception;
    
    public void removeOrganisation(int organisationId) throws Exception;



}
