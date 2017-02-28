package com.coolcuy.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.coolcuy.dto.MemberDto;

public interface MemberDao extends GenericDao<MemberDto>{
	public String checkDuplicate(String email, Connection conn) throws SQLException;
	public int getCount(Connection conn) throws SQLException;
	public int checkPassword(String email, String password, Connection conn) throws SQLException;
	public int getRating(String email, Connection conn) throws SQLException;
}
