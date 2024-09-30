package com.getmyschool.college.validator;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.getmyschool.common.contant.Constant;
import com.getmyschool.common.dto.PartnerLoginsDTO;
import com.getmyschool.common.utils.DateUtils;
import com.getmyschool.common.utils.UserUtils;
import com.getmyschool.common.validator.CustomValidator;

public class PartnerLoginsValidator implements Validator {

	private static final String BAD_REQUEST_ERROR_CD = Constant.BAD_REQUEST_ERROR_CD;
	private static final Pattern VALID_EAMIL_PATTERN = Pattern.compile(Constant.EMAIL_PATTERN);
	private static final List<String> VALID_UPDATE_STATUS = Arrays.asList(Constant.STATUS_ACTIVE,
			Constant.STATUS_DELEATED);
	@Autowired
	private  UserUtils userUtils;
	@Override
	public boolean supports(Class<?> clazz) {
		return PartnerLoginsDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		PartnerLoginsDTO partnerLoginsDTO =(PartnerLoginsDTO) target;
		savePartnerLogins(partnerLoginsDTO, errors);
	}

	public void savePartnerLogins(PartnerLoginsDTO partnerLoginsDTO, Errors errors) {
		Long logedUserId = userUtils.getLogedInUser();
		String createdTime = DateUtils.getAsiaLocalDateTimeInCustomFormat();
		
		if (CustomValidator.isEmpty(partnerLoginsDTO.getFullName()))
			errors.rejectValue("fullName", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
		if (CustomValidator.isEmpty(partnerLoginsDTO.getSchoolId()))
			errors.rejectValue("schoolId", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
		if (!CustomValidator.isValidPattern(VALID_EAMIL_PATTERN, partnerLoginsDTO.getEmail()))
			errors.rejectValue("email", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");

		partnerLoginsDTO.setStatus(Constant.STATUS_ACTIVE);
		partnerLoginsDTO.setCreatedDate(createdTime);
		partnerLoginsDTO.setCreatedBy(logedUserId);
	}
	
	public void getAllPartnerLogins(PartnerLoginsDTO partnerLoginsDTO, Errors errors) {
		Long logedUserId = userUtils.getLogedInUser();
		String createdTime = DateUtils.getAsiaLocalDateTimeInCustomFormat();
		if (null == partnerLoginsDTO.getStatus())
			partnerLoginsDTO.setStatus(Constant.STATUS_ACTIVE);
		partnerLoginsDTO.setUpdatedDate(createdTime);
		partnerLoginsDTO.setUpdatedBy(logedUserId);
	}
	
	public void getPartnerLoginsById(PartnerLoginsDTO partnerLoginsDTO, Errors errors) {
		Long logedUserId = userUtils.getLogedInUser();
		String createdTime = DateUtils.getAsiaLocalDateTimeInCustomFormat();

		if (CustomValidator.isEmpty(partnerLoginsDTO.getId()))
			errors.rejectValue("id", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
		partnerLoginsDTO.setUpdatedDate(createdTime);
		partnerLoginsDTO.setUpdatedBy(logedUserId);
	}
	public void updatePartnerLogins(PartnerLoginsDTO partnerLoginsDTO, Errors errors) {
		Long logedUserId = userUtils.getLogedInUser();
		String createdTime = DateUtils.getAsiaLocalDateTimeInCustomFormat();
		
		if (CustomValidator.isEmpty(partnerLoginsDTO.getId()))
			errors.rejectValue("id", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
		
		if (null != partnerLoginsDTO.getStatus() && !VALID_UPDATE_STATUS.contains(partnerLoginsDTO.getStatus()))
			errors.rejectValue("status", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
		if (null == partnerLoginsDTO.getFullName())
			 errors.rejectValue("fullName", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
		if (null == partnerLoginsDTO.getSchoolId())
			 errors.rejectValue("schoolId", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
		if (null == partnerLoginsDTO.getEmail())
			 errors.rejectValue("email", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");

		partnerLoginsDTO.setStatus(Constant.STATUS_ACTIVE);
		partnerLoginsDTO.setUpdatedDate(createdTime);
		partnerLoginsDTO.setCreatedBy(logedUserId);
}
	public void deletePartnerLogins(PartnerLoginsDTO partnerLoginsDTO, Errors errors) {
		Long logedUserId = userUtils.getLogedInUser();
		String createdTime = DateUtils.getAsiaLocalDateTimeInCustomFormat();
		String status = "Inactive";
		if (null != partnerLoginsDTO.getStatus() && !VALID_UPDATE_STATUS.contains(partnerLoginsDTO.getStatus()))
			errors.rejectValue("status", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
        partnerLoginsDTO.setStatus(status);
		partnerLoginsDTO.setCreatedDate(createdTime);
		partnerLoginsDTO.setUpdatedBy(logedUserId);
	}
}
