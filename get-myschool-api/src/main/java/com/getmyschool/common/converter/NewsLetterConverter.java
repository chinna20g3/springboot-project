package com.getmyschool.common.converter;

import org.springframework.stereotype.Component;

import com.getmyschool.common.domain.NewsLetter;
import com.getmyschool.common.dto.NewsLetterDTO;

@Component
public class NewsLetterConverter {

	/**
	 * @Converter NewsLetter to NewsLetterDTO
	 * @Param NewsLetter 
	 * @return
	 */
		
	public static NewsLetterDTO getNewsLetterDTOByNewsLetter(NewsLetter newsLetter) {
		NewsLetterDTO dto = new NewsLetterDTO();
		
		dto.setId(newsLetter.getId());
		dto.setEmail(newsLetter.getEmail());
		dto.setStatus(newsLetter.getStatus());
		dto.setCreatedBy(newsLetter.getCreatedBy());
		dto.setCreatedDate(newsLetter.getCreatedDate());
		dto.setUpdatedBy(newsLetter.getUpdatedBy());
		dto.setUpdatedDate(newsLetter.getUpdatedDate());	
		
		return dto;	
	}
	
	/**
	 * @Converter NewsLetterDTO to NewsLetter
	 * @Param NewsLetterDTO
	 * @return
	 */
	
	public static NewsLetter getNewsLetterByNewsLetterDTO(NewsLetterDTO newsLetterDTO) {
		NewsLetter newsLetter = new NewsLetter();
		
		newsLetter.setId(newsLetterDTO.getId());
		newsLetter.setEmail(newsLetterDTO.getEmail());
		newsLetter.setStatus(newsLetterDTO.getStatus());
		newsLetter.setCreatedBy(newsLetterDTO.getCreatedBy());
		newsLetter.setCreatedDate(newsLetterDTO.getCreatedDate());
		newsLetter.setUpdatedBy(newsLetterDTO.getUpdatedBy());
		newsLetter.setUpdatedDate(newsLetterDTO.getUpdatedDate());
	
		return newsLetter;
	}
}
