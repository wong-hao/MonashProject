package fit5042.assignm.controllers;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import fit5042.assignm.controllers.user.UserController;

/**
*
* 
*/
@RequestScoped
@Named("searchCustomer")
public class SearchCustomer {
	private Customer customer;

	CustomerApplication app;

	private int searchByInt;
	private String searchByCEO;
	private String searchByState;


	public CustomerApplication getApp() {
		return app;
	}

	public void setApp(CustomerApplication app) {
		this.app = app;
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

	public SearchCustomer() {
        ELContext context
                = FacesContext.getCurrentInstance().getELContext();

        app = (CustomerApplication) FacesContext.getCurrentInstance()
                        .getApplication()
                        .getELResolver()
                        .getValue(context, null, "customerApplication");
        
        app.updateCustomerList();
    }

	/**
	 * Normally each page should have a backing bean but you can actually do it any
	 * how you want.
	 *
	 * @param customer Index
	 */
	public void searchCustomerById(int customerIndex) {
		try {
			// search this customer then refresh the list in PropertyApplication bean
			app.searchCustomerById(customerIndex);
		} catch (Exception ex) {

		}
	}
	
	public void searchCustomerByCEOAndState(String country, String state) {
		try {
			app.searchCustomerByCEOAndState(country, state);
		}
		catch(Exception ex){
			
		}
	}


	public void searchAll() {
		try {
			// return all properties from db via EJB
			app.searchAll();
		} catch (Exception ex) {

		}
	}

	public String getSearchByCEO() {
		return searchByCEO;
	}

	public void setSearchByCEO(String searchByCEO) {
		this.searchByCEO = searchByCEO;
	}

	public String getSearchByState() {
		return searchByState;
	}

	public void setSearchByState(String searchByState) {
		this.searchByState = searchByState;
	}
	
}
