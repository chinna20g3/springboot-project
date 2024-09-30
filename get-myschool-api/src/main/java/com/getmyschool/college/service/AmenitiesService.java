package com.getmyschool.college.service;

import java.util.List;

import com.getmyschool.common.dto.AmenitiesDTO;

public interface AmenitiesService {

	public void saveAmenities(AmenitiesDTO amenitiesDTO);
	
	public List<AmenitiesDTO> getAllAmenities(AmenitiesDTO amenitiesDTO);

	public AmenitiesDTO getAmenitiesById(AmenitiesDTO amenitiesDTO);

	public void updateAmenities(AmenitiesDTO amenitiesDTO);
}
