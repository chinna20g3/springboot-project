package com.getmyschool.common.dao;

import java.util.List;

import com.getmyschool.common.domain.SchoolApproval;
import com.getmyschool.common.dto.SchoolApprovalDTO;

public interface SchoolApprovalDao {

	public SchoolApproval saveSchoolApproval(SchoolApprovalDTO schoolApprovalDTO);
	
	public List<SchoolApproval> getAllSchoolApproval(SchoolApprovalDTO schoolApprovalDTO);
	
	public SchoolApproval getSchoolApprovalById(Long id);
	
}
