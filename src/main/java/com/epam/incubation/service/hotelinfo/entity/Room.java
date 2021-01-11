package com.epam.incubation.service.hotelinfo.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Room {
	@Id
	@Column(name = "ROOM_ID")
	private Integer roomId;
	@Column(name = "ROOM_NAME")
	private String name;
	@Column(name = "ROOM_DESCRIPTION")
	private String description;
	@Column(name = "STATUS")
	private boolean status;
	@ManyToOne
	@JoinColumn(name = "HOTEL_ID")
	private Hotel hotel;
	@OneToMany(mappedBy = "room")
	private List<Inventory> inventories;

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public List<Inventory> getInventories() {
		return inventories;
	}

	public void setInventories(List<Inventory> inventories) {
		this.inventories = inventories;
	}
}
