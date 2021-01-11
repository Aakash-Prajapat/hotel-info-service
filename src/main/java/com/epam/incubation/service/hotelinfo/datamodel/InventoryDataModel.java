package com.epam.incubation.service.hotelinfo.datamodel;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class InventoryDataModel {
	@NotBlank(message = "Room stay Date is mandatory")
	private Date stayDate;
	@NotNull(message = "Quantity is mandatory")
	private int quantity;
	@NotNull(message = "Price is mandatory")
	private Double price;

	public InventoryDataModel(Date stayDate, int quantity, Double price) {
		this.stayDate = stayDate;
		this.quantity = quantity;
		this.price = price;
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
