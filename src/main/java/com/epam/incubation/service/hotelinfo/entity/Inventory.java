package com.epam.incubation.service.hotelinfo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Inventory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "INVENTORY_ID")
	private Integer inventoryId;
	@Column(name = "STAY_DATE")
	@Temporal(TemporalType.DATE)
	private Date stayDate;
	@Column(name = "QUANTITY")
	private int quantity;
	@Column(name = "PRICE")
	private Double price;
	@ManyToOne
	@JoinColumn(name = "ROOM_ID")
	private Room room;

	public Integer getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(Integer inventoryId) {
		this.inventoryId = inventoryId;
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

	public void setRoom(Room room) {
		this.room = room;
	}
}
