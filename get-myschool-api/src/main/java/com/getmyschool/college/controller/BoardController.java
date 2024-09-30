package com.getmyschool.college.controller;

import java.util.HashMap; 
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.MapBindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.getmyschool.college.service.BoardService;
import com.getmyschool.college.validator.BoardValidator;
import com.getmyschool.common.contant.Constant;
import com.getmyschool.common.dto.BoardDTO;
import com.getmyschool.common.exception.FieldException;


@RestController
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardValidator boardValidator;
	
	@Autowired
	private BoardService boardService;

	private LinkedHashMap<String, Object> returnMap;
	
	private static Logger LOGGER = LoggerFactory.getLogger(BoardController.class);

	@RequestMapping(value = "/addBoard", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<LinkedHashMap<String, Object>> addBoard(@RequestBody BoardDTO boardDTO,
			HttpServletRequest request, BindingResult result) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		MapBindingResult err = new MapBindingResult(map, BoardDTO.class.getName());
		boardValidator.saveBoard(boardDTO, err);
		List<ObjectError> list = err.getAllErrors();
		if (list.size() > 0)
			throw new FieldException(list);
		
		boardService.saveBoard(boardDTO);
		returnMap = new LinkedHashMap<String, Object>();
		returnMap.put("responseCode", Constant.SUCCESSFULL_CODE);
		returnMap.put("responseMessage", Constant.SUCCESSFULL_MSG);
		return ResponseEntity.status(HttpStatus.OK).body(returnMap);
	}
	
	@RequestMapping(value = "/getBoards", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	 ResponseEntity<LinkedHashMap<String, Object>> getBoards(@RequestBody BoardDTO boardDTO,
				HttpServletRequest request, BindingResult result) throws Exception {
			HashMap<String, String> map = new HashMap<String, String>();
			MapBindingResult err = new MapBindingResult(map, BoardDTO.class.getName());
			boardValidator.getAllBoards(boardDTO, err);
			List<ObjectError> list = err.getAllErrors();
			if (list.size() > 0)
				throw new FieldException(list);

			List<BoardDTO> boards = boardService.getAllBoards(boardDTO);
			returnMap = new LinkedHashMap<String, Object>();
			returnMap.put("responseCode", Constant.SUCCESSFULL_CODE);
			returnMap.put("responseMessage", Constant.SUCCESSFULL_MSG);
			returnMap.put("boards", boards);
			return ResponseEntity.status(HttpStatus.OK).body(returnMap);
		}
	
	@RequestMapping(value = "/getBoard", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<LinkedHashMap<String, Object>> getBoard(@RequestBody BoardDTO boardDTO,
			HttpServletRequest request, BindingResult result) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		MapBindingResult err = new MapBindingResult(map, BoardDTO.class.getName());
		boardValidator.getBoardById(boardDTO, err);
		List<ObjectError> list = err.getAllErrors();
		if (list.size() > 0)
			throw new FieldException(list);

		BoardDTO boards = boardService.getBoardById(boardDTO);
		returnMap = new LinkedHashMap<String, Object>();
		returnMap.put("responseCode", Constant.SUCCESSFULL_CODE);
		returnMap.put("responseMessage", Constant.SUCCESSFULL_MSG);
		returnMap.put("boards", boards);
		return ResponseEntity.status(HttpStatus.OK).body(returnMap);
	}

	@RequestMapping(value = "/updateBoard", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<LinkedHashMap<String, Object>> updateBoard(@RequestBody BoardDTO boardDTO,
			HttpServletRequest request, BindingResult result) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		MapBindingResult err = new MapBindingResult(map, BoardDTO.class.getName());
		boardValidator.updateBoard(boardDTO, err);
		List<ObjectError> list = err.getAllErrors();
		if (list.size() > 0)
			throw new FieldException(list);

		boardService.updateBoard(boardDTO);
		returnMap = new LinkedHashMap<String, Object>();
		returnMap.put("responseCode", Constant.SUCCESSFULL_CODE);
		returnMap.put("responseMessage", Constant.SUCCESSFULL_MSG);
		return ResponseEntity.status(HttpStatus.OK).body(returnMap);
	}
	@Bean
	public BoardValidator getBoardsValidator() {
		return new BoardValidator();
	}
	
}
