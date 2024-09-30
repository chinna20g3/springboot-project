package com.getmyschool.college.serviceimpl;

import java.util.ArrayList;  
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.getmyschool.college.service.BoardService;
import com.getmyschool.common.converter.BoardConverter;
import com.getmyschool.common.dao.BoardDao;
import com.getmyschool.common.domain.Board;
import com.getmyschool.common.domain.Role;
import com.getmyschool.common.dto.BoardDTO;
import com.getmyschool.common.exception.UnAuthorizedException;
import com.getmyschool.common.service.LoginService;
import com.getmyschool.common.validator.RoleEnum;

@Service("BoardServiceImpl")
public class BoardServiceImpl implements BoardService {

	@Resource(name = "LoginServiceImpl")
	private LoginService loginService;

	@Resource(name = "BoardDaoImpl")
	private BoardDao boardDao;


	private static final Logger LOGGER = LoggerFactory.getLogger(BoardServiceImpl.class);

	@Override
	public void saveBoard(BoardDTO boardDTO) {
		List<Role> roles = loginService.getAllUserRoles(boardDTO.getCreatedBy());
		boolean adminFlag = roles.stream().anyMatch(x -> x.getRole().equals(RoleEnum.SUPER_ADMIN.getRole())
				|| x.getRole().equals(RoleEnum.ADMIN.getRole()));
		if (!(adminFlag)) {
			throw new UnAuthorizedException("LogedIn Board does't have permission to save Board Details.");
		}
		Board board = boardDao.saveBoard(boardDTO);
		LOGGER.info("Board added successfully with saveBoard::" + board.getBoardType());
	}

	@Override
	public List<BoardDTO> getAllBoards(BoardDTO boardDTO) {
	    List<Role> roles = loginService.getAllUserRoles(boardDTO.getUpdatedBy());
	    boolean adminAcccess = roles.stream().anyMatch(x -> x.getRole().equals(RoleEnum.SUPER_ADMIN.getRole())
	    		|| x.getRole().equals(RoleEnum.ADMIN.getRole()));
	    if (!adminAcccess) {
	        throw new UnAuthorizedException("Logged-in Board doesn't have permission to access all board details.");
	    }
	    List<Board> boardList = boardDao.getAllBoard(boardDTO);
	    List<BoardDTO> returnList = new ArrayList<>();
	    
	    for (Board board : boardList) {
	    	BoardDTO dbBoardDTO = BoardConverter.getBoardDTOByBoard(board);
	        returnList.add(dbBoardDTO);
	    }	    
	    return returnList;
	}

	@Override
	public BoardDTO getBoardById(BoardDTO boardDTO) {
		List<Role> roles = loginService.getAllUserRoles(boardDTO.getUpdatedBy());
		boolean adminAcccess = roles.stream().anyMatch(x -> x.getRole().equals(RoleEnum.SUPER_ADMIN.getRole())
				|| x.getRole().equals(RoleEnum.ADMIN.getRole()));
		if (!adminAcccess) {
			if (!boardDTO.getId().equals(boardDTO.getUpdatedBy()))
				throw new UnAuthorizedException("LogedIn Board does't have permission to get Board Details.");
		}
			Board board = boardDao.getBoardById(boardDTO.getId());
			return BoardConverter.getBoardDTOByBoard(board);
	}

	@Override
	public void updateBoard(BoardDTO boardDTO) {
		List<Role> roles = loginService.getAllUserRoles(boardDTO.getUpdatedBy());
		boolean adminAcccess = roles.stream().anyMatch(x -> x.getRole().equals(RoleEnum.SUPER_ADMIN.getRole())
				|| x.getRole().equals(RoleEnum.ADMIN.getRole()));
		if (!adminAcccess) {
			if (!boardDTO.getId().equals(boardDTO.getUpdatedBy()))
				throw new UnAuthorizedException("LogedIn Board does't have permission to update Board Details.");
		}
		Board boards =boardDao.getBoardById(boardDTO.getId());
		BoardDTO dbBoardDTO = BoardConverter.getBoardDTOByBoard(boards);
		
		if (null != boardDTO.getBoardType())
			dbBoardDTO.setBoardType(boardDTO.getBoardType());
		
		dbBoardDTO.setUpdatedBy(boardDTO.getUpdatedBy());
		dbBoardDTO.setUpdatedDate(boardDTO.getUpdatedDate());

		boardDao.saveBoard(dbBoardDTO);
		LOGGER.info("Board details for board id " + boardDTO.getId() + " are updated successfully.");
	}
}
