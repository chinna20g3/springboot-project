package com.getmyschool.college.service;

import java.util.List;

import com.getmyschool.common.dto.SchoolTypeDTO;

public interface SchoolTypeService {
	    
	public void saveSchoolType(SchoolTypeDTO schoolTypeDTO);

	public  List<SchoolTypeDTO> getAllSchoolTypes(SchoolTypeDTO schoolTypeDTO);
	
	public SchoolTypeDTO getSchoolTypeById(SchoolTypeDTO schoolTypeDTO);

	public void updateSchoolType(SchoolTypeDTO schoolTypeDTO);
}
