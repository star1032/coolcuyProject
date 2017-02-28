package com.coolcuy.service;

import java.util.List;

import com.coolcuy.dao.FreeBoardDaoImpl;
import com.coolcuy.dto.FreeBoardDto;
import com.coolcuy.exception.NotSupportedException;

public class FreeBoardServiceImpl implements FreeBoardService{

	FreeBoardDaoImpl dao=new FreeBoardDaoImpl();
	
	@Override
	public int add(FreeBoardDto object) {
		return dao.add(object);
	}

	@Override
	public int delete(int element) {
		return dao.delete(element);
	}

	@Override
	public int deleteAll() {
		throw new NotSupportedException();
		}

	@Override
	public int update(FreeBoardDto object) {
		return dao.update(object);
	}

	@Override
	public FreeBoardDto get(int element) {
		return dao.get(element);
	}

	@Override
	public List<FreeBoardDto> getAll() {
		throw new NotSupportedException();
	}

	@Override
	public int getTotalCount(String keyField, String keyWord) {
		return dao.getTotalCount(keyField, keyWord);
	}

	@Override
	public List<FreeBoardDto> getQnABoardList(String keyField, String keyWord, int start, int end) {
		return dao.getFreeBoardList(keyField, keyWord, start, end);
	}

	@Override
	public void upCount(int num) {
		dao.upCount(num);		
	}

	@Override
	public void reply(FreeBoardDto object) {
		throw new NotSupportedException();	
	}

	@Override
	public void replyUp(int ref, int pos) {
		throw new NotSupportedException();
	}

	@Override
	public int deleteReply(int element) {

		return dao.deleteReply(element);
	}

}
