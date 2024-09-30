package com.getmyschool.college.validator;

import java.util.Arrays;  
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.getmyschool.common.contant.Constant;
import com.getmyschool.common.dto.ReviewsDTO;
import com.getmyschool.common.utils.DateUtils;
import com.getmyschool.common.utils.UserUtils;
import com.getmyschool.common.validator.CustomValidator;

public class ReviewsValidator implements Validator {

	private static final String BAD_REQUEST_ERROR_CD = Constant.BAD_REQUEST_ERROR_CD;

	private static final List<String> VALID_UPDATE_STATUS = Arrays.asList(Constant.STATUS_ACTIVE,
			Constant.STATUS_DELEATED);
	
	@Autowired
    private	UserUtils userUtils;
    
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return ReviewsDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		ReviewsDTO reviewsDTO =(ReviewsDTO) target;
		saveReviews(reviewsDTO, errors);	
	}

	public void saveReviews(ReviewsDTO reviewsDTO, Errors errors) {

		Long logedUserid = userUtils.getLogedInUser();
		String createdTime =DateUtils.getAsiaLocalDateTimeInCustomFormat();
		
		if(CustomValidator.isEmpty(reviewsDTO.getReviewStar()))
			errors.rejectValue("(reviews", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");

		reviewsDTO.setStatus(Constant.STATUS_ACTIVE);
		reviewsDTO.setCreatedDate(createdTime);
		reviewsDTO.setCreatedBy(logedUserid);
	}
	
	public void getAllReviews(ReviewsDTO reviewsDTO, Errors errors) {
		Long logedUserid = userUtils.getLogedInUser();
		String createdTime =DateUtils.getAsiaLocalDateTimeInCustomFormat();
		
		if(null == reviewsDTO.getStatus())
			reviewsDTO.setStatus(Constant.STATUS_ACTIVE);
		
		reviewsDTO.setUpdatedDate(createdTime);
		reviewsDTO.setUpdatedBy(logedUserid);
	}
	
	public void getReviewsById(ReviewsDTO reviewsDTO, Errors errors) {
		Long logedUserid = userUtils.getLogedInUser();
		String createdTime =DateUtils.getAsiaLocalDateTimeInCustomFormat();
		if(CustomValidator.isEmpty(reviewsDTO.getId()))
			errors.rejectValue("(reviews", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");

		reviewsDTO.setUpdatedDate(createdTime);
		reviewsDTO.setUpdatedBy(logedUserid);
	}

	public void updateReviews(ReviewsDTO reviewsDTO, Errors errors) {
		Long logedUserid = userUtils.getLogedInUser();
		String createdTime =DateUtils.getAsiaLocalDateTimeInCustomFormat();
		
		if (CustomValidator.isEmpty(reviewsDTO.getId()))
			errors.rejectValue("id", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
		
		if (null != reviewsDTO.getStatus() && !VALID_UPDATE_STATUS.contains(reviewsDTO.getStatus()))
			errors.rejectValue("status", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
		
		if (null == reviewsDTO.getReviewStar())
			errors.rejectValue("reviewStar", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");	
		
		reviewsDTO.setUpdatedDate(createdTime);
		reviewsDTO.setUpdatedBy(logedUserid);
	}
		
	public void deleteReviews(ReviewsDTO reviewsDTO, Errors errors) {
		Long logedUserid = userUtils.getLogedInUser();
		String createdTime =DateUtils.getAsiaLocalDateTimeInCustomFormat();
		String status =" In_Active";
		
		 if (null != reviewsDTO.getStatus() && !VALID_UPDATE_STATUS.contains(reviewsDTO.getStatus()))
				errors.rejectValue("status", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
				
		reviewsDTO.setStatus(status);
		reviewsDTO.setUpdatedDate(createdTime);
		reviewsDTO.setUpdatedBy(logedUserid);
	}
	
}
