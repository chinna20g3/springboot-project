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

import com.getmyschool.college.service.SchoolService;
import com.getmyschool.college.validator.SchoolValidator;
import com.getmyschool.common.contant.Constant;
import com.getmyschool.common.dto.SchoolDTO;
import com.getmyschool.common.exception.FieldException;

@RestController
@RequestMapping("/school")
public class SchoolController {

	@Autowired
	private SchoolValidator schoolValidator;
	
	@Autowired
	private SchoolService schoolService;

	private LinkedHashMap<String, Object> returnMap;
	
	private static Logger LOGGER = LoggerFactory.getLogger(SchoolController.class);

	@RequestMapping(value = "/addSchool", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<LinkedHashMap<String, Object>> addSchool(@RequestBody SchoolDTO schoolDTO,
			HttpServletRequest request, BindingResult result) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		MapBindingResult err = new MapBindingResult(map, SchoolDTO.class.getName());
		schoolValidator.saveSchool(schoolDTO, err);
		List<ObjectError> list = err.getAllErrors();
		if (list.size() > 0)
			throw new FieldException(list);
		
		schoolService.saveSchool(schoolDTO);
		returnMap = new LinkedHashMap<String, Object>();
		returnMap.put("responseCode", Constant.SUCCESSFULL_CODE);
		returnMap.put("responseMessage", Constant.SUCCESSFULL_MSG);
		return ResponseEntity.status(HttpStatus.OK).body(returnMap);
	}
	
	@RequestMapping(value = "/getSchools", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	 ResponseEntity<LinkedHashMap<String, Object>> getSchools(@RequestBody SchoolDTO schoolDTO,
				HttpServletRequest request, BindingResult result) throws Exception {
			HashMap<String, String> map = new HashMap<String, String>();
			MapBindingResult err = new MapBindingResult(map, SchoolDTO.class.getName());
			schoolValidator.getAllSchools(schoolDTO, err);
			List<ObjectError> list = err.getAllErrors();
			if (list.size() > 0)
				throw new FieldException(list);

			List<SchoolDTO> schools = schoolService.getAllSchools(schoolDTO);
			returnMap = new LinkedHashMap<String, Object>();
			returnMap.put("responseCode", Constant.SUCCESSFULL_CODE);
			returnMap.put("responseMessage", Constant.SUCCESSFULL_MSG);
			returnMap.put("schools", schools);
			return ResponseEntity.status(HttpStatus.OK).body(returnMap);
		}
	
	@RequestMapping(value = "/getSchool", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<LinkedHashMap<String, Object>> getSchool(@RequestBody SchoolDTO schoolDTO,
			HttpServletRequest request, BindingResult result) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		MapBindingResult err = new MapBindingResult(map, SchoolDTO.class.getName());
		schoolValidator.getSchoolById(schoolDTO, err);
		List<ObjectError> list = err.getAllErrors();
		if (list.size() > 0)
			throw new FieldException(list);

		SchoolDTO schools = schoolService.getSchoolById(schoolDTO);
		returnMap = new LinkedHashMap<String, Object>();
		returnMap.put("responseCode", Constant.SUCCESSFULL_CODE);
		returnMap.put("responseMessage", Constant.SUCCESSFULL_MSG);
		returnMap.put("schools", schools);
		return ResponseEntity.status(HttpStatus.OK).body(returnMap);
	}

	@RequestMapping(value = "/updateSchool", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<LinkedHashMap<String, Object>> updateSchool(@RequestBody SchoolDTO schoolDTO,
			HttpServletRequest request, BindingResult result) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		MapBindingResult err = new MapBindingResult(map, SchoolDTO.class.getName());
		schoolValidator.updateSchool(schoolDTO, err);
		List<ObjectError> list = err.getAllErrors();
		if (list.size() > 0)
			throw new FieldException(list);

		schoolService.updateSchool(schoolDTO);
		returnMap = new LinkedHashMap<String, Object>();
		returnMap.put("responseCode", Constant.SUCCESSFULL_CODE);
		returnMap.put("responseMessage", Constant.SUCCESSFULL_MSG);
		return ResponseEntity.status(HttpStatus.OK).body(returnMap);
	}
	
	@Bean
	public SchoolValidator getSchoolsValidator() {
		return new SchoolValidator();
	}
	
}
