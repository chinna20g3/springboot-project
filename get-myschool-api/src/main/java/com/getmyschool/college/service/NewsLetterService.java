package com.getmyschool.college.service;

import java.util.List; 

import com.getmyschool.common.dto.NewsLetterDTO;

public interface NewsLetterService {

	public void saveNewsLetter(NewsLetterDTO newsLetterDTO);
	
	public List<NewsLetterDTO> getAllNewsLetters(NewsLetterDTO newsLetterDTO);
	
	public NewsLetterDTO getNewsLetterById(NewsLetterDTO newsLetterDTO);
	
	public void updateNewsLetter(NewsLetterDTO newsLetterDTO);
	
	public void deleteNewsLetter(NewsLetterDTO newsLetterDTO);


}
