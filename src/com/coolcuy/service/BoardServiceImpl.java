package com.coolcuy.service;

import java.util.List;

import com.coolcuy.dao.BoardDao;
import com.coolcuy.dao.BoardDaoImpl;
import com.coolcuy.dto.BoardDto;

public class BoardServiceImpl implements BoardService{

	BoardDao dao= new BoardDaoImpl();
	
	public int add(BoardDto object) {
		// TODO Auto-generated method stub
		return dao.add(object);
	}

	public int delete(int element) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int deleteAll() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int update(BoardDto object) {
		return dao.update(object);
	}

	public BoardDto get(int element) {
		return dao.get(element);
	}

	public List<BoardDto> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getTotalCount(String keyField, String keyWord) {
		return dao.getTotalCount(keyField, keyWord);
	}

	public List<BoardDto> getBoardList(String keyField, String keyWord, int start, int end) {
		return dao.getBoardList(keyField, keyWord, start, end);
	}

	public void upCount(int num) {
		dao.upCount(num);
	}

}
