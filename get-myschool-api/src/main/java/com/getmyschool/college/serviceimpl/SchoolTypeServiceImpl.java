package com.getmyschool.college.serviceimpl;

import java.util.ArrayList;   
import java.util.List; 

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.getmyschool.college.service.SchoolTypeService;
import com.getmyschool.common.converter.SchoolTypeConverter;
import com.getmyschool.common.dao.SchoolTypeDao;
import com.getmyschool.common.domain.Role;
import com.getmyschool.common.domain.SchoolType;
import com.getmyschool.common.dto.SchoolTypeDTO;
import com.getmyschool.common.exception.UnAuthorizedException;
import com.getmyschool.common.service.LoginService;
import com.getmyschool.common.validator.RoleEnum;

@Service("SchoolTypeServiceImpl")
public class SchoolTypeServiceImpl implements SchoolTypeService {

	@Resource(name = "LoginServiceImpl")
	private LoginService loginService;

	@Resource(name = "SchoolTypeDaoImpl")
	private SchoolTypeDao schoolTypeDao;

	private static final Logger LOGGER = LoggerFactory.getLogger(SchoolTypeServiceImpl.class);

	@Override
	public void saveSchoolType(SchoolTypeDTO schoolTypeDTO) {
		List<Role> roles = loginService.getAllUserRoles(schoolTypeDTO.getCreatedBy());
		boolean adminFlag = roles.stream().anyMatch(x -> x.getRole().equals(RoleEnum.SUPER_ADMIN.getRole())
				|| x.getRole().equals(RoleEnum.ADMIN.getRole()));
		if (!(adminFlag)) {
			throw new UnAuthorizedException("LogedIn SchoolType does't have permission to save SchoolType Details.");
		}

		SchoolType schoolType = schoolTypeDao.saveSchoolType(schoolTypeDTO);
		LOGGER.info("schoolType added successfully with saveSchoolType::" + schoolType.getSchoolType());

	}

	@Override
	public List<SchoolTypeDTO> getAllSchoolTypes(SchoolTypeDTO schoolTypeDTO) {
		 List<Role> roles = loginService.getAllUserRoles(schoolTypeDTO.getUpdatedBy());
		 boolean adminAccess = roles.stream().anyMatch(x -> x.getRole().equals(RoleEnum.SUPER_ADMIN.getRole())
				 || x.getRole().equals(RoleEnum.ADMIN.getRole()));
		 if (!adminAccess) {
			 throw new UnAuthorizedException("Logged-in SchoolType doesn't have permission to access all schoolType details.");
		 }
		 List<SchoolType> schoolTypeList = schoolTypeDao.getAllSchoolType(schoolTypeDTO);
		 List<SchoolTypeDTO> returnList = new ArrayList<>();
		 for (SchoolType schoolType : schoolTypeList) {
			 SchoolTypeDTO dbSchoolTypeDTO = SchoolTypeConverter.getSchoolTypeDTOBySchoolType(schoolType);
		     returnList.add(dbSchoolTypeDTO);
	     }
		 return returnList;
	}

	@Override
	public SchoolTypeDTO getSchoolTypeById(SchoolTypeDTO schoolTypeDTO) {
		List<Role> roles = loginService.getAllUserRoles(schoolTypeDTO.getUpdatedBy());
		boolean adminAccess = roles.stream().anyMatch(x -> x.getRole().equals(RoleEnum.SUPER_ADMIN.getRole())
				|| x.getRole().equals(RoleEnum.ADMIN.getRole()));
		if (!adminAccess) {
			if (!schoolTypeDTO.getId().equals(schoolTypeDTO.getUpdatedBy()))
				throw new UnAuthorizedException("LogedIn schoolType does't have permission to get SchoolType Details.");
		}
		SchoolType schoolType = schoolTypeDao.getSchoolTypeById(schoolTypeDTO.getId());
		return SchoolTypeConverter.getSchoolTypeDTOBySchoolType(schoolType);
	}

	@Override
	public void updateSchoolType(SchoolTypeDTO schoolTypeDTO) {
		List<Role> roles = loginService.getAllUserRoles(schoolTypeDTO.getUpdatedBy());
		boolean adminAccess = roles.stream().anyMatch(x -> x.getRole().equals(RoleEnum.SUPER_ADMIN.getRole())
				|| x.getRole().equals(RoleEnum.ADMIN.getRole()));
		if (!adminAccess) {
			if (!schoolTypeDTO.getId().equals(schoolTypeDTO.getUpdatedBy()))
				throw new UnAuthorizedException("LogedIn schoolType does't have permission to update schoolType Details.");
		}
		
		SchoolType schoolTypes =schoolTypeDao.getSchoolTypeById(schoolTypeDTO.getId());
		SchoolTypeDTO dbSchoolTypeDTO = SchoolTypeConverter.getSchoolTypeDTOBySchoolType(schoolTypes);
		
		if (null != schoolTypeDTO.getSchoolType())
			dbSchoolTypeDTO.setSchoolType(schoolTypeDTO.getSchoolType());
		
		dbSchoolTypeDTO.setUpdatedBy(schoolTypeDTO.getUpdatedBy());
		dbSchoolTypeDTO.setUpdatedDate(schoolTypeDTO.getUpdatedDate());
		schoolTypeDao.saveSchoolType(dbSchoolTypeDTO);
		LOGGER.info("SchoolType details for schoolType id " + schoolTypeDTO.getSchoolType() + " are updated successfully.");
		
	}

}
