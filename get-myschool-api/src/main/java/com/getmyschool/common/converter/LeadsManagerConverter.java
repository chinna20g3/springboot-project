package com.getmyschool.common.converter;

import org.springframework.stereotype.Component;

import com.getmyschool.common.domain.LeadsManager;
import com.getmyschool.common.dto.LeadsManagerDTO;

@Component
public class LeadsManagerConverter {

	/**
	 * @Converter LeadsManager to LeadsManagerDTO
	 * 
	 * @param LeadsManager
	 * @return
	 */
	
	public static LeadsManagerDTO getLeadsManagerDTOByLeadsManager(LeadsManager leadsManager) {
		LeadsManagerDTO dto = new LeadsManagerDTO();
		
		dto.setId(leadsManager.getId());
		dto.setName(leadsManager.getName());
		dto.setPhoneNumber(leadsManager.getPhoneNumber());
		dto.setEmail(leadsManager.getEmail());
		dto.setStatus(leadsManager.getStatus());
		dto.setCollegeId(leadsManager.getCollegeId());
		dto.setLeadStatus(leadsManager.getLeadStatus());
		dto.setLeadDate(leadsManager.getLeadDate());
		dto.setFollowUpDate(leadsManager.getFollowUpDate());
		dto.setNextFollowUpDate(leadsManager.getNextFollowUpDate());
		dto.setRemarks(leadsManager.getRemarks());
		dto.setAssignedTo(leadsManager.getAssignedTo());
		dto.setAssignedBy(leadsManager.getAssignedBy());
		dto.setLeadType(leadsManager.getLeadType());
		dto.setCreatedBy(leadsManager.getCreatedBy());
		dto.setUpdatedBy(leadsManager.getUpdatedBy());
		dto.setCreatedDate(leadsManager.getCreatedDate());
		dto.setUpdatedDate(leadsManager.getUpdatedDate());
		
		return dto;
	}
	
	/**
	 * @Converter LeadsManagerDTO to LeadsManager
	 * 
	 * @param LeadsManagerDTO
	 * @return
	 */
	
	
	public static LeadsManager getLeadsManagerByLeadsManagerDTO(LeadsManagerDTO leadsManagerDTO) {
		LeadsManager leadsManager = new LeadsManager();
		
		leadsManager.setId(leadsManagerDTO.getId());
		leadsManager.setName(leadsManagerDTO.getName());
		leadsManager.setPhoneNumber(leadsManagerDTO.getPhoneNumber());
		leadsManager.setEmail(leadsManagerDTO.getEmail());
		leadsManager.setStatus(leadsManagerDTO.getStatus());
		leadsManager.setCollegeId(leadsManagerDTO.getCollegeId());
		leadsManager.setLeadStatus(leadsManagerDTO.getLeadStatus());
		leadsManager.setLeadDate(leadsManagerDTO.getLeadDate());
		leadsManager.setFollowUpDate(leadsManagerDTO.getFollowUpDate());
		leadsManager.setNextFollowUpDate(leadsManagerDTO.getNextFollowUpDate());
		leadsManager.setRemarks(leadsManagerDTO.getRemarks());
		leadsManager.setAssignedTo(leadsManagerDTO.getAssignedTo());
		leadsManager.setAssignedBy(leadsManagerDTO.getAssignedBy());
		leadsManager.setLeadType(leadsManagerDTO.getLeadType());
		leadsManager.setCreatedBy(leadsManagerDTO.getCreatedBy());
		leadsManager.setUpdatedBy(leadsManagerDTO.getUpdatedBy());
		leadsManager.setCreatedDate(leadsManagerDTO.getCreatedDate());
		leadsManager.setUpdatedDate(leadsManagerDTO.getUpdatedDate());
		
		return leadsManager;
	}
}
