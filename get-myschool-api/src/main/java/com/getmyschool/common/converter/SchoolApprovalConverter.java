package com.getmyschool.common.converter;

import org.springframework.stereotype.Component;

import com.getmyschool.common.domain.SchoolApproval;
import com.getmyschool.common.dto.SchoolApprovalDTO;

@Component
public class SchoolApprovalConverter {

	/**
	 * @Converter SchoolApproval to SchoolApprovalDTO
	 * @param SchoolApproval
	 * @return 
	 */
	
	public static SchoolApprovalDTO getSchoolApprovalDTOBySchoolApproval(SchoolApproval schoolApproval) {
		SchoolApprovalDTO dto =new SchoolApprovalDTO();
		
		dto.setId(schoolApproval.getId());
		dto.setApprovedBy(schoolApproval.getApprovedBy());
		dto.setSchoolId(schoolApproval.getSchoolId());
		dto.setStatus(schoolApproval.getStatus());
		dto.setCreatedBy(schoolApproval.getCreatedBy());
		dto.setCreatedDate(schoolApproval.getCreatedDate());
		dto.setUpdatedBy(schoolApproval.getUpdatedBy());
		dto.setUpdatedDate(schoolApproval.getUpdatedDate());
		
		return dto;
	}

	/**
	 * @Converter SchoolApproval to SchoolApprovalDTO
	 * @param SchoolApproval
	 * @return 
	 */
	
	public static SchoolApproval getSchoolApprovalBySchoolApprovalDTO(SchoolApprovalDTO schoolApprovalDTO) {
		SchoolApproval schoolApproval =new SchoolApproval();
		
		schoolApproval.setId(schoolApprovalDTO.getId());
		schoolApproval.setApprovedBy(schoolApprovalDTO.getApprovedBy());
		schoolApproval.setSchoolId(schoolApprovalDTO.getSchoolId());
		schoolApproval.setStatus(schoolApprovalDTO.getStatus());
		schoolApproval.setCreatedBy(schoolApprovalDTO.getCreatedBy());
		schoolApproval.setCreatedDate(schoolApprovalDTO.getCreatedDate());
		schoolApproval.setUpdatedBy(schoolApprovalDTO.getUpdatedBy());
		schoolApproval.setUpdatedDate(schoolApprovalDTO.getUpdatedDate());
		
		return schoolApproval;
	}
	
}
