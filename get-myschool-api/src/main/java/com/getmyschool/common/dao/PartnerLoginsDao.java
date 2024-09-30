package com.getmyschool.common.dao;

import java.util.List;

import com.getmyschool.common.domain.PartnerLogins;
import com.getmyschool.common.dto.PartnerLoginsDTO;

public interface PartnerLoginsDao {

	public PartnerLogins savePartnerLogins(PartnerLoginsDTO partnerLoginsDTO);
	
	public List<PartnerLogins> getAllPartnerLogins(PartnerLoginsDTO partnerLoginsDTO);
	
	public PartnerLogins getPartnerLoginsById(Long id);
	
	public PartnerLogins getPartnerLoginsByEmail(String email);
}
