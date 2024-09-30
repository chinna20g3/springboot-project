package com.getmyschool.college.validator;

import java.util.Arrays; 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.getmyschool.common.contant.Constant;
import com.getmyschool.common.dto.BlogDTO;
import com.getmyschool.common.utils.DateUtils;
import com.getmyschool.common.utils.UserUtils;
import com.getmyschool.common.validator.CustomValidator;

public class BlogValidator implements Validator {

	private static final String BAD_REQUEST_ERROR_CD = Constant.BAD_REQUEST_ERROR_CD;

	private static final List<String> VALID_UPDATE_STATUS = Arrays.asList(Constant.STATUS_ACTIVE,
			Constant.STATUS_DELEATED);
	@Autowired
	private UserUtils userUtils;
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return BlogDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		BlogDTO blogDTO = (BlogDTO) target;
		saveBlog(blogDTO, errors);
	}
	
	public void saveBlog(BlogDTO blogDTO, Errors errors) {
		Long logedUserId = userUtils.getLogedInUser();
		String createdTime = DateUtils.getAsiaLocalDateTimeInCustomFormat();

		if (CustomValidator.isEmpty(blogDTO.getTitle()))
			errors.rejectValue("title", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
		if (CustomValidator.isEmpty(blogDTO.getDescription()))
			errors.rejectValue("description", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
		if (CustomValidator.isEmpty(blogDTO.getOgUrl()))
			errors.rejectValue("ogUrl", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
	
		blogDTO.setStatus(Constant.STATUS_ACTIVE);
		blogDTO.setCreatedDate(createdTime);
		blogDTO.setCreatedBy(logedUserId);
	}
	
	public void getAllBlogs(BlogDTO blogDTO, Errors errors) {
		Long logedUserId = userUtils.getLogedInUser();
		String createdTime = DateUtils.getAsiaLocalDateTimeInCustomFormat();
		
		if (null == blogDTO.getStatus())
			blogDTO.setStatus(Constant.STATUS_ACTIVE);	
		
		blogDTO.setUpdatedDate(createdTime);
		blogDTO.setUpdatedBy(logedUserId);
	}
	
	public void getBlogById(BlogDTO blogDTO, Errors errors) {
		Long logedUserId = userUtils.getLogedInUser();
		String createdTime = DateUtils.getAsiaLocalDateTimeInCustomFormat();
		if (CustomValidator.isEmpty(blogDTO.getId()))
			errors.rejectValue("id", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");

		blogDTO.setUpdatedDate(createdTime);
		blogDTO.setUpdatedBy(logedUserId);	
	}
	
	public void updateBlog(BlogDTO blogDTO, Errors errors) {
			Long logedUserId = userUtils.getLogedInUser();
			String createdTime = DateUtils.getAsiaLocalDateTimeInCustomFormat();

			if (CustomValidator.isEmpty(blogDTO.getId()))
				errors.rejectValue("id", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");

			if (null != blogDTO.getStatus() && !VALID_UPDATE_STATUS.contains(blogDTO.getStatus()))
				errors.rejectValue("status", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
			if (null == blogDTO.getTitle())
				errors.rejectValue("title", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");	
			if (null == blogDTO.getDescription())
				errors.rejectValue("description", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");	
			if (null == blogDTO.getOgUrl())
				errors.rejectValue("ogUrl", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");	
			
			blogDTO.setUpdatedDate(createdTime);
			blogDTO.setUpdatedBy(logedUserId);
	}
	
}
