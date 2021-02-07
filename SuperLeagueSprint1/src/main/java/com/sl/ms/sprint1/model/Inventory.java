package com.sl.ms.sprint1.model;

import java.time.LocalDate;
import java.time.Month;
import java.util.Date;

public class Inventory {

	private LocalDate invetoryDate;
	private String id;
	private String name;
	private int price;
	private int quantity;

	public Inventory(LocalDate invetoryDate, String id, String name, int price, int quantity) {
		super();
		this.invetoryDate = invetoryDate;
		this.id = id;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}
    @Override
    public String toString() {
    	
        return this.invetoryDate+"  "+this.id +"  " +this.name+"  " +this.price+"  "+this.quantity;
    }
    
	public LocalDate getInvetoryDate() {
		return invetoryDate;
	}

	public void setInvetoryDate(LocalDate invetoryDate) {
		this.invetoryDate = invetoryDate;
	}

	public String getInvetoryDateMonth() {
		return invetoryDate.getMonth() + " " + invetoryDate.getYear();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
