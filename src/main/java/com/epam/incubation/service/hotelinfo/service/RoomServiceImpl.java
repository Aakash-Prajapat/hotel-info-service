package com.epam.incubation.service.hotelinfo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.incubation.service.hotelinfo.datamodel.RoomDataModel;
import com.epam.incubation.service.hotelinfo.entity.Room;
import com.epam.incubation.service.hotelinfo.exception.RecordNotFoundException;
import com.epam.incubation.service.hotelinfo.repository.RoomRepository;

@Service
public class RoomServiceImpl implements RoomService {

	@Autowired
	RoomRepository roomRepo;
	private Boolean activeHotels = Boolean.FALSE;

	public List<RoomDataModel> getAllRooms() {
		List<Room> rooms = (List<Room>) roomRepo.findAll();
		if (rooms.isEmpty())
			throw new RecordNotFoundException("No Hotels found with");

		return convertHotelDomainModelToDataModel(rooms, activeHotels);
	}

	public RoomDataModel getRoomDetails(Integer id) {
		Optional<Room> room = roomRepo.findById(id);
		Optional<RoomDataModel> roomDataModel = Optional.ofNullable(null);
		if (room.isPresent()) {
			roomDataModel = Optional.of(convertHotelDomainModelToDataModel(room.get()));
		} else
			throw new RecordNotFoundException("Room not present with " + id);

		return roomDataModel.get();

	}

	public RoomDataModel disableRoom(Integer roomId) {
		Optional<Room> room = roomRepo.findById(roomId);
		if (room.isPresent()) {
			room.get().setStatus(false);
			Room updatedRoom = roomRepo.save(room.get());
			return convertHotelDomainModelToDataModel(updatedRoom);
		} else {
			throw new RecordNotFoundException("Hotel not found with " + roomId);
		}
	}

	@Override
	public List<RoomDataModel> getAllRoomsByHotelId(Integer hotelId) {
		List<Room> rooms = (List<Room>) roomRepo.getAllRoomsByHotelId(hotelId);
		if (rooms.isEmpty())
			throw new RecordNotFoundException("No rooms found with" + hotelId);

		return convertHotelDomainModelToDataModel(rooms, activeHotels);
	}

	private RoomDataModel convertHotelDomainModelToDataModel(Room room) {
		return new RoomDataModel(room);
	}

	private List<RoomDataModel> convertHotelDomainModelToDataModel(List<Room> rooms, boolean activeRooms) {
		return activeHotels ? rooms.stream().filter(r -> r.isStatus() == Boolean.TRUE).map(RoomDataModel::new)
				.collect(Collectors.toList()) : rooms.stream().map(RoomDataModel::new).collect(Collectors.toList());
	}

}
