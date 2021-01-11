package com.epam.incubation.service.hotelinfo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.incubation.service.hotelinfo.datamodel.AmenityDataModel;
import com.epam.incubation.service.hotelinfo.datamodel.HotelDataModel;
import com.epam.incubation.service.hotelinfo.datamodel.InventoryDataModel;
import com.epam.incubation.service.hotelinfo.datamodel.RoomDataModel;
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
		List<HotelDataModel> hotelDataModel = convertHotelDomainModelToDataModel(hotels, activeHotels);
		return hotelDataModel;
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
		List<Hotel> hotels = (List<Hotel>) hotelRepository.findByAddressCity(city);
		if (!hotels.isEmpty()) {
			List<HotelDataModel> hotelDataModel = convertHotelDomainModelToDataModel(hotels, activeHotels);
			return hotelDataModel;
		} else
			return null;
	}

	public HotelDataModel disableHotel(Integer id) {
		Optional<Hotel> hotel = hotelRepository.findById(id);
		if (hotel.isPresent()) {
			hotel.get().setStatus(false);
			Hotel updatedHotel= hotelRepository.save(hotel.get());
			return convertHotelDomainModelToDataModel(updatedHotel);
		} else {
			throw new RecordNotFoundException("Hotel not found with "+id);
		}
	}

	private HotelDataModel convertHotelDomainModelToDataModel(Hotel hotel) {
		return new HotelDataModel(hotel.getHotelName(), hotel.getHotelDescription(),
				hotel.getAddress().getAddressLine1(), hotel.getAddress().getAddressLine2(),
				hotel.getAddress().getCity(), hotel.getAddress().getState(), hotel.getAddress().getZipcode(),
				hotel.getAddress().getCity(),
				hotel.getAmenities().parallelStream()
						.map(a -> new AmenityDataModel(a.getAmenityName(), a.getAmenityDescription()))
						.collect(Collectors.toList()),
				hotel.getRooms().parallelStream().map(r -> new RoomDataModel(r.getName(), r.getDescription(),
						r.isStatus(),
						r.getInventories().parallelStream()
								.map(i -> new InventoryDataModel(i.getStayDate(), i.getQuantity(), i.getPrice()))
								.collect(Collectors.toList())))
						.collect(Collectors.toList()),
				hotel.getStatus(), hotel.getRating());
	}

	private List<HotelDataModel> convertHotelDomainModelToDataModel(List<Hotel> hotels, Boolean activeHotels) {
		if (activeHotels)
			return hotels.stream().filter(h -> h.getStatus() == Boolean.TRUE).map(h -> new HotelDataModel(
					h.getHotelName(), h.getHotelDescription(), h.getAddress().getAddressLine1(),
					h.getAddress().getAddressLine2(), h.getAddress().getCity(), h.getAddress().getState(),
					h.getAddress().getZipcode(), h.getAddress().getCountry(),
					h.getAmenities().parallelStream()
							.map(a -> new AmenityDataModel(a.getAmenityName(), a.getAmenityDescription()))
							.collect(Collectors.toList()),
					h.getRooms().parallelStream().map(r -> new RoomDataModel(r.getName(), r.getDescription(),
							r.isStatus(),
							r.getInventories().parallelStream()
									.map(i -> new InventoryDataModel(i.getStayDate(), i.getQuantity(), i.getPrice()))
									.collect(Collectors.toList())))
							.collect(Collectors.toList()),
					h.getStatus(), h.getRating())).collect(Collectors.toList());
		else
			return hotels.stream().filter(h -> h.getStatus() == Boolean.TRUE).map(h -> new HotelDataModel(
					h.getHotelName(), h.getHotelDescription(), h.getAddress().getAddressLine1(),
					h.getAddress().getAddressLine2(), h.getAddress().getCity(), h.getAddress().getState(),
					h.getAddress().getZipcode(), h.getAddress().getCountry(),
					h.getAmenities().parallelStream()
							.map(a -> new AmenityDataModel(a.getAmenityName(), a.getAmenityDescription()))
							.collect(Collectors.toList()),
					h.getRooms().parallelStream().map(r -> new RoomDataModel(r.getName(), r.getDescription(),
							r.isStatus(),
							r.getInventories().parallelStream()
									.map(i -> new InventoryDataModel(i.getStayDate(), i.getQuantity(), i.getPrice()))
									.collect(Collectors.toList())))
							.collect(Collectors.toList()),
					h.getStatus(), h.getRating())).collect(Collectors.toList());
	}
}
