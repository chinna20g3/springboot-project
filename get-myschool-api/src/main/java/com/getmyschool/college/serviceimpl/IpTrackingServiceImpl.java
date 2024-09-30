package com.getmyschool.college.serviceimpl;

import java.util.ArrayList; 
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.getmyschool.college.service.IpTrackingService;
import com.getmyschool.common.converter.IpTrackingConverter;
import com.getmyschool.common.dao.IpTrackingDao;
import com.getmyschool.common.domain.IpTracking;
import com.getmyschool.common.domain.Role;
import com.getmyschool.common.dto.IpTrackingDTO;
import com.getmyschool.common.exception.UnAuthorizedException;
import com.getmyschool.common.service.LoginService;
import com.getmyschool.common.validator.RoleEnum;

@Service("IpTrackingServiceImpl")
public class IpTrackingServiceImpl implements IpTrackingService{

	private static final Logger LOGGER = LoggerFactory.getLogger(IpTrackingServiceImpl.class);
	
	@Resource(name ="LoginServiceImpl")
	private LoginService loginService;
	
	@Resource(name ="IpTrackingDaoImpl")
	private IpTrackingDao ipTrackingDao;
	
	@Override
	public void saveIpTracking(IpTrackingDTO ipTrackingDTO) {
		List<Role> roles =loginService.getAllUserRoles(ipTrackingDTO.getCreatedBy());
		boolean adminFlag =roles.stream().anyMatch(x->x.getRole().equals(RoleEnum.SUPER_ADMIN.getRole())
				|| x.getRole().equals(RoleEnum.ADMIN.getRole()));
		if(!(adminFlag))
			throw new UnAuthorizedException("LogedIn IpTracking doesn't have permission to save IpTracking Details.");
		IpTracking ipTracking = ipTrackingDao.saveIpTracking(ipTrackingDTO);
		LOGGER.info("The IpTracking is added successfully with save IpTracking. "+ ipTracking.getIpAddress());		
	}

	@Override
	public List<IpTrackingDTO> getAllIpTracking(IpTrackingDTO ipTrackingDTO) {
		List<Role> roles =loginService.getAllUserRoles(ipTrackingDTO.getUpdatedBy());
		boolean adminAccess =roles.stream().anyMatch(x->x.getRole().equals(RoleEnum.SUPER_ADMIN.getRole())
				||x.getRole().equals(RoleEnum.ADMIN.getRole()));
		if(!adminAccess) {
			throw new UnAuthorizedException("LogedIn IpTracking doesn't have permission to save IpTracking Details. ");
		}
		List<IpTracking> ipTrackingList = ipTrackingDao.getAllIpTracking(ipTrackingDTO);
		List<IpTrackingDTO> returnList = new ArrayList<>();
		for (IpTracking ipTracking : ipTrackingList) {
			IpTrackingDTO dbIpTrackingDTO = IpTrackingConverter.getIpTrackingDTOByIpTracking(ipTracking);
		    returnList.add(dbIpTrackingDTO);
		}		
		return returnList;
	}

	@Override
	public IpTrackingDTO getIpTrackingById(IpTrackingDTO ipTrackingDTO) {
		List<Role> roles =loginService.getAllUserRoles(ipTrackingDTO.getUpdatedBy());
		boolean adminAccess =roles.stream().anyMatch(x->x.getRole().equals(RoleEnum.SUPER_ADMIN.getRole())
				|| x.getRole().equals(RoleEnum.ADMIN.getRole()));
		
		if(!adminAccess) {
			if(!ipTrackingDTO.getId().equals(ipTrackingDTO.getUpdatedBy()))
				throw new UnAuthorizedException("LogedIn IpTracking doesn't have permission to save IpTracking Details.");
		}
		IpTracking ipTracking = ipTrackingDao.getIpTrackingById(ipTrackingDTO.getId());
	    return IpTrackingConverter.getIpTrackingDTOByIpTracking(ipTracking);					
	}
	
	@Override
	public void updateIpTracking(IpTrackingDTO ipTrackingDTO) {
		List<Role> roles =loginService.getAllUserRoles(ipTrackingDTO.getUpdatedBy());
		boolean adminAccess =roles.stream().anyMatch(x->x.getRole().equals(RoleEnum.SUPER_ADMIN.getRole())
				|| x.getRole().equals(RoleEnum.ADMIN.getRole()));
		if(!adminAccess) {
			if(!ipTrackingDTO.getId().equals(ipTrackingDTO.getUpdatedBy()))
				throw new UnAuthorizedException("The IpTracking doesn't have permission to save ipTracking details. ");
		}
		
		IpTracking ipTrackings = ipTrackingDao.getIpTrackingById(ipTrackingDTO.getId());
		IpTrackingDTO dbIpTrackingDTO =IpTrackingConverter.getIpTrackingDTOByIpTracking(ipTrackings);
		
		if(null != ipTrackingDTO.getIpAddress())
			dbIpTrackingDTO.setIpAddress(ipTrackingDTO.getIpAddress());
		if(null != ipTrackingDTO.getLatitude())
			dbIpTrackingDTO.setLatitude(ipTrackingDTO.getLatitude());
		if(null != ipTrackingDTO.getLongitude())
			dbIpTrackingDTO.setLongitude(ipTrackingDTO.getLongitude());
		if(null != ipTrackingDTO.getLocation())
			dbIpTrackingDTO.setLocation(ipTrackingDTO.getLocation());
		
		dbIpTrackingDTO.setUpdatedBy(ipTrackingDTO.getUpdatedBy());
		dbIpTrackingDTO.setUpdatedDate(ipTrackingDTO.getUpdatedDate());
		
		ipTrackingDao.saveIpTracking(ipTrackingDTO);
		LOGGER.info("IpTracking details for ipTracking id " + dbIpTrackingDTO.getId() + " are updated successfully.");
	
	}

	@Override
	public void deleteIpTracking(IpTrackingDTO ipTrackingDTO) {
		List<Role> roles =loginService.getAllUserRoles(ipTrackingDTO.getUpdatedBy());
		boolean adminAccess =roles.stream().anyMatch(x->x.getRole().equals(RoleEnum.SUPER_ADMIN.getRole())
				||x.getRole().equals(RoleEnum.ADMIN.getRole()));
		if(!adminAccess) {
			if (!ipTrackingDTO.getId().equals(ipTrackingDTO.getUpdatedBy()))
			throw new UnAuthorizedException("LogedIn IpTracking doesn't have permission to save IpTracking Details. ");
		}
		
		IpTracking ipTrackings = ipTrackingDao.getIpTrackingById(ipTrackingDTO.getId());
		IpTrackingDTO dbIpTrackingDTO =IpTrackingConverter.getIpTrackingDTOByIpTracking(ipTrackings);
		
		dbIpTrackingDTO.setStatus(ipTrackingDTO.getStatus());
		dbIpTrackingDTO.setUpdatedBy(ipTrackingDTO.getUpdatedBy());
		dbIpTrackingDTO.setUpdatedDate(ipTrackingDTO.getUpdatedDate());
		LOGGER.info("Status for ipTracking with ID " + ipTrackingDTO.getStatus() + " is deleted successfully.");
		
	}

}
