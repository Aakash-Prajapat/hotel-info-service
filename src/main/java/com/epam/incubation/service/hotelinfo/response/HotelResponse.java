package com.epam.incubation.service.hotelinfo.response;

import java.util.List;
import com.epam.incubation.service.hotelinfo.datamodel.HotelDataModel;

import lombok.*;

public class HotelResponse {

	private List<HotelDataModel> hotels;

	public HotelResponse(List<HotelDataModel> hotelsData) {
		this.hotels = hotelsData;
	}

	public List<HotelDataModel> getHotels() {
		return hotels;
	}

	public void setHotels(List<HotelDataModel> hotels) {
		this.hotels = hotels;
	}
}
