package helloworld;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;

/**
 * Session Bean implementation class NameStorageBean
 */
@Singleton
@LocalBean
public class NameStorageBean {
	private String name = "World";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
