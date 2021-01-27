package com.epam.incubation.service.hotelinfo.service;

import java.text.ParseException;

import com.epam.incubation.service.hotelinfo.datamodel.InventoryDetailsResponseModel;
import com.epam.incubation.service.hotelinfo.requestmodel.InventoryRequestModel;

public interface InventoryService {

	public InventoryDetailsResponseModel getInventoryDetails(InventoryRequestModel model) throws ParseException;

	public InventoryDetailsResponseModel updateInventory(InventoryRequestModel model) throws ParseException;

}
