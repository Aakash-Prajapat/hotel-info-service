package com.epam.incubation.service.hotelinfo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.epam.incubation.service.hotelinfo.datamodel.HotelDataModel;
import com.epam.incubation.service.hotelinfo.entity.Hotel;
import com.epam.incubation.service.hotelinfo.exception.RecordNotFoundException;
import com.epam.incubation.service.hotelinfo.repository.HotelInformationRepository;

import brave.sampler.Sampler;

/**
 * hotel service layer responsible to add, update and fetch the hotel information.
 * 
 */
@Service
public class HotelInformationServiceImpl implements HotelInformationService {

	private final Logger logger = LoggerFactory.getLogger(HotelInformationServiceImpl.class);
	
	@Autowired
	HotelInformationRepository hotelRepository;
	private Boolean activeHotels = Boolean.FALSE;

	/**
	 * Responsible to return the all hotel information.
	 * @return the List of HotelDataModel, Holds all hotel information.
	 * 
	 */
	public List<HotelDataModel> getAllHotels() {
		logger.info("Returing all hotels info");
		List<Hotel> hotels = hotelRepository.findAll();
		if (hotels.isEmpty())
			throw new RecordNotFoundException("No Hotels found with");

		return convertHotelDomainModelToDataModel(hotels, activeHotels);

	}

	/**
	 * Responsible to return the Hotel data.
	 * 
	 * @param guestId , Id to which hotel information get fetched.
	 * @return HotelDataModel, Holds hotel information.
	 */
	public HotelDataModel getHotelById(Integer id) {
		Optional<Hotel> hotel = hotelRepository.findById(id);
		if (hotel.isPresent()) {
			return convertHotelDomainModelToDataModel(hotel.get());
		} else
			throw new RecordNotFoundException("Hotel is not present with"+id);
	}

	/**
	 * Responsible to return the Hotel by city.
	 * 
	 * @param String city name , City name to fetch all hotels information.
	 * @return list of HotelDataModel, Holds hotel information.
	 */
	public List<HotelDataModel> findByCity(String city) {
		activeHotels = Boolean.TRUE;
		List<Hotel> hotels = hotelRepository.findByAddressCity(city);
		if (hotels.isEmpty())
			throw new RecordNotFoundException("No Hotels found with " + city);

		return convertHotelDomainModelToDataModel(hotels, activeHotels);

	}

	/**
	 * Responsible to return the Hotel data.
	 * 
	 * @param Integer , Id to which hotel will be disabled.
	 * @return HotelDataModel, Holds hotel information.
	 */
	public HotelDataModel disableHotel(Integer id) {
		Optional<Hotel> hotel = hotelRepository.findById(id);
		if (hotel.isPresent()) {
			hotel.get().setStatus(false);
			Hotel updatedHotel = hotelRepository.save(hotel.get());
			return convertHotelDomainModelToDataModel(updatedHotel);
		} else {
			throw new RecordNotFoundException("Hotel not found with " + id);
		}
	}

	private HotelDataModel convertHotelDomainModelToDataModel(Hotel hotel) {
		return new HotelDataModel(hotel);
	}

	private List<HotelDataModel> convertHotelDomainModelToDataModel(List<Hotel> hotels, boolean activeHotels) {
		return activeHotels ? hotels.stream().filter(h -> h.getStatus() == Boolean.TRUE).map(HotelDataModel::new)
				.collect(Collectors.toList()) : hotels.stream().map(HotelDataModel::new).collect(Collectors.toList());
	}
	
	@Bean
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}
}
