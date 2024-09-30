package com.getmyschool.college.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.getmyschool.college.service.BlogService;
import com.getmyschool.common.converter.BlogConverter;
import com.getmyschool.common.dao.BlogDao;
import com.getmyschool.common.domain.Blog;
import com.getmyschool.common.domain.Role;
import com.getmyschool.common.dto.BlogDTO;
import com.getmyschool.common.exception.UnAuthorizedException;
import com.getmyschool.common.service.LoginService;
import com.getmyschool.common.validator.RoleEnum;

@Service("BlogServiceImpl")
public class BlogServiceImpl implements BlogService {

	@Resource(name = "LoginServiceImpl")
	private LoginService loginService;
	
	@Resource(name = "BlogDaoImpl")
	private BlogDao blogDao;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BlogServiceImpl.class);
	
	@Override
	public void saveBlog(BlogDTO blogDTO) {
		List<Role> roles = loginService.getAllUserRoles(blogDTO.getCreatedBy());
	    boolean adminFlag = roles.stream().anyMatch(x -> x.getRole().equals(RoleEnum.SUPER_ADMIN.getRole())
	    		|| x.getRole().equals(RoleEnum.ADMIN.getRole()));
	    if (!(adminFlag))
	    	throw new UnAuthorizedException("LogedIn Blog does't have permission to save Blog Details.");
	    Blog blog = blogDao.saveBlog(blogDTO);
	    LOGGER.info("blog added successfully with saveBlog::" + blog.getTitle());		
	}

	@Override
	public List<BlogDTO> getAllBlogs(BlogDTO blogDTO) {
		List<Role> roles = loginService.getAllUserRoles(blogDTO.getUpdatedBy());
	    boolean adminAcccess = roles.stream().anyMatch(x -> x.getRole().equals(RoleEnum.SUPER_ADMIN.getRole())
	    		|| x.getRole().equals(RoleEnum.ADMIN.getRole()));
	    if (!adminAcccess) {
	    	throw new UnAuthorizedException("Logged-in Blog doesn't have permission to access all blog details.");
	    	}
	    List<Blog> blogList = blogDao.getAllBlog(blogDTO);
	    List<BlogDTO> returnList = new ArrayList<>();
	    for (Blog blog : blogList) {
	    	BlogDTO dbBlogDTO = BlogConverter.getBlogDTOByBlog(blog);
	    	returnList.add(dbBlogDTO);
	    }
	    return returnList;	
	}

	@Override
	public BlogDTO getBlogById(BlogDTO blogDTO) {
		List<Role> roles = loginService.getAllUserRoles(blogDTO.getUpdatedBy());
		boolean adminAcccess = roles.stream().anyMatch(x -> x.getRole().equals(RoleEnum.SUPER_ADMIN.getRole())
				|| x.getRole().equals(RoleEnum.ADMIN.getRole()));
		if (!adminAcccess) {
			if (!blogDTO.getId().equals(blogDTO.getUpdatedBy()))
				throw new UnAuthorizedException("LogedIn blog does't have permission to get Blog Details.");
		}
			Blog blog = blogDao.getBlogById(blogDTO.getId());
			return BlogConverter.getBlogDTOByBlog(blog);	
	}

	@Override
	public void updateBlog(BlogDTO blogDTO) {
		 List<Role> roles = loginService.getAllUserRoles(blogDTO.getUpdatedBy());
		boolean adminAcccess = roles.stream().anyMatch(x -> x.getRole().equals(RoleEnum.SUPER_ADMIN.getRole())
				|| x.getRole().equals(RoleEnum.ADMIN.getRole()));
		if (!adminAcccess) {
			if (!blogDTO.getId().equals(blogDTO.getUpdatedBy()))
				throw new UnAuthorizedException("LogedIn blog does't have permission to update Blog Details.");
		}
		Blog blogs =blogDao.getBlogById(blogDTO.getId());
		BlogDTO dbBlogDTO = BlogConverter.getBlogDTOByBlog(blogs);
		
		if (null != blogDTO.getTitle())
			dbBlogDTO.setTitle(blogDTO.getTitle());
		if (null != blogDTO.getDescription())
			dbBlogDTO.setDescription(blogDTO.getDescription());
		if (null != blogDTO.getOgUrl())
			dbBlogDTO.setOgUrl(blogDTO.getOgUrl());
		
		dbBlogDTO.setUpdatedBy(blogDTO.getUpdatedBy());
		dbBlogDTO.setUpdatedDate(blogDTO.getUpdatedDate());
		blogDao.saveBlog(dbBlogDTO);
		LOGGER.info("Blog details for blog id " + blogDTO.getId() + " are updated successfully.");
				
	}
}
