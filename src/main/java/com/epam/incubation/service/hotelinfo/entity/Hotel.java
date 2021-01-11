package com.epam.incubation.service.hotelinfo.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;


@Entity
public class Hotel {
	@Id
	@Column(name = "HOTEL_ID")
	private Integer hotelId;
	@Column(name = "HOTEL_NAME")
	private String hotelName;
	@Column(name = "HOTEL_DESCRIPTION")
	private String hotelDescription;
	@Embedded
	private Address address;
	@OneToMany(mappedBy = "hotel")
	private Collection<Room> rooms;
	@ManyToMany
	@JoinTable(name = "HOTEL_AMENITIES", joinColumns = @JoinColumn(name = "HOTEL_ID"), inverseJoinColumns = @JoinColumn(name = "AMENITY_ID"))
	private Collection<Amenity> amenities;
	@Column(name = "STATUS")
	private Boolean status;
	@Column(name = "RATING")
	private int rating;

	public Integer getHotelId() {
		return hotelId;
	}

	public void setHotelId(Integer hotelId) {
		this.hotelId = hotelId;
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Collection<Room> getRooms() {
		return rooms;
	}

	public void setRooms(Collection<Room> rooms) {
		this.rooms = rooms;
	}

	public Collection<Amenity> getAmenities() {
		return amenities;
	}

	public void setAmenities(Collection<Amenity> amenities) {
		this.amenities = amenities;
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
