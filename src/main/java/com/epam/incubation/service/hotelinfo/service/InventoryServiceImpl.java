package com.epam.incubation.service.hotelinfo.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.epam.incubation.service.hotelinfo.datamodel.ApiError;
import com.epam.incubation.service.hotelinfo.datamodel.InventoryDetailsResponseModel;
import com.epam.incubation.service.hotelinfo.datamodel.InventoryResponseModel;
import com.epam.incubation.service.hotelinfo.entity.Inventory;
import com.epam.incubation.service.hotelinfo.repository.InventoryRepository;
import com.epam.incubation.service.hotelinfo.requestmodel.InventoryRequestModel;

/**
 * inventory service layer responsible to update and fetch the inventory information.
 * 
 */
@Service
public class InventoryServiceImpl implements InventoryService {

	private final Logger logger = LoggerFactory.getLogger(InventoryServiceImpl.class);
	
	@Autowired
	InventoryRepository inventoryRepository;

	/**
	 * Responsible to return the inventory data.
	 * 
	 * @param InventoryRequestModel , based on request model, inventory data needs to be fetched.
	 * @return InventoryDetailsResponseModel, Holds inventory information.
	 * throws ParseException
	 */
	public InventoryDetailsResponseModel getInventoryDetails(InventoryRequestModel model) throws ParseException {
		InventoryDetailsResponseModel inventoryDetails = new InventoryDetailsResponseModel();
		logger.info("Fetching the inventory data");
		List<Inventory> inventories = inventoryRepository.getInventoryByDuration(model.getRoomId(), model.getCheckInDate(),
				model.getCheckOutDate());
		List<Inventory> unavailable = inventories.parallelStream().filter(i -> i.getQuantity() < 1)
				.collect(Collectors.toList());
		if (unavailable.isEmpty() && !inventories.isEmpty()) {
			List<InventoryResponseModel> inventoriesList = inventories.stream().map(InventoryResponseModel::new)
					.collect(Collectors.toList());
			inventoriesList.forEach(i -> {
				i.setRoomId(model.getRoomId());
				i.setHotelId(model.getHotelId());
			});
			inventoryDetails.setResponseModel(inventoriesList);
		} else {
			ApiError error = new ApiError(HttpStatus.NOT_FOUND, "Inventory not available");
			inventoryDetails.setError(error);
		}
		return inventoryDetails;
	}

	/**
	 * Responsible to return the inventory data.
	 * 
	 * @param InventoryRequestModel , based on request model, inventory data needs to be updated.
	 * @return InventoryDetailsResponseModel, Holds inventory information.
	 * throws ParseException
	 */
	public InventoryDetailsResponseModel updateInventory(InventoryRequestModel model) throws ParseException {
		InventoryDetailsResponseModel inventoryDetails = new InventoryDetailsResponseModel();
		logger.info("Fetching the inventory data");
		List<Inventory> inventories = inventoryRepository.getInventoryByDuration(model.getRoomId(), model.getCheckInDate(),
				model.getCheckOutDate());
		List<InventoryResponseModel> inventoriesResponse = new ArrayList<>();
		if (model.getOperation().equals("Cancel")) {
			logger.info("Updating the inventory data based on operation Cancelling");
			inventoriesResponse = inventories.stream().map(i -> {
				i.setQuantity(i.getQuantity() + 1);
				return new InventoryResponseModel(inventoryRepository.save(i));
			}).collect(Collectors.toList());
		} else if (model.getOperation().equals("Booking")) {
			logger.info("Updating the inventory data based on operation Booking");
			inventoriesResponse = inventories.stream().map(i -> {
				i.setQuantity(i.getQuantity() - 1);
				return new InventoryResponseModel(inventoryRepository.save(i));
			}).collect(Collectors.toList());
		}

		inventoriesResponse.forEach(i -> {
			i.setRoomId(model.getRoomId());
			i.setHotelId(model.getHotelId());
		});

		inventoryDetails.setResponseModel(inventoriesResponse);
		return inventoryDetails;
	}
}
