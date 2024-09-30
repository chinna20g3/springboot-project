package com.getmyschool.college.validator;

import java.util.Arrays;  
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.getmyschool.common.contant.Constant;
import com.getmyschool.common.dto.SchoolTypeDTO;
import com.getmyschool.common.utils.DateUtils;
import com.getmyschool.common.utils.UserUtils;
import com.getmyschool.common.validator.CustomValidator;

public class SchoolTypeValidator implements Validator {

	private static final String BAD_REQUEST_ERROR_CD = Constant.BAD_REQUEST_ERROR_CD;

	private static final List<String> VALID_UPDATE_STATUS = Arrays.asList(Constant.STATUS_ACTIVE,
			Constant.STATUS_DELEATED);
	@Autowired
	private UserUtils userUtils;

	@Override
	public boolean supports(Class<?> clazz) {
		return SchoolTypeDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		SchoolTypeDTO schoolTypeDTO = (SchoolTypeDTO) target;
		saveSchoolType(schoolTypeDTO, errors);
	}

	public void saveSchoolType(SchoolTypeDTO schoolTypeDTO, Errors errors) {
		Long logedUserId = userUtils.getLogedInUser();
		String createdTime = DateUtils.getAsiaLocalDateTimeInCustomFormat();

		if (CustomValidator.isEmpty(schoolTypeDTO.getSchoolType()))
			errors.rejectValue("schoolType", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
		
		schoolTypeDTO.setStatus(Constant.STATUS_ACTIVE);
		schoolTypeDTO.setCreatedDate(createdTime);
		schoolTypeDTO.setCreatedBy(logedUserId);
	}

	public void getAllSchoolTypes(SchoolTypeDTO schoolTypeDTO, Errors errors) {
		Long logedUserid = userUtils.getLogedInUser();
		String createdTime = DateUtils.getAsiaLocalDateTimeInCustomFormat();

		if (null == schoolTypeDTO.getStatus())
			schoolTypeDTO.setStatus(Constant.STATUS_ACTIVE);

		schoolTypeDTO.setUpdatedDate(createdTime);
		schoolTypeDTO.setUpdatedBy(logedUserid);		
		
	}

	public void getSchoolTypeById(SchoolTypeDTO schoolTypeDTO, Errors errors) {
		Long logedUserid = userUtils.getLogedInUser();
		String createdTime = DateUtils.getAsiaLocalDateTimeInCustomFormat();

		if (CustomValidator.isEmpty(schoolTypeDTO.getId()))
			errors.rejectValue("id", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");

		schoolTypeDTO.setUpdatedDate(createdTime);
		schoolTypeDTO.setUpdatedBy(logedUserid);	
	}

	public void updateSchoolType(SchoolTypeDTO schoolTypeDTO, Errors errors) {
		Long logedUserid = userUtils.getLogedInUser();
		String createdTime = DateUtils.getAsiaLocalDateTimeInCustomFormat();

		if (CustomValidator.isEmpty(schoolTypeDTO.getId()))
			errors.rejectValue("id", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
		
		if (null != schoolTypeDTO.getStatus() && !VALID_UPDATE_STATUS.contains(schoolTypeDTO.getStatus()))
			errors.rejectValue("status", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
		if (null == schoolTypeDTO.getSchoolType())
			errors.rejectValue("schoolType", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
		
		schoolTypeDTO.setUpdatedDate(createdTime);
		schoolTypeDTO.setUpdatedBy(logedUserid);	
	}
}
