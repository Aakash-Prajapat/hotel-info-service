package com.epam.incubation.service.hotelinfo.requestmodel;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class InventoryRequestModel {

	@NotNull(message = "Check In Date is mandatory")
	private Date checkInDate;
	@NotNull(message = "Check Out Date is mandatory")
	private Date checkOutDate;
	@NotNull(message = "Hotel Id is mandatory")
	private Integer hotelId;
	@NotNull(message = "Room Id is mandatory")
	private Integer roomId;
	@NotEmpty(message = "Operation is Mandatory")
	private String operation;

	public Date getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(Date checkInDate) {
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
