package com.getmyschool.common.daoimpl;

import java.util.List;    
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.getmyschool.common.converter.BlogConverter;
import com.getmyschool.common.dao.BlogDao;
import com.getmyschool.common.domain.Blog;
import com.getmyschool.common.dto.BlogDTO;
import com.getmyschool.common.exception.ResourceNotFoundException;
import com.getmyschool.common.repository.BlogRepository;

@Transactional
@Service("BlogDaoImpl")
public class BlogDaoImpl implements BlogDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private BlogRepository blogRepository;
	
	@Override
	public Blog saveBlog(BlogDTO blogsDTO) {
		Blog blogs = BlogConverter.getBlogByBlogDTO(blogsDTO);
		return blogRepository.save(blogs);
	}

	@Override
	public List<Blog> getAllBlog(BlogDTO blogsDTO) {
		List<Blog> returnList = null;
		StringBuffer sqlQuery = new StringBuffer("from Blog a where 1=1");

		if (null != blogsDTO.getId())
			sqlQuery.append(" AND a.id = :id");
		if (null != blogsDTO.getStatus())
			sqlQuery.append(" AND a.status = :status");
		
		sqlQuery.append(" order by a.id DESC");
		Query query = entityManager.createQuery(sqlQuery.toString());

		if (null != blogsDTO.getId())
			query.setParameter("id", blogsDTO.getId());
		if (null != blogsDTO.getStatus())
			query.setParameter("status", blogsDTO.getStatus());
		
		// query.setFirstResult(blogDTO.getOffset());
		/// query.setMaxResults(blogDTO.getLimit());

		returnList = query.getResultList();

		return returnList;		
	}

	@Override
	public Blog getBlogById(Long id) {
		Optional<Blog> blog = blogRepository.findById(id);
		if (!blog.isPresent())
			throw new ResourceNotFoundException("The blog is not found in the system. id:" + id);
		return blog.get();
	}

}
