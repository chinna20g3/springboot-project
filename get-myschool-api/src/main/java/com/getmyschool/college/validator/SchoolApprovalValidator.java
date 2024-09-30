package com.getmyschool.college.validator;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.getmyschool.common.contant.Constant;
import com.getmyschool.common.dto.SchoolApprovalDTO;
import com.getmyschool.common.utils.DateUtils;
import com.getmyschool.common.utils.UserUtils;
import com.getmyschool.common.validator.CustomValidator;

public class SchoolApprovalValidator implements Validator{
	
    private static final String BAD_REQUEST_ERROR_CD = Constant.BAD_REQUEST_ERROR_CD;
	
	private static final List<String> VALID_UPDATE_STATUS = Arrays.asList(Constant.STATUS_ACTIVE,
			Constant.STATUS_DELEATED);

	@Autowired
	private UserUtils userUtils;
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return SchoolApprovalDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		SchoolApprovalDTO schoolApprovalDTO =(SchoolApprovalDTO) target;
		saveSchoolApproval(schoolApprovalDTO, errors);
	}

	public void saveSchoolApproval(SchoolApprovalDTO schoolApprovalDTO, Errors errors) {
		Long logedUserid = userUtils.getLogedInUser();
		String createdTime = DateUtils.getAsiaLocalDateTimeInCustomFormat();
		
		if(CustomValidator.isEmpty(schoolApprovalDTO.getSchoolId()))
			errors.rejectValue("school", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
		  
	    if(CustomValidator.isEmpty(schoolApprovalDTO.getApprovedBy()))
	    	errors.rejectValue("school", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
		schoolApprovalDTO.setStatus(Constant.STATUS_ACTIVE);
		schoolApprovalDTO.setCreatedDate(createdTime);
		schoolApprovalDTO.setCreatedBy(logedUserid);
	}
	
	public void getAllSchoolApproval(SchoolApprovalDTO schoolApprovalDTO, Errors errors) {
		Long logedUserid = userUtils.getLogedInUser();
		String createdTime = DateUtils.getAsiaLocalDateTimeInCustomFormat();
		
		if (null == schoolApprovalDTO.getStatus())
			schoolApprovalDTO.setStatus(Constant.STATUS_ACTIVE);
 	  	    
		schoolApprovalDTO.setUpdatedDate(createdTime);
		schoolApprovalDTO.setUpdatedBy(logedUserid);		
	}
	
	public void getSchoolApprovalById(SchoolApprovalDTO schoolApprovalDTO, Errors errors) {
		Long logedUserid = userUtils.getLogedInUser();
		String createdTime = DateUtils.getAsiaLocalDateTimeInCustomFormat();
		
		if (null == schoolApprovalDTO.getId())
			errors.rejectValue("id", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
 	  	    
		schoolApprovalDTO.setUpdatedDate(createdTime);
		schoolApprovalDTO.setUpdatedBy(logedUserid);				
	}
	
	public void updateSchoolApproval(SchoolApprovalDTO schoolApprovalDTO, Errors errors) {
		Long logedUserid = userUtils.getLogedInUser();
		String createdTime = DateUtils.getAsiaLocalDateTimeInCustomFormat();
		
		if (CustomValidator.isEmpty(schoolApprovalDTO.getId()))
			errors.rejectValue("id", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
		if (null != schoolApprovalDTO.getStatus() && !VALID_UPDATE_STATUS.contains(schoolApprovalDTO.getStatus()))
			errors.rejectValue("status", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
		if (null == schoolApprovalDTO.getApprovedBy())
			errors.rejectValue("approvedBy", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
		if (null == schoolApprovalDTO.getSchoolId())
			errors.rejectValue("schoolId", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
		
		schoolApprovalDTO.setUpdatedDate(createdTime);
		schoolApprovalDTO.setUpdatedBy(logedUserid);		
	}
	
	public void deleteSchoolApproval(SchoolApprovalDTO schoolApprovalDTO, Errors errors) {
		Long logedUserid = userUtils.getLogedInUser();
		String createdTime = DateUtils.getAsiaLocalDateTimeInCustomFormat();
		String status ="In_Active";
		
		if (null != schoolApprovalDTO.getStatus() && !VALID_UPDATE_STATUS.contains(schoolApprovalDTO.getStatus()))
			errors.rejectValue("status", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");		
		
		schoolApprovalDTO.setStatus(status);
		schoolApprovalDTO.setUpdatedDate(createdTime);
		schoolApprovalDTO.setUpdatedBy(logedUserid);
	}
	
}


