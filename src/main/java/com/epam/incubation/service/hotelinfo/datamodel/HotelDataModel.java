package com.epam.incubation.service.hotelinfo.datamodel;

import java.util.Collection;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class HotelDataModel {
	
	@NotBlank(message = "Hotel Name is mandatory")
	private String hotelName;
	@NotBlank(message = "Hotel Description is mandatory")
	private String hotelDescription;
	@NotBlank(message = "Hotel Property Number is mandatory")
	private String propertyNumber;
	@NotBlank(message = "Hotel Street name is mandatory")
	private String street;
	@NotBlank(message = "Hotel City name is mandatory")
	private String city;
	@NotBlank(message = "Hotel State name is mandatory")
	private String state;
	@NotNull(message = "Zipcode is mandatory")
	private long zipcode;
	@NotBlank(message = "Hotel Country name is mandatory")
	private String country;
	private Collection<AmenityDataModel> amenities;
	private Collection<RoomDataModel> rooms;
	@NotBlank(message = "Hotel Status is mandatory")
	private Boolean status;
	@NotNull(message = "Rating is mandatory")
	private int rating;

	public HotelDataModel(String hotelName, String hotelDescription, String propertyNumber, String street, String city,
			String state, long zipcode, String country, Collection<AmenityDataModel> amenities,
			Collection<RoomDataModel> rooms, Boolean status, int rating) {
		this.hotelName = hotelName;
		this.hotelDescription = hotelDescription;
		this.propertyNumber = propertyNumber;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.country = country;
		this.amenities = amenities;
		this.rooms = rooms;
		this.status = status;
		this.rating = rating;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getHotelDescription() {
		return hotelDescription;
	}

	public void setHotelDescription(String hotelDescription) {
		this.hotelDescription = hotelDescription;
	}

	public String getPropertyNumber() {
		return propertyNumber;
	}

	public void setPropertyNumber(String propertyNumber) {
		this.propertyNumber = propertyNumber;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public long getZipcode() {
		return zipcode;
	}

	public void setZipcode(long zipcode) {
		this.zipcode = zipcode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Collection<AmenityDataModel> getAmenities() {
		return amenities;
	}

	public void setAmenities(Collection<AmenityDataModel> amenities) {
		this.amenities = amenities;
	}

	public Collection<RoomDataModel> getRooms() {
		return rooms;
	}

	public void setRooms(Collection<RoomDataModel> rooms) {
		this.rooms = rooms;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
}
