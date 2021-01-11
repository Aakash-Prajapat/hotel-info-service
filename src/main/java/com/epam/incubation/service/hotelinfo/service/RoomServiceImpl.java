package com.epam.incubation.service.hotelinfo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.incubation.service.hotelinfo.entity.Room;
import com.epam.incubation.service.hotelinfo.repository.RoomRepository;

@Service
public class RoomServiceImpl implements RoomService {

	@Autowired
	RoomRepository roomRepo;
	
	public List<Room> getAllRooms(){
		return (List<Room>) roomRepo.findAll();
	}
}
