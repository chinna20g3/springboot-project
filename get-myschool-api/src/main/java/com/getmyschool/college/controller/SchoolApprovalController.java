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

import com.getmyschool.college.service.SchoolApprovalService;
import com.getmyschool.college.validator.SchoolApprovalValidator;
import com.getmyschool.common.contant.Constant;
import com.getmyschool.common.dto.SchoolApprovalDTO;
import com.getmyschool.common.exception.FieldException;

@RestController
@RequestMapping("/schoolApproval")
public class SchoolApprovalController {

	@Autowired
	private SchoolApprovalService schoolApprovalService;
	
	@Autowired
	private SchoolApprovalValidator schoolApprovalValidator;
	
	private LinkedHashMap<String, Object> returnMap;
	
	private static Logger LOGGER = LoggerFactory.getLogger(SchoolApprovalController.class);

	@RequestMapping(value ="/addSchoolApproval", produces = MediaType.APPLICATION_JSON_VALUE,consumes= MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
	public ResponseEntity<LinkedHashMap<String, Object>> addSchoolApproval(@RequestBody SchoolApprovalDTO schoolApprovalDTO,
			HttpServletRequest request, BindingResult result)throws Exception {
	HashMap<String, String> map =new HashMap<String,String>();
	MapBindingResult err =new MapBindingResult(map ,SchoolApprovalDTO.class.getName());
	schoolApprovalValidator.saveSchoolApproval(schoolApprovalDTO, err);
	List<ObjectError> list =err.getAllErrors();
	if(list.size()>0)
		throw new FieldException(list);
	
	schoolApprovalService.saveSchoolApproval(schoolApprovalDTO);
	returnMap = new LinkedHashMap<String, Object>();
	returnMap.put("responseCode", Constant.SUCCESSFULL_CODE);
	returnMap.put("responseMessage", Constant.SUCCESSFULL_CODE);
	return ResponseEntity.status(HttpStatus.OK).body(returnMap);
	}
	
	@RequestMapping (value ="/getSchoolApprovals", produces =MediaType.APPLICATION_JSON_VALUE, consumes =MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
	private ResponseEntity<LinkedHashMap<String, Object>> getSchoolApprovals(@RequestBody SchoolApprovalDTO schoolApprovalDTO,
			HttpServletRequest request,BindingResult result)throws Exception{
		HashMap<String, String> map =new HashMap<String, String>();
		MapBindingResult err = new MapBindingResult(map ,SchoolApprovalDTO.class.getName());
		schoolApprovalValidator.getAllSchoolApproval(schoolApprovalDTO, err);
		List<ObjectError> list =err.getAllErrors();
		if(list.size()> 0)
			throw new FieldException(list);
		List<SchoolApprovalDTO> schoolApprovals = schoolApprovalService.getAllSchoolApproval(schoolApprovalDTO);
		returnMap =new LinkedHashMap<String, Object>();
		returnMap.put("responseCode",Constant.SUCCESSFULL_CODE);
		returnMap.put("responseMessage",Constant.SUCCESSFULL_MSG);
		returnMap.put("schoolApprovals", schoolApprovals);
		return ResponseEntity.status(HttpStatus.OK).body(returnMap);
	}
		
	
	@RequestMapping (value = "getSchoolApproval",produces =MediaType.APPLICATION_JSON_VALUE, consumes =MediaType.APPLICATION_JSON_VALUE, method =RequestMethod.POST)
	private ResponseEntity<LinkedHashMap<String,Object>> getSchoolApproval (@RequestBody SchoolApprovalDTO schoolApprovalDTO,
			HttpServletRequest request,BindingResult result)throws Exception {
		HashMap<String, String> map =new HashMap<String, String>();
		MapBindingResult err =new MapBindingResult(map, SchoolApprovalDTO.class.getName());
		schoolApprovalValidator.getSchoolApprovalById(schoolApprovalDTO, err);
		List<ObjectError> list =err.getAllErrors();
		if(list.size()>0)
			throw new FieldException(list);
		
		SchoolApprovalDTO schoolApprovals =schoolApprovalService.getSchoolApprovalById(schoolApprovalDTO);
		returnMap =new LinkedHashMap<String, Object>();
		returnMap.put("responseCode",Constant.SUCCESSFULL_CODE );
		returnMap.put("responseMSG", Constant.SUCCESSFULL_MSG);
		returnMap.put("schoolApprovals", schoolApprovals);
		return ResponseEntity.status(HttpStatus.OK).body(returnMap);
			
	}
	
	@RequestMapping(value = "/updateSchoolApproval", produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE,method =RequestMethod.POST)
	private ResponseEntity<LinkedHashMap<String, Object>> updateSchoolApproval(SchoolApprovalDTO schoolApprovalDTO,
			HttpServletRequest request,BindingResult result)throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		MapBindingResult err = new MapBindingResult(map, SchoolApprovalDTO.class.getName());
		
		schoolApprovalValidator.updateSchoolApproval(schoolApprovalDTO, err);
		List<ObjectError> list =err.getAllErrors();
		if(list.size()>0)
			throw new FieldException(list);
		
		schoolApprovalService.updateSchoolApproval(schoolApprovalDTO);
		returnMap =new LinkedHashMap<String, Object>();
		returnMap.put("responseCode",Constant.SUCCESSFULL_CODE );
		returnMap.put("responseMSG", Constant.SUCCESSFULL_MSG);
		return ResponseEntity.status(HttpStatus.OK).body(returnMap);
			
	}		
	
	
	@RequestMapping(value ="/deleteSchoolApproval",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
	private ResponseEntity<LinkedHashMap<String,Object>> deleteSchoolApproval(@RequestBody SchoolApprovalDTO schoolApprovalDTO,
			HttpServletRequest request, BindingResult result) throws Exception{
		HashMap<String,String> map = new HashMap<String, String>();
	    MapBindingResult err = new MapBindingResult(map, SchoolApprovalDTO.class.getName());
	    
	    schoolApprovalValidator.deleteSchoolApproval(schoolApprovalDTO, err);
	    List<ObjectError> list =err.getAllErrors();
	    if(list.size()>0)
	    	throw new FieldException(list);
	    
	    schoolApprovalService.deleteSchoolApproval(schoolApprovalDTO);
	    returnMap = new LinkedHashMap<String , Object>();
	    returnMap.put("responseCode",Constant.SUCCESSFULL_CODE );
	    returnMap.put("responseMsg",Constant.SUCCESSFULL_MSG);
	    return ResponseEntity.status(HttpStatus.OK).body(returnMap);
	    
	}
	
	@Bean
	public SchoolApprovalValidator getSchoolApprovalsValidator() {
		return new SchoolApprovalValidator();
		}
	
}
	
























