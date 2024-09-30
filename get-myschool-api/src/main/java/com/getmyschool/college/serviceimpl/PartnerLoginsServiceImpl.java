package com.getmyschool.college.serviceimpl;

import java.util.ArrayList;   
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.getmyschool.college.service.PartnerLoginsService;
import com.getmyschool.common.converter.PartnerLoginsConverter;
import com.getmyschool.common.dao.PartnerLoginsDao;
import com.getmyschool.common.domain.PartnerLogins;
import com.getmyschool.common.domain.Role;
import com.getmyschool.common.dto.PartnerLoginsDTO;
import com.getmyschool.common.exception.UnAuthorizedException;
import com.getmyschool.common.service.LoginService;
import com.getmyschool.common.validator.RoleEnum;

@Service("PartnerLoginsServiceImpl")
public class PartnerLoginsServiceImpl implements PartnerLoginsService {

	@Resource(name = "LoginServiceImpl")
	private LoginService loginService;
	
	@Resource(name = "PartnerLoginsDaoImpl")
	private PartnerLoginsDao partnerLoginsDao;

	private static final Logger LOGGER = LoggerFactory.getLogger(PartnerLoginsServiceImpl.class);

	@Override
	public void savePartnerLogins(PartnerLoginsDTO partnerLoginsDTO) {
		List<Role> roles = loginService.getAllUserRoles(partnerLoginsDTO.getCreatedBy());
		boolean adminFlag = roles.stream().anyMatch(x -> x.getRole().equals(RoleEnum.SUPER_ADMIN.getRole())
				|| x.getRole().equals(RoleEnum.ADMIN.getRole()));
		if (!(adminFlag)) {
			throw new UnAuthorizedException("LogedIn PartnerLogins does't have permission to save PartnerLogins Details.");
		}
		PartnerLogins partnerLogins = partnerLoginsDao.savePartnerLogins(partnerLoginsDTO);
		LOGGER.info("partnerLogins added successfully with savePartnerLogins::" + partnerLogins.getEmail());

	}

	@Override
	public List<PartnerLoginsDTO> getAllPartnerLogins(PartnerLoginsDTO partnerLoginsDTO) {
		List<Role> roles = loginService.getAllUserRoles(partnerLoginsDTO.getUpdatedBy());
	    boolean adminAccess = roles.stream().anyMatch(x -> x.getRole().equals(RoleEnum.SUPER_ADMIN.getRole())
	    		|| x.getRole().equals(RoleEnum.ADMIN.getRole()));
	    if (!adminAccess) {
	    	throw new UnAuthorizedException("Logged-in PartnerLogins doesn't have permission to access all PartnerLogins details.");
	    }
	    List<PartnerLogins> partnerLoginsList = partnerLoginsDao.getAllPartnerLogins(partnerLoginsDTO);
	    List<PartnerLoginsDTO> returnList = new ArrayList<>();
	    for (PartnerLogins partnerLogins : partnerLoginsList) {
	    	PartnerLoginsDTO dbPartnerLoginsDTO = PartnerLoginsConverter.getPartnerLoginsDTOByPartnerLogins(partnerLogins);
			returnList.add(dbPartnerLoginsDTO);
		}
	    return returnList;
	}

	@Override
	public PartnerLoginsDTO getPartnerLoginsById(PartnerLoginsDTO partnerLoginsDTO) {
		List<Role> roles = loginService.getAllUserRoles(partnerLoginsDTO.getUpdatedBy());
		boolean adminAccess = roles.stream().anyMatch(x -> x.getRole().equals(RoleEnum.SUPER_ADMIN.getRole())
				|| x.getRole().equals(RoleEnum.ADMIN.getRole()));
		if (!adminAccess) {
			if (!partnerLoginsDTO.getId().equals(partnerLoginsDTO.getUpdatedBy()))
				throw new UnAuthorizedException("LogedIn partnerLogins does't have permission to get partnerLogins Details.");
		}
			PartnerLogins partnerLogins = partnerLoginsDao.getPartnerLoginsById(partnerLoginsDTO.getId());
			return PartnerLoginsConverter.getPartnerLoginsDTOByPartnerLogins(partnerLogins);
	}

	@Override
	public void updatePartnerLogins(PartnerLoginsDTO partnerLoginsDTO) {
		List<Role> roles = loginService.getAllUserRoles(partnerLoginsDTO.getUpdatedBy());	
		boolean adminAccess = roles.stream().anyMatch(x -> x.getRole().equals(RoleEnum.SUPER_ADMIN.getRole())
				|| x.getRole().equals(RoleEnum.ADMIN.getRole()));
		if (!adminAccess) {
			if (!partnerLoginsDTO.getId().equals(partnerLoginsDTO.getUpdatedBy()))
			throw new UnAuthorizedException("LogedIn partnerLogins does't have permission to update partnerLogins Details.");
		}
		PartnerLogins partnerLogins =partnerLoginsDao.getPartnerLoginsById(partnerLoginsDTO.getId());
		PartnerLoginsDTO dbPartnerLoginsDTO = PartnerLoginsConverter.getPartnerLoginsDTOByPartnerLogins(partnerLogins);
		
		if (null != partnerLoginsDTO.getFullName())
			dbPartnerLoginsDTO.setFullName(partnerLoginsDTO.getFullName());
		if (null != partnerLoginsDTO.getSchoolId())
			dbPartnerLoginsDTO.setSchoolId(partnerLoginsDTO.getSchoolId());
		if (null != partnerLoginsDTO.getEmail())
			dbPartnerLoginsDTO.setEmail(partnerLoginsDTO.getEmail());
		
		dbPartnerLoginsDTO.setUpdatedBy(partnerLoginsDTO.getUpdatedBy());
		dbPartnerLoginsDTO.setUpdatedDate(partnerLoginsDTO.getUpdatedDate());

		partnerLoginsDao.savePartnerLogins(dbPartnerLoginsDTO);
		LOGGER.info("PartnerLogins details for partnerLogins id " + partnerLoginsDTO.getId() + " are updated successfully.");
		
	}

	@Override
	public void deletePartnerLogins(PartnerLoginsDTO partnerLoginsDTO) {
		List<Role> roles = loginService.getAllUserRoles(partnerLoginsDTO.getUpdatedBy());
	    boolean adminAccess = roles.stream().anyMatch(x ->
	            x.getRole().equals(RoleEnum.ADMIN.getRole()));

	    if (!adminAccess) {
	        // Check if the logged-in partnerLogins has permission to delete status
	        if (!partnerLoginsDTO.getId().equals(partnerLoginsDTO.getUpdatedBy())) 
	            throw new UnAuthorizedException("Logged-in PartnerLogins doesn't have permission to delete status.");
	    }
	    
	    PartnerLogins partnerLogins =partnerLoginsDao.getPartnerLoginsById(partnerLoginsDTO.getId());
		PartnerLoginsDTO dbPartnerLoginsDTO = PartnerLoginsConverter.getPartnerLoginsDTOByPartnerLogins(partnerLogins);
		

	    // Perform deletion logic here, such as updating the status field in the database
		dbPartnerLoginsDTO.setStatus(partnerLoginsDTO.getStatus());
		dbPartnerLoginsDTO.setUpdatedBy(partnerLoginsDTO.getUpdatedBy());
		dbPartnerLoginsDTO.setUpdatedDate(partnerLoginsDTO.getUpdatedDate());
	    LOGGER.info("Status for partnerLogins with ID " + partnerLoginsDTO.getStatus() + " is deleted successfully.");
	}

}
