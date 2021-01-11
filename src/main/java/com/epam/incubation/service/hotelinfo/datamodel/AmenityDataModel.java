package com.epam.incubation.service.hotelinfo.datamodel;

import javax.validation.constraints.NotBlank;

public class AmenityDataModel {

	@NotBlank(message = "Amenity name is mandatory")
	private String amenityName;
	@NotBlank(message = "Amenity Description is mandatory")
	private String amenityDescription;

	public AmenityDataModel(String amenityName, String amenityDescription) {
		this.amenityName = amenityName;
		this.amenityDescription = amenityDescription;
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
