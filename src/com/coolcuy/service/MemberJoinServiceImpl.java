package com.coolcuy.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.coolcuy.dao.CardDao;
import com.coolcuy.dao.CardDaoImpl;
import com.coolcuy.dao.MemberDao;
import com.coolcuy.dao.MemberDaoImpl;
import com.coolcuy.dto.MemberJoinDto;
import com.coolcuy.jdbc.connection.ConnectionProvider;
import com.coolcuy.util.JdbcUtil;

public class MemberJoinServiceImpl implements MemberJoinService{
	private MemberDao memberDao;
	private CardDao cardDao;
	
	
	public MemberJoinServiceImpl() {
		cardDao = new CardDaoImpl();
		memberDao = new MemberDaoImpl();
	}
	
	@Override
	public int add(MemberJoinDto object) {
		Connection conn = null;
		int x = -1;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			memberDao.add(object.getMemberDto(), conn);
			x = cardDao.add(object.getCardDto(), conn);
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			
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
			conn.setAutoCommit(false);
			
			
			// 예약확인 로직 추가 요망..
			
			cardDao.delete(element, conn);
			x = memberDao.delete(element, conn);
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			
			throw new RuntimeException(e);
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
			conn.setAutoCommit(false);
			
			cardDao.deleteAll(conn);
			x = memberDao.deleteAll(conn);
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			
			throw new RuntimeException(e);
		} finally{
			JdbcUtil.close(conn);
		}		
		
		return x;
	}
}
