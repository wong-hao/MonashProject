/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.tutex.repository.entities;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.NumberFormat;

/**
 *
 * @author Junyang
 * 
 */
//TODO Exercise 1.3 Step 1 Please refer tutorial exercise. 
public class Property {

	public Property(Property property) {
		this.ID = property.ID;
		this.address = property.address;
		this.numberofbedrooms = property.numberofbedrooms;
		this.size = property.size;
		this.prize = property.prize;
	}

	/**
	 * Default constructor
	 */
	public Property() {
		this.ID = 0;
		this.address = "";
		this.numberofbedrooms = 0;
		this.size = 0;
		this.prize = 0.0;
	}

//    property ID, address, number of bedrooms, size and price
	public Property(int ID, String address, int numberofbedrooms, int size, double prize) {
		this.ID = ID;
		this.address = address;
		this.numberofbedrooms = numberofbedrooms;
		this.size = size;
		this.prize = prize;
	}

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getNumberofbedrooms() {
		return numberofbedrooms;
	}

	public void setNumberofbedrooms(int numberofbedrooms) {
		this.numberofbedrooms = numberofbedrooms;
	}

	public float getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public double getPrize() {
		return prize;
	}

	public void setPrize(double prize) {
		this.prize = prize;
	}

	private int ID;
	private String address;
	private int numberofbedrooms;
	private int size;
	private double prize;

}
