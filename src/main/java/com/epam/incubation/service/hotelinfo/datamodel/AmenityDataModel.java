package com.epam.incubation.service.hotelinfo.datamodel;

import javax.validation.constraints.NotBlank;

import com.epam.incubation.service.hotelinfo.entity.Amenity;

public class AmenityDataModel {

	@NotBlank(message = "Amenity name is mandatory")
	private String amenityName;
	@NotBlank(message = "Amenity Description is mandatory")
	private String amenityDescription;

	public AmenityDataModel(Amenity amenity) {
		this.amenityName = amenity.getAmenityName();
		this.amenityDescription = amenity.getAmenityDescription();
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

}
