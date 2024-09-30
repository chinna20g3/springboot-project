package com.getmyschool.common.converter;

import org.springframework.stereotype.Component;

import com.getmyschool.common.domain.IpTracking;
import com.getmyschool.common.dto.IpTrackingDTO;

@Component
public class IpTrackingConverter {
	
	/**
	 *@Converter IpTracking to IpTrackingDTO
	 * @param IpTrackingDao
	 * @return 
	 */

	public static IpTrackingDTO getIpTrackingDTOByIpTracking(IpTracking ipTracking) {
		IpTrackingDTO dto =new IpTrackingDTO();
		
		dto.setIpAddress(ipTracking.getIpAddress());
		dto.setLatitude(ipTracking.getLatitude());
		dto.setLongitude(ipTracking.getLongitude());
		dto.setLocation(ipTracking.getLocation());
		dto.setStatus(ipTracking.getStatus());
		dto.setCreatedBy(ipTracking.getCreatedBy());
		dto.setCreatedDate(ipTracking.getCreatedDate());
		dto.setUpdatedBy(ipTracking.getUpdatedBy());
		dto.setCreatedDate(ipTracking.getCreatedDate());
		
		return dto;		
	}
	
	/**
	 *@Converter IpTrackingDTO to IpTracking
	 * @param IpTrackingDTO
	 * @return 
	 */

	public static IpTracking getIpTrackingByIpTrackingDTO(IpTrackingDTO ipTrackingDTO) {
		IpTracking ipTracking =new IpTracking();
		ipTracking.setIpAddress(ipTrackingDTO.getIpAddress());
		ipTracking.setLatitude(ipTrackingDTO.getLatitude());
		ipTracking.setLongitude(ipTrackingDTO.getLongitude());
		ipTracking.setLocation(ipTrackingDTO.getLocation());
		ipTracking.setStatus(ipTrackingDTO.getStatus());
		ipTracking.setCreatedBy(ipTrackingDTO.getCreatedBy());
		ipTracking.setCreatedDate(ipTrackingDTO.getCreatedDate());
		ipTracking.setUpdatedBy(ipTrackingDTO.getUpdatedBy());
		ipTracking.setCreatedDate(ipTrackingDTO.getCreatedDate());
		
		return ipTracking;		
	}
}
