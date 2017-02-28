package com.coolcuy.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.coolcuy.dao.MemberDao;
import com.coolcuy.dao.MemberDaoImpl;
import com.coolcuy.dto.MemberDto;
import com.coolcuy.exception.DuplicateException;
import com.coolcuy.exception.NotFoundMemberExecption;
import com.coolcuy.jdbc.connection.ConnectionProvider;
import com.coolcuy.util.JdbcUtil;

public class MemberServiceImpl implements MemberService {
	MemberDao dao = new MemberDaoImpl();
	
	@Override
	public int add(MemberDto object) {
		Connection conn = null;
		int x = -1;
		
		try {
			conn = ConnectionProvider.getConnection();
			x = dao.add(object, conn);
			
		} catch (SQLException e) {
			throw new RuntimeException();
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
	public MemberDto update(MemberDto object) {
		String email = object.getEmail();
		
		Connection conn = null;
		MemberDto updatedMember = null;
		int x = -1;
		
		MemberDto member = get(email);
		
		if(member.getEmail() != null){		
			try {
				conn = ConnectionProvider.getConnection();
				x = dao.update(object, conn);
				
				if(x == 1)
					updatedMember = get(email);	// is new Connection
				
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally{
				JdbcUtil.close(conn);
			}
		}
		
		return updatedMember;
	}

	@Override
	public MemberDto get(String element) {
		Connection conn = null;
		MemberDto member = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			member = dao.get(element, conn);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally{
			JdbcUtil.close(conn);
		}
		
		return member;
	}
	
	@Override
	public List<MemberDto> getAll() {
		Connection conn = null;
		List<MemberDto> getMembers = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			getMembers = dao.getAll(conn);
			
		} catch (SQLException e) {
			throw new RuntimeException();
		} finally{
			JdbcUtil.close(conn);
		}
		
		return getMembers;
	}
	
	@Override
	public int checkDuplicate(String email) {
		Connection conn = null;
		String getEmail = null;
		int x = -1;
		
		try {
			conn = ConnectionProvider.getConnection();
			getEmail = dao.checkDuplicate(email, conn);
			
			if(getEmail != null){
				x = 1;
				throw new DuplicateException();
			}else{
				x = 0;
			}
		} catch (SQLException e) {
			throw new RuntimeException();
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
			throw new RuntimeException();
		} finally{
			JdbcUtil.close(conn);
		}
		
		return x;
	}

	@Override
	public int checkPassword(String email, String password) {
		Connection conn = null;
		int x = -1;
		
		try {
			conn = ConnectionProvider.getConnection();
			x = dao.checkPassword(email, password, conn);
			
			if(x <= 0)
				throw new NotFoundMemberExecption();
				
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally{
			JdbcUtil.close(conn);
		}
		
		return x;
	}
}
