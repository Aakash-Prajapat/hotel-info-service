package com.epam.incubation.service.hotelinfo.datamodel;

import java.util.List;

public class InventoryDetailsResponseModel {
	private List<InventoryResponseModel> responseModel;
	private ApiError error;

	public List<InventoryResponseModel> getResponseModel() {
		return responseModel;
	}

	public void setResponseModel(List<InventoryResponseModel> responseModel) {
		this.responseModel = responseModel;
	}

	public ApiError getError() {
		return error;
	}

	public void setError(ApiError error) {
		this.error = error;
	}
}
