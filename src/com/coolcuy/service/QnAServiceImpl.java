package com.coolcuy.service;

import java.util.List;

import com.coolcuy.dao.QnADaoImpl;
import com.coolcuy.dto.QnADto;

public class QnAServiceImpl implements QnAService {
	QnADaoImpl dao= new QnADaoImpl();
	
	
	@Override
	public int add(QnADto object) {
		return dao.add(object);
	}

	@Override
	public int delete(int element) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteAll() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(QnADto object) {
		return dao.update(object);
	}

	@Override
	public QnADto get(int element) {
		return dao.get(element);

	}

	@Override
	public List<QnADto> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalCount(String keyField, String keyWord) {
		return dao.getTotalCount(keyField, keyWord);
	}

	@Override
	public List<QnADto> getQnABoardList(String keyField, String keyWord, int start, int end) {
		return dao.getQnABoardList(keyField, keyWord, start, end);
	}

	@Override
	public void upCount(int num) {
		dao.upCount(num);		
	}

	@Override
	public void reply(QnADto object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void replyUp(int ref, int pos) {
		// TODO Auto-generated method stub
		
	}

}
