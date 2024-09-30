package com.getmyschool.common.dao;

import java.util.List;

import com.getmyschool.common.domain.Board;
import com.getmyschool.common.dto.BoardDTO;

public interface BoardDao {

	public Board saveBoard(BoardDTO boardDTO);
	
	public List<Board> getAllBoard(BoardDTO boardsDTO);

	public Board getBoardById(Long id);

}
