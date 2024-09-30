package com.getmyschool.common.converter;

import org.springframework.stereotype.Component;

import com.getmyschool.common.domain.PartnerLogins;
import com.getmyschool.common.dto.PartnerLoginsDTO;

@Component
public class PartnerLoginsConverter {

	/**
	 *@Convert PartnerLogins to PartnerLoginsDTO
	 *@param PartnerLogins
	 * @Return
	 */
	
	public static PartnerLoginsDTO getPartnerLoginsDTOByPartnerLogins(PartnerLogins partnerLogins) {
		PartnerLoginsDTO dto = new PartnerLoginsDTO();
		
		dto.setId(partnerLogins.getId());
		dto.setFullName(partnerLogins.getFullName());
		dto.setSchoolId(partnerLogins.getSchoolId());
		dto.setEmail(partnerLogins.getEmail());
		dto.setStatus(partnerLogins.getStatus());
		dto.setCreatedBy(partnerLogins.getCreatedBy());
		dto.setCreatedDate(partnerLogins.getCreatedDate());
		dto.setUpdatedBy(partnerLogins.getUpdatedBy());
		dto.setUpdatedDate(partnerLogins.getUpdatedDate());	
		
		return dto;
	}

	/**
	 *@Convert PartnerLoginsDTO to PartnerLogins
	 *@param PartnerLoginsDTO
	 * @Return
	 */

	public static PartnerLogins getPartnerLoginsByPartnerLoginsDTO(PartnerLoginsDTO partnerLoginsDTO) {	
		PartnerLogins partnerLogins = new PartnerLogins();
		
		partnerLogins.setId(partnerLoginsDTO.getId());
		partnerLogins.setFullName(partnerLoginsDTO.getFullName());
		partnerLogins.setSchoolId(partnerLoginsDTO.getSchoolId());
		partnerLogins.setEmail(partnerLoginsDTO.getEmail());
		partnerLogins.setStatus(partnerLoginsDTO.getStatus());
		partnerLogins.setCreatedBy(partnerLoginsDTO.getCreatedBy());
		partnerLogins.setCreatedDate(partnerLoginsDTO.getCreatedDate());
		partnerLogins.setUpdatedBy(partnerLoginsDTO.getUpdatedBy());
		partnerLogins.setUpdatedDate(partnerLoginsDTO.getUpdatedDate());
		
		return partnerLogins;
	}
	
}