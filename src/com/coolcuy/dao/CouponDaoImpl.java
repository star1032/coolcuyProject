package com.coolcuy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.coolcuy.dto.CouponDto;
import com.coolcuy.jdbc.connection.ConnectionProvider;
import com.coolcuy.util.JdbcUtil;

public class CouponDaoImpl implements CouponDao {
	public CouponDaoImpl() {
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int add(CouponDto object, Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		String query = "INSERT INTO COUPON VALUES ('#CP'||LPAD(SEQ_COUPON.NEXTVAL, 5, 0),"
				+ " ?,"
				+ " TO_DATE(?, 'YYYY-MM-DD'),"
				+ " TO_DATE(?, 'YYYY-MM-DD'),"
				+ " TO_DATE(?, 'YYYY-MM-DD'),"
				+ " TO_DATE(?, 'YYYY-MM-DD'),"
				+ " ?, ?, ?, ?, ?,"
				+ " SYSDATE)";
		int x = -1;
		try{
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, object.getCouponName());
			pstmt.setDate(2, object.getIssueStart());
			pstmt.setDate(3, object.getIssueEnd());
			pstmt.setDate(4, object.getExpiredStart());
			pstmt.setDate(5, object.getExpiredEnd());
			pstmt.setString(6, object.getAvailableSpot());
			pstmt.setString(7, object.getRestriction());
			pstmt.setString(8, object.getLimitType());
			pstmt.setString(9, object.getNote());
			pstmt.setString(10, object.getImageName());
			
			x = pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
		
		return x;
	}

	@Override
	public int delete(String element, Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		String query = "DELETE FROM COUPON WHERE COUPONNUMBER = ?";
		
		int x = -1;
		
		try{
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, element);
			x = pstmt.executeUpdate();
		} finally{
			JdbcUtil.close(pstmt);
		}
		
		return x;
	}

	@Override
	public int deleteAll(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		String query = "DELETE COUPON";
		
		int x = -1;
		try{
			pstmt = conn.prepareStatement(query);
			
			x = pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
		return x;
		
	}

	@Override
	public int update(CouponDto object, Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		int x = -1;
		
		String query = "UPDATE COUPON SET COUPONNAME=?,"
				+ " ISSUESTART=TO_DATE(?, 'YYYY-MM-DD'),"
				+ " ISSUEEND=TO_DATE(?, 'YYYY-MM-DD'),"
				+ " EXPIREDSTART=TO_DATE(?, 'YYYY-MM-DD'),"
				+ " EXPIREDEND=TO_DATE(?, 'YYYY-MM-DD'),"
				+ " AVAILABLESPOT=?, RESTRICTION=?, LIMITTYPE=?, NOTE=?,"
				+ " IMAGENAME=?,"
				+ " WHERE COUPONNUMBER=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, object.getCouponName());
			pstmt.setDate(2, object.getIssueStart());
			pstmt.setDate(3, object.getIssueEnd());
			pstmt.setDate(4, object.getExpiredStart());
			pstmt.setDate(5, object.getExpiredEnd());
			pstmt.setString(6, object.getAvailableSpot());
			pstmt.setString(7, object.getRestriction());
			pstmt.setString(8, object.getLimitType());
			pstmt.setString(9, object.getNote());
			pstmt.setString(10, object.getImageName());
			pstmt.setString(11, object.getCouponNumber());
			
			x = pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
		
		return x;
		
	}

	@Override
	public CouponDto get(String element, Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query ="SELECT COUPONNUMBER, COUPONNAME,"
				+ " TO_CHAR(ISSUESTART, 'YYYY-MM-DD') AS ISSUESTART,"
				+ " TO_CHAR(ISSUEEND, 'YYYY-MM-DD') AS ISSUEEND,"
				+ " TO_CHAR(EXPIREDSTART, 'YYYY-MM-DD') AS EXPIREDSTART,"
				+ " TO_CHAR(EXPIREDEND, 'YYYY-MM-DD') AS EXPIREDEND,"
				+ " AVAILABLESPOT, RESTRICTION,"
				+ " LIMITTYPE, NOTE, IMAGENAME,"
				+ " TO_CHAR(REGDATE, 'YYYY-MM-DD HH24:MI') AS REGDATE"
				+ " FROM COUPON WHERE COUPONNUMBER=?";
		
		CouponDto coupon = null;
		
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, element);
			
			rs=pstmt.executeQuery();
			
			coupon = new CouponDto();
			
			if(rs.next()){
				coupon.setCouponNumber(rs.getString("couponNumber"));
				coupon.setCouponName(rs.getString("couponName"));
				coupon.setIssueStart(rs.getDate("issueStart"));
				coupon.setIssueEnd(rs.getDate("issueEnd"));
				coupon.setExpiredStart(rs.getDate("expiredStart"));
				coupon.setExpiredEnd(rs.getDate("expiredEnd"));
				coupon.setAvailableSpot(rs.getString("availableSpot"));
				coupon.setRestriction(rs.getString("restriction"));
				coupon.setLimitType(rs.getString("limitType"));
				coupon.setNote(rs.getString("note"));
				coupon.setImageName(rs.getString("imageName"));
				coupon.setRegDate(rs.getString("regDate"));
			}
			
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		
		return coupon;
		
	}
	
	@Override
	public List<CouponDto> getAll(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = " SELECT COUPONNUMBER, COUPONNAME,"
				+ " TO_CHAR(ISSUESTART, 'YYYY-MM-DD') AS ISSUESTART,"
				+ " TO_CHAR(ISSUEEND, 'YYYY-MM-DD') AS ISSUEEND,"
				+ " TO_CHAR(EXPIREDSTART, 'YYYY-MM-DD') AS EXPIREDSTART,"
				+ " TO_CHAR(EXPIREDEND, 'YYYY-MM-DD') AS EXPIREDEND,"
				+ " AVAILABLESPOT, RESTRICTION, LIMITTYPE, NOTE, IMAGENAME,"
				+ " TO_CHAR(REGDATE, 'YYYY-MM-DD HH24:MI') AS REGDATE"
				+ " FROM COUPON ORDER BY COUPONNUMBER ASC";
		
		List<CouponDto> coupons = new ArrayList<CouponDto>();
		
		try{
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				CouponDto coupon = new CouponDto(
						rs.getString("couponNumber"),
						rs.getString("couponName"),
						rs.getDate("issueStart"),
						rs.getDate("issueEnd"),
						rs.getDate("expiredStart"),
						rs.getDate("expiredEnd"),
						rs.getString("availableSpot"),
						rs.getString("restriction"),
						rs.getString("limitType"),
						rs.getString("note"),
						rs.getString("imageName"),
						rs.getString("regDate")
					);
				coupons.add(coupon);
			}
			
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		
		return coupons;
	}
	
	@Override
	public int updateImage(CouponDto object, Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		
		String query = "UPDATE COUPON SET IMAGENAME=?"
				+ " WHERE COUPONNUMBER=?";
		
		int x = -1;
		
		try{
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, object.getImageName());
			pstmt.setString(2, object.getCouponNumber());
			
		}finally {
			JdbcUtil.close(pstmt);
		}
		
		return x;
	}

	@Override
	public int getCount(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "SELECT COUNT(COUPONNUMBER) FROM COUPON";
		
		int count = -1;
		try {
			
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(query);
			
			rs = pstmt.executeQuery();
			
			if(rs.next())
				count = rs.getInt(1);
			
			
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		
		return count;
	}


}
