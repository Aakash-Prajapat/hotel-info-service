package com.epam.incubation.service.hotelinfo.service;

import java.util.List;

import com.epam.incubation.service.hotelinfo.datamodel.RoomDataModel;

public interface RoomService {

	public List<RoomDataModel> getAllRooms();

	public List<RoomDataModel> getAllRoomsByHotelId(Integer roomId);

	public RoomDataModel getRoomDetails(Integer roomId);

	public RoomDataModel disableRoom(Integer roomId);

}
