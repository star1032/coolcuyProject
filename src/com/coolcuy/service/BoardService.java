package com.coolcuy.service;

import java.util.List;

import com.coolcuy.dto.BoardDto;

public interface BoardService{
	public int add(BoardDto object);
	public int delete(int element);
	public int deleteAll();
	public int update(BoardDto object);
	public BoardDto get(int element);
	public List<BoardDto> getAll();
	public int getTotalCount(String keyField,String keyWord);
	public List<BoardDto> getBoardList(String keyField, String keyWord, int start, int end);
	public void upCount(int num);
}
