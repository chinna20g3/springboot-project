package com.getmyschool.common.daoimpl;

import java.util.List;   
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.getmyschool.common.converter.BoardConverter;
import com.getmyschool.common.dao.BoardDao;
import com.getmyschool.common.domain.Board;
import com.getmyschool.common.dto.BoardDTO;
import com.getmyschool.common.exception.ResourceNotFoundException;
import com.getmyschool.common.repository.BoardRepository;

@Transactional
@Service("BoardDaoImpl")
public class BoardDaoImpl implements BoardDao {
		
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private BoardRepository  boardRepository;
	
	@Override
	public Board saveBoard(BoardDTO boardDTO) {
		Board board = BoardConverter.getBoardByBoardDTO(boardDTO);
		return boardRepository.save(board);
  }

	@Override
	public List<Board> getAllBoard(BoardDTO boardsDTO) {
		List<Board> returnList = null;
		StringBuffer sqlQuery = new StringBuffer("from Board a where 1=1");

		if (null != boardsDTO.getId())
			sqlQuery.append(" AND a.id = :id");
		if (null != boardsDTO.getStatus())
			sqlQuery.append(" AND a.status = :status");
		
		sqlQuery.append(" order by a.id DESC");
		Query query = entityManager.createQuery(sqlQuery.toString());

		if (null != boardsDTO.getId())
			query.setParameter("id", boardsDTO.getId());
		if (null != boardsDTO.getStatus())
			query.setParameter("status", boardsDTO.getStatus());
		
		// query.setFirstResult(boardDTO.getOffset());
		/// query.setMaxResults(boardDTO.getLimit());

		returnList = query.getResultList();

		return returnList;	
	}

	@Override
	public Board getBoardById(Long id) {
		Optional<Board> board = boardRepository.findById(id);
		if (!board.isPresent())
			throw new ResourceNotFoundException("The board is not found in the system. id:" + id);
		return board.get();
	}
	
}