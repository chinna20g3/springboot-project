package com.getmyschool.college.service;

import java.util.List;

import com.getmyschool.common.dto.BoardDTO;

public interface BoardService {
	
	public void saveBoard(BoardDTO boardDTO);
	
	public List<BoardDTO> getAllBoards(BoardDTO boardDTO);

	public BoardDTO  getBoardById(BoardDTO boardDTO);
	
	public void updateBoard(BoardDTO boardDTO);
	

}
