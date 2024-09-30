package com.getmyschool.college.service;

import java.util.List;

import com.getmyschool.common.dto.PrincipalLoginsDTO;

public interface PrincipalLoginsService {

	public void savePrincipalLogins(PrincipalLoginsDTO principalLoginsDTO);
	
	public List<PrincipalLoginsDTO> getAllPrincipalLogins(PrincipalLoginsDTO principalLoginsDTO);
	
	public PrincipalLoginsDTO getPrincipalLoginsById(PrincipalLoginsDTO principalLoginsDTO);
	
	public void updatePrincipalLogins(PrincipalLoginsDTO principalLoginsDTO);

	public void deletePrincipalLogins(PrincipalLoginsDTO principalLoginsDTO);

	
}
