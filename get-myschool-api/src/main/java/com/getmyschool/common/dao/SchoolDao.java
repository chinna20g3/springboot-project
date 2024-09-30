package com.getmyschool.common.dao;

import java.util.List;

import com.getmyschool.common.domain.School;
import com.getmyschool.common.dto.SchoolDTO;

public interface SchoolDao {

	public School saveSchool(SchoolDTO schoolsDTO);
		
	public List<School> getAllSchool(SchoolDTO schoolsDTO );
	
	public School getSchoolById(Long id);
}
