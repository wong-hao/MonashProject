package fit5042.tutex.repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;

import fit5042.tutex.repository.constants.CommonInstance;
import fit5042.tutex.repository.entities.ContactPerson;
import fit5042.tutex.repository.entities.Property;

@Stateless
public class JPAPropertyRepositoryImpl implements PropertyRepository {
	private ArrayList<Property> propertyList;
	
	public JPAPropertyRepositoryImpl() {
    	propertyList = new ArrayList<Property>();
    	this.initialisePropertyList();
    }
	
	public void initialisePropertyList() {
    	propertyList.clear();
    	
    	propertyList.add(CommonInstance.PROPERTY_FIRST);
    	propertyList.add(CommonInstance.PROPERTY_SECOND);
    	propertyList.add(CommonInstance.PROPERTY_THIRD);
    	propertyList.add(CommonInstance.PROPERTY_FOURTH);
    }

	public ArrayList<Property> getPropertyList() {
		return propertyList;
	}

	public void setPropertyList(ArrayList<Property> propertyList) {
		this.propertyList = propertyList;
	}
	
	public void removeProperty(int propertyId) {
    	for (Property p : propertyList) {
    		if (p.getPropertyId() == propertyId) {
    			propertyList.remove(p);
    			break;
    		}
    	}
    	
    }
    
    public void addProperty(Property property) {
    	propertyList.add(property);
    }
    
    public void editProperty(Property property) {
    	for (Property p : propertyList) {
    		int id = property.getPropertyId() - 1; //+1 in jsf edit, so should -1 here
    		if (p.getPropertyId() == id) {
    			propertyList.set(id, property);
    			break;
    		}
    	}
    }

	public int getPropertyId() {
		return propertyList.get(propertyList.size() - 1).getPropertyId();
	}
	
	public Property searchPropertyById(int propertyId) {
		for (Property p : propertyList) {
    		if (p.getPropertyId() == propertyId) {
    			return p;
    		}
    	}
		return null;
	}
	
	public List<ContactPerson> getAllContactPeople() {
		Set<ContactPerson> contactPersonSet = new HashSet<>();
		
		for (Property p : propertyList) {
			contactPersonSet.add(p.getContactPerson());
    	}
		
		List<ContactPerson> contactPersonList = new ArrayList<>();
		
		//This method will returns all the contact person in a list without duplication
        //Please write your code here
		for (ContactPerson c : contactPersonSet) {
			contactPersonList.add(c);
		}
		
		return contactPersonList;
	}
	
	public Set<Property> searchPropertyByContactPerson(ContactPerson contactPerson) {
		Set<Property> propertySet = new HashSet<>();
		
        //This method will find all the properties which belong to the particular contact person
		//Please write your code here
		for (Property p : propertyList) {
			if (p.getContactPerson().equals(contactPerson)) {
				propertySet.add(p);
			}
    	}
		
		return propertySet;
	}
	
	public List<Property> searchPropertyByBudget(double budget) {
		List<Property> properties = new ArrayList<>();
		
        //This method will return a list of properties that satisfy the criteria of bellowing a specific budget
		//Please write your code here
		for (Property p : propertyList) {
			if (p.getPrice() <= budget) {
				properties.add(p);
			}
		}
		
		return properties;
    }

}
