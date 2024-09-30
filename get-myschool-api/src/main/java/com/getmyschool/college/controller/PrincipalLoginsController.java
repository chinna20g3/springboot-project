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

import com.getmyschool.college.service.PrincipalLoginsService;
import com.getmyschool.college.validator.PrincipalLoginsValidator;
import com.getmyschool.common.contant.Constant;
import com.getmyschool.common.dto.PrincipalLoginsDTO;
import com.getmyschool.common.exception.FieldException;


@RestController
@RequestMapping("/principalLogins")
public class PrincipalLoginsController {

	@Autowired
	private PrincipalLoginsService principalLoginsService;
	
	@Autowired
	private PrincipalLoginsValidator principalLoginsValidator;
	
	private LinkedHashMap<String, Object> returnMap;
	
	private static Logger LOGGER = LoggerFactory.getLogger(PrincipalLoginsController.class);

	@RequestMapping(value = "/addPrincipalLogins",produces =MediaType.APPLICATION_JSON_VALUE,consumes =MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
	public ResponseEntity<LinkedHashMap<String, Object>> addPrincipalLogins(@RequestBody PrincipalLoginsDTO principalLoginsDTO,
			HttpServletRequest request, BindingResult result) throws Exception {

		HashMap<String, String> map =new HashMap<String, String>();
		MapBindingResult err = new MapBindingResult (map, PrincipalLoginsDTO.class.getName());
		principalLoginsValidator.savePrincipalLogins(principalLoginsDTO, err);
		List<ObjectError> list =err.getAllErrors();
		if(list.size() >0)
			throw new FieldException(list);
		
		principalLoginsService.savePrincipalLogins(principalLoginsDTO);
		returnMap =new LinkedHashMap<String, Object>();
		returnMap.put("responseCode",Constant.SUCCESSFULL_CODE);
		returnMap.put("responseMessage",Constant.SUCCESSFULL_MSG);
		return ResponseEntity.status(HttpStatus.OK).body(returnMap);
	}
		
	@RequestMapping(value = "/getPrincipalLogins",produces =MediaType.APPLICATION_JSON_VALUE,consumes =MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
	public ResponseEntity<LinkedHashMap<String, Object>> getPrincipalLogins(@RequestBody PrincipalLoginsDTO principalLoginsDTO,
			HttpServletRequest request, BindingResult result)throws Exception {
		HashMap<String,String> map =new HashMap<String,String>();
		MapBindingResult err =new MapBindingResult(map, PrincipalLoginsDTO.class.getName());
		principalLoginsValidator.getAllPrincipalLogins(principalLoginsDTO, err);
		List<ObjectError> list =err.getAllErrors();
		if(list.size()>0)
			throw new FieldException(list);
		
		List<PrincipalLoginsDTO> principalLogins =principalLoginsService.getAllPrincipalLogins(principalLoginsDTO);
		returnMap =new LinkedHashMap<String, Object>();
		returnMap.put("responseCode",Constant.SUCCESSFULL_CODE);
		returnMap.put("responseMessage",Constant.SUCCESSFULL_MSG);
		returnMap.put("principalLogins", principalLogins);
		return ResponseEntity.status(HttpStatus.OK).body(returnMap);
		
	}
	@RequestMapping(value = "/getPrincipalLoginsById",produces =MediaType.APPLICATION_JSON_VALUE,consumes =MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
	public ResponseEntity<LinkedHashMap<String, Object>> getPrincipalLoginsById(@RequestBody PrincipalLoginsDTO principalLoginsDTO,
			HttpServletRequest request, BindingResult result)throws Exception {
		HashMap<String,String> map =new HashMap<String,String>();
		MapBindingResult err =new MapBindingResult(map, PrincipalLoginsDTO.class.getName());
		principalLoginsValidator.getPrincipalLoginsById(principalLoginsDTO, err);
		List<ObjectError> list =err.getAllErrors();
		if(list.size()>0)
			throw new FieldException(list);
		
		PrincipalLoginsDTO principalLogins =principalLoginsService.getPrincipalLoginsById(principalLoginsDTO);
		returnMap =new LinkedHashMap<String, Object>();
		returnMap.put("responseCode",Constant.SUCCESSFULL_CODE);
		returnMap.put("responseMessage",Constant.SUCCESSFULL_MSG);
		returnMap.put("principalLogins", principalLogins);
		return ResponseEntity.status(HttpStatus.OK).body(returnMap);
		
	}
	
		

	@RequestMapping(value = "/updatePrincipalLogins",produces =MediaType.APPLICATION_JSON_VALUE,consumes =MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
	public ResponseEntity<LinkedHashMap<String, Object>> updatePrincipalLogins(@RequestBody PrincipalLoginsDTO principalLoginsDTO,
			HttpServletRequest request,BindingResult result) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		MapBindingResult err = new MapBindingResult(map, PrincipalLoginsDTO.class.getName());
		principalLoginsValidator.updatePrincipalLogins(principalLoginsDTO, err);
		List<ObjectError> list =err.getAllErrors();
		if(list.size() >0)
			throw new FieldException(list);
		
		principalLoginsService.updatePrincipalLogins(principalLoginsDTO);
		returnMap =new LinkedHashMap<String, Object>();
		returnMap.put("responseCode",Constant.SUCCESSFULL_CODE);
		returnMap.put("responseMessage",Constant.SUCCESSFULL_MSG);
		return ResponseEntity.status(HttpStatus.OK).body(returnMap);
		
	}
	@RequestMapping(value = "/deletePrincipalLogins", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<LinkedHashMap<String, Object>> deletePrincipalLogins(@RequestBody PrincipalLoginsDTO principalLoginsDTO,
			HttpServletRequest request, BindingResult result) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		MapBindingResult err = new MapBindingResult(map, PrincipalLoginsDTO.class.getName());
		principalLoginsValidator.deletePrincipalLogins(principalLoginsDTO, err);
		List<ObjectError> list = err.getAllErrors();
		if (list.size() > 0)
			throw new FieldException(list);

		principalLoginsService.deletePrincipalLogins(principalLoginsDTO);
		returnMap = new LinkedHashMap<String, Object>();
		returnMap.put("responseCode", Constant.SUCCESSFULL_CODE);
		returnMap.put("responseMessage", Constant.SUCCESSFULL_MSG);
		return ResponseEntity.status(HttpStatus.OK).body(returnMap);
	}
	
	@Bean
	public PrincipalLoginsValidator getPrincipalLoginsValidator() {
		return new PrincipalLoginsValidator();
	}
	
}