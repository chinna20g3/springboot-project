package com.getmyschool.college.service;

import java.util.List;

import com.getmyschool.common.dto.BlogDTO;


public interface BlogService {

	public void saveBlog(BlogDTO bloDTO);
	
	public List<BlogDTO> getAllBlogs(BlogDTO blogDTO);
	
	public BlogDTO getBlogById(BlogDTO blogDTO);
	
	public void updateBlog(BlogDTO blogDTO);
}
