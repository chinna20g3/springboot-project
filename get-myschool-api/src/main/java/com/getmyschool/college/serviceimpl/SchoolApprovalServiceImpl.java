package com.getmyschool.college.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.getmyschool.college.service.SchoolApprovalService;
import com.getmyschool.common.converter.SchoolApprovalConverter;
import com.getmyschool.common.dao.SchoolApprovalDao;
import com.getmyschool.common.domain.Role;
import com.getmyschool.common.domain.SchoolApproval;
import com.getmyschool.common.dto.SchoolApprovalDTO;
import com.getmyschool.common.exception.UnAuthorizedException;
import com.getmyschool.common.service.LoginService;
import com.getmyschool.common.validator.RoleEnum;

@Service("SchoolApprovalServiceImpl")
public class SchoolApprovalServiceImpl implements SchoolApprovalService {

	@Resource(name ="LoginServiceImpl")
	private LoginService loginService;
	
	@Resource(name = "SchoolApprovalDaoImpl")
	private SchoolApprovalDao schoolApprovalDao;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SchoolApprovalServiceImpl.class);

	
	@Override
	public void saveSchoolApproval(SchoolApprovalDTO schoolApprovalDTO) {
		List<Role> roles =loginService.getAllUserRoles(schoolApprovalDTO.getCreatedBy());
		boolean adminFlag =roles.stream().anyMatch(x ->x.getRole().equals(RoleEnum.SUPER_ADMIN.getRole())
				|| x.getRole().equals(RoleEnum.ADMIN.getRole()));
		if(!(adminFlag)) {
			throw new UnAuthorizedException("LogedIn SchoolApproval doesn't have permission to save SchoolApproval Details.");
		}
		SchoolApproval schoolApproval =schoolApprovalDao.saveSchoolApproval(schoolApprovalDTO);
		LOGGER.info("SchoolApproval added Successfully with saveSchoolApproval::" + schoolApproval.getSchoolId());		
	}

	@Override
	public List<SchoolApprovalDTO> getAllSchoolApproval(SchoolApprovalDTO schoolApprovalDTO) {
		List<Role> roles = loginService.getAllUserRoles(schoolApprovalDTO.getUpdatedBy());
		boolean adminAccess = roles.stream().anyMatch(x ->x.getRole().equals(RoleEnum.SUPER_ADMIN.getRole())
				||x.getRole().equals(RoleEnum.ADMIN.getRole()));
		if(!adminAccess) {
			throw new UnAuthorizedException("LogedIn SchoolApproval doesn't have permission to access all SchoolApproval Details.");
		}
		 List<SchoolApproval> schoolApprovalList = schoolApprovalDao.getAllSchoolApproval(schoolApprovalDTO);
		    List<SchoolApprovalDTO> returnList = new ArrayList<>();
		    
		    for (SchoolApproval schoolApproval : schoolApprovalList) {
		    	SchoolApprovalDTO dbSchoolApprovalDTO = SchoolApprovalConverter.getSchoolApprovalDTOBySchoolApproval(schoolApproval);
		        returnList.add(dbSchoolApprovalDTO);
		    }
		return returnList;
	}

	@Override
	public SchoolApprovalDTO getSchoolApprovalById(SchoolApprovalDTO schoolApprovalDTO) {
		List<Role> roles =loginService.getAllUserRoles(schoolApprovalDTO.getUpdatedBy());
		boolean adminAccess = roles.stream().anyMatch(x ->x.getRole().equals(RoleEnum.SUPER_ADMIN.getRole())
				||x.getRole().equals(RoleEnum.ADMIN.getRole()));
		if(!adminAccess) {
			if (!schoolApprovalDTO.getId().equals(schoolApprovalDTO.getUpdatedBy()))
			throw new UnAuthorizedException(" LogedIn SchoolApproval doesn't have permission to access id of SchoolApproval Details.");
		}	
		SchoolApproval schoolApproval = schoolApprovalDao.getSchoolApprovalById(schoolApprovalDTO.getId());
		return SchoolApprovalConverter.getSchoolApprovalDTOBySchoolApproval(schoolApproval);		
	}
		
	@Override
	public void updateSchoolApproval(SchoolApprovalDTO schoolApprovalDTO) {
		List<Role> roles= loginService.getAllUserRoles(schoolApprovalDTO.getUpdatedBy());
		boolean adminAccess =roles.stream().anyMatch(x->x.getRole().equals(RoleEnum.SUPER_ADMIN.getRole())
				||x.getRole().equals(RoleEnum.ADMIN.getRole()));
		if(!adminAccess) {
			if (!schoolApprovalDTO.getId().equals(schoolApprovalDTO.getUpdatedBy()))
			throw new UnAuthorizedException(" LogedIn SchoolApproval doesn't have permission to update SchoolApproval Details.");		
		}
		SchoolApproval schoolApprovals =schoolApprovalDao.getSchoolApprovalById(schoolApprovalDTO.getId());
		SchoolApprovalDTO dbSchoolApprovalDTO =SchoolApprovalConverter.getSchoolApprovalDTOBySchoolApproval(schoolApprovals);
		
		if(null !=schoolApprovalDTO.getSchoolId())
			dbSchoolApprovalDTO.setSchoolId(schoolApprovalDTO.getSchoolId());
		if(null !=schoolApprovalDTO.getApprovedBy())
			dbSchoolApprovalDTO.setApprovedBy(schoolApprovalDTO.getApprovedBy());
		if(null !=schoolApprovalDTO.getSchoolId())
			dbSchoolApprovalDTO.setSchoolId(schoolApprovalDTO.getSchoolId());
		
		dbSchoolApprovalDTO.setUpdatedBy(schoolApprovalDTO.getUpdatedBy());
		dbSchoolApprovalDTO.setUpdatedDate(schoolApprovalDTO.getUpdatedDate());

		schoolApprovalDao.saveSchoolApproval(dbSchoolApprovalDTO);
		LOGGER.info("SchoolApproval details for schoolApproval id " + schoolApprovalDTO.getId() + " are updated successfully.");
	}

	@Override
	public void deleteSchoolApproval(SchoolApprovalDTO schoolApprovalDTO) {
		List<Role> roles =loginService.getAllUserRoles(schoolApprovalDTO.getUpdatedBy());
		boolean adminAccess =roles.stream().anyMatch(x->x.getRole().equals(RoleEnum.SUPER_ADMIN.getRole())
				||x.getRole().equals(RoleEnum.ADMIN.getRole()));
		if(!adminAccess) {
			if(!schoolApprovalDTO.getId().equals(schoolApprovalDTO.getUpdatedBy())) 	
			throw new UnAuthorizedException("Login SchoolApproval doesn't have permission to delete SchoolApprovalDetails.");
		}		
		
		SchoolApproval schoolApprovals =schoolApprovalDao.getSchoolApprovalById(schoolApprovalDTO.getId());
		SchoolApprovalDTO dbSchoolApprovalDTO =SchoolApprovalConverter.getSchoolApprovalDTOBySchoolApproval(schoolApprovals);
		
		dbSchoolApprovalDTO.setStatus(schoolApprovalDTO.getStatus());
		dbSchoolApprovalDTO.setUpdatedBy(schoolApprovalDTO.getUpdatedBy());
		dbSchoolApprovalDTO.setUpdatedDate(schoolApprovalDTO.getUpdatedDate());
		LOGGER.info("SchoolApproval details for schoolApproval status " + schoolApprovalDTO.getStatus() + " are deleted successfully.");
	}	
}
