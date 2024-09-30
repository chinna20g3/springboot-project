package com.getmyschool.college.serviceimpl;

import java.util.ArrayList;  
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.getmyschool.college.service.SchoolService;
import com.getmyschool.common.converter.SchoolConverter;
import com.getmyschool.common.dao.SchoolDao;
import com.getmyschool.common.domain.Role;
import com.getmyschool.common.domain.School;
import com.getmyschool.common.dto.SchoolDTO;
import com.getmyschool.common.exception.UnAuthorizedException;
import com.getmyschool.common.service.LoginService;
import com.getmyschool.common.validator.RoleEnum;

@Service("SchoolServiceImpl")
public class SchoolServiceImpl implements SchoolService {

	@Resource(name = "LoginServiceImpl")
	private LoginService loginService;

	@Resource(name = "SchoolDaoImpl")
	private SchoolDao schoolDao;

	private static final Logger LOGGER = LoggerFactory.getLogger(SchoolServiceImpl.class);

	@Override
	public void saveSchool(SchoolDTO schoolDTO) {
		List<Role> roles = loginService.getAllUserRoles(schoolDTO.getCreatedBy());
		boolean adminFlag = roles.stream().anyMatch(x -> x.getRole().equals(RoleEnum.SUPER_ADMIN.getRole())
				|| x.getRole().equals(RoleEnum.ADMIN.getRole()));
		if (!(adminFlag)) {
			throw new UnAuthorizedException("LogedIn School does't have permission to save School Details.");
		}
		School school = schoolDao.saveSchool(schoolDTO);
		LOGGER.info("School added successfully with saveSchool::" + school.getSchoolName());
		
	}

	@Override
	public List<SchoolDTO> getAllSchools(SchoolDTO schoolDTO) {
		List<Role> roles = loginService.getAllUserRoles(schoolDTO.getUpdatedBy());
	    boolean adminAcccess = roles.stream().anyMatch(x -> x.getRole().equals(RoleEnum.SUPER_ADMIN.getRole())
	            || x.getRole().equals(RoleEnum.ADMIN.getRole()));

	    if (!adminAcccess) {
	        throw new UnAuthorizedException("Logged-in School doesn't have permission to access all school details.");
	    }

	    List<School> schoolList = schoolDao.getAllSchool(schoolDTO);
	    List<SchoolDTO> returnList = new ArrayList<>();
	    
	    for (School school : schoolList) {
	    	SchoolDTO dbSchoolDTO = SchoolConverter.getSchoolDTOBySchool(school);
	        returnList.add(dbSchoolDTO);
	    }
	    
	    return returnList;
	}

	@Override
	public SchoolDTO getSchoolById(SchoolDTO schoolDTO) {
		List<Role> roles = loginService.getAllUserRoles(schoolDTO.getUpdatedBy());
		boolean adminAcccess = roles.stream().anyMatch(x -> x.getRole().equals(RoleEnum.SUPER_ADMIN.getRole())
				|| x.getRole().equals(RoleEnum.ADMIN.getRole()));
		if (!adminAcccess) {
			if (!schoolDTO.getId().equals(schoolDTO.getUpdatedBy()))
				throw new UnAuthorizedException("LogedIn School does't have permission to get School Details.");
		}
		School school = schoolDao.getSchoolById(schoolDTO.getId());
		return SchoolConverter.getSchoolDTOBySchool(school);
	}

	@Override
    public void updateSchool(SchoolDTO schoolDTO) {	
		List<Role> roles = loginService.getAllUserRoles(schoolDTO.getUpdatedBy());
		boolean adminAcccess = roles.stream().anyMatch(x -> x.getRole().equals(RoleEnum.SUPER_ADMIN.getRole())
				|| x.getRole().equals(RoleEnum.ADMIN.getRole()));
		if (!adminAcccess) { 
			if (!schoolDTO.getId().equals(schoolDTO.getUpdatedBy()))
				throw new UnAuthorizedException("LogedIn School does't have permission to update School Details.");
		}
		School schools =schoolDao.getSchoolById(schoolDTO.getId());
		SchoolDTO dbSchoolDTO = SchoolConverter.getSchoolDTOBySchool(schools);
			
		if (null != schoolDTO.getSchoolName())
			dbSchoolDTO.setSchoolName(schoolDTO.getSchoolName());
		if (null != schoolDTO.getSchoolType())
			dbSchoolDTO.setSchoolType(schoolDTO.getSchoolType());
		if (null != schoolDTO.getOgUrl())
			dbSchoolDTO.setOgUrl(schoolDTO.getOgUrl());
			
		dbSchoolDTO.setUpdatedBy(schoolDTO.getUpdatedBy());
		dbSchoolDTO.setUpdatedDate(schoolDTO.getUpdatedDate());

		schoolDao.saveSchool(dbSchoolDTO);
		LOGGER.info("School details for school id " + schoolDTO.getId() + " are updated successfully.");
	}

}