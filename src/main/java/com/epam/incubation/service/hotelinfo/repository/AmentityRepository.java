package com.epam.incubation.service.hotelinfo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.epam.incubation.service.hotelinfo.entity.Amenity;

@Repository
public interface AmentityRepository extends CrudRepository<Amenity, Integer>{

}
