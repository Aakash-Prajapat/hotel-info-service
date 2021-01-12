package com.epam.incubation.service.hotelinfo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.incubation.service.hotelinfo.datamodel.InventoryRequestModel;
import com.epam.incubation.service.hotelinfo.datamodel.InventoryResponseModel;
import com.epam.incubation.service.hotelinfo.entity.Inventory;
import com.epam.incubation.service.hotelinfo.exception.InventoryNotAvailableException;
import com.epam.incubation.service.hotelinfo.repository.InventoryRepository;

@Service
public class InventoryServiceImpl implements InventoryService {

	@Autowired
	InventoryRepository inventoryRepository;

	public List<InventoryResponseModel> getInventoryDetails(InventoryRequestModel model) {
		List<Inventory> inventories = inventoryRepository.getInventoryByDuration(model.getCheckIntDate(),
				model.getCheckOutDate(), model.getRoomId());
		List<Inventory> unavailable = inventories.parallelStream().filter(i -> i.getQuantity() < 1)
				.collect(Collectors.toList());
		if (unavailable.isEmpty()) {
			List<InventoryResponseModel> inventoriesList = inventories.stream().map(InventoryResponseModel::new)
					.collect(Collectors.toList());
			inventoriesList.forEach(i -> {
				i.setRoomId(model.getRoomId());
				i.setHotelId(model.getHotelId());
			});
			return inventoriesList;
		} else {
			String message = "Inventories are not available for date";
			String date = unavailable.stream().map(Object::toString).collect(Collectors.joining(","));
			throw new InventoryNotAvailableException(message + date);
		}
	}

	public List<InventoryResponseModel> updateInventory(InventoryRequestModel model) {
		List<Inventory> inventories = inventoryRepository.getInventoryByDuration(model.getCheckIntDate(),
				model.getCheckOutDate(), model.getRoomId());
		List<InventoryResponseModel> inventoriesResponse = new ArrayList<>();
		if (model.getOperation().equals("Cancel")) {
			inventoriesResponse = inventories.stream().map(i -> {
				i.setQuantity(i.getQuantity() + 1);
				return new InventoryResponseModel(inventoryRepository.save(i));
			}).collect(Collectors.toList());
		} else if ("Booking".equals(model.getOperation())) {
			inventoriesResponse = inventories.stream().map(i -> {
				i.setQuantity(i.getQuantity() - 1);
				return new InventoryResponseModel(inventoryRepository.save(i));
			}).collect(Collectors.toList());
		}

		inventoriesResponse.forEach(i -> {
			i.setRoomId(model.getRoomId());
			i.setHotelId(model.getHotelId());
		});

		return inventoriesResponse;
	}
}
