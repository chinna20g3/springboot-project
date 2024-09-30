package com.getmyschool.college.serviceimpl;

import java.util.ArrayList; 
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.getmyschool.college.service.LeadsManagerService;
import com.getmyschool.common.converter.LeadsManagerConverter;
import com.getmyschool.common.dao.LeadsManagerDao;
import com.getmyschool.common.domain.LeadsManager;
import com.getmyschool.common.domain.Role;
import com.getmyschool.common.dto.LeadsManagerDTO;
import com.getmyschool.common.exception.UnAuthorizedException;
import com.getmyschool.common.service.LoginService;
import com.getmyschool.common.validator.RoleEnum;

@Service("LeadsManagerServiceImpl")
public class LeadsManagerServiceImpl implements LeadsManagerService {


	private static final Logger LOGGER = LoggerFactory.getLogger(LeadsManagerServiceImpl.class);

	@Resource(name = "LoginServiceImpl")
	private LoginService loginService;

	@Resource(name = "LeadsManagerDaoImpl")
	private LeadsManagerDao leadsManagerDao;
	@Override
	public void saveLeadsManager(LeadsManagerDTO leadsManagerDTO) {
		List<Role> roles = loginService.getAllUserRoles(leadsManagerDTO.getCreatedBy());
		boolean adminFlag = roles.stream().anyMatch(x -> x.getRole().equals(RoleEnum.SUPER_ADMIN.getRole())
				|| x.getRole().equals(RoleEnum.ADMIN.getRole()));
		if (!(adminFlag)) {
			throw new UnAuthorizedException("LogedIn LeadsManager does't have permission to save User Details.");
		}
		LeadsManager leadsManager = leadsManagerDao.saveLeadsManager(leadsManagerDTO);
		LOGGER.info("leadsManager added successfully with saveLeadsManager::" + leadsManager.getEmail());
	}

	@Override
	public List<LeadsManagerDTO> getAllLeadsManagers(LeadsManagerDTO leadsManagerDTO) {
		List<Role> roles = loginService.getAllUserRoles(leadsManagerDTO.getUpdatedBy());
	    boolean adminAcccess = roles.stream().anyMatch(x -> x.getRole().equals(RoleEnum.SUPER_ADMIN.getRole())
	            || x.getRole().equals(RoleEnum.ADMIN.getRole()));
	    if (!adminAcccess) {
	        throw new UnAuthorizedException("Logged-in LeadsManager doesn't have permission to access all LeadsManager details.");
	    }
	    List<LeadsManager> leadsManagerList = leadsManagerDao.getAllLeadsManager(leadsManagerDTO);
	    List<LeadsManagerDTO> returnList = new ArrayList<>();   
	    for (LeadsManager leadsManager : leadsManagerList) {
	    	LeadsManagerDTO dbLeadsManagerDTO = LeadsManagerConverter.getLeadsManagerDTOByLeadsManager(leadsManager);
	        returnList.add(dbLeadsManagerDTO);
	    }	    
	    return returnList;
	}

	@Override
	public LeadsManagerDTO getLeadsManagerById(LeadsManagerDTO leadsManagerDTO) {
		List<Role> roles = loginService.getAllUserRoles(leadsManagerDTO.getUpdatedBy());
		boolean adminAcccess = roles.stream().anyMatch(x -> x.getRole().equals(RoleEnum.SUPER_ADMIN.getRole())
				|| x.getRole().equals(RoleEnum.ADMIN.getRole()));
		if (!adminAcccess) {
			if (!leadsManagerDTO.getId().equals(leadsManagerDTO.getUpdatedBy()))
				throw new UnAuthorizedException("LogedIn leadsManager does't have permission to get LeadsManager Details.");
		}
			LeadsManager leadsManager = leadsManagerDao.getLeadsManagerById(leadsManagerDTO.getId());
			return LeadsManagerConverter.getLeadsManagerDTOByLeadsManager(leadsManager);
	}

	@Override
	public void updateLeadsManager(LeadsManagerDTO leadsManagerDTO) {
		List<Role> roles = loginService.getAllUserRoles(leadsManagerDTO.getUpdatedBy());
		boolean adminAcccess = roles.stream().anyMatch(x -> x.getRole().equals(RoleEnum.SUPER_ADMIN.getRole())
				|| x.getRole().equals(RoleEnum.ADMIN.getRole()));
		if (!adminAcccess) 
			if (!leadsManagerDTO.getId().equals(leadsManagerDTO.getUpdatedBy()))
				throw new UnAuthorizedException("LogedIn leadsManager does't have permission to update leadsManager Details.");
		// Check user exists or not
		LeadsManager leadsManagers = leadsManagerDao.getLeadsManagerById(leadsManagerDTO.getId());
		LeadsManagerDTO dbLeadsManagerDTO = LeadsManagerConverter.getLeadsManagerDTOByLeadsManager(leadsManagers);
		
		if (null != leadsManagerDTO.getEmail())
			dbLeadsManagerDTO.setEmail(leadsManagerDTO.getEmail());
		if (null != leadsManagerDTO.getName())
			dbLeadsManagerDTO.setName(leadsManagerDTO.getName());
		if (null != leadsManagerDTO.getPhoneNumber())
			dbLeadsManagerDTO.setPhoneNumber(leadsManagerDTO.getPhoneNumber());
		if (null != leadsManagerDTO.getCollegeId())
			dbLeadsManagerDTO.setCollegeId(leadsManagerDTO.getCollegeId());
		
		dbLeadsManagerDTO.setUpdatedBy(leadsManagerDTO.getUpdatedBy());
		dbLeadsManagerDTO.setUpdatedDate(leadsManagerDTO.getUpdatedDate());
		leadsManagerDao.saveLeadsManager(dbLeadsManagerDTO);
		LOGGER.info("LeadsManager details for leadsManager id " + leadsManagerDTO.getId() + " are updated successfully.");
			
	}

	@Override
	public void deleteLeadsManager(LeadsManagerDTO leadsManagerDTO) {
		List<Role> roles = loginService.getAllUserRoles(leadsManagerDTO.getUpdatedBy());
		boolean adminAcccess = roles.stream().anyMatch(x -> x.getRole().equals(RoleEnum.SUPER_ADMIN.getRole())
				|| x.getRole().equals(RoleEnum.ADMIN.getRole()));
		if (!adminAcccess) {
	        // Check if the logged-in leads manager has permission to delete status
			if (!leadsManagerDTO.getId().equals(leadsManagerDTO.getUpdatedBy())) 
				throw new UnAuthorizedException("Logged-in leads manager doesn't have permission to delete status.");
		}
		
		LeadsManager leadsManagers = leadsManagerDao.getLeadsManagerById(leadsManagerDTO.getId());
		LeadsManagerDTO dbLeadsManagerDTO = LeadsManagerConverter.getLeadsManagerDTOByLeadsManager(leadsManagers);
		
	    // Perform deletion logic here, such as updating the status field in the database
		dbLeadsManagerDTO.setStatus(leadsManagerDTO.getStatus());
		dbLeadsManagerDTO.setUpdatedBy(leadsManagerDTO.getUpdatedBy());
		dbLeadsManagerDTO.setUpdatedDate(leadsManagerDTO.getUpdatedDate());
		LOGGER.info("Status for leads manager with ID " + leadsManagerDTO.getStatus() + " is deleted successfully.");
	}
}


