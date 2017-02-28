package com.coolcuy.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.coolcuy.dao.MemberDao;
import com.coolcuy.dao.MemberDaoImpl;
import com.coolcuy.dto.MemberDto;
import com.coolcuy.dto.UserDto;
import com.coolcuy.exception.LoginFailException;
import com.coolcuy.jdbc.connection.ConnectionProvider;
import com.coolcuy.util.JdbcUtil;

public class LoginService {
	
	MemberDao dao= new MemberDaoImpl();
	MemberDto member;
	public UserDto login(String id,String password){
		
		Connection conn = null;
		try{
		conn= ConnectionProvider.getConnection();
		member= dao.get(id,conn);
		if(member==null){
			throw new LoginFailException();
		}
		if(dao.checkPassword(id, password, conn)<1){
			throw new LoginFailException();
		}
		
		}catch (SQLException e) {
			throw new RuntimeException(e);
		} finally{
			JdbcUtil.close(conn);
		}
		
		return new UserDto(member.getEmail(),member.getName());
	}

}