package com.getmyschool.college.serviceimpl;

import java.util.ArrayList; 
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.getmyschool.college.service.NewsLetterService;
import com.getmyschool.common.converter.NewsLetterConverter;
import com.getmyschool.common.dao.NewsLetterDao;
import com.getmyschool.common.domain.NewsLetter;
import com.getmyschool.common.domain.Role;
import com.getmyschool.common.dto.NewsLetterDTO;
import com.getmyschool.common.exception.UnAuthorizedException;
import com.getmyschool.common.service.LoginService;
import com.getmyschool.common.validator.RoleEnum;

@Service("NewsLetterServiceImpl")
public class NewsLetterServiceImpl implements NewsLetterService{

	LinkedHashMap<String, Object> returnMap = null;

	@Resource(name = "LoginServiceImpl")
	private LoginService loginService;
	
	@Resource(name = "NewsLetterDaoImpl")
	private NewsLetterDao newsLetterDao;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(NewsLetterServiceImpl.class);


	@Override
	public void saveNewsLetter(NewsLetterDTO newsLetterDTO) {
		List<Role> roles = loginService.getAllUserRoles(newsLetterDTO.getCreatedBy());
		boolean adminFlag = roles.stream().anyMatch(x -> x.getRole().equals(RoleEnum.SUPER_ADMIN.getRole())
				|| x.getRole().equals(RoleEnum.ADMIN.getRole()));
		if (!(adminFlag)) {
			throw new UnAuthorizedException("LogedIn NewsLetter does't have permission to save NewsLetter Details.");
		}
		NewsLetter newsLetter = newsLetterDao.saveNewsLetter(newsLetterDTO);
		LOGGER.info("newsLetter added successfully with saveNewsLetter::" + newsLetter.getEmail());

	}


	@Override
	public List<NewsLetterDTO> getAllNewsLetters(NewsLetterDTO newsLetterDTO) {
		List<Role> roles = loginService.getAllUserRoles(newsLetterDTO.getUpdatedBy());
		boolean adminAcccess = roles.stream().anyMatch(x -> x.getRole().equals(RoleEnum.SUPER_ADMIN.getRole())
				|| x.getRole().equals(RoleEnum.ADMIN.getRole()));
		if (!adminAcccess) {
			throw new UnAuthorizedException("Logged-in NewsLetter doesn't have permission to access all newsLetter details."); 
			}
		List<NewsLetter> newsLetterList = newsLetterDao.getAllNewsLetter(newsLetterDTO);
		List<NewsLetterDTO> returnList = new ArrayList<>();
		for (NewsLetter newsLetter : newsLetterList) {
			NewsLetterDTO dbNewsLetterDTO = NewsLetterConverter.getNewsLetterDTOByNewsLetter(newsLetter);
		    returnList.add(dbNewsLetterDTO);
	    }
		return returnList;
	}

	@Override
	public NewsLetterDTO getNewsLetterById(NewsLetterDTO newsLetterDTO) {
		List<Role> roles = loginService.getAllUserRoles(newsLetterDTO.getUpdatedBy());
		boolean adminAcccess = roles.stream().anyMatch(x -> x.getRole().equals(RoleEnum.SUPER_ADMIN.getRole())
				|| x.getRole().equals(RoleEnum.ADMIN.getRole()));
		if (!adminAcccess) {
			if (!newsLetterDTO.getId().equals(newsLetterDTO.getUpdatedBy()))
				throw new UnAuthorizedException("LogedIn NewsLetter does't have permission to get newsLetter Details.");
		}
			NewsLetter newsLetter = newsLetterDao.getNewsLetterById(newsLetterDTO.getId());
			return NewsLetterConverter.getNewsLetterDTOByNewsLetter(newsLetter);
	}

	@Override
	public void updateNewsLetter(NewsLetterDTO newsLetterDTO) {
		List<Role> roles = loginService.getAllUserRoles(newsLetterDTO.getUpdatedBy());
		boolean adminAcccess = roles.stream().anyMatch(x -> x.getRole().equals(RoleEnum.SUPER_ADMIN.getRole())
				|| x.getRole().equals(RoleEnum.ADMIN.getRole()));
		if (!adminAcccess) {
			if (!newsLetterDTO.getId().equals(newsLetterDTO.getUpdatedBy()))
				throw new UnAuthorizedException("LogedIn NewsLetter does't have permission to update newsLetter Details.");
		}
		NewsLetter newsLetters = newsLetterDao.getNewsLetterById(newsLetterDTO.getId());
		NewsLetterDTO dbNewsLetterDTO = NewsLetterConverter.getNewsLetterDTOByNewsLetter(newsLetters);
	
		if (null != newsLetterDTO.getEmail())
			dbNewsLetterDTO.setEmail(newsLetterDTO.getEmail());

		dbNewsLetterDTO.setUpdatedBy(newsLetterDTO.getUpdatedBy());
		dbNewsLetterDTO.setUpdatedDate(newsLetterDTO.getUpdatedDate());
		newsLetterDao.saveNewsLetter(dbNewsLetterDTO);
		LOGGER.info("NewsLetter details for newsLetter id " + newsLetterDTO.getId() + " are updated successfully.");		
	}


	@Override
	public void deleteNewsLetter(NewsLetterDTO newsLetterDTO) {
		List<Role> roles = loginService.getAllUserRoles(newsLetterDTO.getUpdatedBy());
	    boolean adminAccess = roles.stream().anyMatch(x ->x.getRole().equals(RoleEnum.ADMIN.getRole()));
	    if (!adminAccess) {
	    	// Check if the logged-in leads manager has permission to delete status
        	if (!newsLetterDTO.getId().equals(newsLetterDTO.getUpdatedBy())) 
        		throw new UnAuthorizedException("Logged-in leads manager doesn't have permission to delete status.");
		}
	    
	    NewsLetter newsLetters = newsLetterDao.getNewsLetterById(newsLetterDTO.getId());
		NewsLetterDTO dbNewsLetterDTO = NewsLetterConverter.getNewsLetterDTOByNewsLetter(newsLetters);
	
	 // Perform deletion logic here, such as updating the status field in the database
		dbNewsLetterDTO.setStatus(newsLetterDTO.getStatus());
		dbNewsLetterDTO.setUpdatedBy(newsLetterDTO.getUpdatedBy());
		dbNewsLetterDTO.setUpdatedDate(newsLetterDTO.getUpdatedDate());
        LOGGER.info("Status for leads manager with ID " + newsLetterDTO.getStatus() + " is deleted successfully.");
	}
}
	
	








