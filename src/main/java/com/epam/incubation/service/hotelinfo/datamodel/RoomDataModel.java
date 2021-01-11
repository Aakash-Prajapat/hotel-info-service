package com.epam.incubation.service.hotelinfo.datamodel;

import java.util.List;

import javax.validation.constraints.NotBlank;

public class RoomDataModel {
	@NotBlank(message = "Room name is mandatory")
	private String name;
	@NotBlank(message = "Hotel Description is mandatory")
	private String description;
	@NotBlank(message = "Room Status is mandatory")
	private boolean status;
	private List<InventoryDataModel> inventories;

	public RoomDataModel(String name, String description, boolean status, List<InventoryDataModel> inventories) {
		this.name = name;
		this.description = description;
		this.status = status;
		this.inventories = inventories;
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
