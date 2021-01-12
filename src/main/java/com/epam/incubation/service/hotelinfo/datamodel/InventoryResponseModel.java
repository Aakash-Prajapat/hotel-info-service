package com.epam.incubation.service.hotelinfo.datamodel;

import java.util.Date;

import com.epam.incubation.service.hotelinfo.entity.Inventory;

public class InventoryResponseModel {
	private Integer inventoryId;
	private Date stayDate;
	private Integer roomId;
	private Integer hotelId;
	private Double rateRoom;

	public InventoryResponseModel() {

	}

	public InventoryResponseModel(Inventory inventory) {
		this.stayDate = inventory.getStayDate();
		this.inventoryId = inventory.getInventoryId();
		this.rateRoom = inventory.getPrice();
	}

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

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public Integer getHotelId() {
		return hotelId;
	}

	public void setHotelId(Integer hotelId) {
		this.hotelId = hotelId;
	}

	public Double getRateRoom() {
		return rateRoom;
	}

	public void setRateRoom(Double rateRoom) {
		this.rateRoom = rateRoom;
	}
}
