package com.epam.incubation.service.hotelinfo.resources;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.epam.incubation.service.hotelinfo.datamodel.HotelDataModel;
import com.epam.incubation.service.hotelinfo.datamodel.InventoryRequestModel;
import com.epam.incubation.service.hotelinfo.datamodel.InventoryResponseModel;
import com.epam.incubation.service.hotelinfo.datamodel.RoomDataModel;

public interface HotelInfoResource {

	public List<HotelDataModel> getAllHotels();

	public HotelDataModel getById(Integer id);

	public List<HotelDataModel> getByCity(String name);

	public ResponseEntity<HotelDataModel> disableHotel(Integer id);

	public List<InventoryResponseModel> getInventoryDetails(InventoryRequestModel model);

	public List<InventoryResponseModel> updateInventory(InventoryRequestModel model);

	public List<RoomDataModel> getAllRoomsByHotelId(Integer roomId);

	public RoomDataModel getRoomDetails(Integer roomId);

	public RoomDataModel disableRoom(Integer roomId);
}
