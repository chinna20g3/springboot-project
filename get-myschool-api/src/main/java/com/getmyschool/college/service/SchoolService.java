package com.getmyschool.college.service;

import java.util.List;

import com.getmyschool.common.dto.SchoolDTO;


public interface SchoolService {

    public void saveSchool(SchoolDTO schoolDTO);
	
	public List<SchoolDTO> getAllSchools(SchoolDTO schoolDTO);

	public SchoolDTO  getSchoolById(SchoolDTO schoolDTO);
	
	public void updateSchool(SchoolDTO schoolDTO);
}
