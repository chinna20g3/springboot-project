package com.getmyschool.college.validator;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.getmyschool.common.contant.Constant;
import com.getmyschool.common.dto.NewsLetterDTO;
import com.getmyschool.common.utils.DateUtils;
import com.getmyschool.common.utils.UserUtils;
import com.getmyschool.common.validator.CustomValidator;

public class NewsLetterValidator  implements Validator{

	private static final String BAD_REQUEST_ERROR_CD = Constant.BAD_REQUEST_ERROR_CD;
	private static final Pattern VALID_EAMIL_PATTERN = Pattern.compile(Constant.EMAIL_PATTERN);

	private static final List<String> VALID_UPDATE_STATUS = Arrays.asList(Constant.STATUS_ACTIVE,
			Constant.STATUS_DELEATED);
	
	@Autowired
	private UserUtils userUtils;
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return NewsLetterDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		NewsLetterDTO newsLetterDTO = (NewsLetterDTO) target;
		saveNewsLetter(newsLetterDTO, errors);
	}
	
	public void saveNewsLetter(NewsLetterDTO newsLetterDTO, Errors errors) {
		
		 Long logedUserid = userUtils.getLogedInUser();
	     String createdTime = DateUtils.getAsiaLocalDateTimeInCustomFormat();
	     if (!CustomValidator.isValidPattern(VALID_EAMIL_PATTERN, newsLetterDTO.getEmail()))
	    	 errors.rejectValue("email", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
	     
	     newsLetterDTO.setStatus(Constant.STATUS_ACTIVE);
	     newsLetterDTO.setCreatedDate(createdTime);
	     newsLetterDTO.setCreatedBy(logedUserid);	
	}	
	
	public void getAllNewsLetters(NewsLetterDTO newsLetterDTO, Errors errors) {
		 Long logedUserid = userUtils.getLogedInUser();
		 String createdTime = DateUtils.getAsiaLocalDateTimeInCustomFormat();
	     if (null == newsLetterDTO.getStatus())
	    	 newsLetterDTO.setStatus(Constant.STATUS_ACTIVE);	
	     newsLetterDTO.setUpdatedDate(createdTime);
	     newsLetterDTO.setUpdatedBy(logedUserid);	
	}	
	
	public void getNewsLetterById(NewsLetterDTO newsLetterDTO, Errors errors) {
			
		 Long logedUserid = userUtils.getLogedInUser();
		 String createdTime = DateUtils.getAsiaLocalDateTimeInCustomFormat();
		 if (CustomValidator.isEmpty(newsLetterDTO.getId()))
			 errors.rejectValue("id", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
		 newsLetterDTO.setUpdatedDate(createdTime);
		 newsLetterDTO.setUpdatedBy(logedUserid);		
	}
	
	public void updateNewsLetter(NewsLetterDTO newsLetterDTO, Errors errors) {
		
		 Long logedUserid = userUtils.getLogedInUser();
		 String createdTime = DateUtils.getAsiaLocalDateTimeInCustomFormat();
		 if (CustomValidator.isEmpty(newsLetterDTO.getId()))
			 errors.rejectValue("id", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
		 if (null != newsLetterDTO.getStatus() && !VALID_UPDATE_STATUS.contains(newsLetterDTO.getStatus()))
			 errors.rejectValue("status", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
		 if (!CustomValidator.isValidPattern(VALID_EAMIL_PATTERN, newsLetterDTO.getEmail()))
				errors.rejectValue("email", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");

		 newsLetterDTO.setUpdatedDate(createdTime);
		 newsLetterDTO.setUpdatedBy(logedUserid);	
	}	
	
	public void deleteNewsLetter(NewsLetterDTO newsLetterDTO, Errors errors) {
		Long loggedUserid = userUtils.getLogedInUser();
	    String deletionTime = DateUtils.getAsiaLocalDateTimeInCustomFormat();
        String status = "Inactive";
	    if (null != newsLetterDTO.getStatus() && !VALID_UPDATE_STATUS.contains(newsLetterDTO.getStatus()))
			errors.rejectValue("status", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
			
	    newsLetterDTO.setStatus(status);
	    newsLetterDTO.setUpdatedDate(deletionTime);
	    newsLetterDTO.setUpdatedBy(loggedUserid);	
	}
	
}
