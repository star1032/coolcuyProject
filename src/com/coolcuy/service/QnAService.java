package com.coolcuy.service;

import java.util.List;

import com.coolcuy.dto.QnADto;

public interface QnAService {
	public int add(QnADto object);
	public int delete(int element);
	public int deleteAll();
	public int update(QnADto object);
	public QnADto get(int element);
	public List<QnADto> getAll();
	public int getTotalCount(String keyField,String keyWord);
	public List<QnADto> getQnABoardList(String keyField, String keyWord, int start, int end);
	public void upCount(int num);
	public void reply(QnADto object);
	public void replyUp(int ref, int pos);
}
