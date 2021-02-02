package com.epam.incubation.service.hotelinfo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.incubation.service.hotelinfo.datamodel.HotelDataModel;
import com.epam.incubation.service.hotelinfo.entity.Address;
import com.epam.incubation.service.hotelinfo.entity.Amenity;
import com.epam.incubation.service.hotelinfo.entity.Hotel;
import com.epam.incubation.service.hotelinfo.entity.Inventory;
import com.epam.incubation.service.hotelinfo.entity.Room;
import com.epam.incubation.service.hotelinfo.exception.RecordNotFoundException;
import com.epam.incubation.service.hotelinfo.repository.HotelInformationRepository;
import com.epam.incubation.service.hotelinfo.response.HotelApiResponse;
import com.epam.incubation.service.hotelinfo.response.HotelResponse;

@ExtendWith(MockitoExtension.class)
class HotelInformationServiceTest {

	@Mock
	HotelInformationRepository hotelRepository;
	
	@InjectMocks
	HotelInformationServiceImpl service;

	@Test
	void GetAllHotels_ShouldReturnAllHotels() throws ParseException {
		List<Hotel> hotels = getMockedHotel("getAllHotels");
		given(hotelRepository.findAll()).willReturn(hotels);
		List<HotelDataModel> hotel = service.getAllHotels();
		assertEquals("Sayaji", hotel.get(0).getHotelName());
		
	}
	
	@Test
	void GetAllHotels_NotFound() throws ParseException {
		given(hotelRepository.findAll()).willReturn(new ArrayList<>());
		assertThrows(RecordNotFoundException.class, () -> service.getAllHotels());
		
	}
	
	@Test
	void GetHotelById_ShouldReturnHotel() throws ParseException {
		Optional<Hotel> hotel = Optional.ofNullable(getMockedHotel("getHotelById").get(0));
		given(hotelRepository.findById(1)).willReturn(hotel);
		HotelDataModel hotelDataModel = service.getHotelById(1);
		assertEquals("Sayaji", hotelDataModel.getHotelName());
	}
	
	@Test
	void GetHotelById_NotFound() throws ParseException {
		given(hotelRepository.findById(1)).willReturn(Optional.ofNullable(null));
		assertThrows(RecordNotFoundException.class, () -> service.getHotelById(1));
		
	}
	
	@Test
	void findByCity_ShouldReturnHotels() throws ParseException {
		List<Hotel> hotel = getMockedHotel("findByCity");
		given(hotelRepository.findByAddressCity("Indore")).willReturn(hotel);
		HotelApiResponse<HotelResponse> response = service.findByCity("Indore");
		assertEquals("Sayaji", response.getData().getHotels().get(0).getHotelName());
	}
	
	@Test
	void disableHotel_ShouldReturnHotel() throws ParseException {
		Optional<Hotel> hotel = Optional.ofNullable(getMockedHotel("disableHotel").get(0));
		given(hotelRepository.findById(1)).willReturn(hotel);
		given(hotelRepository.save(any(Hotel.class))).willReturn(hotel.get());
		hotel.get().setStatus(false);
		HotelDataModel hotelDataModel = service.disableHotel(1);
		assertEquals(false, hotelDataModel.getStatus());
	}
	
	@Test
	void disableHotel_NotFound() throws ParseException {
		given(hotelRepository.findById(1)).willReturn(Optional.ofNullable(null));
		assertThrows(RecordNotFoundException.class, () -> service.disableHotel(1));
		
	}
	
	private List<Hotel> getMockedHotel(String methodType) throws ParseException{
		
		SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");
		//1. Hotel
		Address address1 = new Address();
		address1.setAddressLine1("1"); address1.setAddressLine2("Nagar Road"); address1.setCity("Indore"); address1.setState("MP");
		address1.setZipcode(422323); address1.setCountry("India");
		
		Address address2 = new Address();
		address2.setAddressLine1("1"); address2.setAddressLine2("Nagar Road"); address2.setCity("Indore"); address2.setState("MP");
		address2.setZipcode(422323); address2.setCountry("India");
		
		Inventory i1 = new Inventory(); Inventory i2 = new Inventory();
		i1.setInventoryId(1); i1.setPrice(500.0); i1.setStayDate(myFormat.parse("01-02-2021")); i1.setQuantity(5); 
		i2.setInventoryId(1); i2.setPrice(500.0); i2.setStayDate(myFormat.parse("02-02-2021")); i1.setQuantity(5); 
		
		Amenity amenity1= new Amenity(); Amenity amenity2 = new Amenity(); 
		amenity1.setAmenityId(1); amenity1.setAmenityName("TV"); amenity1.setAmenityDescription("TV");
		amenity2.setAmenityId(1); amenity2.setAmenityName("TV"); amenity2.setAmenityDescription("TV");
		Room room1 = new Room();
		room1.setRoomId(1); room1.setStatus(true); room1.setName("Delux"); room1.setDescription("Delux"); 
		room1.setInventories(Arrays.asList(i1)); i1.setRoom(room1); i2.setRoom(room1);
		
		Hotel hotel1 = new Hotel();
		
		hotel1.setHotelId(1);
		hotel1.setAddress(address1);
		hotel1.setHotelDescription("Great hotel"); hotel1.setHotelName("Sayaji"); 
		hotel1.setAmenities(Arrays.asList(amenity1, amenity2)); hotel1.setRating(5); hotel1.setStatus(true);
		hotel1.setRooms(Arrays.asList(room1));

		Inventory i3 = new Inventory(); Inventory i4 = new Inventory();
		i3.setInventoryId(1); i3.setPrice(500.0); i3.setStayDate(myFormat.parse("01-02-2021")); i3.setQuantity(5); 
		i4.setInventoryId(1); i4.setPrice(500.0); i4.setStayDate(myFormat.parse("02-02-2021")); i4.setQuantity(5); 
		
		
		Room room2 = new Room();
		room2.setRoomId(1); room2.setStatus(true); room2.setName("Delux"); room2.setDescription("Delux"); 
		room2.setInventories(Arrays.asList(i1)); i3.setRoom(room2); i4.setRoom(room2);
		
		Hotel hotel2 = new Hotel();
		
		hotel2.setHotelId(2);
		hotel2.setAddress(address2);
		hotel2.setHotelDescription("Great Redisson hotel"); hotel2.setHotelName("Redisson"); 
		hotel2.setAmenities(Arrays.asList(amenity1, amenity2)); hotel2.setRating(5); hotel1.setStatus(true);
		hotel2.setRooms(Arrays.asList(room2));

		
		switch(methodType) {
			case "getHotelById":
				return Arrays.asList(hotel1);
			case "findByCity":
				return Arrays.asList(hotel1, hotel2);
			case "disableHotel":
				return Arrays.asList(hotel1);
			default:
				return Arrays.asList(hotel1, hotel2); //get All Hotel
		} 
	}
}
