package com.epam.incubation.service.hotelinfo.resources;

import java.text.ParseException;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.epam.incubation.service.hotelinfo.datamodel.HotelDataModel;
import com.epam.incubation.service.hotelinfo.datamodel.InventoryDetailsResponseModel;
import com.epam.incubation.service.hotelinfo.requestmodel.InventoryRequestModel;
import com.epam.incubation.service.hotelinfo.response.HotelApiResponse;
import com.epam.incubation.service.hotelinfo.response.HotelResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Hotel Controller api layer to fetch, update and add the hotels
 */
@Api(value = "Hotel Information Service")
public interface HotelInfoResource {

	/**
	 * Responsible to return the all hotel information.
	 * @return the List of HotelDataModel, Holds all hotel information.
	 * 
	 */
	@ApiOperation(value = "All Get Hotel Information")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	public List<HotelDataModel> getAllHotels();

	/**
	 * Responsible to return the Hotel data.
	 * 
	 * @param guestId , Id to which hotel information get fetched.
	 * @return HotelDataModel, Holds hotel information.
	 */
	@ApiOperation(value = "Get Hotel Information by Id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	public HotelDataModel getById(Integer id);

	/**
	 * Responsible to return the Hotel by city.
	 * 
	 * @param String city name , City name to fetch all hotels information.
	 * @return list of HotelDataModel, Holds hotel information.
	 */
	@ApiOperation(value = "Get Hotel Information by City")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	public HotelApiResponse<HotelResponse> getByCity(String name);

	/**
	 * Responsible to return the Hotel data.
	 * 
	 * @param Integer , Id to which hotel will be disabled.
	 * @return HotelDataModel, Holds hotel information.
	 */
	@ApiOperation(value = "Disable hotel by Id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully updated"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	public ResponseEntity<HotelDataModel> disableHotel(Integer id);

	/**
	 * Responsible to return the inventory data.
	 * 
	 * @param InventoryRequestModel , based on request model, inventory data needs to be fetched.
	 * @return InventoryDetailsResponseModel, Holds inventory information.
	 * throws ParseException
	 */
	@ApiOperation(value = "Get Inventories based on duration")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	public ResponseEntity<InventoryDetailsResponseModel> getInventoryDetails(
			@ApiParam(value = "Inventory request model to get inventoryDetails", required = true) InventoryRequestModel model)
			throws ParseException;

	/**
	 * Responsible to return the inventory data.
	 * 
	 * @param InventoryRequestModel , based on request model, inventory data needs to be updated.
	 * @return InventoryDetailsResponseModel, Holds inventory information.
	 * throws ParseException
	 */
	@ApiOperation(value = "Update Inventories based on duration")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully update"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	public ResponseEntity<InventoryDetailsResponseModel> updateInventory(
			@ApiParam(value = "Inventory request model to get inventoryDetails", required = true) InventoryRequestModel model)
			throws ParseException;

}
