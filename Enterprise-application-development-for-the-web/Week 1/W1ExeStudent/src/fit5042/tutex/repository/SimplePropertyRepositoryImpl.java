/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.tutex.repository;

import fit5042.tutex.repository.entities.Property;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO Exercise 1.3 Step 2 Complete this class.
 * 
 * This class implements the PropertyRepository class. You will need to add the
 * keyword "implements" PropertyRepository.
 * 
 * @author Junyang
 */
public class SimplePropertyRepositoryImpl implements PropertyRepository {
	private final List<Property> properties;

	public SimplePropertyRepositoryImpl() {
//        List<Property> properties;
		properties = new ArrayList<Property>();
	}

	@Override
	public void addProperty(Property property) throws Exception {
		if ((!properties.contains(property)) && (searchPropertyById(property.getID()) == null))
			properties.add(property);
	}

	@Override
	public Property searchPropertyById(int id) throws Exception {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
		List<Property> propertiesList = getAllProperties();
		for (int i = 0; i < propertiesList.size(); i++) {
			if (propertiesList.get(i).getID() == id) {
				return propertiesList.get(i);
			}
		}

		return null;

	}

	@Override
	public List<Property> getAllProperties() throws Exception {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

		List<Property> properties = new ArrayList<>(this.properties.size());
		for (Property property : this.properties) {
			properties.add(new Property(property));
		}
		return properties;

	}

}
