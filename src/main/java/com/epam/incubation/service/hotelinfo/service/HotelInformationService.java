package com.epam.incubation.service.hotelinfo.service;

import java.util.List;

import com.epam.incubation.service.hotelinfo.datamodel.HotelDataModel;
import com.epam.incubation.service.hotelinfo.response.HotelApiResponse;
import com.epam.incubation.service.hotelinfo.response.HotelResponse;

public interface HotelInformationService {

	public List<HotelDataModel> getAllHotels();
	
	public HotelDataModel getHotelById(Integer id);
	
	public HotelApiResponse<HotelResponse> findByCity(String city);
	
	public HotelDataModel disableHotel(Integer id);
}
