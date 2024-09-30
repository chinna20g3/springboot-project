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

import com.getmyschool.college.service.LeadsManagerService;
import com.getmyschool.college.validator.LeadsManagerValidator;
import com.getmyschool.common.contant.Constant;
import com.getmyschool.common.dto.LeadsManagerDTO;
import com.getmyschool.common.exception.FieldException;


@RestController
@RequestMapping("/leadsManager")
public class LeadsManagerController {

	@Autowired
	private LeadsManagerValidator leadsManagerValidator;

	@Autowired
	private LeadsManagerService leadsManagerService;

	private LinkedHashMap<String, Object> returnMap;

	private static Logger LOGGER = LoggerFactory.getLogger(LeadsManagerController.class);

	
	@RequestMapping(value = "/addLeadsManager", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<LinkedHashMap<String, Object>> addLeadsManager(@RequestBody LeadsManagerDTO leadsManagerDTO,
			HttpServletRequest request, BindingResult result) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		MapBindingResult err = new MapBindingResult(map, LeadsManagerDTO.class.getName());
		leadsManagerValidator.saveLeadsManager(leadsManagerDTO, err);
		List<ObjectError> list = err.getAllErrors();
		if (list.size() > 0)
			throw new FieldException(list);

		leadsManagerService.saveLeadsManager(leadsManagerDTO);
		returnMap = new LinkedHashMap<String, Object>();
		returnMap.put("responseCode", Constant.SUCCESSFULL_CODE);
		returnMap.put("responseMessage", Constant.SUCCESSFULL_MSG);
		return ResponseEntity.status(HttpStatus.OK).body(returnMap);
	}

	
	@RequestMapping(value = "/getLeadsManagers", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<LinkedHashMap<String, Object>> getLeadsManagers(@RequestBody LeadsManagerDTO leadsManagerDTO,
			HttpServletRequest request, BindingResult result) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		MapBindingResult err = new MapBindingResult(map, LeadsManagerDTO.class.getName());
		leadsManagerValidator.getAllLeadsManagers(leadsManagerDTO, err);
		List<ObjectError> list = err.getAllErrors();
		if (list.size() > 0)
			throw new FieldException(list);

		List<LeadsManagerDTO> leadsManagers = leadsManagerService.getAllLeadsManagers(leadsManagerDTO);
		returnMap = new LinkedHashMap<String, Object>();
		returnMap.put("responseCode", Constant.SUCCESSFULL_CODE);
		returnMap.put("responseMessage", Constant.SUCCESSFULL_MSG);
		returnMap.put("leadsManagers", leadsManagers);
		return ResponseEntity.status(HttpStatus.OK).body(returnMap);
	}

	@RequestMapping(value = "/getLeadsManager", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<LinkedHashMap<String, Object>> getLeadsManager(@RequestBody LeadsManagerDTO leadsManagerDTO,
			HttpServletRequest request, BindingResult result) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		MapBindingResult err = new MapBindingResult(map, LeadsManagerDTO.class.getName());
		leadsManagerValidator.getLeadsManagerById(leadsManagerDTO, err);
		List<ObjectError> list = err.getAllErrors();
		if (list.size() > 0)
			throw new FieldException(list);

		LeadsManagerDTO leadsManagers = leadsManagerService.getLeadsManagerById(leadsManagerDTO);
		returnMap = new LinkedHashMap<String, Object>();
		returnMap.put("responseCode", Constant.SUCCESSFULL_CODE);
		returnMap.put("responseMessage", Constant.SUCCESSFULL_MSG);
		returnMap.put("leadsManagers", leadsManagers);
		return ResponseEntity.status(HttpStatus.OK).body(returnMap);
	}

	@RequestMapping(value = "/updateLeadsManager", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<LinkedHashMap<String, Object>> updateLeadsManager(@RequestBody LeadsManagerDTO leadsManagerDTO,
			HttpServletRequest request, BindingResult result) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		MapBindingResult err = new MapBindingResult(map, LeadsManagerDTO.class.getName());
		leadsManagerValidator.updateLeadsManager(leadsManagerDTO, err);
		List<ObjectError> list = err.getAllErrors();
		if (list.size() > 0)
			throw new FieldException(list);

		leadsManagerService.updateLeadsManager(leadsManagerDTO);
		returnMap = new LinkedHashMap<String, Object>();
		returnMap.put("responseCode", Constant.SUCCESSFULL_CODE);
		returnMap.put("responseMessage", Constant.SUCCESSFULL_MSG);
		return ResponseEntity.status(HttpStatus.OK).body(returnMap);
	}
	

	@RequestMapping(value = "/deleteLeadsManager", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<LinkedHashMap<String, Object>> deleteLeadsManager(@RequestBody LeadsManagerDTO leadsManagerDTO,
			HttpServletRequest request, BindingResult result) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		MapBindingResult err = new MapBindingResult(map, LeadsManagerDTO.class.getName());
		leadsManagerValidator.deleteLeadsManager(leadsManagerDTO, err);
		List<ObjectError> list = err.getAllErrors();
		if (list.size() > 0)
			throw new FieldException(list);

		leadsManagerService.deleteLeadsManager(leadsManagerDTO);
		returnMap = new LinkedHashMap<String, Object>();
		returnMap.put("responseCode", Constant.SUCCESSFULL_CODE);
		returnMap.put("responseMessage", Constant.SUCCESSFULL_MSG);
		return ResponseEntity.status(HttpStatus.OK).body(returnMap);
	}
	@Bean
	public LeadsManagerValidator getLeadsManagersValidator() {
		return new LeadsManagerValidator();
	}
}
	
	


