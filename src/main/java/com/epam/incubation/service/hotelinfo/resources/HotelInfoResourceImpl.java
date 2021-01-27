package com.epam.incubation.service.hotelinfo.resources;

import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.incubation.service.hotelinfo.datamodel.HotelDataModel;
import com.epam.incubation.service.hotelinfo.datamodel.InventoryDetailsResponseModel;
import com.epam.incubation.service.hotelinfo.requestmodel.InventoryRequestModel;
import com.epam.incubation.service.hotelinfo.service.HotelInformationServiceImpl;
import com.epam.incubation.service.hotelinfo.service.InventoryServiceImpl;

@RestController
@RequestMapping("/hotelInfo")
public class HotelInfoResourceImpl implements HotelInfoResource {

	private final Logger logger = LoggerFactory.getLogger(HotelInfoResourceImpl.class);

	@Autowired
	HotelInformationServiceImpl hotelService;
	@Autowired
	InventoryServiceImpl inventoryService;

	@GetMapping
	@PreAuthorize("hasRole('GUEST')")
	public List<HotelDataModel> getAllHotels() {
		logger.info("Calling service to Fetch All hotels");
		return hotelService.getAllHotels();
	}

	@GetMapping("/getByCity/{name}")
	@PreAuthorize("hasRole('GUEST')")
	public List<HotelDataModel> getByCity(@PathVariable(value = "name") String name) {
		logger.info("Calling service to Fetch All hotels by city");
		return hotelService.findByCity(name);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('GUEST')")
	public HotelDataModel getById(@PathVariable(value = "id") Integer id) {
		logger.info("Calling service to hotel by id");
		return hotelService.getHotelById(id);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<HotelDataModel> disableHotel(@PathVariable(value = "id") Integer id) {
		logger.info("Calling service to disable hotel by id");
		HotelDataModel hotel = hotelService.disableHotel(id);
		return new ResponseEntity<>(hotel, HttpStatus.OK);
	}

	@PostMapping("/inventoryService")
	@PreAuthorize("hasRole('GUEST')")
	public ResponseEntity<InventoryDetailsResponseModel> getInventoryDetails(
			@Valid @RequestBody InventoryRequestModel model) throws ParseException {
		logger.info("Calling service to fetch the inventory details");
		InventoryDetailsResponseModel inventoryDetails = inventoryService.getInventoryDetails(model);
		return new ResponseEntity<>(inventoryDetails, HttpStatus.OK);
	}

	@PutMapping("/inventoryService")
	@PreAuthorize("hasRole('GUEST')")
	public ResponseEntity<InventoryDetailsResponseModel> updateInventory(
			@Valid @RequestBody InventoryRequestModel model) throws ParseException {
		logger.info("Calling service to update the inventory");
		InventoryDetailsResponseModel inventoryDetails = inventoryService.updateInventory(model);
		return new ResponseEntity<>(inventoryDetails, HttpStatus.OK);
	}
}
