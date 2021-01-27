package com.epam.incubation.service.hotelinfo.resources;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.lenient;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.epam.incubation.service.hotelinfo.datamodel.HotelDataModel;
import com.epam.incubation.service.hotelinfo.datamodel.InventoryDataModel;
import com.epam.incubation.service.hotelinfo.datamodel.InventoryDetailsResponseModel;
import com.epam.incubation.service.hotelinfo.datamodel.InventoryResponseModel;
import com.epam.incubation.service.hotelinfo.datamodel.RoomDataModel;
import com.epam.incubation.service.hotelinfo.exception.RecordNotFoundException;
import com.epam.incubation.service.hotelinfo.requestmodel.InventoryRequestModel;
import com.epam.incubation.service.hotelinfo.service.HotelInformationServiceImpl;
import com.epam.incubation.service.hotelinfo.service.InventoryServiceImpl;
import com.epam.incubation.service.hotelinfo.util.JwtUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class HotelInfoResourceImplTest {

	private MockMvc mockMvc;

	@Mock
	private ObjectMapper objectMapper;

	@InjectMocks
	HotelInfoResourceImpl hotelInfoResourceImpl;

	@Mock
	HotelInformationServiceImpl service;

	@Mock
	InventoryServiceImpl inventoryService;

	@Autowired
	FilterChainProxy springSecurityFilterChain;
	
	@Autowired
	JwtUtil jwtUtil;

	@BeforeEach
	void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(hotelInfoResourceImpl).setControllerAdvice(new RestExceptionHandler())
				.apply(springSecurity(springSecurityFilterChain)).build();
	}

	@Test
	void getHotelById_ShouldReturnHotel() throws Exception {
		HotelDataModel hotelDataModel = getMockedHotels("getById").get(0);
		given(service.getHotelById(1)).willReturn(hotelDataModel);
		mockMvc.perform(
				MockMvcRequestBuilders.get("/hotelInfo/1").with(user("Guest").password("password").roles("GUEST")))
				.andExpect(status().isOk()).andDo(MockMvcResultHandlers.print())
				.andExpect(jsonPath("$.hotelName").value("Sayaji")).andExpect(jsonPath("$.rooms", hasSize(1)));
	}
	
	@Test
	void getHotelById_notFound() throws Exception {
		given(service.getHotelById(1)).willThrow(new RecordNotFoundException("Record Not Found with 1"));

		mockMvc.perform(MockMvcRequestBuilders.get("/hotelInfo/1")
				.with(user("Guest").password("password").roles("GUEST"))).andExpect(status().isNotFound());
	}

	@Test
	void getHotelsByCity_ShouldReturnAllHotels() throws Exception {
		List<HotelDataModel> hotelDataModel = getMockedHotels("findByCity");
		given(service.findByCity("Indore")).willReturn(hotelDataModel);
		mockMvc.perform(MockMvcRequestBuilders.get("/hotelInfo/getByCity/Indore")
				.with(user("Guest").password("password").roles("GUEST"))).andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print()).andExpect(jsonPath("$[0].hotelName").value("Sayaji"))
				.andExpect(jsonPath("$[1].rooms", hasSize(1)));
	}

	@Test
	void getAllHotels_ShouldReturnAllHotels() throws Exception {
		List<HotelDataModel> hotelDataModel = getMockedHotels("getAll");
		given(service.getAllHotels()).willReturn(hotelDataModel);
		mockMvc.perform(
				MockMvcRequestBuilders.get("/hotelInfo").with(user("Guest").password("password").roles("GUEST")))
				.andExpect(status().isOk()).andDo(MockMvcResultHandlers.print())
				.andExpect(jsonPath("$[0].hotelName").value("Sayaji")).andExpect(jsonPath("$[1].rooms", hasSize(1)));
	}

	@Test
	void getAllHotels_ShouldThrowException() throws Exception {
		List<HotelDataModel> hotelDataModel = getMockedHotels("getAll");
		lenient().doReturn(hotelDataModel).when(service).getAllHotels();
		mockMvc.perform(MockMvcRequestBuilders.get("/hotelInfo")).andExpect(status().isForbidden())
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	void disableHotel_ShouldDisableHotel() throws Exception {
		HotelDataModel hotelDataModel = getMockedHotels("disableHotel").get(0);
		given(service.disableHotel(1)).willReturn(hotelDataModel);
		mockMvc.perform(
				MockMvcRequestBuilders.put("/hotelInfo/1").with(user("Guest").password("password").roles("GUEST")))
				.andExpect(status().isOk()).andDo(MockMvcResultHandlers.print())
				.andExpect(jsonPath("$.hotelName").value("Sayaji")).andExpect(jsonPath("$.rooms", hasSize(1)));
	}

	@Test
	void getInventory_ShouldReturnInventories() throws Exception {
		SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");
		InventoryRequestModel model = new InventoryRequestModel();
		model.setCheckInDate(myFormat.parse("01-02-2021"));
		model.setHotelId(1);
		model.setOperation("Get");
		model.setCheckOutDate(myFormat.parse("01-02-2021"));
		model.setRoomId(1);

		InventoryResponseModel response = new InventoryResponseModel();
		response.setHotelId(1);
		response.setInventoryId(1);
		response.setRoomId(1);
		response.setRateRoom(500.0);
		response.setStayDate(myFormat.parse("01-02-2021"));

		InventoryDetailsResponseModel responseModel = new InventoryDetailsResponseModel();
		responseModel.setResponseModel(Arrays.asList(response));

		InventoryRequestModel model1 = new InventoryRequestModel();
		model1.setCheckInDate(myFormat.parse("01-02-2021"));
		model1.setHotelId(1);
		model1.setOperation("Get");
		model1.setCheckOutDate(myFormat.parse("01-02-2021"));
		model1.setRoomId(1);

		// Mockito.when(inventoryService).getInventoryDetails(model1)).thenReturn(responseModel);

		lenient().doReturn(responseModel).when(inventoryService).getInventoryDetails(ArgumentMatchers.eq(model));
		mockMvc.perform(MockMvcRequestBuilders.post("/hotelInfo/inventoryService").content(asJsonString(model1))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.with(user("Guest").password("password").roles("GUEST"))).andExpect(status().isOk());
	}

	@Test
	void getInventory_ShouldThrowException() throws Exception {
		SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");
		InventoryRequestModel model = new InventoryRequestModel();
		model.setCheckInDate(myFormat.parse("01-02-2021"));
		model.setHotelId(1);
		model.setCheckOutDate(myFormat.parse("01-02-2021"));
		model.setRoomId(1);

		InventoryResponseModel response = new InventoryResponseModel();
		response.setHotelId(1);
		response.setInventoryId(1);
		response.setRoomId(1);
		response.setRateRoom(500.0);
		response.setStayDate(myFormat.parse("01-02-2021"));

		InventoryDetailsResponseModel responseModel = new InventoryDetailsResponseModel();
		responseModel.setResponseModel(Arrays.asList(response));

		InventoryRequestModel model1 = new InventoryRequestModel();
		model1.setCheckInDate(myFormat.parse("01-02-2021"));
		model1.setHotelId(1);
		model1.setCheckOutDate(myFormat.parse("01-02-2021"));
		model1.setRoomId(1);

		// Mockito.when(inventoryService).getInventoryDetails(model1)).thenReturn(responseModel);
		lenient().doReturn(responseModel).when(inventoryService).getInventoryDetails(ArgumentMatchers.eq(model1));
		mockMvc.perform(MockMvcRequestBuilders.post("/hotelInfo/inventoryService").content(asJsonString(model1))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.with(user("Guest").password("password").roles("GUEST"))).andExpect(status().isBadRequest())
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	void getInventory_ShouldThrowBedRequestException() throws Exception {
		SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");
		InventoryRequestModel model = new InventoryRequestModel();
		model.setCheckInDate(myFormat.parse("01-02-2021"));
		model.setHotelId(1);
		model.setCheckOutDate(myFormat.parse("01-02-2021"));
		model.setRoomId(1);

		InventoryResponseModel response = new InventoryResponseModel();
		response.setHotelId(1);
		response.setInventoryId(1);
		response.setRoomId(1);
		response.setRateRoom(500.0);
		response.setStayDate(myFormat.parse("01-02-2021"));

		InventoryDetailsResponseModel responseModel = new InventoryDetailsResponseModel();
		responseModel.setResponseModel(Arrays.asList(response));

		InventoryRequestModel model1 = new InventoryRequestModel();
		model1.setCheckInDate(myFormat.parse("01-02-2021"));
		model1.setHotelId(1);
		model1.setCheckOutDate(myFormat.parse("01-02-2021"));
		model1.setRoomId(1);

		lenient().doReturn(responseModel).when(inventoryService).getInventoryDetails(ArgumentMatchers.eq(model1));
		mockMvc.perform(MockMvcRequestBuilders.post("/hotelInfo/inventoryService")
				.with(user("Guest").password("password").roles("GUEST"))).andExpect(status().isBadRequest())
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	void updateInventory_ShouldUpdateAndReturnInventory() throws Exception {
		SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");
		InventoryRequestModel model = new InventoryRequestModel();
		model.setCheckInDate(myFormat.parse("01-02-2021"));
		model.setHotelId(1);
		model.setOperation("Get");
		model.setCheckOutDate(myFormat.parse("01-02-2021"));
		model.setRoomId(1);

		InventoryResponseModel response = new InventoryResponseModel();
		response.setHotelId(1);
		response.setInventoryId(1);
		response.setRoomId(1);
		response.setRateRoom(500.0);
		response.setStayDate(myFormat.parse("01-02-2021"));

		InventoryDetailsResponseModel responseModel = new InventoryDetailsResponseModel();
		responseModel.setResponseModel(Arrays.asList(response));

		InventoryRequestModel model1 = new InventoryRequestModel();
		model1.setCheckInDate(myFormat.parse("01-02-2021"));
		model1.setHotelId(1);
		model1.setOperation("Cancel");
		model1.setCheckOutDate(myFormat.parse("01-02-2021"));
		model1.setRoomId(1);

		UserDetails userDetails = new User("Guest", "test", Arrays.asList(() -> "ROLE_GUEST"));
		String token = jwtUtil.generateToken(userDetails);
		lenient().doReturn(responseModel).when(inventoryService).getInventoryDetails(ArgumentMatchers.eq(model));
		mockMvc.perform(MockMvcRequestBuilders.put("/hotelInfo/inventoryService").header("Authorization", "Bearer "+token).content(asJsonString(model1))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.with(user("Guest").password("password").roles("GUEST"))).andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}

	private List<HotelDataModel> getMockedHotels(String methodType) throws ParseException {
		SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");
		HotelDataModel hotelDataModel1 = new HotelDataModel();
		hotelDataModel1.setCity("Indore");
		hotelDataModel1.setHotelName("Sayaji");
		hotelDataModel1.setStatus(true);
		RoomDataModel rm1 = new RoomDataModel();
		rm1.setStatus(true);
		rm1.setName("Delux");
		InventoryDataModel i1 = new InventoryDataModel();
		i1.setPrice(500.0);
		i1.setQuantity(2);
		i1.setStayDate(myFormat.parse("01-02-2021"));
		hotelDataModel1.setRooms(Arrays.asList(rm1));

		HotelDataModel hotelDataModel2 = new HotelDataModel();
		hotelDataModel2.setCity("Indore");
		hotelDataModel2.setHotelName("Sayaji");
		hotelDataModel2.setStatus(true);
		RoomDataModel rm2 = new RoomDataModel();
		rm2.setStatus(true);
		rm2.setName("Delux"); rm2.setDescription("Delux");
		InventoryDataModel i2 = new InventoryDataModel();
		i2.setPrice(500.0);
		i2.setQuantity(2);
		i2.setStayDate(myFormat.parse("01-02-2021"));
		rm2.setInventories(Arrays.asList(i2));
		hotelDataModel2.setRooms(Arrays.asList(rm2));
		switch (methodType) {
		case "getById":
			return Arrays.asList(hotelDataModel1);
		case "findByCity":
			return Arrays.asList(hotelDataModel1, hotelDataModel1);
		case "disableHotel":
			return Arrays.asList(hotelDataModel1);
		default:
			return Arrays.asList(hotelDataModel1, hotelDataModel2); // get All Hotels
		}
	}

	static String asJsonString(final Object obj) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(obj);
	}

}
