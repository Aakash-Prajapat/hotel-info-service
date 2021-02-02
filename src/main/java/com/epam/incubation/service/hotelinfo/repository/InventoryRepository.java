package com.epam.incubation.service.hotelinfo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.epam.incubation.service.hotelinfo.entity.Inventory;

@Repository
public interface InventoryRepository extends CrudRepository<Inventory, Integer> {
	@Query(value = "SELECT * FROM Inventory i WHERE room_id = :roomId AND stay_date >= :checkInDate AND stay_date <= :checkOutDate", nativeQuery = true)
	public List<Inventory> getInventoryByDuration(@Param("roomId") Integer roomId, @Param("checkInDate") Date checkInDate, @Param("checkOutDate") Date checkOutDate);
}
