package com.coolcuy.service;

import java.util.List;

import com.coolcuy.dto.FreeBoardDto;

public interface FreeBoardService {
	public int add(FreeBoardDto object);
	public int delete(int element);
	public int deleteAll();
	public int update(FreeBoardDto object);
	public FreeBoardDto get(int element);
	public List<FreeBoardDto> getAll();
	public int getTotalCount(String keyField,String keyWord);
	public List<FreeBoardDto> getQnABoardList(String keyField, String keyWord, int start, int end);
	public void upCount(int num);
	public void reply(FreeBoardDto object);
	public void replyUp(int ref, int pos);
	public int deleteReply(int element);
}
