package com.getmyschool.college.validator;


import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.getmyschool.common.contant.Constant;
import com.getmyschool.common.dto.GenderClassificationDTO;
import com.getmyschool.common.utils.DateUtils;
import com.getmyschool.common.utils.UserUtils;
import com.getmyschool.common.validator.CustomValidator;

public class GenderClassificationValidator implements Validator {

	private static final String BAD_REQUEST_ERROR_CD = Constant.BAD_REQUEST_ERROR_CD;

	private static final List<String> VALID_UPDATE_STATUS = Arrays.asList(Constant.STATUS_ACTIVE,
			Constant.STATUS_DELEATED);
	@Autowired
	private UserUtils userUtils;
	
     @Override
	public boolean supports(Class<?> clazz) {
		return GenderClassificationDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		GenderClassificationDTO genderClassificationDTO = (GenderClassificationDTO) target;
		saveGenderClassification(genderClassificationDTO, errors);
	}
	
	public void saveGenderClassification(GenderClassificationDTO genderClassificationDTO, Errors errors) {
		Long logedUserId = userUtils.getLogedInUser();
		String createdTime = DateUtils.getAsiaLocalDateTimeInCustomFormat();

		if (CustomValidator.isEmpty(genderClassificationDTO.getGenderClassificationType()))
			errors.rejectValue("genderClassification", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
		
		genderClassificationDTO.setStatus(Constant.STATUS_ACTIVE);
		genderClassificationDTO.setCreatedDate(createdTime);
		genderClassificationDTO.setCreatedBy(logedUserId);
	}

	public void getAllGenderClassifications(GenderClassificationDTO genderClassificationDTO, Errors errors) {
		Long logedUserid = userUtils.getLogedInUser();
		String createdTime = DateUtils.getAsiaLocalDateTimeInCustomFormat();

		if (null == genderClassificationDTO.getStatus())
			genderClassificationDTO.setStatus(Constant.STATUS_ACTIVE);

		genderClassificationDTO.setUpdatedDate(createdTime);
		genderClassificationDTO.setUpdatedBy(logedUserid);		
	}

	public void getGenderClassificationById(GenderClassificationDTO genderClassificationDTO, Errors errors) {
		Long logedUserid = userUtils.getLogedInUser();
		String createdTime = DateUtils.getAsiaLocalDateTimeInCustomFormat();

		if (CustomValidator.isEmpty(genderClassificationDTO.getId()))
			errors.rejectValue("id", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");

		genderClassificationDTO.setUpdatedDate(createdTime);
		genderClassificationDTO.setUpdatedBy(logedUserid);
		
	}

	public void updateGenderClassification(GenderClassificationDTO genderClassificationDTO, Errors errors) {
		Long logedUserid = userUtils.getLogedInUser();
		String createdTime = DateUtils.getAsiaLocalDateTimeInCustomFormat();

		if (CustomValidator.isEmpty(genderClassificationDTO.getId()))
			errors.rejectValue("id", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
		
		if (null != genderClassificationDTO.getStatus() && !VALID_UPDATE_STATUS.contains(genderClassificationDTO.getStatus()))
			errors.rejectValue("status", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
		
		if (null == genderClassificationDTO.getGenderClassificationType())
			errors.rejectValue("genderClassificationType", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");	
		
		genderClassificationDTO.setUpdatedDate(createdTime);
		genderClassificationDTO.setUpdatedBy(logedUserid);
	}
}
