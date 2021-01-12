package com.epam.incubation.service.hotelinfo.datamodel;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;

import com.epam.incubation.service.hotelinfo.entity.Room;

public class RoomDataModel {
	@NotBlank(message = "Room name is mandatory")
	private String name;
	@NotBlank(message = "Hotel Description is mandatory")
	private String description;
	@NotBlank(message = "Room Status is mandatory")
	private boolean status;
	private List<InventoryDataModel> inventories;

	public RoomDataModel() {

	}

	public RoomDataModel(Room room) {
		this.name = room.getName();
		this.description = room.getDescription();
		this.status = room.isStatus();
		this.inventories = room.getInventories().stream().map(InventoryDataModel::new).collect(Collectors.toList());
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

	public List<InventoryDataModel> getInventories() {
		return inventories;
	}

	public void setInventories(List<InventoryDataModel> inventories) {
		this.inventories = inventories;
	}
}
