package com.getmyschool.common.converter;

import org.springframework.stereotype.Component;

import com.getmyschool.common.domain.Board; 
import com.getmyschool.common.dto.BoardDTO;

@Component
public class BoardConverter {
	
	/**
	 * convert Board to BoardDTO
	 * 
	 * @param Board
	 * @return
	 */
	
	public static BoardDTO getBoardDTOByBoard(Board board) {
		BoardDTO dto = new BoardDTO();

	
		dto.setId(board.getId());
		dto.setBoardType(board.getBoardType());
		dto.setStatus(board.getStatus());
		dto.setCreatedBy(board.getCreatedBy());
		dto.setCreatedDate(board.getCreatedDate());
		dto.setUpdatedBy(board.getUpdatedBy());
		dto.setUpdatedDate(board.getUpdatedDate());
		
		return dto;

}
	
	/**
	 * convert BoardDTO to Board
	 * 
	 * @param BoardDTO
	 * @return
	 */
	
	public static Board getBoardByBoardDTO(BoardDTO boardDTO) {
		Board board = new Board();
		
		 board.setId(boardDTO.getId());
		 board.setBoardType(boardDTO.getBoardType());
		 board.setStatus(boardDTO.getStatus());
		 board.setCreatedBy(boardDTO.getCreatedBy());
		 board.setCreatedDate(boardDTO.getCreatedDate());
		 board.setUpdatedBy(boardDTO.getUpdatedBy());
		 board.setUpdatedDate(boardDTO.getUpdatedDate());
		
		 return board;
		
		
		
	}
	
}
