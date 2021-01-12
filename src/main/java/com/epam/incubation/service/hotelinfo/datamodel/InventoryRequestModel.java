package com.epam.incubation.service.hotelinfo.datamodel;

import java.util.Date;

public class InventoryRequestModel {

	private Date checkInDate;
	private Date checkOutDate;
	private Integer hotelId;
	private Integer roomId;
	private String operation;

	public Date getCheckIntDate() {
		return checkInDate;
	}

	public void setCheckIntDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}

	public Date getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public Integer getHotelId() {
		return hotelId;
	}

	public void setHotelId(Integer hotelId) {
		this.hotelId = hotelId;
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

}
