package com.epam.incubation.service.hotelinfo.datamodel;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.epam.incubation.service.hotelinfo.entity.Inventory;

public class InventoryDataModel {
	@NotBlank(message = "Room stay Date is mandatory")
	private Date stayDate;
	@NotNull(message = "Quantity is mandatory")
	private int quantity;
	@NotNull(message = "Price is mandatory")
	private Double price;

	public InventoryDataModel() {

	}

	public InventoryDataModel(Inventory inventory) {
		this.stayDate = inventory.getStayDate();
		this.quantity = inventory.getQuantity();
		this.price = inventory.getPrice();
	}

	public Date getStayDate() {
		return stayDate;
	}

	public void setStayDate(Date stayDate) {
		this.stayDate = stayDate;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}
