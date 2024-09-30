package com.getmyschool.common.converter;

import org.springframework.stereotype.Component;

import com.getmyschool.common.domain.SchoolType;
import com.getmyschool.common.dto.SchoolTypeDTO;

@Component
public class SchoolTypeConverter {
	
	/**
	 * convert SchoolType to SchoolTypeDTO
	 * 
	 * @param SchoolType
	 * @return
	 */
	
	public static SchoolTypeDTO getSchoolTypeDTOBySchoolType(SchoolType schoolType) {
		SchoolTypeDTO dto = new SchoolTypeDTO();

		dto.setId(schoolType.getId());
		dto.setSchoolType(schoolType.getSchoolType());
		dto.setStatus(schoolType.getStatus());
		dto.setCreatedBy(schoolType.getCreatedBy());
		dto.setCreatedDate(schoolType.getCreatedDate());
		dto.setUpdatedBy(schoolType.getUpdatedBy());
		dto.setUpdatedDate(schoolType.getUpdatedDate());
		
		return dto;
		
	}

	/**
	 * convert SchoolTypeDTO to SchoolType
	 * 
	 * @param SchoolTypeDTO
	 * @return
	 */
	
	public static SchoolType getSchoolTypeBySchoolTypeDTO(SchoolTypeDTO schoolTypeDTO) {
		SchoolType schoolType = new SchoolType();	
		
		schoolType.setId(schoolTypeDTO.getId());
		schoolType.setSchoolType(schoolTypeDTO.getSchoolType());
		schoolType.setStatus(schoolTypeDTO.getStatus());
		schoolType.setCreatedBy(schoolTypeDTO.getCreatedBy());
		schoolType.setCreatedDate(schoolTypeDTO.getCreatedDate());
		schoolType.setUpdatedBy(schoolTypeDTO.getUpdatedBy());
		schoolType.setUpdatedDate(schoolTypeDTO.getUpdatedDate());
        
		return schoolType;
	}
	
}
	
