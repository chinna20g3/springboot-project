package com.getmyschool.college.serviceimpl;

import java.util.ArrayList; 
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.getmyschool.college.service.PrincipalLoginsService;
import com.getmyschool.common.converter.PrincipalLoginsConverter;
import com.getmyschool.common.dao.PrincipalLoginsDao;
import com.getmyschool.common.domain.PrincipalLogins;
import com.getmyschool.common.domain.Role;
import com.getmyschool.common.dto.PrincipalLoginsDTO;
import com.getmyschool.common.exception.UnAuthorizedException;
import com.getmyschool.common.service.LoginService;
import com.getmyschool.common.validator.RoleEnum;

@Service("PrincipalLoginsServiceImpl")
public class PrincipalLoginsServiceImpl implements PrincipalLoginsService {

	@Resource(name = "LoginServiceImpl")
	private LoginService loginService;
	
	@Resource(name = "PrincipalLoginsDaoImpl")
	private PrincipalLoginsDao principalLoginsDao;
	private static final Logger LOGGER = LoggerFactory.getLogger(PrincipalLoginsServiceImpl.class);

	@Override
	public void savePrincipalLogins(PrincipalLoginsDTO principalLoginsDTO) {
		List<Role> roles =loginService.getAllUserRoles( principalLoginsDTO.getCreatedBy());
		boolean adminFlag = roles.stream().anyMatch(x -> x.getRole().equals(RoleEnum.SUPER_ADMIN.getRole())
				|| x.getRole().equals(RoleEnum.ADMIN.getRole()));
		if (!(adminFlag)) {
			throw new UnAuthorizedException("LogedIn PrincipalLogins does't have permission to save SchoolType Details.");
		}
		PrincipalLogins principalLogins = principalLoginsDao.savePrincipalLogins(principalLoginsDTO);
		LOGGER.info("PrincipalLogins is added successfullly with savePrincipalLogins::"+ principalLogins.getEmail());	
	}

	@Override
	public List<PrincipalLoginsDTO> getAllPrincipalLogins(PrincipalLoginsDTO principalLoginsDTO) {
		List<Role> roles = loginService.getAllUserRoles(principalLoginsDTO.getUpdatedBy());
	    boolean adminAccess = roles.stream().anyMatch(x -> x.getRole().equals(RoleEnum.SUPER_ADMIN.getRole())
	            || x.getRole().equals(RoleEnum.ADMIN.getRole()));
	    if (!adminAccess) {
	    	throw new UnAuthorizedException("Logged-in PrincipalLogins doesn't have permission to access all principalLogins details.");
	    }
	    List<PrincipalLogins> principalLoginsList = principalLoginsDao.getAllPrincipalLogins(principalLoginsDTO);
	    List<PrincipalLoginsDTO> returnList = new ArrayList<>();		    
	    for (PrincipalLogins principalLogins : principalLoginsList) {
	    	PrincipalLoginsDTO dbPrincipalLoginsDTO = PrincipalLoginsConverter.getPrincipalLoginsDTOByPrincipalLogins(principalLogins);
	    	returnList.add(dbPrincipalLoginsDTO);
	    }
	    return returnList;
	}

	@Override
	public PrincipalLoginsDTO getPrincipalLoginsById(PrincipalLoginsDTO principalLoginsDTO) {
		List<Role> roles =loginService.getAllUserRoles( principalLoginsDTO.getUpdatedBy());
		boolean adminAccess = roles.stream().anyMatch(x -> x.getRole().equals(RoleEnum.SUPER_ADMIN.getRole())
				|| x.getRole().equals(RoleEnum.ADMIN.getRole()));
		if (!adminAccess) {
			if (!principalLoginsDTO.getId().equals(principalLoginsDTO.getUpdatedBy()))
			throw new UnAuthorizedException("LogedIn SchoolType does't have permission to save SchoolType Details.");
		}
		PrincipalLogins principalLogins =principalLoginsDao.savePrincipalLogins(principalLoginsDTO);	
		return PrincipalLoginsConverter.getPrincipalLoginsDTOByPrincipalLogins(principalLogins);
	}		

	@Override
	public void updatePrincipalLogins(PrincipalLoginsDTO principalLoginsDTO) {
		List<Role> roles =loginService.getAllUserRoles( principalLoginsDTO.getUpdatedBy());	
		boolean adminAccess = roles.stream().anyMatch(x -> x.getRole().equals(RoleEnum.SUPER_ADMIN.getRole())
				|| x.getRole().equals(RoleEnum.ADMIN.getRole()));
		if (!adminAccess) {
			if (!principalLoginsDTO.getId().equals(principalLoginsDTO.getUpdatedBy()))
			throw new UnAuthorizedException("LogedIn SchoolType does't have permission to save SchoolType Details.");

		}
		PrincipalLogins principalLogins =principalLoginsDao.getPrincipalLoginsById(principalLoginsDTO.getId());
		PrincipalLoginsDTO dbPrincipalLoginsDTO = PrincipalLoginsConverter.getPrincipalLoginsDTOByPrincipalLogins(principalLogins);
		
		if (null != principalLoginsDTO.getEmail())
			dbPrincipalLoginsDTO.setEmail(principalLoginsDTO.getEmail());
		if (null != principalLoginsDTO.getSchoolId())
			dbPrincipalLoginsDTO.setSchoolId(principalLoginsDTO.getSchoolId());
		if (null != principalLoginsDTO.getFullName())
			dbPrincipalLoginsDTO.setFullName(principalLoginsDTO.getFullName());
		
		dbPrincipalLoginsDTO.setUpdatedBy(principalLoginsDTO.getUpdatedBy());
		dbPrincipalLoginsDTO.setUpdatedDate(principalLoginsDTO.getUpdatedDate());
		
	    principalLoginsDao.savePrincipalLogins(dbPrincipalLoginsDTO);
		LOGGER.info("PrincipalLogins details for schoolType id " + principalLoginsDTO.getId() + " are updated successfully.");
		
	}

	@Override
	public void deletePrincipalLogins(PrincipalLoginsDTO principalLoginsDTO) {
		List<Role> roles =loginService.getAllUserRoles( principalLoginsDTO.getUpdatedBy());
		boolean adminAccess = roles.stream().anyMatch(x -> x.getRole().equals(RoleEnum.SUPER_ADMIN.getRole())
				|| x.getRole().equals(RoleEnum.ADMIN.getRole()));
		if (!(adminAccess)) {
	    // Check if the logged-in principalLogins has permission to delete status
			if (!principalLoginsDTO.getId().equals(principalLoginsDTO.getUpdatedBy())) 
				throw new UnAuthorizedException("Logged-in PrincipalLogins doesn't have permission to delete status.");
        }
		
		PrincipalLogins principalLogins =principalLoginsDao.getPrincipalLoginsById(principalLoginsDTO.getId());
		PrincipalLoginsDTO dbPrincipalLoginsDTO = PrincipalLoginsConverter.getPrincipalLoginsDTOByPrincipalLogins(principalLogins);
		
	    // Perform deletion logic here, such as updating the status field in the database
		dbPrincipalLoginsDTO.setStatus(principalLoginsDTO.getStatus());
		dbPrincipalLoginsDTO.setUpdatedBy(principalLoginsDTO.getUpdatedBy());
		dbPrincipalLoginsDTO.setUpdatedDate(principalLoginsDTO.getUpdatedDate());
		LOGGER.info("PrincipalLogins Details for principalLogins with status " + principalLoginsDTO.getStatus() + " is deleted successfully.");
	}

}
