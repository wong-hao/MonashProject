package fit5042.assignm.controllers.user;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import fit5042.assignm.controllers.Customer;
import fit5042.assignm.controllers.user.UserController;

/**
*
* 
*/
@RequestScoped
@Named("usersearchCustomer")
public class UserSearchCustomer {
	private Customer customer;

	UserController app2;

	private int searchByInt;
	private String searchByCEO;
	private String searchByState;

	public UserController getApp() {
		return app2;
	}

	public void setApp(UserController app2) {
		this.app2 = app2;
	}


	public int getSearchByInt() {
		return searchByInt;
	}

	public void setSearchByInt(int searchByInt) {
		this.searchByInt = searchByInt;
	}
	

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Customer getCustomer() {
		return customer;
	}

	public UserSearchCustomer() {
        ELContext context
                = FacesContext.getCurrentInstance().getELContext();

        app2 = (UserController) FacesContext.getCurrentInstance()
                        .getApplication()
                        .getELResolver()
                        .getValue(context, null, "userController");
        
        app2.updateCustomerList();
    }

	/**
	 * Normally each page should have a backing bean but you can actually do it any
	 * how you want.
	 *
	 * @param customer Index
	 */

	public void usersearchCustomerById(int customerIndex) {
		try {
			// search this customer then refresh the list in PropertyApplication bean
			app2.searchCustomerById(customerIndex);
		} catch (Exception ex) {

		}
	}

	public void searchCustomerByCEOAndState(String country, String state) {
		try {
			app2.searchCustomerByCEOAndState(country, state);
		}
		catch(Exception ex){
			
		}
	}
	
	public void usersearchAll() {
		try {
			// return all properties from db via EJB
        	app2.updateCustomerList(app2.getUser());
		} catch (Exception ex) {

		}
	}

	public String getSearchByState() {
		return searchByState;
	}

	public void setSearchByState(String searchByState) {
		this.searchByState = searchByState;
	}

	public String getSearchByCEO() {
		return searchByCEO;
	}

	public void setSearchByCEO(String searchByCEO) {
		this.searchByCEO = searchByCEO;
	}

}