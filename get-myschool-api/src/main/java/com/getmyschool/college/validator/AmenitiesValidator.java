package com.getmyschool.college.validator;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.getmyschool.common.contant.Constant;
import com.getmyschool.common.dto.AmenitiesDTO;
import com.getmyschool.common.utils.DateUtils;
import com.getmyschool.common.utils.UserUtils;
import com.getmyschool.common.validator.CustomValidator;

public class AmenitiesValidator implements Validator{
	
	private static final String BAD_REQUEST_ERROR_CD = Constant.BAD_REQUEST_ERROR_CD;

	private static final List<String> VALID_UPDATE_STATUS = Arrays.asList(Constant.STATUS_ACTIVE,
			Constant.STATUS_DELEATED);
	@Autowired
	private UserUtils userUtils;
	
     @Override
	public boolean supports(Class<?> clazz) {
		return AmenitiesDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		AmenitiesDTO amenitiesDTO = (AmenitiesDTO) target;
		saveAmenities(amenitiesDTO, errors);
	}

	public void saveAmenities(AmenitiesDTO amenitiesDTO, Errors errors) {
		Long logedUserId = userUtils.getLogedInUser();
		String createdTime = DateUtils.getAsiaLocalDateTimeInCustomFormat();

		if (CustomValidator.isEmpty(amenitiesDTO.getAmenitiesType()))
			errors.rejectValue("amenities", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
		amenitiesDTO.setStatus(Constant.STATUS_ACTIVE);
		amenitiesDTO.setUpdatedDate(createdTime);
		amenitiesDTO.setUpdatedBy(logedUserId);
	}

	public void getAllAmenities(AmenitiesDTO amenitiesDTO, Errors errors) {
		Long logedUserid = userUtils.getLogedInUser();
		String createdTime = DateUtils.getAsiaLocalDateTimeInCustomFormat();

		if (null == amenitiesDTO.getStatus())
			amenitiesDTO.setStatus(Constant.STATUS_ACTIVE);

		amenitiesDTO.setUpdatedDate(createdTime);
		amenitiesDTO.setUpdatedBy(logedUserid);	
	}
	
	public void getAmenitiesById(AmenitiesDTO amenitiesDTO, Errors errors) {
		Long logedUserid = userUtils.getLogedInUser();
		String createdTime = DateUtils.getAsiaLocalDateTimeInCustomFormat();

		if (CustomValidator.isEmpty(amenitiesDTO.getId()))
			errors.rejectValue("id", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");

		amenitiesDTO.setUpdatedDate(createdTime);
		amenitiesDTO.setUpdatedBy(logedUserid);	
	}

	public void updateAmenities(AmenitiesDTO amenitiesDTO, Errors errors) {
		Long logedUserid = userUtils.getLogedInUser();
		String createdTime = DateUtils.getAsiaLocalDateTimeInCustomFormat();

		if (CustomValidator.isEmpty(amenitiesDTO.getId()))
			errors.rejectValue("id", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");

		if (null != amenitiesDTO.getStatus() && !VALID_UPDATE_STATUS.contains(amenitiesDTO.getStatus()))
			errors.rejectValue("status", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
		if (null == amenitiesDTO.getAmenitiesType())
			errors.rejectValue("amenitiesType", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");	

		amenitiesDTO.setUpdatedDate(createdTime);
		amenitiesDTO.setUpdatedBy(logedUserid);
	}
		
}

