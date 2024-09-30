package com.getmyschool.college.service;

import java.util.List;

import com.getmyschool.common.dto.SchoolApprovalDTO;

public interface SchoolApprovalService {

	public void saveSchoolApproval(SchoolApprovalDTO schoolApprovalDTO);
	
	public List<SchoolApprovalDTO> getAllSchoolApproval(SchoolApprovalDTO schoolApprovalDTO);
	
	public SchoolApprovalDTO getSchoolApprovalById(SchoolApprovalDTO schoolApprovalDTO);
	
	public void updateSchoolApproval(SchoolApprovalDTO schoolApprovalDTO);
	
	public void deleteSchoolApproval(SchoolApprovalDTO schoolApprovalDTO);
}
