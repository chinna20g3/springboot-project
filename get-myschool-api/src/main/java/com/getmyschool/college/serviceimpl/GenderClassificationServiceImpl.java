package com.getmyschool.college.serviceimpl;

import java.util.ArrayList;  
import java.util.List; 

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.getmyschool.college.service.GenderClassificationService;
import com.getmyschool.common.converter.GenderClassificationConverter;
import com.getmyschool.common.dao.GenderClassificationDao;
import com.getmyschool.common.domain.GenderClassification;
import com.getmyschool.common.domain.Role;
import com.getmyschool.common.dto.GenderClassificationDTO;
import com.getmyschool.common.exception.UnAuthorizedException;
import com.getmyschool.common.service.LoginService;
import com.getmyschool.common.validator.RoleEnum;

@Service("GenderClassificationServiceImpl")
public class GenderClassificationServiceImpl implements GenderClassificationService {

	@Resource(name = "LoginServiceImpl")
	private LoginService loginService;

	@Resource(name =  "GenderClassificationDaoImpl")
	private GenderClassificationDao genderClassificationDao;
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GenderClassificationServiceImpl.class);


	@Override
	public void saveGenderClassification(GenderClassificationDTO genderClassificationDTO) {
		List<Role> roles = loginService.getAllUserRoles(genderClassificationDTO.getCreatedBy());
		boolean adminFlag = roles.stream().anyMatch(x -> x.getRole().equals(RoleEnum.SUPER_ADMIN.getRole())
				|| x.getRole().equals(RoleEnum.ADMIN.getRole()));
		if (!(adminFlag)) {
			throw new UnAuthorizedException("LogedIn GenderClassification does't have permission to save GenderClassification Details.");
		}
		GenderClassification genderClassification = genderClassificationDao.saveGenderClassification(genderClassificationDTO);
		LOGGER.info("genderClassification added successfully with saveGenderClassification::" + genderClassification.getGenderClassificationType());

	}


	@Override
	public List<GenderClassificationDTO> getAllGenderClassifications(GenderClassificationDTO genderClassificationDTO) {
		List<Role> roles = loginService.getAllUserRoles(genderClassificationDTO.getUpdatedBy());
		boolean adminAcccess = roles.stream().anyMatch(x -> x.getRole().equals(RoleEnum.SUPER_ADMIN.getRole())
				|| x.getRole().equals(RoleEnum.ADMIN.getRole()));
		if (!adminAcccess) {
			throw new UnAuthorizedException("Logged-in GenderClassification doesn't have permission to access all board details.");
	    }
		List<GenderClassification> genderClassificationList = genderClassificationDao.getAllGenderClassification(genderClassificationDTO);
		List<GenderClassificationDTO> returnList = new ArrayList<>();
		for (GenderClassification genderClassification : genderClassificationList) {
			GenderClassificationDTO dbGenderClassificationDTO = GenderClassificationConverter.getGenderClassificationDTOByGenderClassification(genderClassification);
		    returnList.add(dbGenderClassificationDTO);
	    }
		return returnList;
	}

	@Override
	public GenderClassificationDTO getGenderClassificationById(GenderClassificationDTO genderClassificationDTO) {
		List<Role> roles = loginService.getAllUserRoles(genderClassificationDTO.getUpdatedBy());
		boolean adminAcccess = roles.stream().anyMatch(x -> x.getRole().equals(RoleEnum.SUPER_ADMIN.getRole())
				|| x.getRole().equals(RoleEnum.ADMIN.getRole()));
		if (!adminAcccess) {
			if (!genderClassificationDTO.getId().equals(genderClassificationDTO.getUpdatedBy()))
				throw new UnAuthorizedException("LogedIn GenderClassification does't have permission to get GenderClassification Details.");
		}
			GenderClassification genderClassification = genderClassificationDao.getGenderClassificationById(genderClassificationDTO.getId());
			return GenderClassificationConverter.getGenderClassificationDTOByGenderClassification(genderClassification);
	}


	@Override
	public void updateGenderClassification(GenderClassificationDTO genderClassificationDTO) {
		List<Role> roles = loginService.getAllUserRoles(genderClassificationDTO.getUpdatedBy());
		boolean adminAcccess = roles.stream().anyMatch(x -> x.getRole().equals(RoleEnum.SUPER_ADMIN.getRole())
				|| x.getRole().equals(RoleEnum.ADMIN.getRole()));
		if (!adminAcccess) {
			if (!genderClassificationDTO.getId().equals(genderClassificationDTO.getUpdatedBy()))
				throw new UnAuthorizedException("LogedIn GenderClassification does't have permission to update genderClassification Details.");
		}
		GenderClassification genderClassifications =genderClassificationDao.getGenderClassificationById(genderClassificationDTO.getId());
		GenderClassificationDTO dbGenderClassificationDTO = GenderClassificationConverter.getGenderClassificationDTOByGenderClassification(genderClassifications);
		
		if (null != genderClassificationDTO.getGenderClassificationType())
			dbGenderClassificationDTO.setGenderClassificationType(genderClassificationDTO.getGenderClassificationType());
		
		dbGenderClassificationDTO.setUpdatedBy(genderClassificationDTO.getUpdatedBy());
		dbGenderClassificationDTO.setUpdatedDate(genderClassificationDTO.getUpdatedDate());

		genderClassificationDao.saveGenderClassification(dbGenderClassificationDTO);
		LOGGER.info("GenderClassification details for genderClassification id " + genderClassificationDTO.getId() + " are updated successfully.");
		
	}
}




