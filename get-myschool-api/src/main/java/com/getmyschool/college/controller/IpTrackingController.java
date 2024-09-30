package com.getmyschool.college.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServlet;
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

import com.getmyschool.college.service.IpTrackingService;
import com.getmyschool.college.validator.IpTrackingValidator;
import com.getmyschool.common.contant.Constant;
import com.getmyschool.common.dto.IpTrackingDTO;
import com.getmyschool.common.exception.FieldException;

@RestController
@RequestMapping("/ipTracking")
public class IpTrackingController  {
	
	private static Logger LOGGER = LoggerFactory.getLogger(IpTrackingController.class);

	
	private LinkedHashMap<String, Object> returnMap;
	
	@Autowired
	private IpTrackingValidator ipTrackingValidator;
	
	@Autowired
	private IpTrackingService ipTrackingService;
	
	
	@RequestMapping(value ="/addIpTracking", produces=MediaType.APPLICATION_JSON_VALUE,consumes =MediaType.APPLICATION_JSON_VALUE,method =RequestMethod.POST)
	private ResponseEntity<LinkedHashMap<String, Object>> addIpTracking(@RequestBody IpTrackingDTO ipTrackingDTO,
	HttpServletRequest request,BindingResult result)throws Exception {
		
		HashMap<String, String> map =new HashMap<String, String>();
		MapBindingResult err =new MapBindingResult(map,IpTrackingDTO.class.getName());
		ipTrackingValidator.saveIpTracking(ipTrackingDTO, err);	
		List<ObjectError> list =err.getAllErrors();
		if(list.size() >0) 
			throw new FieldException(list);		
		
		ipTrackingService.saveIpTracking(ipTrackingDTO);
		returnMap =new LinkedHashMap<String, Object>();
		returnMap.put("responsecode", Constant.SUCCESSFULL_CODE);
		returnMap.put("responsemessage", Constant.SUCCESSFULL_MSG);
		return ResponseEntity.status(HttpStatus.OK).body(returnMap);		
	}
		
	
	@RequestMapping(value = "/getIpTrackings", produces=MediaType.APPLICATION_JSON_VALUE, consumes =MediaType.APPLICATION_JSON_VALUE, method= RequestMethod.POST)
	private ResponseEntity<LinkedHashMap<String,  Object>> getIpTrackings(@RequestBody IpTrackingDTO ipTrackingDTO,
			HttpServletRequest request,BindingResult result )throws Exception {
		HashMap<String, String> map = new HashMap<String,String>();
		MapBindingResult err = new MapBindingResult(map, IpTrackingDTO.class.getName());
		ipTrackingValidator.getAllIpTracking(ipTrackingDTO, err);
		List<ObjectError> list =err.getAllErrors();
		if(list.size()>0) {
			throw new FieldException(list);
		}
		
		List<IpTrackingDTO> ipTrackings =ipTrackingService.getAllIpTracking(ipTrackingDTO);
		returnMap =new LinkedHashMap<String, Object> (returnMap);
		returnMap.put("ResonseCode",Constant.SUCCESSFULL_CODE);
		returnMap.put("ResponseMessage",Constant.SUCCESSFULL_MSG);
		returnMap.put("ipTracking", ipTrackings);
			
		return ResponseEntity.status(HttpStatus.OK).body(returnMap);	
	}
	
	@RequestMapping(value = "/getIpTracking", produces=MediaType.APPLICATION_JSON_VALUE, consumes =MediaType.APPLICATION_JSON_VALUE, method= RequestMethod.POST)
	private ResponseEntity<LinkedHashMap<String,  Object>> getIpTracking(@RequestBody IpTrackingDTO ipTrackingDTO,
			HttpServletRequest request,BindingResult result )throws Exception {
		HashMap<String, String> map = new HashMap<String,String>();
		MapBindingResult err = new MapBindingResult(map, IpTrackingDTO.class.getName());
		ipTrackingValidator.getIpTrackingById(ipTrackingDTO, err);
		List<ObjectError> list =err.getAllErrors();
		if(list.size()>0) {
			throw new FieldException(list);
		}
		
		IpTrackingDTO ipTrackings =ipTrackingService.getIpTrackingById(ipTrackingDTO);
		returnMap =new LinkedHashMap<String, Object> (returnMap);
		returnMap.put("ResonseCode",Constant.SUCCESSFULL_CODE);
		returnMap.put("ResponseMessage",Constant.SUCCESSFULL_MSG);
		returnMap.put("ipTracking", ipTrackings);
			
		return ResponseEntity.status(HttpStatus.OK).body(returnMap);	
	}
	
	@RequestMapping(value = "/updateIpTracking", produces=MediaType.APPLICATION_JSON_VALUE, consumes =MediaType.APPLICATION_JSON_VALUE, method= RequestMethod.POST)
	private ResponseEntity<LinkedHashMap<String,  Object>> updateIpTracking(@RequestBody IpTrackingDTO ipTrackingDTO,
			HttpServletRequest request,BindingResult result )throws Exception {
		HashMap<String, String> map = new HashMap<String,String>();
		MapBindingResult err = new MapBindingResult(map, IpTrackingDTO.class.getName());
		ipTrackingValidator.updateIpTracking(ipTrackingDTO, err);
		List<ObjectError> list =err.getAllErrors();
		if(list.size()>0) {
			throw new FieldException(list);
		}
		
		ipTrackingService.updateIpTracking(ipTrackingDTO);
		returnMap =new LinkedHashMap<String, Object> (returnMap);
		returnMap.put("ResonseCode",Constant.SUCCESSFULL_CODE);
		returnMap.put("ResponseMessage",Constant.SUCCESSFULL_MSG);			
		return ResponseEntity.status(HttpStatus.OK).body(returnMap);	
	}
	
	
	@RequestMapping(value = "/deleteIpTracking", produces=MediaType.APPLICATION_JSON_VALUE, consumes =MediaType.APPLICATION_JSON_VALUE, method= RequestMethod.POST)
	private ResponseEntity<LinkedHashMap<String,  Object>> deleteIpTracking(@RequestBody IpTrackingDTO ipTrackingDTO,
			HttpServletRequest request,BindingResult result )throws Exception {
		HashMap<String, String> map = new HashMap<String,String>();
		MapBindingResult err = new MapBindingResult(map, IpTrackingDTO.class.getName());
		ipTrackingValidator.deleteIpTracking(ipTrackingDTO, err);
		List<ObjectError> list =err.getAllErrors();
		if(list.size()>0) {
			throw new FieldException(list);
		}
		
        ipTrackingService.deleteIpTracking(ipTrackingDTO);
		returnMap =new LinkedHashMap<String, Object> (returnMap);
		returnMap.put("ResonseCode",Constant.SUCCESSFULL_CODE);
		returnMap.put("ResponseMessage",Constant.SUCCESSFULL_MSG);			
		return ResponseEntity.status(HttpStatus.OK).body(returnMap);	
	}
	
	@Bean
	public IpTrackingValidator getIpTrackingValidator() {
		return new IpTrackingValidator();
	}

}
