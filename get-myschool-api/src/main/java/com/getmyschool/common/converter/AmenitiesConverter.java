package com.getmyschool.common.converter;

import org.springframework.stereotype.Component;

import com.getmyschool.common.domain.Amenities; 
import com.getmyschool.common.dto.AmenitiesDTO;

@Component
public class AmenitiesConverter {

	
	/**
	 * convert Amenities to AmenitiesDTO
	 * 
	 *@param Amenities
	 *@return
	 */
	public static AmenitiesDTO getAmenitiesDTOByAmenities(Amenities amenities) {
		AmenitiesDTO dto = new AmenitiesDTO();
		
		dto.setId(amenities.getId());
		dto.setAmenitiesType(amenities.getAmenitiesType());
		dto.setStatus(amenities.getStatus());
		dto.setCreatedBy(amenities.getCreatedBy());
		dto.setCreatedDate(amenities.getCreatedDate());
		dto.setUpdatedBy(amenities.getUpdatedBy());
		dto.setUpdatedDate(amenities.getUpdatedDate());
		
		return dto;
	}
	

	/**
	 * convert AmenitiesDTO to Amenities
	 * 
	 *@param AmenitiesDTO
	 *@return
	 */
	
	public static Amenities getAmenitiesByAmenitiesDTO(AmenitiesDTO amenitiesDTO) {
		Amenities amenities = new Amenities();
		
		amenities.setId(amenitiesDTO.getId());
		amenities.setAmenitiesType(amenitiesDTO.getAmenitiesType());
		amenities.setStatus(amenitiesDTO.getStatus());
		amenities.setCreatedBy(amenitiesDTO.getCreatedBy());
		amenities.setCreatedDate(amenitiesDTO.getCreatedDate());
		amenities.setUpdatedBy(amenitiesDTO.getUpdatedBy());
		amenities.setUpdatedDate(amenitiesDTO.getUpdatedDate());
		
		return amenities;
	}
}
