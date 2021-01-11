package com.epam.incubation.service.hotelinfo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.epam.incubation.service.hotelinfo.entity.Hotel;

@Repository
public interface HotelInformationRepository extends CrudRepository<Hotel, Integer>{

	List<Hotel> findByAddressCity(String city); 
}
