package fit5042.assignm.repository;


import fit5042.assignm.repository.CustomerContactRepository;
import fit5042.assignm.repository.entities.Customer;
import fit5042.assignm.repository.entities.CustomerContact;

import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * 
 */
@Stateless

public class JPACustomerContactRepositorylmpl implements CustomerContactRepository{
	@PersistenceContext(unitName = "CRMSystem-ejb")
    private EntityManager entityManager;
	
	@Override
    public void addCustomerContact(CustomerContact customerContact) throws Exception {
    	List<CustomerContact> customerContacts =  entityManager.createNamedQuery(CustomerContact.GET_ALL_CONTACT_QUERY_NAME).getResultList(); 
    	customerContact.setCustomerContactId(customerContacts.get(0).getCustomerContactId() + 1);
        Customer customer = customerContact.getCustomer();
        customer.getCustomerContacts().add(customerContact);
    	entityManager.merge(customer);
        //entityManager.flush();
    }
     
    @Override
    public CustomerContact searchCustomerContactId(int id) throws Exception {
    	CustomerContact customerContact = entityManager.find(CustomerContact.class, id);
        return customerContact;
    }

    @Override
    public List<CustomerContact> getAllCustomerContacts() throws Exception {
        return entityManager.createNamedQuery(CustomerContact.GET_ALL_CONTACT_QUERY_NAME).getResultList();
    }
    


    @Override
    public List<CustomerContact> searchCustomerContactByCustomer(Customer customer) throws Exception {
       customer = entityManager.find(Customer.class, customer.getCustomerId());
       customer.getCustomerContacts().size();
        entityManager.refresh(customer);

        return customer.getCustomerContacts();
    }

    @Override
    public List<Customer> getAllCustomers() throws Exception {
        return entityManager.createNamedQuery(Customer.GET_ALL_CUSTOMERS_QUERY_NAME).getResultList();
    }

    @Override
    public void removeCustomerContact(int customerContactId) throws Exception {
    	CustomerContact customerContact = this.searchCustomerContactId(customerContactId);

        if (customerContact != null) {
            entityManager.remove(customerContact);
        }
    }

    @Override
    public void editCustomerContact(CustomerContact customerContact) throws Exception {   
         try {
            entityManager.merge(customerContact);
        } catch (Exception ex) {
            
        }
    }

  

}
