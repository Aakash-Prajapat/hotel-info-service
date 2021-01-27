package com.epam.incubation.service.hotelinfo.service;

import java.util.List;

import com.epam.incubation.service.hotelinfo.datamodel.HotelDataModel;

public interface HotelInformationService {

	public List<HotelDataModel> getAllHotels();
	
	public HotelDataModel getHotelById(Integer id);
	
	public List<HotelDataModel> findByCity(String city);
	
	public HotelDataModel disableHotel(Integer id);
}
