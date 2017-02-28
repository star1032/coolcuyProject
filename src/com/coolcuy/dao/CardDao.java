package com.coolcuy.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.coolcuy.dto.CardDto;

public interface CardDao extends GenericDao<CardDto>{
	
	public int checkDuplicate(String cardNumber, Connection conn) throws SQLException;
	public int getCount(Connection conn) throws SQLException;
	public int checkPassword(String CardNumber, String password, Connection conn) throws SQLException;
	public List<CardDto> getAllByEmail(String element, Connection conn) throws SQLException;

}
