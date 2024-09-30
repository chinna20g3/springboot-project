package com.getmyschool.college.validator;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.getmyschool.common.contant.Constant;
import com.getmyschool.common.dto.BoardDTO;
import com.getmyschool.common.utils.DateUtils;
import com.getmyschool.common.utils.UserUtils;
import com.getmyschool.common.validator.CustomValidator;
public class BoardValidator implements Validator{
	
	private static final String BAD_REQUEST_ERROR_CD = Constant.BAD_REQUEST_ERROR_CD;

	private static final List<String> VALID_UPDATE_STATUS = Arrays.asList(Constant.STATUS_ACTIVE,
			Constant.STATUS_DELEATED);
	@Autowired
	private UserUtils userUtils;

	@Override
	public boolean supports(Class<?> clazz) {
		return BoardDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		BoardDTO boardDTO = (BoardDTO) target;
		saveBoard(boardDTO, errors);
	}
	
	public void saveBoard(BoardDTO boardDTO, Errors errors) {
		Long logedUserId = userUtils.getLogedInUser();
		String createdTime = DateUtils.getAsiaLocalDateTimeInCustomFormat();
		
		if (CustomValidator.isEmpty(boardDTO.getBoardType()))
			errors.rejectValue("board", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
		boardDTO.setStatus(Constant.STATUS_ACTIVE);
		boardDTO.setCreatedDate(createdTime);
		boardDTO.setCreatedBy(logedUserId);
	}
	
	public void getAllBoards(BoardDTO boardDTO, Errors errors) {
		Long logedUserid = userUtils.getLogedInUser();
		String createdTime = DateUtils.getAsiaLocalDateTimeInCustomFormat();

		if (null == boardDTO.getStatus())
			boardDTO.setStatus(Constant.STATUS_ACTIVE);

		boardDTO.setUpdatedDate(createdTime);
		boardDTO.setUpdatedBy(logedUserid);

	}
	
	public void  getBoardById(BoardDTO boardDTO, Errors errors) {
		Long logedUserid = userUtils.getLogedInUser();
		String createdTime = DateUtils.getAsiaLocalDateTimeInCustomFormat();

		if (CustomValidator.isEmpty(boardDTO.getId()))
			errors.rejectValue("id", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");

		boardDTO.setUpdatedDate(createdTime);
		boardDTO.setUpdatedBy(logedUserid);	
    }
	
	public void updateBoard(BoardDTO boardDTO, Errors errors) {
		Long logedUserid = userUtils.getLogedInUser();
		String createdTime = DateUtils.getAsiaLocalDateTimeInCustomFormat();
		
		if (CustomValidator.isEmpty(boardDTO.getId()))
			errors.rejectValue("id", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");

		if (null != boardDTO.getStatus() && !VALID_UPDATE_STATUS.contains(boardDTO.getStatus()))
			errors.rejectValue("status", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");
		if (null == boardDTO.getBoardType())
			errors.rejectValue("boardType", BAD_REQUEST_ERROR_CD, "is an empty or not in valid format");	

		boardDTO.setUpdatedDate(createdTime);
		boardDTO.setUpdatedBy(logedUserid);	
	}	
}
