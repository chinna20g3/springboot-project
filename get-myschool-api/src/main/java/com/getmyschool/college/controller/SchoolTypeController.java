package com.getmyschool.college.controller;

import java.util.HashMap;   
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.MapBindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.getmyschool.college.service.SchoolTypeService;
import com.getmyschool.college.validator.SchoolTypeValidator;
import com.getmyschool.common.contant.Constant;
import com.getmyschool.common.dto.SchoolTypeDTO;
import com.getmyschool.common.exception.FieldException;

@RestController
@RequestMapping("/schoolType")
public class SchoolTypeController {

	@Autowired
	private SchoolTypeValidator schoolTypeValidator;

	@Autowired
	private SchoolTypeService schoolTypeService;

	private LinkedHashMap<String, Object> returnMap;

	private static Logger LOGGER = LoggerFactory.getLogger(SchoolTypeController.class);

	@RequestMapping(value = "/addSchoolType", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<LinkedHashMap<String, Object>> addSchoolType(@RequestBody SchoolTypeDTO schoolTypeDTO,
			HttpServletRequest request, BindingResult result) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		MapBindingResult err = new MapBindingResult(map, SchoolTypeDTO.class.getName());
		schoolTypeValidator.saveSchoolType(schoolTypeDTO, err);
		List<ObjectError> list = err.getAllErrors();
		if (list.size() > 0)
			throw new FieldException(list);

		schoolTypeService.saveSchoolType(schoolTypeDTO);
		returnMap = new LinkedHashMap<String, Object>();
		returnMap.put("responseCode", Constant.SUCCESSFULL_CODE);
		returnMap.put("responseMessage", Constant.SUCCESSFULL_MSG);
		return ResponseEntity.status(HttpStatus.OK).body(returnMap);
	}
	
	@RequestMapping(value = "/getSchoolTypes", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<LinkedHashMap<String, Object>> getSchoolTypes(@RequestBody SchoolTypeDTO schoolTypeDTO,
			HttpServletRequest request, BindingResult result) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		MapBindingResult err = new MapBindingResult(map, SchoolTypeDTO.class.getName());
		schoolTypeValidator.getAllSchoolTypes(schoolTypeDTO, err);
		List<ObjectError> list = err.getAllErrors();
		if (list.size() > 0)
			throw new FieldException(list);

		List<SchoolTypeDTO> schoolTypes = schoolTypeService.getAllSchoolTypes(schoolTypeDTO);
		returnMap = new LinkedHashMap<String, Object>();
		returnMap.put("responseCode", Constant.SUCCESSFULL_CODE);
		returnMap.put("responseMessage", Constant.SUCCESSFULL_MSG);
		returnMap.put("schoolTypes", schoolTypes);
		return ResponseEntity.status(HttpStatus.OK).body(returnMap);
	}

	@RequestMapping(value = "/getSchoolType", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<LinkedHashMap<String, Object>> getSchoolType(@RequestBody SchoolTypeDTO schoolTypeDTO,
			HttpServletRequest request, BindingResult result) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		MapBindingResult err = new MapBindingResult(map, SchoolTypeDTO.class.getName());
		schoolTypeValidator.getSchoolTypeById(schoolTypeDTO, err);
		List<ObjectError> list = err.getAllErrors();
		if (list.size() > 0)
			throw new FieldException(list);

		SchoolTypeDTO schoolTypes = schoolTypeService.getSchoolTypeById(schoolTypeDTO);
		returnMap = new LinkedHashMap<String, Object>();
		returnMap.put("responseCode", Constant.SUCCESSFULL_CODE);
		returnMap.put("responseMessage", Constant.SUCCESSFULL_MSG);
		returnMap.put("schoolTypes", schoolTypes);
		return ResponseEntity.status(HttpStatus.OK).body(returnMap);
	}

	@RequestMapping(value = "/updateSchoolType", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<LinkedHashMap<String, Object>> updateSchoolType(@RequestBody SchoolTypeDTO schoolTypeDTO,
			HttpServletRequest request, BindingResult result) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		MapBindingResult err = new MapBindingResult(map, SchoolTypeDTO.class.getName());
		schoolTypeValidator.updateSchoolType(schoolTypeDTO, err);
		List<ObjectError> list = err.getAllErrors();
		if (list.size() > 0)
			throw new FieldException(list);

		schoolTypeService.updateSchoolType(schoolTypeDTO);
		returnMap = new LinkedHashMap<String, Object>();
		returnMap.put("responseCode", Constant.SUCCESSFULL_CODE);
		returnMap.put("responseMessage", Constant.SUCCESSFULL_MSG);
		return ResponseEntity.status(HttpStatus.OK).body(returnMap);
	}
	
	@Bean
	public SchoolTypeValidator getSchoolTypesValidator() {
		return new SchoolTypeValidator();
	}

}
