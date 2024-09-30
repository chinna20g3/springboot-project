package com.getmyschool.common.dao;

import java.util.List;

import com.getmyschool.common.domain.LeadsManager;
import com.getmyschool.common.dto.LeadsManagerDTO;

public interface LeadsManagerDao {

	public LeadsManager saveLeadsManager(LeadsManagerDTO leadsManagersDTO);
	
	public List<LeadsManager> getAllLeadsManager(LeadsManagerDTO leadsManagersDTO);
	
	public LeadsManager getLeadsManagerById(Long Id);
	
	public LeadsManager getLeadsManagerByEmail(String email);

}
