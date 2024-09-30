package com.getmyschool.common.dao;

import java.util.List; 

import com.getmyschool.common.domain.NewsLetter;
import com.getmyschool.common.dto.NewsLetterDTO;

public interface NewsLetterDao {

	public NewsLetter saveNewsLetter(NewsLetterDTO newsLettersDTO);
	
	public List<NewsLetter> getAllNewsLetter(NewsLetterDTO newsLettersDTO);
	
	public NewsLetter getNewsLetterById(Long id);

	public NewsLetter getNewsLetterByEmail(String email);
	
}
