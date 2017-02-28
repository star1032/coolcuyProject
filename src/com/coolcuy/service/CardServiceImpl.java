package com.coolcuy.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.coolcuy.dao.CardDao;
import com.coolcuy.dao.CardDaoImpl;
import com.coolcuy.dto.CardDto;
import com.coolcuy.exception.DuplicateException;
import com.coolcuy.exception.NotFoundCardExecption;
import com.coolcuy.jdbc.connection.ConnectionProvider;
import com.coolcuy.util.JdbcUtil;

public class CardServiceImpl implements CardService {
	
	CardDao dao = new CardDaoImpl();

	@Override
	public int add(CardDto object) {
		Connection conn = null;
		int x = -1;
		
		try {
			conn = ConnectionProvider.getConnection();
			x = dao.add(object, conn);
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally{
			JdbcUtil.close(conn);
		}
		
		return x;
	}

	@Override
	public int delete(String element) {
		Connection conn = null;
		int x = -1;
		
		try {
			conn = ConnectionProvider.getConnection();
			x = dao.delete(element, conn);
			
		} catch (SQLException e) {
			throw new RuntimeException();
		} finally{
			JdbcUtil.close(conn);
		}
		
		return x;
	}

	@Override
	public int deleteAll() {
		Connection conn = null;
		int x = -1;
		
		try {
			conn = ConnectionProvider.getConnection();
			x = dao.deleteAll(conn);
		} catch (SQLException e) {
			throw new RuntimeException();
		} finally{
			JdbcUtil.close(conn);
		}
		
		return x;
	}

	@Override
	public CardDto update(CardDto object) {
		String cardNumber = object.getCardNumber();
				
		Connection conn = null;
		CardDto updatedCard = null;
		int x = -1;
				
		CardDto getCard = get(cardNumber);

		try {
			conn = ConnectionProvider.getConnection();
			
			if(getCard != null){
				x = dao.update(object, conn);
				
				if(x == 1)
					updatedCard = get(cardNumber);	// is new Connection
			}

		} catch (SQLException e) {
			throw new RuntimeException();
		} finally{
			JdbcUtil.close(conn);
		}
		
		return updatedCard;
	}
	
	@Override
	public List<CardDto> getAllByEmail(String element){
		Connection conn = null;
		List<CardDto> getCards = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			
			getCards = dao.getAllByEmail(element, conn);
		} catch (SQLException e) {
			throw new RuntimeException();
		} finally{
			JdbcUtil.close(conn);
		}
		
		return getCards;
	}
	
	@Override
	public CardDto get(String element) {
		Connection conn = null;
		CardDto getCard = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			
			getCard = dao.get(element, conn);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally{
			JdbcUtil.close(conn);
		}
		
		return getCard;
	}

	@Override
	public List<CardDto> getAll() {
		Connection conn = null;
		List<CardDto> getCards = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			
			getCards = dao.getAll(conn);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally{
			JdbcUtil.close(conn);
		}
		
		return getCards;
	}

	@Override
	public int checkDuplicate(String cardNumber) {
		Connection conn = null;
				
		int x = -1;
		
		try {
			conn = ConnectionProvider.getConnection();
			
			x = dao.checkDuplicate(cardNumber, conn);
			
			if(x >= 1)
				throw new DuplicateException();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally{
			JdbcUtil.close(conn);
		}
		
		return x;
	}

	@Override
	public int getCount() {
		Connection conn = null;
		
		int x = -1;
		
		try {
			conn = ConnectionProvider.getConnection();
			
			x = dao.getCount(conn);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally{
			JdbcUtil.close(conn);
		}
		
		return x;
	}

	@Override
	public int checkPassword(String cardNumber, String password) {
		Connection conn = null;
		
		int x = -1;
		
		try {
			conn = ConnectionProvider.getConnection();
			
			x = dao.checkPassword(cardNumber, password, conn);
			
			if(x == 0)
				throw new NotFoundCardExecption();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally{
			JdbcUtil.close(conn);
		}
		
		return x;
	}

}
