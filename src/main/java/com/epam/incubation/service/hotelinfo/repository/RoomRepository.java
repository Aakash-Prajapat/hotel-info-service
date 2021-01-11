package com.epam.incubation.service.hotelinfo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.epam.incubation.service.hotelinfo.entity.Room;

@Repository
public interface RoomRepository extends CrudRepository<Room, Integer> {

}
