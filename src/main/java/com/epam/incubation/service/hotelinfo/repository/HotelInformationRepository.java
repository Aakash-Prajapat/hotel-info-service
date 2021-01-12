package com.epam.incubation.service.hotelinfo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epam.incubation.service.hotelinfo.entity.Hotel;

@Repository
public interface HotelInformationRepository extends JpaRepository<Hotel, Integer>{

	List<Hotel> findByAddressCity(String city); 
}
