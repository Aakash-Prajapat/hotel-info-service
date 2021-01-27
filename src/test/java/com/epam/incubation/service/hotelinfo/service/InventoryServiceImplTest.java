package com.epam.incubation.service.hotelinfo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.incubation.service.hotelinfo.datamodel.InventoryDataModel;
import com.epam.incubation.service.hotelinfo.datamodel.InventoryDetailsResponseModel;
import com.epam.incubation.service.hotelinfo.entity.Inventory;
import com.epam.incubation.service.hotelinfo.entity.Room;
import com.epam.incubation.service.hotelinfo.repository.InventoryRepository;
import com.epam.incubation.service.hotelinfo.requestmodel.InventoryRequestModel;

@ExtendWith(MockitoExtension.class)
class InventoryServiceImplTest {

	@Mock
	InventoryRepository repository;
	@InjectMocks
	InventoryServiceImpl service;
	
	@Test
	void getInventoryDetails_ShouldReturnInventoryDetails() throws ParseException {
		SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");
		InventoryRequestModel request = new InventoryRequestModel();
		request.setCheckInDate(myFormat.parse("01-02-2021")); request.setHotelId(1);
		request.setCheckOutDate(myFormat.parse("02-02-2021")); request.setRoomId(1);
		request.setOperation("Get");
		List<Inventory> inventories = getMockedInventory("getInventoryDetails");
		
		given(repository.getInventoryByDuration(1, myFormat.parse("01-02-2021"), myFormat.parse("02-02-2021")))
		.willReturn(inventories);
		InventoryDetailsResponseModel inventory = service.getInventoryDetails(request);
		assertNotNull(inventory.getResponseModel());
		assertNull(inventory.getError());
	}
	
	@Test
	void getInventoryDetails_ShouldReturnException() throws ParseException {
		SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");
		InventoryRequestModel request = new InventoryRequestModel();
		request.setCheckInDate(myFormat.parse("01-02-2021")); request.setHotelId(1);
		request.setCheckOutDate(myFormat.parse("02-02-2021")); request.setRoomId(1);
		request.setOperation("Get");
		List<Inventory> inventories = getMockedInventory("getInventoryDetails");
		inventories.get(0).setQuantity(0);
		given(repository.getInventoryByDuration(1, myFormat.parse("01-02-2021"), myFormat.parse("02-02-2021")))
		.willReturn(new ArrayList<>());
		InventoryDetailsResponseModel inventory = service.getInventoryDetails(request);
		assertNotNull(inventory.getError());
		assertNull(inventory.getResponseModel());
	}
	
	@Test
	void updateInventoryDetails_Cancel_ShouldReturnInventoryDetails() throws ParseException {
		SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");
		InventoryRequestModel request = new InventoryRequestModel();
		request.setCheckInDate(myFormat.parse("01-02-2021")); request.setHotelId(1);
		request.setCheckOutDate(myFormat.parse("02-02-2021")); request.setRoomId(1);
		request.setOperation("Cancel");
		List<Inventory> inventories = getMockedInventory("updateInventory");
		Inventory inv = getMockedInventory("getInventoryDetails").get(0);
		inv.setQuantity(6);
		given(repository.getInventoryByDuration(1, myFormat.parse("01-02-2021"), myFormat.parse("02-02-2021")))
		.willReturn(inventories);
		given(repository.save(any(Inventory.class))).willReturn(inv);
		InventoryDetailsResponseModel inventory = service.updateInventory(request);
		assertNotNull(inventory.getResponseModel());
		assertNull(inventory.getError());
	}
	
	@Test
	void updateInventoryDetails__ShouldReturnInventoryDetails() throws ParseException {
		SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");
		InventoryRequestModel request = new InventoryRequestModel();
		request.setCheckInDate(myFormat.parse("01-02-2021")); request.setHotelId(1);
		request.setCheckOutDate(myFormat.parse("02-02-2021")); request.setRoomId(1);
		request.setOperation("Booking");
		List<Inventory> inventories = getMockedInventory("updateInventory");
		Inventory inv = getMockedInventory("getInventoryDetails").get(0);
		inv.setQuantity(4);
		given(repository.getInventoryByDuration(1, myFormat.parse("01-02-2021"), myFormat.parse("02-02-2021")))
		.willReturn(inventories);
		given(repository.save(any(Inventory.class))).willReturn(inv);
		InventoryDetailsResponseModel inventory = service.updateInventory(request);
		assertNotNull(inventory.getResponseModel());
		assertNull(inventory.getError());
	}
	
	private List<Inventory> getMockedInventory(String methodType) throws ParseException{
		SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");
		
		Inventory i1 = new Inventory(); Inventory i2 = new Inventory();
		i1.setInventoryId(1); i1.setPrice(500.0); i1.setStayDate(myFormat.parse("01-02-2021")); i1.setQuantity(5); 
		i2.setInventoryId(1); i2.setPrice(500.0); i2.setStayDate(myFormat.parse("02-02-2021")); i2.setQuantity(5); 
		Room room1 = new Room();
		room1.setRoomId(1);
		i1.setRoom(room1); i2.setRoom(room1);
		Inventory i3 = new Inventory(); Inventory i4 = new Inventory();
		i3.setInventoryId(1); i3.setPrice(500.0); i3.setStayDate(myFormat.parse("01-02-2021")); i3.setQuantity(5); 
		i4.setInventoryId(1); i4.setPrice(500.0); i4.setStayDate(myFormat.parse("02-02-2021")); i4.setQuantity(5); 
		Room room2 = new Room();
		room2.setRoomId(1);
		i3.setRoom(room2); i4.setRoom(room2);
		
		switch(methodType) {
		case "getInventoryDetails":
			return Arrays.asList(i1, i2);
		case "updateInventory":
			return Arrays.asList(i3, i4);
		default:
			return Arrays.asList(i1, i2, i3, i4); //get All Hotel
	} 
	}

}
