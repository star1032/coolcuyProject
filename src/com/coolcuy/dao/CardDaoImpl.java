package com.coolcuy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.coolcuy.dto.CardDto;
import com.coolcuy.jdbc.connection.ConnectionProvider;
import com.coolcuy.util.JdbcUtil;

public class CardDaoImpl implements CardDao {

	public CardDaoImpl() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public int add(CardDto object, Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		String query = "INSERT INTO CARD VALUES(?,?,TO_DATE(?, 'MM-YY'),?,?,SYSDATE,?)";
		
		int x = -1;
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, object.getCardNumber());
			pstmt.setString(2, object.getCardType());
			pstmt.setString(3, object.getCardExpiryDate());
			pstmt.setString(4, object.getBirth());
			pstmt.setString(5, object.getCardPassword());
			pstmt.setString(6, object.getEmail());
			
			x = pstmt.executeUpdate();
		}finally {
			JdbcUtil.close(pstmt);
		}
		
		return x;
	}
	
	@Override
	public int delete(String element, Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		String query = "DELETE FROM CARD WHERE CARDNUMBER =?";
		
		int x = -1;
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, element);
			x = pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
				
		return x;
	}

	@Override
	public int deleteAll(Connection conn) throws SQLException{
		PreparedStatement pstmt = null;
		String query = "DELETE CARD";
		
		int x = -1;
		try {
			pstmt = conn.prepareStatement(query);
			
			x = pstmt.executeUpdate();
		}finally {
			JdbcUtil.close(pstmt);
		}
		return x;
		
	}

	@Override
	public int update(CardDto object, Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		String query = "UPDATE CARD SET CARDPASSWORD=? "
				+ "WHERE CARDNUMBER=?";
		
		int x = -1;
		
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, object.getCardPassword());
			pstmt.setString(2, object.getCardNumber());
			
		}finally {
		    JdbcUtil.close(pstmt);
		}	
		
		return x;
	}

	@Override
	public CardDto get(String element, Connection conn) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT CARDNUMBER, CARDTYPE, "
				+ "TO_CHAR(CARDEXPIRYDATE, 'MM/YY') AS CARDEXPIRYDATE, "
				+ "BIRTH, CARDPASSWORD, TO_CHAR(REGDATE, 'YYYY-MM-DD')AS REGDATE, "
				+ "EMAIL FROM CARD WHERE CARDNUMBER=?";
		
		CardDto card = null;
		System.out.println(element);
		
		try {
			pstmt = conn.prepareStatement(query);			
			pstmt.setString(1, element);
			
			rs = pstmt.executeQuery();
			
			card = new CardDto();
			
			if(rs.next()){
				card.setCardNumber(rs.getString("cardNumber"));
				card.setCardType(rs.getString("cardType"));
				card.setCardExpiryDate(rs.getString("cardExpiryDate"));
				card.setBirth(rs.getString("birth"));
				card.setCardPassword(rs.getString("cardPassword"));
				card.setRegDate(rs.getString("regDate"));
				card.setEmail(rs.getString("email"));
			}
			
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		
		return card;
	}

	@Override
	public List<CardDto> getAll(Connection conn) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query ="SELECT CARDNUMBER, CARDTYPE, "
				+ "TO_CHAR(CARDEXPIRYDATE, 'MM/YY') AS CARDEXPIRYDATE, "
				+ "BIRTH, CARDPASSWORD, TO_CHAR(REGDATE, 'YYYY-MM-DD')AS REGDATE, "
				+ "EMAIL FROM CARD ORDER BY CARDNUMBER ASC";
		
		List<CardDto> cards = new ArrayList<CardDto>();
		
		try {
			conn= ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				CardDto card = new CardDto(
						rs.getString("cardNumber"),
						rs.getString("cardType"),
						rs.getString("cardExpirydate"),
						rs.getString("birth"),
						rs.getString("cardPassword"),
						rs.getString("regDate"),
						rs.getString("email")
					);
				cards.add(card);
			}
		}finally {
		    JdbcUtil.close(pstmt);
		    JdbcUtil.close(rs);
		}
		
		return cards;
	}

	@Override
	public int checkDuplicate(String cardNumber, Connection conn) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = -1;
		
		String query ="SELECT COUNT(CARDNUMBER) AS COUNT FROM CARD WHERE CARDNUMBER=?";
		
		try{
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, cardNumber);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				x = rs.getInt("count");
			}
			
		}finally {
		    JdbcUtil.close(pstmt);
		    JdbcUtil.close(rs);
		}
		
		return x;	
	}

	@Override
	public int getCount(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "SELECT COUNT(CARDNUMBER) FROM CARD";
		
		int count = -1;
		try{
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(query);
			
			rs = pstmt.executeQuery();
			
			if(rs.next())
				count = rs.getInt(1);
			
		}finally {
		    JdbcUtil.close(pstmt);
		    JdbcUtil.close(rs);
		}
		
		return count;
	}

	@Override
	public int checkPassword(String cardNumber, String password, Connection conn) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "SELECT COUNT(CARDNUMBER) AS COUNT FROM CARD WHERE CARDNUMBER=? AND CARDPASSWORD=?";
		int x = -1;
		
		try{
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, cardNumber);
			pstmt.setString(2, password);			
			rs = pstmt.executeQuery();
			conn.commit();
			
			if(rs.next())
				x = rs.getInt("COUNT");
						
		}finally {
		    JdbcUtil.close(pstmt);
		    JdbcUtil.close(rs);
		}
		return x;
	}

	@Override
	public List<CardDto> getAllByEmail(String element, Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "SELECT CARDNUMBER, CARDTYPE, "
				+ "TO_CHAR(CARDEXPIRYDATE, 'MM/YY') AS CARDEXPIRYDATE, "
				+ "BIRTH, CARDPASSWORD, TO_CHAR(REGDATE, 'YYYY-MM-DD')AS REGDATE, "
				+ "EMAIL FROM CARD WHERE EMAIL=? ORDER BY CARDNUMBER ASC";
		List<CardDto> cards = new ArrayList<CardDto>();
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, element);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				CardDto card = new CardDto(
						rs.getString("cardNumber"),
						rs.getString("cardType"),
						rs.getString("cardExpirydate"),
						rs.getString("birth"),
						rs.getString("cardPassword"),
						rs.getString("regDate"),
						rs.getString("email")
					);
				cards.add(card);
			}
			
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		
		return cards;
	}

}
