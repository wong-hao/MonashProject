/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.tutex.calculator;

import fit5042.tutex.repository.constants.CommonInstance;
import fit5042.tutex.repository.entities.Property;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.CreateException;
import javax.ejb.Stateful;
//import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Elliot
 * 
 */
@Stateful 
// Stateless - if another client (e.g. another instance of the bean) is trying to access the bean , the container may give the existing instance to that client. 
  //So the state can be modified by the new client. In other words, no separate bean instances for each client.
//Stateful, there will be separate bean instance for each client.
public class ComparePropertySessionBean implements CompareProperty {

	private Set<Property> list;
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public ComparePropertySessionBean() {
    	list = new HashSet<>();
    }
    
    @Override
    public void addProperty(Property property) {
        list.add(property);
    }
    
    @Override
    public void removeProperty(Property property) {
		// TODO Auto-generated method stub
        for (Property p : list) {
        	if (p.getPropertyId() == property.getPropertyId()) {
        		list.remove(p);
        		break;
        	}
        }
    }    

    @Override
    public int bestPerRoom() {
		// TODO Auto-generated method stub
        Integer bestID=0;
        int numberOfRooms;
        double price;
        double bestPerRoom=100000000.00;
        for(Property p : list)
        {
            numberOfRooms = p.getNumberOfBedrooms();
            price = p.getPrice();
            if(price / numberOfRooms < bestPerRoom)
            {
                bestPerRoom = price / numberOfRooms;
                bestID = p.getPropertyId();
            }
        }
        return bestID;
    }

    /**
     *
     * @return 
     * @throws javax.ejb.CreateException
     * @throws java.rmi.RemoteException
     */
    @PostConstruct
    public void init() {
        list=new HashSet<>();
    }

    public CompareProperty create() throws CreateException, RemoteException {
        return null;
    }

    public void ejbCreate() throws CreateException {
    }

}
