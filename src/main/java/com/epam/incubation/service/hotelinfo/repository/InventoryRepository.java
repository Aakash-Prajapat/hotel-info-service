package com.epam.incubation.service.hotelinfo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.epam.incubation.service.hotelinfo.entity.Inventory;

@Repository
public interface InventoryRepository extends CrudRepository<Inventory, Integer> {

	@Query(value = "SELECT * INVENTORY I where I.roomId = :roomId AND I.STAY_DATE => :checkInDate AND I.STAY_DATE <= :checkOutDate", nativeQuery = true)
	public List<Inventory> getInventoryByDuration(Date checkInDate, Date checkOutDate, Integer roomId);

}
