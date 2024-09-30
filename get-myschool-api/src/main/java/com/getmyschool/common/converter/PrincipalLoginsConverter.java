package com.getmyschool.common.converter;

import org.springframework.stereotype.Component;

import com.getmyschool.common.domain.PrincipalLogins;
import com.getmyschool.common.dto.PrincipalLoginsDTO;

@Component
public class PrincipalLoginsConverter {

	/**
	 * @Converter PrincipalLogins to PrincipalLoginsDTO
	 * @param PrincipalLoginsDTO
	 * @return
	 */
	
	public static PrincipalLoginsDTO getPrincipalLoginsDTOByPrincipalLogins(PrincipalLogins principalLogins) {
		PrincipalLoginsDTO dto = new PrincipalLoginsDTO(); 
		
		dto.setId(principalLogins.getId());
		dto.setFullName(principalLogins.getFullName());
		dto.setSchoolId(principalLogins.getSchoolId());
		dto.setEmail(principalLogins.getEmail());
		dto.setStatus(principalLogins.getStatus());
		dto.setCreatedBy(principalLogins.getCreatedBy());
		dto.setCreatedDate(principalLogins.getCreatedDate());
		dto.setUpdatedBy(principalLogins.getUpdatedBy());
		dto.setUpdatedDate(principalLogins.getUpdatedDate());
		
		return dto;
	}
	
	
	/**
	 * @Converter PrincipalLoginsDTO to PrincipalLogins
	 * @param PrincipalLoginsDTO
	 * @return
	 */
	
	public static PrincipalLogins getPrincipalLoginsByPrincipalLoginsDTO(PrincipalLoginsDTO principalLoginsDTO) {
		PrincipalLogins principalLogins = new PrincipalLogins(); 
		principalLogins.setId(principalLoginsDTO.getId());
		principalLogins.setFullName(principalLoginsDTO.getFullName());
		principalLogins.setSchoolId(principalLoginsDTO.getSchoolId());
		principalLogins.setEmail(principalLoginsDTO.getEmail());
		principalLogins.setStatus(principalLoginsDTO.getStatus());
		principalLogins.setCreatedBy(principalLoginsDTO.getCreatedBy());
		principalLogins.setCreatedDate(principalLoginsDTO.getCreatedDate());
		principalLogins.setUpdatedBy(principalLoginsDTO.getUpdatedBy());
		principalLogins.setUpdatedDate(principalLoginsDTO.getUpdatedDate());
		
		return principalLogins;
	}
	
}
