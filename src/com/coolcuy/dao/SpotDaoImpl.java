package com.coolcuy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.coolcuy.dto.SpotDto;
import com.coolcuy.dto.SpotNameDto;
import com.coolcuy.util.JdbcUtil;

public class SpotDaoImpl implements SpotDao {

	final String URL = "jdbc:oracle:thin:@192.168.0.56:1521:XE";
	final String USER = "yun";
	final String PASSWORD = "1111";

	public SpotDaoImpl() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public int add(SpotDto object, Connection conn) throws SQLException{
		PreparedStatement pstmt = null;
		String quary = "INSERT INTO SPOT VALUES(?,?,?,?,?,?,SYSDATE,?,?,?,?)";

		int x = -1;		
		try {
			pstmt = conn.prepareStatement(quary);
			
			pstmt.setString(1, object.getSpotName());
			pstmt.setString(2, object.getPassword());
			pstmt.setString(3, object.getPosition());
			pstmt.setString(4, object.getOpenTime());
			pstmt.setString(5, object.getCloseTime());
			pstmt.setString(6, object.getPhoneNumber());
			pstmt.setString(7, object.getArea());
			pstmt.setString(8, object.getZipCode());
			pstmt.setString(9, object.getRoadAddr());
			pstmt.setString(10, object.getDetailAddr());
			
			x = pstmt.executeUpdate();
			
		}finally {
			JdbcUtil.close(pstmt);
		}
		
		return x;
		
	}

	@Override
	public int delete(String element, Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		String quary = "DELETE FROM SPOT WHERE SPOTNAME=?";

		int x = -1;

		try {
			pstmt = conn.prepareStatement(quary);

			pstmt.setString(1, element);
			pstmt.executeUpdate();
			
		}finally {
			JdbcUtil.close(pstmt);
		}
		
		return x;
	}

	@Override
	public int deleteAll(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		String quary = "DELETE FROM SPOT";
		int x = -1;

		try {
			pstmt = conn.prepareStatement(quary);

			x = pstmt.executeUpdate();
		}finally {
			JdbcUtil.close(pstmt);
		}
		
		return x;	
	}

	@Override
	public int update(SpotDto object, Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		String quary = "UPDATE SPOT SET PASSWORD=? ,OPENTIME=?, CLOSETIME=?, PHONENUMBER=?"
				+ " WHERE SPOTNAME=?";
		int x = -1;

		try {
			pstmt = conn.prepareStatement(quary);

			pstmt.setString(1, object.getPassword());
			pstmt.setString(2, object.getOpenTime());
			pstmt.setString(3, object.getCloseTime());
			pstmt.setString(4, object.getPhoneNumber());
			pstmt.setString(5, object.getSpotName());

			x = pstmt.executeUpdate();

		}finally {
			JdbcUtil.close(pstmt);
		}

		return x;
	}

	@Override
	public SpotDto get(String element, Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String quary = "SELECT PASSWORD, POSITION, SPOTNAME, "
				+ " OPENTIME, CLOSETIME, PHONENUMBER, TO_CHAR(REGDATE, 'YYYY-MM-DD HH24:MI:SS') AS REGDATE, AREA, "
				+ " ZIPCODE, ROADADDR, DETAILADDR " 
				+ " FROM SPOT WHERE SPOTNAME=?";

		SpotDto spot = new SpotDto();
		
		try {
			pstmt = conn.prepareStatement(quary);

			pstmt.setString(1, element);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				spot.setPassword(rs.getString("password"));
				spot.setPosition(rs.getString("position"));
				spot.setSpotName(rs.getString("spotName"));
				spot.setOpenTime(rs.getString("openTime"));
				spot.setCloseTime(rs.getString("closeTime"));
				spot.setPhoneNumber(rs.getString("phoneNumber"));
				spot.setRegDate(rs.getString("regDate"));
				spot.setArea(rs.getString("area"));
				spot.setZipCode(rs.getString("zipCode"));
				spot.setRoadAddr(rs.getString("roadAddr"));
				spot.setDetailAddr(rs.getString("detailAddr"));
			}
			
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}

		
		return spot;
	
	}

	@Override
	public List<SpotDto> getAll(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "SELECT PASSWORD, POSITION, SPOTNAME, "
				+ " OPENTIME, CLOSETIME, PHONENUMBER, TO_CHAR(REGDATE, 'YYYY-MM-DD HH24:MI:SS') AS REGDATE, AREA, "
				+ " ZIPCODE, ROADADDR, DETAILADDR"
				+ " FROM SPOT"
				+ " ORDER BY SPOTNAME ASC";
		
		List<SpotDto> spots = new ArrayList<SpotDto>();
		
		try {
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				SpotDto spot = new SpotDto(
						rs.getString("spotName"),
						rs.getString("password"),
						rs.getString("position"),
						rs.getString("openTime"),
						rs.getString("closeTime"),
						rs.getString("phoneNumber"),
						rs.getString("regDate"),
						rs.getString("area"),
						rs.getString("zipCode"),		
						rs.getString("roadAddr"),		
						rs.getString("detailAddr")
					);
				
				spots.add(spot);
			}
				
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}

		return spots;
	}

	@Override
	public int getCount(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = -1;
		
		String query = "SELECT COUNT(SPOTNAME) FROM SPOT";
		
		try{
			pstmt = conn.prepareStatement(query);		
			rs = pstmt.executeQuery();
			
			rs.next();
			x = rs.getInt(1);
			
		}finally{
			JdbcUtil.close(pstmt);
		}
		
		return x;
	}
		
	@Override
	public List<SpotNameDto> getAllSpotName(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		List<SpotNameDto> spotNames = null;
				
		String query = "SELECT AREA, SPOTNAME FROM SPOT";
		
		try{
			pstmt = conn.prepareStatement(query);			
			rs = pstmt.executeQuery();
			
			spotNames = new ArrayList<SpotNameDto>();
			
			while(rs.next()){
				
				spotNames.add(new SpotNameDto(rs.getString("area"), rs.getString("spotName")));
			}
		
		}finally{
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		
		return spotNames;
	}
}