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

import com.getmyschool.college.service.GradeClassificationService;
import com.getmyschool.college.validator.GradeClassificationValidator;
import com.getmyschool.common.contant.Constant;
import com.getmyschool.common.dto.GradeClassificationDTO;
import com.getmyschool.common.exception.FieldException;

@RestController
@RequestMapping("/gradeClassification")
public class GradeClassificationController {

	
	@Autowired
	private GradeClassificationValidator gradeClassificationValidator;
	
    @Autowired
    private GradeClassificationService gradeClassificationService;
    
	private LinkedHashMap<String, Object> returnMap;
	
	private static Logger LOGGER = LoggerFactory.getLogger(GradeClassificationController.class);

	@RequestMapping(value = "/addGradeClassification", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<LinkedHashMap<String, Object>> addGradeClassification(@RequestBody GradeClassificationDTO gradeClassificationDTO,
			HttpServletRequest request, BindingResult result) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		MapBindingResult err = new MapBindingResult(map, GradeClassificationDTO.class.getName());
		gradeClassificationValidator.saveGradeClassification(gradeClassificationDTO, err);
		List<ObjectError> list = err.getAllErrors();
		if (list.size() > 0)
			throw new FieldException(list);

		gradeClassificationService.saveGradeClassification(gradeClassificationDTO);
		returnMap = new LinkedHashMap<String, Object>();
		returnMap.put("responseCode", Constant.SUCCESSFULL_CODE);
		returnMap.put("responseMessage", Constant.SUCCESSFULL_MSG);
		return ResponseEntity.status(HttpStatus.OK).body(returnMap);
	}

	@RequestMapping(value = "/getGradeClassifications", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<LinkedHashMap<String, Object>> getGradeClassifications(@RequestBody GradeClassificationDTO gradeClassificationDTO,
			HttpServletRequest request, BindingResult result) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		MapBindingResult err = new MapBindingResult(map, GradeClassificationDTO.class.getName());
		gradeClassificationValidator.getAllGradeClassifications(gradeClassificationDTO, err);
		List<ObjectError> list = err.getAllErrors();
		if (list.size() > 0)
			throw new FieldException(list);

		List<GradeClassificationDTO> gradeClassifications = gradeClassificationService.getAllGradeClassifications(gradeClassificationDTO);
		returnMap = new LinkedHashMap<String, Object>();
		returnMap.put("responseCode", Constant.SUCCESSFULL_CODE);
		returnMap.put("responseMessage", Constant.SUCCESSFULL_MSG);
		returnMap.put("gradeClassifications", gradeClassifications);
		return ResponseEntity.status(HttpStatus.OK).body(returnMap);
	}

	@RequestMapping(value = "/getGradeClassification", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<LinkedHashMap<String, Object>> getGradeClassification(@RequestBody GradeClassificationDTO gradeClassificationDTO,
			HttpServletRequest request, BindingResult result) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		MapBindingResult err = new MapBindingResult(map, GradeClassificationDTO.class.getName());
		gradeClassificationValidator.getGradeClassificationById(gradeClassificationDTO, err);
		List<ObjectError> list = err.getAllErrors();
		if (list.size() > 0)
			throw new FieldException(list);

		GradeClassificationDTO gradeClassifications = gradeClassificationService.getGradeClassificationById(gradeClassificationDTO);
		returnMap = new LinkedHashMap<String, Object>();
		returnMap.put("responseCode", Constant.SUCCESSFULL_CODE);
		returnMap.put("responseMessage", Constant.SUCCESSFULL_MSG);
		returnMap.put("gradeClassifications", gradeClassifications);
		return ResponseEntity.status(HttpStatus.OK).body(returnMap);
	}

	@RequestMapping(value = "/updateGradeClassification", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<LinkedHashMap<String, Object>> updateGradeClassification(@RequestBody GradeClassificationDTO gradeClassificationDTO,
			HttpServletRequest request, BindingResult result) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		MapBindingResult err = new MapBindingResult(map, GradeClassificationDTO.class.getName());
		gradeClassificationValidator.updateGradeClassification(gradeClassificationDTO, err);
		List<ObjectError> list = err.getAllErrors();
		if (list.size() > 0)
			throw new FieldException(list);

		gradeClassificationService.updateGradeClassification(gradeClassificationDTO);
		returnMap = new LinkedHashMap<String, Object>();
		returnMap.put("responseCode", Constant.SUCCESSFULL_CODE);
		returnMap.put("responseMessage", Constant.SUCCESSFULL_MSG);
		return ResponseEntity.status(HttpStatus.OK).body(returnMap);
	}

	@Bean
	public GradeClassificationValidator getGradeClassificationsValidator() {
		return new GradeClassificationValidator();
	}

}
