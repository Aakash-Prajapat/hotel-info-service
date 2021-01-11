package com.epam.incubation.service.hotelinfo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.incubation.service.hotelinfo.datamodel.HotelDataModel;
import com.epam.incubation.service.hotelinfo.entity.Hotel;
import com.epam.incubation.service.hotelinfo.exception.RecordNotFoundException;
import com.epam.incubation.service.hotelinfo.repository.HotelInformationRepository;

@Service
public class HotelInformationServiceImpl implements HotelInformationService {

	@Autowired
	HotelInformationRepository hotelRepository;
	private Boolean activeHotels = Boolean.FALSE;

	public List<HotelDataModel> getAllHotels() {
		List<Hotel> hotels = (List<Hotel>) hotelRepository.findAll();
		if (hotels.isEmpty())
			throw new RecordNotFoundException("No Hotels found with");

		return convertHotelDomainModelToDataModel(hotels, activeHotels);

	}

	public Optional<HotelDataModel> getHotelById(Integer id) {
		Optional<Hotel> hotel = hotelRepository.findById(id);
		Optional<HotelDataModel> hotelDataModel = Optional.ofNullable(null);
		if (hotel.isPresent()) {
			hotelDataModel = Optional.of(convertHotelDomainModelToDataModel(hotel.get()));
		}

		return hotelDataModel;
	}

	public List<HotelDataModel> findByCity(String city) {
		activeHotels = Boolean.TRUE;
		List<Hotel> hotels = hotelRepository.findByAddressCity(city);
		if (hotels.isEmpty())
			throw new RecordNotFoundException("No Hotels found with " + city);

		return convertHotelDomainModelToDataModel(hotels, activeHotels);

	}

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
		return activeHotels
				? hotels.stream().filter(h -> h.getStatus() == Boolean.TRUE).map(HotelDataModel::new)
						.collect(Collectors.toList())
				: hotels.stream().map(HotelDataModel::new).collect(Collectors.toList());
	}
}
