package com.getmyschool.college.validator;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.getmyschool.common.contant.Constant;
import com.getmyschool.common.dto.LeadsManagerDTO;
import com.getmyschool.common.utils.DateUtils;
import com.getmyschool.common.utils.UserUtils;
import com.getmyschool.common.validator.CustomValidator;

public class LeadsManagerValidator implements Validator {

	private static final String BAD_REQUEST_ERROR_CD = Constant.BAD_REQUEST_ERROR_CD;
	private static final Pattern VALID_EAMIL_PATTERN = Pattern.compile(Constant.EMAIL_PATTERN);
	private static final Pattern MOBILE_PATTERN = Pattern.compile(Constant.MOBILE_PATTERN);

	private static final List<String> VALID_UPDATE_STATUS = Arrays.asList(Constant.STATUS_ACTIVE,
			Constant.STATUS_DELEATED);

	@Autowired
	private UserUtils userUtils;

	@Override
	public boolean supports(Class<?> clazz) {
		return LeadsManagerDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		LeadsManagerDTO leadsManagerDTO = (LeadsManagerDTO) target;
		saveLeadsManager(leadsManagerDTO, errors);
	}

	public void saveLeadsManager(LeadsManagerDTO leadsManagerDTO, Errors errors) {

		Long logedUserId = userUtils.getLogedInUser();
		String createdTime = DateUtils.getAsiaLocalDateTimeInCustomFormat();

		if (CustomValidator.isEmpty(leadsManagerDTO.getName()))
			errors.rejectValue("name", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");

		if (!CustomValidator.isValidPattern(VALID_EAMIL_PATTERN, leadsManagerDTO.getEmail()))
			errors.rejectValue("email", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");

		if (!CustomValidator.isValidPattern(MOBILE_PATTERN, leadsManagerDTO.getPhoneNumber()))
			errors.rejectValue("phoneNumber", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");

		if (CustomValidator.isEmpty(leadsManagerDTO.getCollegeId()))
			errors.rejectValue("collegeId", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
		
		leadsManagerDTO.setStatus(Constant.STATUS_ACTIVE);
		leadsManagerDTO.setCreatedDate(createdTime);
		leadsManagerDTO.setCreatedBy(logedUserId);
	}

	public void getAllLeadsManagers(LeadsManagerDTO leadsManagerDTO, Errors errors) {
		Long logedUserid = userUtils.getLogedInUser();
		String createdTime = DateUtils.getAsiaLocalDateTimeInCustomFormat();

		if (null == leadsManagerDTO.getStatus())
			leadsManagerDTO.setStatus(Constant.STATUS_ACTIVE);

		leadsManagerDTO.setUpdatedDate(createdTime);
		leadsManagerDTO.setUpdatedBy(logedUserid);
	}

	public void getLeadsManagerById(LeadsManagerDTO leadsManagerDTO, Errors errors) {
		Long logedUserid = userUtils.getLogedInUser();
		String createdTime = DateUtils.getAsiaLocalDateTimeInCustomFormat();

		if (CustomValidator.isEmpty(leadsManagerDTO.getId()))
			errors.rejectValue("id", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");

		leadsManagerDTO.setUpdatedDate(createdTime);
		leadsManagerDTO.setUpdatedBy(logedUserid);
	}

	public void updateLeadsManager(LeadsManagerDTO leadsManagerDTO, Errors errors) {
		Long logedUserid = userUtils.getLogedInUser();
		String createdTime = DateUtils.getAsiaLocalDateTimeInCustomFormat();

		if (CustomValidator.isEmpty(leadsManagerDTO.getId()))
			errors.rejectValue("id", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
		if (null != leadsManagerDTO.getStatus() && !VALID_UPDATE_STATUS.contains(leadsManagerDTO.getStatus()))
			errors.rejectValue("status", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
		if (null == leadsManagerDTO.getName())
			errors.rejectValue("name", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
		if (null == leadsManagerDTO.getEmail())
			errors.rejectValue("email", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
		if (null == leadsManagerDTO.getPhoneNumber())
			errors.rejectValue("phoneNumber", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
		if (null == leadsManagerDTO.getCollegeId())
			errors.rejectValue("collegeId", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");


		leadsManagerDTO.setUpdatedDate(createdTime);
		leadsManagerDTO.setUpdatedBy(logedUserid);
	}

	public void deleteLeadsManager(LeadsManagerDTO leadsManagerDTO, Errors errors) {
		Long loggedUserid = userUtils.getLogedInUser();
		String deletionTime = DateUtils.getAsiaLocalDateTimeInCustomFormat();
		String status = "Inactive";
		if (null != leadsManagerDTO.getStatus() && !VALID_UPDATE_STATUS.contains(leadsManagerDTO.getStatus()))
			errors.rejectValue("status", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");

		leadsManagerDTO.setStatus(status);
		leadsManagerDTO.setUpdatedDate(deletionTime);
		leadsManagerDTO.setUpdatedBy(loggedUserid);
	}

}
