package com.getmyschool.common.dao;

import java.util.List;

import com.getmyschool.common.domain.SchoolType;
import com.getmyschool.common.dto.SchoolTypeDTO;

public interface SchoolTypeDao {
	
	public SchoolType saveSchoolType(SchoolTypeDTO schoolTypesDTO);
		
	public List<SchoolType> getAllSchoolType(SchoolTypeDTO schoolTypesDTO );
	
	public SchoolType getSchoolTypeById(Long id);
	
}