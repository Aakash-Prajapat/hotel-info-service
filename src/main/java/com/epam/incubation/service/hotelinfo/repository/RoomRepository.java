package com.epam.incubation.service.hotelinfo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.epam.incubation.service.hotelinfo.entity.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

	@Query("SELECT r FROM Room r where hotel_id = :hotelId")
	List<Room> getAllRoomsByHotelId(@Param("hotelId") Integer hotelId);
}
