package com.getmyschool.college.service;

import java.util.List;

import com.getmyschool.common.dto.LeadsManagerDTO;
import com.getmyschool.common.dto.SchoolTypeDTO;


public interface LeadsManagerService {

	public void saveLeadsManager(LeadsManagerDTO leadsManagerDTO);

	public  List<LeadsManagerDTO> getAllLeadsManagers(LeadsManagerDTO leadsManagerDTO);
	
	public LeadsManagerDTO getLeadsManagerById(LeadsManagerDTO leadsManagerDTO);

	public void updateLeadsManager(LeadsManagerDTO leadsManagerDTO);
	
	public void deleteLeadsManager(LeadsManagerDTO leadsManagerDTO);
}
