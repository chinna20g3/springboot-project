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

import com.getmyschool.college.service.PartnerLoginsService;
import com.getmyschool.college.validator.PartnerLoginsValidator;
import com.getmyschool.common.contant.Constant;
import com.getmyschool.common.dto.PartnerLoginsDTO;
import com.getmyschool.common.exception.FieldException;

@RestController
@RequestMapping("/partnerLogins")
public class PartnerLoginsController {

	@Autowired
	private PartnerLoginsValidator partnerLoginsValidator;
	
	@Autowired
	private PartnerLoginsService partnerLoginsService;
	
	private LinkedHashMap<String, Object> returnMap;

	private static Logger LOGGER = LoggerFactory.getLogger(PartnerLoginsController.class);

	@RequestMapping(value = "/addPartnerLogins", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<LinkedHashMap<String, Object>> addPartnerLogins(@RequestBody PartnerLoginsDTO partnerLoginsDTO,
			HttpServletRequest request, BindingResult result) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		MapBindingResult err = new MapBindingResult(map, PartnerLoginsDTO.class.getName());
		partnerLoginsValidator.savePartnerLogins(partnerLoginsDTO, err);
		List<ObjectError> list = err.getAllErrors();
		if (list.size() > 0)
			throw new FieldException(list);

		partnerLoginsService.savePartnerLogins(partnerLoginsDTO);
		returnMap = new LinkedHashMap<String, Object>();
		returnMap.put("responseCode", Constant.SUCCESSFULL_CODE);
		returnMap.put("responseMessage", Constant.SUCCESSFULL_MSG);
		return ResponseEntity.status(HttpStatus.OK).body(returnMap);
	}
	
	@RequestMapping(value = "/getPartnerLogins", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<LinkedHashMap<String, Object>> getPartnerLogins(@RequestBody PartnerLoginsDTO partnerLoginsDTO,
			HttpServletRequest request, BindingResult result) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		MapBindingResult err = new MapBindingResult(map, PartnerLoginsDTO.class.getName());
		partnerLoginsValidator.getAllPartnerLogins(partnerLoginsDTO, err);
		List<ObjectError> list = err.getAllErrors();
		if (list.size() > 0)
			throw new FieldException(list);

		List<PartnerLoginsDTO> partnerLogins = partnerLoginsService.getAllPartnerLogins(partnerLoginsDTO);
		returnMap = new LinkedHashMap<String, Object>();
		returnMap.put("responseCode", Constant.SUCCESSFULL_CODE);
		returnMap.put("responseMessage", Constant.SUCCESSFULL_MSG);
		returnMap.put("partnerLogins", partnerLogins);
		return ResponseEntity.status(HttpStatus.OK).body(returnMap);
	}

	@RequestMapping(value = "/getPartnerLoginsById", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<LinkedHashMap<String, Object>> getPartnerLoginsById(@RequestBody PartnerLoginsDTO partnerLoginsDTO,
			HttpServletRequest request, BindingResult result) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		MapBindingResult err = new MapBindingResult(map, PartnerLoginsDTO.class.getName());
		partnerLoginsValidator.getPartnerLoginsById(partnerLoginsDTO, err);
		List<ObjectError> list = err.getAllErrors();
		if (list.size() > 0)
			throw new FieldException(list);

		PartnerLoginsDTO partnerLogins = partnerLoginsService.getPartnerLoginsById(partnerLoginsDTO);
		returnMap = new LinkedHashMap<String, Object>();
		returnMap.put("responseCode", Constant.SUCCESSFULL_CODE);
		returnMap.put("responseMessage", Constant.SUCCESSFULL_MSG);
		returnMap.put("partnerLogins", partnerLogins);
		return ResponseEntity.status(HttpStatus.OK).body(returnMap);
	}

	

	@RequestMapping(value = "/updatePartnerLogins", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<LinkedHashMap<String, Object>> updatePartnerLogins(@RequestBody PartnerLoginsDTO partnerLoginsDTO,
			HttpServletRequest request, BindingResult result) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		MapBindingResult err = new MapBindingResult(map, PartnerLoginsDTO.class.getName());
		partnerLoginsValidator.updatePartnerLogins(partnerLoginsDTO, err);
		List<ObjectError> list = err.getAllErrors();
		if (list.size() > 0)
			throw new FieldException(list);

		partnerLoginsService.updatePartnerLogins(partnerLoginsDTO);
		returnMap = new LinkedHashMap<String, Object>();
		returnMap.put("responseCode", Constant.SUCCESSFULL_CODE);
		returnMap.put("responseMessage", Constant.SUCCESSFULL_MSG);
		return ResponseEntity.status(HttpStatus.OK).body(returnMap);
	}

	@RequestMapping(value = "/deletePartnerLogins", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<LinkedHashMap<String, Object>> deletePartnerLogins(@RequestBody PartnerLoginsDTO partnerLoginsDTO,
			HttpServletRequest request, BindingResult result) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		MapBindingResult err = new MapBindingResult(map, PartnerLoginsDTO.class.getName());
		partnerLoginsValidator.deletePartnerLogins(partnerLoginsDTO, err);
		List<ObjectError> list = err.getAllErrors();
		if (list.size() > 0)
			throw new FieldException(list);

		partnerLoginsService.deletePartnerLogins(partnerLoginsDTO);
		returnMap = new LinkedHashMap<String, Object>();
		returnMap.put("responseCode", Constant.SUCCESSFULL_CODE);
		returnMap.put("responseMessage", Constant.SUCCESSFULL_MSG);
		return ResponseEntity.status(HttpStatus.OK).body(returnMap);
	}
	
	@Bean
	public PartnerLoginsValidator getPartnerLoginsValidator() {
		return new PartnerLoginsValidator();
	}
}

