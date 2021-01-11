package com.epam.incubation.service.hotelinfo.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Amenity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "AMENITY_ID")
	private Integer amenityId;
	@Column(name = "AMENITY_NAME")
	private String amenityName;
	@Column(name = "AMENITY_DESC")
	private String amenityDescription;
	@ManyToMany(mappedBy = "amenities")
	private Collection<Hotel> hotel;

	public Integer getAmenityId() {
		return amenityId;
	}

	public void setAmenityId(Integer amenityId) {
		this.amenityId = amenityId;
	}

	public String getAmenityName() {
		return amenityName;
	}

	public void setAmenityName(String amenityName) {
		this.amenityName = amenityName;
	}

	public String getAmenityDescription() {
		return amenityDescription;
	}

	public void setAmenityDescription(String amenityDescription) {
		this.amenityDescription = amenityDescription;
	}

	public void setHotel(Collection<Hotel> hotel) {
		this.hotel = hotel;
	}
}
