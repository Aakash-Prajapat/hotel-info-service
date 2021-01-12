package com.epam.incubation.service.hotelinfo.service;

import java.util.List;

import com.epam.incubation.service.hotelinfo.datamodel.InventoryDataModel;
import com.epam.incubation.service.hotelinfo.datamodel.InventoryRequestModel;
import com.epam.incubation.service.hotelinfo.datamodel.InventoryResponseModel;

public interface InventoryService {

	public List<InventoryResponseModel> getInventoryDetails(InventoryRequestModel model);

	public List<InventoryResponseModel> updateInventory(InventoryRequestModel model);

}
