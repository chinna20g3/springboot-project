package com.getmyschool.college.validator;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.getmyschool.common.contant.Constant;
import com.getmyschool.common.dto.IpTrackingDTO;
import com.getmyschool.common.utils.DateUtils;
import com.getmyschool.common.utils.UserUtils;
import com.getmyschool.common.validator.CustomValidator;

public class IpTrackingValidator implements Validator{

	private static final String BAD_REQUEST_ERROR_CD = Constant.BAD_REQUEST_ERROR_CD;
	
	private static final List<String> VALID_UPDATE_STATUS = Arrays.asList(Constant.STATUS_ACTIVE,
			Constant.STATUS_DELEATED);

	@Autowired	
    private UserUtils userUtils;
		
	@Override
	public boolean supports(Class<?> clazz) {
		return IpTrackingDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		IpTrackingDTO ipTrackingDTO =(IpTrackingDTO) target;
		saveIpTracking(ipTrackingDTO, errors);
	}

	public void saveIpTracking(IpTrackingDTO ipTrackingDTO, Errors errors) {

		Long logedUserid = userUtils.getLogedInUser();
		String createdTime = DateUtils.getAsiaLocalDateTimeInCustomFormat();
		
		if (CustomValidator.isEmpty(ipTrackingDTO.getIpAddress()))
			errors.rejectValue("ipAddress", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
		if (CustomValidator.isEmpty(ipTrackingDTO.getLatitude()))
			errors.rejectValue("latitude", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
		if (CustomValidator.isEmpty(ipTrackingDTO.getLongitude()))
			errors.rejectValue("longitude", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
		if (CustomValidator.isEmpty(ipTrackingDTO.getLocation()))
			errors.rejectValue("location", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");

		ipTrackingDTO.setStatus(Constant.STATUS_ACTIVE);
		ipTrackingDTO.setCreatedBy(logedUserid);
		ipTrackingDTO.setCreatedDate(createdTime);	
	}
	
	public void getAllIpTracking(IpTrackingDTO ipTrackingDTO, Errors errors) {
		Long logedUserid = userUtils.getLogedInUser();
		String createdTime = DateUtils.getAsiaLocalDateTimeInCustomFormat();
		
		if (null == ipTrackingDTO.getStatus())
			ipTrackingDTO.setStatus(Constant.STATUS_ACTIVE);

		ipTrackingDTO.setCreatedBy(logedUserid);
		ipTrackingDTO.setCreatedDate(createdTime);	
	}
	
	public void getIpTrackingById(IpTrackingDTO ipTrackingDTO, Errors errors) {
		Long logedUserid = userUtils.getLogedInUser();
		String createdTime = DateUtils.getAsiaLocalDateTimeInCustomFormat();
		
		if(CustomValidator.isEmpty(ipTrackingDTO.getId()))
			errors.rejectValue("ipAddress", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
		
		ipTrackingDTO.setCreatedBy(logedUserid);
		ipTrackingDTO.setCreatedDate(createdTime);
	}
	
	public void updateIpTracking(IpTrackingDTO ipTrackingDTO, Errors errors) {

		Long logedUserid = userUtils.getLogedInUser();
		String createdTime = DateUtils.getAsiaLocalDateTimeInCustomFormat();
		
		if (CustomValidator.isEmpty(ipTrackingDTO.getId()))
			errors.rejectValue("id", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");

		if (null == ipTrackingDTO.getIpAddress())
			errors.rejectValue("ipAddress", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");

		if (null != ipTrackingDTO.getStatus() && !VALID_UPDATE_STATUS.contains(ipTrackingDTO.getStatus()))
			errors.rejectValue("status", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
	
		if (null == ipTrackingDTO.getLatitude())
			errors.rejectValue("latitude", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");

		if (null == ipTrackingDTO.getLongitude())
			errors.rejectValue("longitude", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");

		if (null == ipTrackingDTO.getLocation())
			errors.rejectValue("location", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");

		ipTrackingDTO.setCreatedBy(logedUserid);
		ipTrackingDTO.setCreatedDate(createdTime);
	}
	
	public void deleteIpTracking(IpTrackingDTO ipTrackingDTO, Errors errors) {

		Long logedUserid = userUtils.getLogedInUser();
		String createdTime = DateUtils.getAsiaLocalDateTimeInCustomFormat();
		String status = "In_Active";
		if (null != ipTrackingDTO.getStatus() && !VALID_UPDATE_STATUS.contains(ipTrackingDTO.getStatus()))
			errors.rejectValue("status", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
		
		ipTrackingDTO.setStatus(status);
		ipTrackingDTO.setCreatedBy(logedUserid);
		ipTrackingDTO.setCreatedDate(createdTime);		
	}

}
