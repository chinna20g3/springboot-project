package com.getmyschool.college.validator;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


import com.getmyschool.common.contant.Constant;
import com.getmyschool.common.dto.GradeClassificationDTO;
import com.getmyschool.common.utils.DateUtils;
import com.getmyschool.common.utils.UserUtils;
import com.getmyschool.common.validator.CustomValidator;

public class GradeClassificationValidator implements Validator{
	
	private static final String BAD_REQUEST_ERROR_CD = Constant.BAD_REQUEST_ERROR_CD;

	private static final List<String> VALID_UPDATE_STATUS = Arrays.asList(Constant.STATUS_ACTIVE,
			Constant.STATUS_DELEATED);
	@Autowired
	private UserUtils userUtils;
	
    @Override
	public boolean supports(Class<?> clazz) {
		return GradeClassificationDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		GradeClassificationDTO gradeClassificationDTO = (GradeClassificationDTO) target;
		saveGradeClassification(gradeClassificationDTO, errors);
	}
	
	public void saveGradeClassification(GradeClassificationDTO gradeClassificationDTO, Errors errors) {
		Long logedUserId = userUtils.getLogedInUser();
		String createdTime = DateUtils.getAsiaLocalDateTimeInCustomFormat();

		if (CustomValidator.isEmpty(gradeClassificationDTO.getGradeClassificationType()))
			errors.rejectValue("gradeClassification", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
		
		if (null != gradeClassificationDTO.getStatus() && !VALID_UPDATE_STATUS.contains(gradeClassificationDTO.getStatus()))
			errors.rejectValue("status", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
		
		gradeClassificationDTO.setStatus(Constant.STATUS_ACTIVE);
		gradeClassificationDTO.setCreatedDate(createdTime);
		gradeClassificationDTO.setCreatedBy(logedUserId);
	}

	public void getAllGradeClassifications(GradeClassificationDTO gradeClassificationDTO, Errors errors) {
		Long logedUserid = userUtils.getLogedInUser();
		String createdTime = DateUtils.getAsiaLocalDateTimeInCustomFormat();

		if (null == gradeClassificationDTO.getStatus())
			gradeClassificationDTO.setStatus(Constant.STATUS_ACTIVE);

		gradeClassificationDTO.setUpdatedDate(createdTime);
		gradeClassificationDTO.setUpdatedBy(logedUserid);		
	}

	public void getGradeClassificationById(GradeClassificationDTO gradeClassificationDTO, Errors errors) {
		Long logedUserid = userUtils.getLogedInUser();
		String createdTime = DateUtils.getAsiaLocalDateTimeInCustomFormat();

		if (CustomValidator.isEmpty(gradeClassificationDTO.getId()))
			errors.rejectValue("id", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");

		gradeClassificationDTO.setUpdatedDate(createdTime);
		gradeClassificationDTO.setUpdatedBy(logedUserid);
	}

	public void updateGradeClassification(GradeClassificationDTO gradeClassificationDTO, Errors errors) {
		Long logedUserid = userUtils.getLogedInUser();
		String createdTime = DateUtils.getAsiaLocalDateTimeInCustomFormat();

		if (CustomValidator.isEmpty(gradeClassificationDTO.getId()))
			errors.rejectValue("id", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
		
		if (null != gradeClassificationDTO.getStatus() && !VALID_UPDATE_STATUS.contains(gradeClassificationDTO.getStatus()))
			errors.rejectValue("status", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
		
		if (null == gradeClassificationDTO.getGradeClassificationType())
			errors.rejectValue("gradeClassificationType", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");	
		
		gradeClassificationDTO.setUpdatedDate(createdTime);
		gradeClassificationDTO.setUpdatedBy(logedUserid);	
	}
}
