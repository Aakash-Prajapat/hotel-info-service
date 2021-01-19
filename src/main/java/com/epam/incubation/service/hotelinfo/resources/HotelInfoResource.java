package com.epam.incubation.service.hotelinfo.resources;

import java.text.ParseException;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.epam.incubation.service.hotelinfo.datamodel.HotelDataModel;
import com.epam.incubation.service.hotelinfo.datamodel.InventoryDetailsResponseModel;
import com.epam.incubation.service.hotelinfo.datamodel.InventoryResponseModel;
import com.epam.incubation.service.hotelinfo.datamodel.RoomDataModel;
import com.epam.incubation.service.hotelinfo.requestmodel.InventoryRequestModel;

public interface HotelInfoResource {

	public List<HotelDataModel> getAllHotels();

	public HotelDataModel getById(Integer id);

	public List<HotelDataModel> getByCity(String name);

	public ResponseEntity<HotelDataModel> disableHotel(Integer id);

	public ResponseEntity<InventoryDetailsResponseModel> getInventoryDetails(InventoryRequestModel model) throws ParseException;

	public ResponseEntity<InventoryDetailsResponseModel> updateInventory(InventoryRequestModel model) throws ParseException;

	public List<RoomDataModel> getAllRoomsByHotelId(Integer roomId);

	public RoomDataModel getRoomDetails(Integer roomId);

	public RoomDataModel disableRoom(Integer roomId);
}
