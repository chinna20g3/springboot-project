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

import com.getmyschool.college.service.AmenitiesService;
import com.getmyschool.college.validator.AmenitiesValidator;
import com.getmyschool.common.contant.Constant;
import com.getmyschool.common.dto.AmenitiesDTO;
import com.getmyschool.common.exception.FieldException;

@RestController
@RequestMapping("/amenities")
public class AmenitiesController {

	@Autowired
	private AmenitiesValidator amenitiesValidator;

	@Autowired
	private AmenitiesService amenitiesService;

	private LinkedHashMap<String, Object> returnMap;

	private static Logger LOGGER = LoggerFactory.getLogger(AmenitiesController.class);

	@RequestMapping(value = "/addAmenities", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<LinkedHashMap<String, Object>> addAmenities(@RequestBody AmenitiesDTO amenitiesDTO,
			HttpServletRequest request, BindingResult result) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		MapBindingResult err = new MapBindingResult(map, AmenitiesDTO.class.getName());
		amenitiesValidator.saveAmenities(amenitiesDTO, err);
		List<ObjectError> list = err.getAllErrors();
		if (list.size() > 0)
			throw new FieldException(list);

		amenitiesService.saveAmenities(amenitiesDTO);
		returnMap = new LinkedHashMap<String, Object>();
		returnMap.put("responseCode", Constant.SUCCESSFULL_CODE);
		returnMap.put("responseMessage", Constant.SUCCESSFULL_MSG);
		return ResponseEntity.status(HttpStatus.OK).body(returnMap);
	}

	@RequestMapping(value = "/getAmenities", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<LinkedHashMap<String, Object>> getAmenities(@RequestBody AmenitiesDTO amenitiesDTO,
			HttpServletRequest request, BindingResult result) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		MapBindingResult err = new MapBindingResult(map, AmenitiesDTO.class.getName());
		amenitiesValidator.getAllAmenities(amenitiesDTO, err);
		List<ObjectError> list = err.getAllErrors();
		if (list.size() > 0)
			throw new FieldException(list);

		List<AmenitiesDTO> amenities = amenitiesService.getAllAmenities(amenitiesDTO);
		returnMap = new LinkedHashMap<String, Object>();
		returnMap.put("responseCode", Constant.SUCCESSFULL_CODE);
		returnMap.put("responseMessage", Constant.SUCCESSFULL_MSG);
		returnMap.put("amenities", amenities);
		return ResponseEntity.status(HttpStatus.OK).body(returnMap);
	}

	@RequestMapping(value = "/getAmenitiesById", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<LinkedHashMap<String, Object>> getAmenitiesById(@RequestBody AmenitiesDTO amenitiesDTO,
			HttpServletRequest request, BindingResult result) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		MapBindingResult err = new MapBindingResult(map, AmenitiesDTO.class.getName());
		amenitiesValidator.getAmenitiesById(amenitiesDTO, err);
		List<ObjectError> list = err.getAllErrors();
		if (list.size() > 0)
			throw new FieldException(list);

		AmenitiesDTO amenities = amenitiesService.getAmenitiesById(amenitiesDTO);
		returnMap = new LinkedHashMap<String, Object>();
		returnMap.put("responseCode", Constant.SUCCESSFULL_CODE);
		returnMap.put("responseMessage", Constant.SUCCESSFULL_MSG);
		returnMap.put("amenities", amenities);
		return ResponseEntity.status(HttpStatus.OK).body(returnMap);
	}

	@RequestMapping(value = "/updateAmenities", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<LinkedHashMap<String, Object>> updateAmenities(@RequestBody AmenitiesDTO amenitiesDTO,
			HttpServletRequest request, BindingResult result) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		MapBindingResult err = new MapBindingResult(map, AmenitiesDTO.class.getName());
		amenitiesValidator.updateAmenities(amenitiesDTO, err);
		List<ObjectError> list = err.getAllErrors();
		if (list.size() > 0)
			throw new FieldException(list);

		amenitiesService.updateAmenities(amenitiesDTO);
		returnMap = new LinkedHashMap<String, Object>();
		returnMap.put("responseCode", Constant.SUCCESSFULL_CODE);
		returnMap.put("responseMessage", Constant.SUCCESSFULL_MSG);
		return ResponseEntity.status(HttpStatus.OK).body(returnMap);
	}

	@Bean
	public AmenitiesValidator getAmenitiessValidator() {
		return new AmenitiesValidator();
	}
	
}
