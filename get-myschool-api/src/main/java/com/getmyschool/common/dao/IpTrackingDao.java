package com.getmyschool.common.dao;

import java.util.List;

import com.getmyschool.common.domain.IpTracking;
import com.getmyschool.common.dto.IpTrackingDTO;

public interface IpTrackingDao {

	public IpTracking saveIpTracking(IpTrackingDTO ipTrackingDTO);
	
	public List<IpTracking> getAllIpTracking(IpTrackingDTO ipTrackingDTO);
	
	public IpTracking getIpTrackingById(Long id);
	
}
