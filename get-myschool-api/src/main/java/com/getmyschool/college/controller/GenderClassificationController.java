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

import com.getmyschool.college.service.GenderClassificationService;
import com.getmyschool.college.validator.GenderClassificationValidator;
import com.getmyschool.common.contant.Constant;
import com.getmyschool.common.dto.GenderClassificationDTO;
import com.getmyschool.common.exception.FieldException;

@RestController
@RequestMapping("/genderClassification")
public class GenderClassificationController {
	
	@Autowired
	private GenderClassificationValidator genderClassificationValidator;
	
    @Autowired
    private GenderClassificationService genderClassificationService;
    
	private LinkedHashMap<String, Object> returnMap;
	
	private static Logger LOGGER = LoggerFactory.getLogger(GradeClassificationController.class);

	@RequestMapping(value = "/addGenderClassification", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<LinkedHashMap<String, Object>> addGenderClassification(@RequestBody GenderClassificationDTO genderClassificationDTO,
			HttpServletRequest request, BindingResult result) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		MapBindingResult err = new MapBindingResult(map, GenderClassificationDTO.class.getName());
		genderClassificationValidator.saveGenderClassification(genderClassificationDTO, err);
		List<ObjectError> list = err.getAllErrors();
		if (list.size() > 0)
			throw new FieldException(list);

		genderClassificationService.saveGenderClassification(genderClassificationDTO);
		returnMap = new LinkedHashMap<String, Object>();
		returnMap.put("responseCode", Constant.SUCCESSFULL_CODE);
		returnMap.put("responseMessage", Constant.SUCCESSFULL_MSG);
		return ResponseEntity.status(HttpStatus.OK).body(returnMap);
	}

 
	@RequestMapping(value = "/getGenderClassifications", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<LinkedHashMap<String, Object>> getGenderClassifications(@RequestBody GenderClassificationDTO genderClassificationDTO,
			HttpServletRequest request, BindingResult result) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		MapBindingResult err = new MapBindingResult(map, GenderClassificationDTO.class.getName());
		genderClassificationValidator.getAllGenderClassifications(genderClassificationDTO, err);
		List<ObjectError> list = err.getAllErrors();
		if (list.size() > 0)
			throw new FieldException(list);

		List<GenderClassificationDTO> genderClassifications = genderClassificationService.getAllGenderClassifications(genderClassificationDTO);
		returnMap = new LinkedHashMap<String, Object>();
		returnMap.put("responseCode", Constant.SUCCESSFULL_CODE);
		returnMap.put("responseMessage", Constant.SUCCESSFULL_MSG);
		returnMap.put("GenderClassifications", genderClassifications);
		return ResponseEntity.status(HttpStatus.OK).body(returnMap);
	}

	@RequestMapping(value = "/getGenderClassification", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<LinkedHashMap<String, Object>> getGenderClassification(@RequestBody GenderClassificationDTO genderClassificationDTO,
			HttpServletRequest request, BindingResult result) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		MapBindingResult err = new MapBindingResult(map, GenderClassificationDTO.class.getName());
		genderClassificationValidator.getGenderClassificationById(genderClassificationDTO, err);
		List<ObjectError> list = err.getAllErrors();
		if (list.size() > 0)
			throw new FieldException(list);

		GenderClassificationDTO genderClassifications = genderClassificationService.getGenderClassificationById(genderClassificationDTO);
		returnMap = new LinkedHashMap<String, Object>();
		returnMap.put("responseCode", Constant.SUCCESSFULL_CODE);
		returnMap.put("responseMessage", Constant.SUCCESSFULL_MSG);
		returnMap.put("genderClassifications", genderClassifications);
		return ResponseEntity.status(HttpStatus.OK).body(returnMap);
	}

	@RequestMapping(value = "/updateGenderClassification", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<LinkedHashMap<String, Object>> updateGenderClassification(@RequestBody GenderClassificationDTO genderClassificationDTO,
			HttpServletRequest request, BindingResult result) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		MapBindingResult err = new MapBindingResult(map, GenderClassificationDTO.class.getName());
		genderClassificationValidator.updateGenderClassification(genderClassificationDTO, err);
		List<ObjectError> list = err.getAllErrors();
		if (list.size() > 0)
			throw new FieldException(list);

		genderClassificationService.updateGenderClassification(genderClassificationDTO);
		returnMap = new LinkedHashMap<String, Object>();
		returnMap.put("responseCode", Constant.SUCCESSFULL_CODE);
		returnMap.put("responseMessage", Constant.SUCCESSFULL_MSG);
		return ResponseEntity.status(HttpStatus.OK).body(returnMap);
	}
	
	@Bean
	public GenderClassificationValidator getGenderClassificationsValidator() {
		return new GenderClassificationValidator();
	}

}
