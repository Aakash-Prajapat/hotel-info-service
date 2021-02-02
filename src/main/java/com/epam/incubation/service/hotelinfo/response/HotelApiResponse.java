package com.epam.incubation.service.hotelinfo.response;

import org.springframework.http.HttpStatus;

import com.epam.incubation.service.hotelinfo.datamodel.ApiError;

public class HotelApiResponse<T> {

	private T data;
	private ApiError error;
	private HttpStatus status;

	public HotelApiResponse(T actualData, HttpStatus status, ApiError error) {
		this.data = actualData;
		this.error = error;
		this.status = status;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public ApiError getError() {
		return error;
	}

	public void setError(ApiError error) {
		this.error = error;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

}
