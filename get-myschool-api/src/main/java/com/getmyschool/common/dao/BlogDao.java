package com.getmyschool.common.dao;

import java.util.List;

import com.getmyschool.common.domain.Blog;
import com.getmyschool.common.dto.BlogDTO;

public interface BlogDao {

	public Blog saveBlog(BlogDTO blogsDTO);
	
	public List<Blog> getAllBlog(BlogDTO blogsDTO);
	
	public Blog getBlogById(Long id);

}
