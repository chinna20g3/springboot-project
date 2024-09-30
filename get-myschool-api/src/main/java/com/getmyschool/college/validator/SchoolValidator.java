package com.getmyschool.college.validator;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.getmyschool.common.contant.Constant;
import com.getmyschool.common.dto.SchoolDTO;
import com.getmyschool.common.utils.DateUtils;
import com.getmyschool.common.utils.UserUtils;
import com.getmyschool.common.validator.CustomValidator;

public  class SchoolValidator implements Validator {

	private static final String BAD_REQUEST_ERROR_CD = Constant.BAD_REQUEST_ERROR_CD;
	
	private static final List<String> VALID_UPDATE_STATUS = Arrays.asList(Constant.STATUS_ACTIVE,
			Constant.STATUS_DELEATED);
	@Autowired
	private UserUtils userUtils;

	@Override
	public boolean supports(Class<?> clazz) {
		return SchoolDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		SchoolDTO schoolDTO = (SchoolDTO) target;
		saveSchool(schoolDTO, errors);
	}

	public void saveSchool(SchoolDTO schoolDTO, Errors errors) {
		Long logedUserId = userUtils.getLogedInUser();
		String createdTime = DateUtils.getAsiaLocalDateTimeInCustomFormat();
		
		if (CustomValidator.isEmpty(schoolDTO.getSchoolName()))
			errors.rejectValue("schoolName", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
		if (CustomValidator.isEmpty(schoolDTO.getSchoolType()))
			errors.rejectValue("schoolType", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
		if (CustomValidator.isEmpty(schoolDTO.getOgUrl()))
			errors.rejectValue("ogUrl", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
	
		schoolDTO.setStatus(Constant.STATUS_ACTIVE);
		schoolDTO.setCreatedDate(createdTime);
		schoolDTO.setCreatedBy(logedUserId);
	}
	
	public void getAllSchools(SchoolDTO schoolDTO, Errors errors) {
		Long logedUserid = userUtils.getLogedInUser();
		String createdTime = DateUtils.getAsiaLocalDateTimeInCustomFormat();

		if (null == schoolDTO.getStatus())
			schoolDTO.setStatus(Constant.STATUS_ACTIVE);
		
		schoolDTO.setUpdatedDate(createdTime);
		schoolDTO.setUpdatedBy(logedUserid);	
	}

	public void getSchoolById(SchoolDTO schoolDTO, Errors errors) {

		Long logedUserid = userUtils.getLogedInUser();
		String createdTime = DateUtils.getAsiaLocalDateTimeInCustomFormat();

		if (CustomValidator.isEmpty(schoolDTO.getId()))
			errors.rejectValue("id", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");

		schoolDTO.setUpdatedDate(createdTime);
		schoolDTO.setUpdatedBy(logedUserid);
	}
	
	public void updateSchool(SchoolDTO schoolDTO, Errors errors) {
		Long logedUserid = userUtils.getLogedInUser();
		String createdTime = DateUtils.getAsiaLocalDateTimeInCustomFormat();
		
		if (CustomValidator.isEmpty(schoolDTO.getId()))
			errors.rejectValue("id", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
		if (null != schoolDTO.getStatus() && !VALID_UPDATE_STATUS.contains(schoolDTO.getStatus()))
			errors.rejectValue("status", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
		if (null == schoolDTO.getSchoolName())
			errors.rejectValue("schoolName", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
		if (null == schoolDTO.getSchoolType())
			errors.rejectValue("schoolType", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
		if (null == schoolDTO.getOgUrl())
			errors.rejectValue("ogUrl", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
		
		schoolDTO.setUpdatedDate(createdTime);
		schoolDTO.setUpdatedBy(logedUserid);
	}	
	
}
