package com.getmyschool.college.serviceimpl;

import java.util.ArrayList;     
import java.util.List; 
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import com.getmyschool.college.service.AmenitiesService;
import com.getmyschool.common.converter.AmenitiesConverter;
import com.getmyschool.common.dao.AmenitiesDao;
import com.getmyschool.common.domain.Amenities;
import com.getmyschool.common.domain.Role;
import com.getmyschool.common.dto.AmenitiesDTO;
import com.getmyschool.common.exception.UnAuthorizedException;
import com.getmyschool.common.service.LoginService;
import com.getmyschool.common.validator.RoleEnum;

@Service("AmenitiesServiceImpl")
public class AmenitiesServiceImpl implements AmenitiesService {

	
	@Resource(name = "LoginServiceImpl")
	private LoginService loginService;

	@Resource(name = "AmenitiesDaoImpl")
	private  AmenitiesDao  amenitiesDao;

	private static final Logger LOGGER = LoggerFactory.getLogger(AmenitiesServiceImpl.class);

	@Override
	public void saveAmenities(AmenitiesDTO amenitiesDTO) {
		List<Role> roles = loginService.getAllUserRoles(amenitiesDTO.getCreatedBy());
		boolean adminFlag = roles.stream().anyMatch(x -> x.getRole().equals(RoleEnum.SUPER_ADMIN.getRole())
				|| x.getRole().equals(RoleEnum.ADMIN.getRole()));
		if (!(adminFlag)) {
			throw new UnAuthorizedException("LogedIn Amenities does't have permission to save Amenities Details.");
		}
		Amenities amenities = amenitiesDao.saveAmenities(amenitiesDTO);
		LOGGER.info("amenities added successfully with saveAmenities::" + amenities.getAmenitiesType());

	}

	@Override
	public List<AmenitiesDTO> getAllAmenities(AmenitiesDTO amenitiesDTO) {
		List<Role> roles = loginService.getAllUserRoles(amenitiesDTO.getUpdatedBy());
	    boolean adminAccess = roles.stream().anyMatch(x -> x.getRole().equals(RoleEnum.SUPER_ADMIN.getRole())
	    		|| x.getRole().equals(RoleEnum.ADMIN.getRole()));
	    if (!adminAccess) {
		        throw new UnAuthorizedException("Logged-in Amenities doesn't have permission to access all board details.");
		}
	    List<Amenities> amenitiesList = amenitiesDao.getAllAmenities(amenitiesDTO);
		List<AmenitiesDTO> returnList = new ArrayList<>();
		for (Amenities amenities : amenitiesList) {
			AmenitiesDTO dbAmenitiesDTO = AmenitiesConverter.getAmenitiesDTOByAmenities(amenities);
		    returnList.add(dbAmenitiesDTO);
		}
		return returnList;
	}

	@Override
	public AmenitiesDTO getAmenitiesById(AmenitiesDTO amenitiesDTO) {
		List<Role> roles = loginService.getAllUserRoles(amenitiesDTO.getUpdatedBy());
		boolean adminAccess = roles.stream().anyMatch(x -> x.getRole().equals(RoleEnum.SUPER_ADMIN.getRole())
				|| x.getRole().equals(RoleEnum.ADMIN.getRole()));
		if (!adminAccess) {
			if (!amenitiesDTO.getId().equals(amenitiesDTO.getUpdatedBy()))
				throw new UnAuthorizedException("LogedIn Amenities does't have permission to get Amenities Details.");
		}
			Amenities amenities = amenitiesDao.getAmenitiesById(amenitiesDTO.getId());
			return AmenitiesConverter.getAmenitiesDTOByAmenities(amenities);
	}

	@Override
	public void updateAmenities(AmenitiesDTO amenitiesDTO) {
		List<Role> roles = loginService.getAllUserRoles(amenitiesDTO.getUpdatedBy());
		boolean adminAccess = roles.stream().anyMatch(x -> x.getRole().equals(RoleEnum.SUPER_ADMIN.getRole())
				|| x.getRole().equals(RoleEnum.ADMIN.getRole()));
		if (!adminAccess) {
			if (!amenitiesDTO.getId().equals(amenitiesDTO.getUpdatedBy()))
				throw new UnAuthorizedException("LogedIn Amenities does't have permission to update Amenities Details.");
		}
		Amenities amenities =amenitiesDao.getAmenitiesById(amenitiesDTO.getId());
		AmenitiesDTO dbAmenitiesDTO = AmenitiesConverter.getAmenitiesDTOByAmenities(amenities);
		
		if (null != amenitiesDTO.getAmenitiesType())
			dbAmenitiesDTO.setAmenitiesType(amenitiesDTO.getAmenitiesType());
		
		dbAmenitiesDTO.setUpdatedBy(amenitiesDTO.getUpdatedBy());
		dbAmenitiesDTO.setUpdatedDate(amenitiesDTO.getUpdatedDate());
		amenitiesDao.saveAmenities(dbAmenitiesDTO);
		LOGGER.info("Amenities details for amenities id " + amenitiesDTO.getId() + " are updated successfully.");
	}


}
