package com.epam.incubation.service.hotelinfo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.incubation.service.hotelinfo.datamodel.HotelDataModel;
import com.epam.incubation.service.hotelinfo.exception.RecordNotFoundException;
import com.epam.incubation.service.hotelinfo.service.HotelInformationServiceImpl;
import com.epam.incubation.service.hotelinfo.service.RoomServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

	@GetMapping("/hotelInfo")
	@ApiOperation(value = "All Get Hotel Information")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Successfully retrieved"),
		    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
		})
	public List<HotelDataModel> getHotelsInfo(){
		return hotelService.getAllHotels();
	}
	
	@GetMapping("/hotelInfo/getByCity/{name}")
	@ApiOperation(value = "Get Hotel Information by City")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Successfully retrieved"),
		    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
		})
	public List<HotelDataModel> getByCity(@PathVariable(value = "name") String name) {
		List<HotelDataModel> hotels = hotelService.findByCity(name);
		if(null == hotels) {
			throw new RecordNotFoundException("No Hotels found with " + name);
		} else
			return hotels;
	}
	
	@GetMapping("/hotelInfo/{id}")
	@ApiOperation(value = "Get Hotel Information by Id")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Successfully retrieved"),
		    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
		})
	public HotelDataModel getHotelById(@PathVariable(value = "id") Integer id) {
		return hotelService.getHotelById(id).orElseThrow(() -> new RecordNotFoundException("Hotel Not found by " + id));
	}
	
	@PutMapping("/hotelInfo/{id}")
	public ResponseEntity<HotelDataModel> disableHotel(@PathVariable(value = "id") Integer id) {
		HotelDataModel hotel = hotelService.disableHotel(id);
		return new ResponseEntity<HotelDataModel>(hotel, HttpStatus.OK);
	}	
}
