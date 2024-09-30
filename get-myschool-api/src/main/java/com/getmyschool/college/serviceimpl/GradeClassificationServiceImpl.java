package com.getmyschool.college.serviceimpl;

import java.util.ArrayList;  
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.getmyschool.college.service.GradeClassificationService;
import com.getmyschool.common.converter.GradeClassificationConverter;
import com.getmyschool.common.dao.GradeClassificationDao;
import com.getmyschool.common.domain.GradeClassification;
import com.getmyschool.common.domain.Role;
import com.getmyschool.common.dto.GradeClassificationDTO;
import com.getmyschool.common.exception.UnAuthorizedException;
import com.getmyschool.common.service.LoginService;
import com.getmyschool.common.validator.RoleEnum;

@Service("GradeClassificationServiceImpl")
public class GradeClassificationServiceImpl implements GradeClassificationService {

	@Resource(name = "LoginServiceImpl")
	private LoginService loginService;

	@Resource(name =  "GradeClassificationDaoImpl")
	private GradeClassificationDao gradeClassificationDao;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GradeClassificationServiceImpl.class);


	@Override
	public void saveGradeClassification(GradeClassificationDTO gradeClassificationDTO) {
		List<Role> roles = loginService.getAllUserRoles(gradeClassificationDTO.getCreatedBy());
		boolean adminFlag = roles.stream().anyMatch(x -> x.getRole().equals(RoleEnum.SUPER_ADMIN.getRole())
				|| x.getRole().equals(RoleEnum.ADMIN.getRole()));
		if (!(adminFlag)) {
			throw new UnAuthorizedException("LogedIn GradeClassification does't have permission to save GradeClassification Details.");
		}
		GradeClassification gradeClassification = gradeClassificationDao.saveGradeClassification(gradeClassificationDTO);
		LOGGER.info("gradeClassification added successfully with saveGradeClassification::" + gradeClassification.getGradeClassificationType());

	}


	@Override
	public List<GradeClassificationDTO> getAllGradeClassifications(GradeClassificationDTO gradeClassificationDTO) {
		 List<Role> roles = loginService.getAllUserRoles(gradeClassificationDTO.getUpdatedBy());
		  boolean adminAcccess = roles.stream().anyMatch(x -> x.getRole().equals(RoleEnum.SUPER_ADMIN.getRole())
				  || x.getRole().equals(RoleEnum.ADMIN.getRole()));
		  if (!adminAcccess) {
			  throw new UnAuthorizedException("Logged-in GradeClassification doesn't have permission to access all board details.");
		  }
		  List<GradeClassification> gradeClassificationList = gradeClassificationDao.getAllGradeClassification(gradeClassificationDTO);
		  List<GradeClassificationDTO> returnList = new ArrayList<>();
		  for (GradeClassification gradeClassification : gradeClassificationList) {
			  GradeClassificationDTO dbGradeClassificationDTO = GradeClassificationConverter.getGradeClassificationDTOByGradeClassification(gradeClassification);
		      returnList.add(dbGradeClassificationDTO);
		  }
		  return returnList;
	}


	@Override
	public GradeClassificationDTO getGradeClassificationById(GradeClassificationDTO gradeClassificationDTO) {
		List<Role> roles = loginService.getAllUserRoles(gradeClassificationDTO.getUpdatedBy());
		boolean adminAcccess = roles.stream().anyMatch(x -> x.getRole().equals(RoleEnum.SUPER_ADMIN.getRole())
				|| x.getRole().equals(RoleEnum.ADMIN.getRole()));
		if (!adminAcccess) {
			if (! gradeClassificationDTO.getId().equals( gradeClassificationDTO.getUpdatedBy()))
				throw new UnAuthorizedException("LogedIn GradeClassification does't have permission to get GradeClassification Details.");
		}
			GradeClassification  gradeClassification =  gradeClassificationDao.getGradeClassificationById(gradeClassificationDTO.getId());
			return  GradeClassificationConverter.getGradeClassificationDTOByGradeClassification( gradeClassification);
	}

	@Override
	public void updateGradeClassification(GradeClassificationDTO gradeClassificationDTO) {
		List<Role> roles = loginService.getAllUserRoles(gradeClassificationDTO.getUpdatedBy());
		boolean adminAcccess = roles.stream().anyMatch(x -> x.getRole().equals(RoleEnum.SUPER_ADMIN.getRole())
				|| x.getRole().equals(RoleEnum.ADMIN.getRole()));
		if (!adminAcccess) {
			if (!gradeClassificationDTO.getId().equals(gradeClassificationDTO.getUpdatedBy()))
				throw new UnAuthorizedException("LogedIn GradeClassification does't have permission to update GradeClassification Details.");
		}
		GradeClassification gradeClassifications =gradeClassificationDao.getGradeClassificationById(gradeClassificationDTO.getId());
		GradeClassificationDTO dbGradeClassificationDTO = GradeClassificationConverter.getGradeClassificationDTOByGradeClassification(gradeClassifications);
		
		if (null != gradeClassificationDTO.getGradeClassificationType())
			dbGradeClassificationDTO.setGradeClassificationType(gradeClassificationDTO.getGradeClassificationType());
		
		dbGradeClassificationDTO.setUpdatedBy(gradeClassificationDTO.getUpdatedBy());
		dbGradeClassificationDTO.setUpdatedDate(gradeClassificationDTO.getUpdatedDate());

		gradeClassificationDao.saveGradeClassification(dbGradeClassificationDTO);
		LOGGER.info("GradeClassification details for gradeClassification id " + gradeClassificationDTO.getId() + " are updated successfully.");
	}
}



