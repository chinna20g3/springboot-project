package com.getmyschool.college.service;

import java.util.List;

import com.getmyschool.common.domain.PartnerLogins;
import com.getmyschool.common.dto.PartnerLoginsDTO;

public interface PartnerLoginsService {

	public void savePartnerLogins(PartnerLoginsDTO partnerLoginsDTO);

	public List<PartnerLoginsDTO> getAllPartnerLogins(PartnerLoginsDTO partnerLoginsDTO);

	public PartnerLoginsDTO getPartnerLoginsById(PartnerLoginsDTO  partnerLoginsDTO);

	public void updatePartnerLogins(PartnerLoginsDTO partnerLoginsDTO);

	public void deletePartnerLogins(PartnerLoginsDTO partnerLoginsDTO);

}
