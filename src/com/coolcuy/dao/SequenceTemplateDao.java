package com.coolcuy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.coolcuy.jdbc.connection.ConnectionProvider;

public class SequenceTemplateDao {
	public void createSquence(String sequenceName, int start, int increment, int minValue){
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String query = "CREATE SEQUENCE "+ sequenceName
				+ " START WITH 100 "
				+ " INCREMENT BY 100 "
				+ " MINVALUE 100 "
				+ " NOCYCLE "
				+ " NOCACHE";
		
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(query);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn != null){try{conn.close();}catch(SQLException e){}}
			if(pstmt != null){try{pstmt.close();}catch(SQLException e){}}
		}	
	}
	
	public void dropSequence(String sequenceName){
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String query = "DROP SEQUENCE " + sequenceName;
		
		try{
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(query);

			pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(conn != null){try{conn.close();}catch(SQLException e){}}
			if(pstmt != null){try{pstmt.close();}catch(SQLException e){}}
		}	
	}
}
