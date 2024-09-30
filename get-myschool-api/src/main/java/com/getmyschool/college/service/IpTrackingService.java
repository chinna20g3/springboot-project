package com.getmyschool.college.service;

import java.util.List; 

import com.getmyschool.common.dto.IpTrackingDTO;

public interface IpTrackingService {

	public void saveIpTracking(IpTrackingDTO ipTrackingDTO);
	
	public List<IpTrackingDTO> getAllIpTracking(IpTrackingDTO ipTrackingDTO);
	
	public IpTrackingDTO getIpTrackingById(IpTrackingDTO ipTrackingDTO);
	
	public void updateIpTracking(IpTrackingDTO ipTrackingDTO);
	
	public void deleteIpTracking(IpTrackingDTO ipTrackingDTO);


}
