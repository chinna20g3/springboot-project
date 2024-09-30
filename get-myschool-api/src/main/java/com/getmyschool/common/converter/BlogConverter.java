package com.getmyschool.common.converter;

import org.springframework.stereotype.Component;

import com.getmyschool.common.domain.Blog;
import com.getmyschool.common.dto.BlogDTO;

@Component
public class BlogConverter {

	/**
	 *Converter Blog to BlogDTO
	 *@param Blog
	 *@return
	 */
	
	public static BlogDTO getBlogDTOByBlog(Blog blog) {
	 BlogDTO dto = new BlogDTO();
	 
	 dto.setId(blog.getId());
	 dto.setStatus(blog.getStatus());
	 dto.setTitle(blog.getTitle());
	 dto.setDescription(blog.getDescription());
	 dto.setOgUrl(blog.getOgUrl());
	 dto.setCreatedBy(blog.getCreatedBy());
	 dto.setCreatedDate(blog.getCreatedDate());
	 dto.setUpdatedBy(blog.getUpdatedBy());
	 dto.setUpdatedDate(blog.getUpdatedDate());
 
	return dto;
}
	
	/**
	 *Converter BlogDTO to Blog
	 *@param BlogDTO
	 *@return
	 */
	
	
	public static Blog getBlogByBlogDTO(BlogDTO blogDTO) {
		Blog blog = new Blog();
		
		blog.setId(blogDTO.getId());
		blog.setStatus(blogDTO.getStatus());
		blog.setTitle(blogDTO.getTitle());
		blog.setDescription(blogDTO.getDescription());
		blog.setOgUrl(blogDTO.getOgUrl());
		blog.setCreatedBy(blogDTO.getCreatedBy());
		blog.setCreatedDate(blogDTO.getCreatedDate());
		blog.setUpdatedBy(blogDTO.getUpdatedBy());
		blog.setUpdatedDate(blogDTO.getUpdatedDate());
		
		return blog;
	}

}