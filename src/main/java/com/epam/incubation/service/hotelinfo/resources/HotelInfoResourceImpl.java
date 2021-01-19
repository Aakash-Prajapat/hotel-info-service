package com.epam.incubation.service.hotelinfo.resources;

import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.incubation.service.hotelinfo.datamodel.HotelDataModel;
import com.epam.incubation.service.hotelinfo.datamodel.InventoryDetailsResponseModel;
import com.epam.incubation.service.hotelinfo.datamodel.RoomDataModel;
import com.epam.incubation.service.hotelinfo.requestmodel.InventoryRequestModel;
import com.epam.incubation.service.hotelinfo.service.HotelInformationServiceImpl;
import com.epam.incubation.service.hotelinfo.service.InventoryServiceImpl;
import com.epam.incubation.service.hotelinfo.service.RoomServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1")
@Api(value = "Hotel Information Service")
public class HotelInfoResourceImpl implements HotelInfoResource {

	@Autowired
	HotelInformationServiceImpl hotelService;
	@Autowired
	RoomServiceImpl roomService;
	@Autowired
	InventoryServiceImpl inventoryService;

	@GetMapping("/hotelInfo")
	@ApiOperation(value = "All Get Hotel Information")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	public List<HotelDataModel> getAllHotels() {
		return hotelService.getAllHotels();
	}

	@GetMapping("/hotelInfo/getByCity/{name}")
	@ApiOperation(value = "Get Hotel Information by City")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	public List<HotelDataModel> getByCity(@PathVariable(value = "name") String name) {
		return hotelService.findByCity(name);
	}

	@GetMapping("/hotelInfo/{id}")
	@ApiOperation(value = "Get Hotel Information by Id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	public HotelDataModel getById(@PathVariable(value = "id") Integer id) {
		return hotelService.getHotelById(id);
	}

	@PutMapping("/hotelInfo/{id}")
	@ApiOperation(value = "Disable hotel by Id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully updated"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	public ResponseEntity<HotelDataModel> disableHotel(@PathVariable(value = "id") Integer id) {
		HotelDataModel hotel = hotelService.disableHotel(id);
		return new ResponseEntity<>(hotel, HttpStatus.OK);
	}

	@GetMapping("/hotelInfo/roomService/{id}")
	@ApiOperation(value = "Get Room details by Id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	public RoomDataModel getRoomDetails(@PathVariable(value = "id") Integer roomId) {
		return roomService.getRoomDetails(roomId);
	}

	@PutMapping("/hotelInfo/roomService/{id}")
	@ApiOperation(value = "Disable room by Id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully updated"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	public RoomDataModel disableRoom(Integer roomId) {
		return roomService.disableRoom(roomId);
	}

	@GetMapping("/hotelInfo/roomService/allRoomsByHotelId/{id}")
	@ApiOperation(value = "Get All Rooms by Hotel id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	public List<RoomDataModel> getAllRoomsByHotelId(@PathVariable(value = "id") Integer hotelId) {
		return roomService.getAllRoomsByHotelId(hotelId);
	}

	@PostMapping("/hotelInfo/inventoryService")
	@ApiOperation(value = "Get Inventories based on duration")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	public ResponseEntity<InventoryDetailsResponseModel> getInventoryDetails(
			@ApiParam(value = "Inventory request model to get inventoryDetails", required = true) @Valid @RequestBody InventoryRequestModel model) throws ParseException {
		InventoryDetailsResponseModel inventoryDetails = inventoryService.getInventoryDetails(model);
			return new ResponseEntity<InventoryDetailsResponseModel>(inventoryDetails, HttpStatus.OK);
	}

	@PutMapping("/hotelInfo/inventoryService")
	@ApiOperation(value = "Update Inventories based on duration")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully update"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	public ResponseEntity<InventoryDetailsResponseModel> updateInventory(
			@ApiParam(value = "Inventory request model to get inventoryDetails", required = true) @Valid @RequestBody InventoryRequestModel model) throws ParseException {
		InventoryDetailsResponseModel inventoryDetails = inventoryService.updateInventory(model);
		return new ResponseEntity<InventoryDetailsResponseModel>(inventoryDetails, HttpStatus.OK);
	}
}
