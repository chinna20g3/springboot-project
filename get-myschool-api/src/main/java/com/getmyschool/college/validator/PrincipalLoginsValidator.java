package com.getmyschool.college.validator;

import java.util.Arrays; 
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.getmyschool.common.contant.Constant;
import com.getmyschool.common.dto.PrincipalLoginsDTO;
import com.getmyschool.common.utils.DateUtils;
import com.getmyschool.common.utils.UserUtils;
import com.getmyschool.common.validator.CustomValidator;

public class PrincipalLoginsValidator implements Validator {

	private static final String BAD_REQUEST_ERROR_CD = Constant.BAD_REQUEST_ERROR_CD;
	private static final Pattern VALID_EAMIL_PATTERN = Pattern.compile(Constant.EMAIL_PATTERN);
	private static final List<String> VALID_UPDATE_STATUS = Arrays.asList(Constant.STATUS_ACTIVE,
			Constant.STATUS_DELEATED);
	@Autowired
	private UserUtils userUtils;
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return PrincipalLoginsDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		PrincipalLoginsDTO principalLoginsDTO =(PrincipalLoginsDTO) target;
		savePrincipalLogins(principalLoginsDTO, errors);	
	}

	public void savePrincipalLogins(PrincipalLoginsDTO principalLoginsDTO, Errors errors) {
		Long logedUserid = userUtils.getLogedInUser();
		String createdTime = DateUtils.getAsiaLocalDateTimeInCustomFormat();
		
		if (CustomValidator.isEmpty(principalLoginsDTO.getFullName()))
			errors.rejectValue("principalLogins", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
		
		if (CustomValidator.isEmpty(principalLoginsDTO.getSchoolId()))
			errors.rejectValue("principalLogins", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");

		if (!CustomValidator.isValidPattern(VALID_EAMIL_PATTERN, principalLoginsDTO.getEmail()))
			errors.rejectValue("email", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");

		principalLoginsDTO.setStatus(Constant.STATUS_ACTIVE);
		principalLoginsDTO.setCreatedBy(logedUserid);
		principalLoginsDTO.setCreatedDate(createdTime);		
	}

	public void getAllPrincipalLogins(PrincipalLoginsDTO principalLoginsDTO, Errors errors) {
		Long logedUserid = userUtils.getLogedInUser();
		String createdTime = DateUtils.getAsiaLocalDateTimeInCustomFormat();
		if (null == principalLoginsDTO.getStatus())
			principalLoginsDTO.setStatus(Constant.STATUS_ACTIVE);
		principalLoginsDTO.setUpdatedBy(logedUserid);
		principalLoginsDTO.setUpdatedDate(createdTime);
	}

	public void getPrincipalLoginsById(PrincipalLoginsDTO principalLoginsDTO, Errors errors) {
		Long logedUserid = userUtils.getLogedInUser();
		String createdTime = DateUtils.getAsiaLocalDateTimeInCustomFormat();
		if (null == principalLoginsDTO.getId())
			 errors.rejectValue("id", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
		principalLoginsDTO.setUpdatedBy(logedUserid);
		principalLoginsDTO.setUpdatedDate(createdTime);
	}
	
	public void updatePrincipalLogins(PrincipalLoginsDTO principalLoginsDTO, Errors errors) {
		Long logedUserid = userUtils.getLogedInUser();
		String createdTime = DateUtils.getAsiaLocalDateTimeInCustomFormat();
		if (null == principalLoginsDTO.getId())
			 errors.rejectValue("id", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
		if (null != principalLoginsDTO.getStatus() && !VALID_UPDATE_STATUS.contains(principalLoginsDTO.getStatus()))
			 errors.rejectValue("status", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
		if (null == principalLoginsDTO.getFullName())
			 errors.rejectValue("fullName", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
		
		if (null == principalLoginsDTO.getSchoolId())
			 errors.rejectValue("schoolId", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");

		if (null == principalLoginsDTO.getEmail())
			 errors.rejectValue("email", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");

		principalLoginsDTO.setUpdatedBy(logedUserid);
		principalLoginsDTO.setUpdatedDate(createdTime);
	
	}
	
	public void deletePrincipalLogins(PrincipalLoginsDTO principalLoginsDTO, Errors errors) {
		Long logedUserid = userUtils.getLogedInUser();
		String createdTime = DateUtils.getAsiaLocalDateTimeInCustomFormat();
		String status = "In_Active";
		 if (null != principalLoginsDTO.getStatus() && !VALID_UPDATE_STATUS.contains(principalLoginsDTO.getStatus()))
				errors.rejectValue("status", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
				
		principalLoginsDTO.setStatus(status);
		principalLoginsDTO.setUpdatedBy(logedUserid);
		principalLoginsDTO.setUpdatedDate(createdTime);
	}
	
}
