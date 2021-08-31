package fit5042.assignm.repository;

import fit5042.assignm.repository.CustomerContactRepository;
import fit5042.assignm.repository.CustomerRepository;

import fit5042.assignm.repository.entities.Customer;
import fit5042.assignm.repository.entities.CustomerContact;
import fit5042.assignm.repository.entities.Groups;
import fit5042.assignm.repository.entities.Organisation;
import fit5042.assignm.repository.entities.Users;

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


public class JPACustomerRepositorylmpl implements CustomerRepository {
	@PersistenceContext(unitName = "CRMSystem-ejb")
    private EntityManager entityManager;
	
	@Override
    public void addCustomer(Customer customer) throws Exception {
    	List<Customer> customers =  entityManager.createNamedQuery(Customer.GET_ALL_CUSTOMERS_QUERY_NAME).getResultList(); 
    	customer.setCustomerId(customers.get(0).getCustomerId() + 1);
        entityManager.persist(customer);
    }
     
    @Override
    public Customer searchCustomerId(int id) throws Exception {
    	Customer customer = entityManager.find(Customer.class, id);
        return customer;
    }
    
    @Override
    public void removeCustomer(int customerId) throws Exception {
    	Customer customer = this.searchCustomerId(customerId);

        if (customer!= null) {
            entityManager.remove(customer);
        }
    }

    @Override
    public void editCustomer(Customer customer) throws Exception {   
         try {
            entityManager.merge(customer);
        } catch (Exception ex) {
            
        }
    }

    
    
    @Override
    public List<Customer> getAllCustomers() throws Exception {
        return entityManager.createNamedQuery(Customer.GET_ALL_CUSTOMERS_QUERY_NAME).getResultList();
    }

	@Override
	public Set<CustomerContact> searchContactByCustomer(Customer customer) throws Exception {
		customer = entityManager.find(Customer.class, customer.getCustomerId());
		customer.getContacts().size();
		
        entityManager.refresh(customer);

        return customer.getContacts();

}

	@Override
	public Set<Customer> searchCustomerByUser(Users user) throws Exception {
		user = entityManager.find(Users.class, user.getUserId());
		user.getCustomers().size();
		
        entityManager.refresh(user);

        return user.getCustomers();
	}
	
	@Override
	public Users searchUserByName(String userName) throws Exception {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(Users.class);
        
        Root<Users> p = query.from(Users.class);
        
        query.select(p).where(builder.equal(p.get("username").as(String.class), userName));
        
        Users u = (Users)(entityManager.createQuery(query).getResultList().get(0));
        return u;
        
	}

	public List<Users> getAllUsers() throws Exception {
		return entityManager.createNamedQuery(Users.GET_ALL_QUERY_NAME).getResultList();
	}

	
	@Override
	public void editUser(Users user) throws Exception {
		 try {
	            entityManager.merge(user);
	        } catch (Exception ex) {

	        }
		
	}

	@Override
	public void addUser(Users user) throws Exception {
		List<Users> users = entityManager.createNamedQuery(Users.GET_ALL_QUERY_NAME).getResultList();
		int maxid = 0;
		   for(Users tempUser:users) {
			   if(tempUser.getUserId() >= maxid) {
				   maxid = tempUser.getUserId();
			   }
		   }
		   user.setUserId(maxid+ 1);
	        entityManager.persist(user);
		
	}
	
	@Override
	public void addGroup(Groups group) throws Exception {
		group.setId(getMaxGroupId());
		entityManager.persist(group);
		
	}

	@Override
	public int getMaxGroupId() throws Exception {
		List<Groups> groups = entityManager.createNamedQuery(Groups.GET_ALL_QUERY_NAME).getResultList();
		int maxid = 0;
		   for(Groups group:groups) {
			   if(group.getId() >= maxid) {
				   maxid = group.getId() ;
			   }
		   }
		   maxid = maxid + 1;
		   return maxid;
	}

	@Override
	public void removeUser(int userId) throws Exception {
		Users user = entityManager.find(Users.class, userId);
		
		if (user != null) {
            entityManager.remove(user);
        }
		
	}

	@Override
	public List<Organisation> getAllOrganisation() throws Exception {
		return entityManager.createNamedQuery(Organisation.GET_ALL_QUERY_NAME).getResultList();
	}

	
	@Override
	public void editOrganisation(Organisation organisation) throws Exception {
		 try {
	            entityManager.merge(organisation);
	        } catch (Exception ex) {

	        }
		
	}

	@Override
	public void addOrganisation(Organisation organisation) throws Exception {
		List<Organisation> organisations = entityManager.createNamedQuery(Organisation.GET_ALL_QUERY_NAME).getResultList();
		int maxid = 0;
		   for(Organisation tempType:organisations) {
			   if(tempType.getOrganisationId() >= maxid) {
				   maxid = tempType.getOrganisationId();
			   }
		   }
		   organisation.setOrganisationId(maxid+ 1);
	        entityManager.persist(organisation);
		
	}

	@Override
	public void removeOrganisation(int organisationId) throws Exception {
		Organisation organisation = entityManager.find(Organisation.class, organisationId);
		
		if (organisation != null) {
            entityManager.remove(organisation);
        }		
	}
	
	
	

}
