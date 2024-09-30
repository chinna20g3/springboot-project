package com.getmyschool.common.dao;

import java.util.List;

import com.getmyschool.common.domain.PrincipalLogins;
import com.getmyschool.common.dto.PrincipalLoginsDTO;

public interface PrincipalLoginsDao {

	public PrincipalLogins savePrincipalLogins(PrincipalLoginsDTO principalLoginsDTO);
	
	public List<PrincipalLogins> getAllPrincipalLogins(PrincipalLoginsDTO principalLoginsDTO);

	public PrincipalLogins getPrincipalLoginsById(Long id);
	
}
