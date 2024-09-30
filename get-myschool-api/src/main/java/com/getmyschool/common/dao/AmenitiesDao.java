package com.getmyschool.common.dao;

import java.util.List;

import com.getmyschool.common.domain.Amenities;
import com.getmyschool.common.dto.AmenitiesDTO;

public interface AmenitiesDao {

	public Amenities saveAmenities(AmenitiesDTO amenitiesDTO);
	
	public List<Amenities> getAllAmenities(AmenitiesDTO amenitiesDTO);

	public Amenities getAmenitiesById(Long id);

}
