package com.coolcuy.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.coolcuy.dao.CardDao;
import com.coolcuy.dto.CardDto;

public class testCardDao implements CardDao{

	@Override
	public int add(CardDto object, Connection conn) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String element, Connection conn) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteAll(Connection conn) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(CardDto object, Connection conn) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public CardDto get(String element, Connection conn) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CardDto> getAll(Connection conn) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int checkDuplicate(String cardNumber, Connection conn) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCount(Connection conn) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int checkPassword(String CardNumber, String password, Connection conn) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<CardDto> getAllByEmail(String element, Connection conn) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
