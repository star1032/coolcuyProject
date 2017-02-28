
//�뜲�씠�꽣 踰좎씠�뒪�뿉 �젒洹쇳븯�뒗 媛앹껜
package com.coolcuy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.coolcuy.dto.LicenseDto;
import com.coolcuy.dto.MemberDto;
import com.coolcuy.util.JdbcUtil;

public class MemberDaoImpl implements MemberDao {
	public MemberDaoImpl() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public int add(MemberDto object, Connection conn) throws SQLException{
		PreparedStatement pstmt = null;
		String quary = "INSERT INTO MEMBER VALUES(?,?,?,?,?,?,?,?,?,?,?,?,"
				+ "TO_DATE(?, 'YYYY-MM-DD'),TO_DATE(?, 'YYYY-MM-DD'),SYSDATE,?,?)";

		int x = -1;
		try {
			pstmt = conn.prepareStatement(quary);

			LicenseDto license = object.getLicenseDto();
			
			pstmt.setString(1, object.getEmail());
			pstmt.setString(2, object.getPhoneNumber());
			pstmt.setString(3, object.getName());
			pstmt.setString(4, object.getPassword());
			pstmt.setString(5, object.getRating());
			pstmt.setString(6, object.getZipCode());
			pstmt.setString(7, object.getRoadAddr());
			pstmt.setString(8, object.getDetailAddr());
			pstmt.setString(9, object.getPrimaryArea());
			pstmt.setString(10, license.getLicenseNumber());
			pstmt.setString(11, license.getLicenseType());
			pstmt.setString(12, license.getGender());
			pstmt.setString(13, license.getIssuDate());
			pstmt.setString(14, license.getExpiryDate());
			pstmt.setInt(15, object.getPoint());
			pstmt.setInt(16, object.getRentCnt());
			
			x = pstmt.executeUpdate();
			
		}finally {
			JdbcUtil.close(pstmt);
		}

		return x;
	}

	@Override
	public int delete(String element, Connection conn) throws SQLException{
		PreparedStatement pstmt = null;
		String quary = "DELETE FROM MEMBER WHERE EMAIL=?";

		int x = -1;

		try {
			pstmt = conn.prepareStatement(quary);

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
		String quary = "DELETE MEMBER ";
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
	public int update(MemberDto object, Connection conn) throws SQLException{
		PreparedStatement pstmt = null;
		LicenseDto license = object.getLicenseDto();
		int x = -1;
		
		String query = "UPDATE MEMBER SET PHONENUMBER=?, NAME=?, PASSWORD=?, ZIPCODE=?, "
				+ "ROADADDR=?, DETAILADDR=?, PRIMARYAREA=?, LICENSENUMBER=?, LICENSETYPE=?, "
				+ "GENDER=?, ISSUDATE=TO_DATE(?, 'YYYY-MM-DD'), "
				+ "EXPIRYDATE=TO_DATE(?, 'YYYY-MM-DD') "
				+ "WHERE EMAIL=?";
		
		try{
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, object.getPhoneNumber());
			pstmt.setString(2, object.getName());
			pstmt.setString(3, object.getPassword());
			pstmt.setString(4, object.getZipCode());
			pstmt.setString(5, object.getRoadAddr());
			pstmt.setString(6, object.getDetailAddr());
			pstmt.setString(7, object.getPrimaryArea());
			
			pstmt.setString(8, license.getLicenseNumber());
			pstmt.setString(9, license.getLicenseType());
			pstmt.setString(10, license.getGender());
			pstmt.setString(11, license.getIssuDate());
			pstmt.setString(12, license.getExpiryDate());
			pstmt.setString(13, license.getEmail());
			
			x = pstmt.executeUpdate();
		}finally{
			JdbcUtil.close(pstmt);
		}
		
		return x;
	}

	@Override
	public MemberDto get(String element, Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		
		String quary = "SELECT EMAIL, PHONENUMBER, NAME, PASSWORD, RATING, ZIPCODE, "
				+ "ROADADDR, DETAILADDR, PRIMARYAREA, LICENSENUMBER, LICENSETYPE, "
				+ "GENDER, TO_CHAR(ISSUDATE, 'YYYY-MM-DD') AS ISSUDATE, "
				+ "TO_CHAR(EXPIRYDATE, 'YYYY-MM-DD') AS EXPIRYDATE, "
				+ "TO_CHAR(REGDATE, 'YYYY-MM-DD') AS REGDATE , "
				+ "POINT, RENTCNT "
				+ "FROM MEMBER "
				+ "WHERE EMAIL=? "
				+ "ORDER BY EMAIL ASC";

		MemberDto member = null;

		try {
			pstmt = conn.prepareStatement(quary);

			pstmt.setString(1, element);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				member = new MemberDto(rs.getString("email"),
						rs.getString("phoneNumber"),
						rs.getString("name"),
						rs.getString("password"),
						rs.getString("rating"),
						rs.getString("zipCode"),
						rs.getString("roadAddr"),
						rs.getString("detailAddr"),
						rs.getString("primaryArea"),
						rs.getString("regDate"),
						rs.getInt("rentCnt"),
						rs.getInt("point"),
						
						new LicenseDto(
								rs.getString("email"), 
								rs.getString("licenseNumber"),
								rs.getString("licenseType"), 
								rs.getString("gender"),
								rs.getString("issuDate"),
								rs.getString("expiryDate")
								)
						);
				
			}

		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}

		return member;
	}

	@Override
	public List<MemberDto> getAll(Connection conn) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "SELECT EMAIL, PHONENUMBER, NAME, "
				+ "PASSWORD, RATING, TRIM(ZIPCODE) AS ZIPCODE, ROADADDR, "
				+ "DETAILADDR, PRIMARYAREA, LICENSENUMBER, LICENSETYPE, GENDER, "
				+ "TO_CHAR(ISSUDATE, 'YYYY-MM-DD') AS ISSUDATE, "
				+ "TO_CHAR(EXPIRYDATE, 'YYYY-MM-DD') AS EXPIRYDATE, "
				+ "TO_CHAR(REGDATE, 'YYYY-MM-DD HH24:MI:SS') AS REGDATE, "
				+ "POINT, RENTCNT FROM MEMBER ORDER BY EMAIL ASC";
		
		List<MemberDto> members = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			members =  new ArrayList<MemberDto>();
			
			while(rs.next()){
				
				MemberDto member = new MemberDto(
						rs.getString("email"),
						rs.getString("phoneNumber"),
						rs.getString("name"),
						rs.getString("password"),
						rs.getString("rating"),
						rs.getString("zipCode"),
						rs.getString("roadAddr"),
						rs.getString("detailAddr"),
						rs.getString("primaryArea"),
						rs.getString("regDate"),
						rs.getInt("rentCnt"),
						rs.getInt("point"),
						
						new LicenseDto(
								rs.getString("email"), 
								rs.getString("licenseNumber"),
								rs.getString("licenseType"), 
								rs.getString("gender"),
								rs.getString("issuDate"),
								rs.getString("expiryDate")
								)
						);
				
				System.out.println(member.getLicenseDto().getLicenseNumber());
				
				members.add(member);
			}
				
		}finally {
		    JdbcUtil.close(pstmt);
		    JdbcUtil.close(rs);
		}

		return members;
	}

	@Override
	public String checkDuplicate(String email, Connection conn) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String quary = "SELECT EMAIL FROM MEMBER WHERE EMAIL=?";
		
		String getEmail = null;
		
		try {
			pstmt = conn.prepareStatement(quary);
			
			pstmt.setString(1, email);

			rs = pstmt.executeQuery();

			if (rs.next())
				getEmail = rs.getString("email");
			
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		
		return getEmail;
	}

	@Override
	public int getCount(Connection conn) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String quary = "SELECT COUNT(EMAIL) FROM MEMBER";

		int count = -1;
		
		try {
			pstmt = conn.prepareStatement(quary);

			rs = pstmt.executeQuery();

			if (rs.next())
				count = rs.getInt(1);
			
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}

		return count;
	}
	
	@Override
	public int checkPassword(String email, String password, Connection conn) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String quary = "SELECT COUNT(EMAIL) AS COUNT FROM MEMBER WHERE EMAIL=? AND PASSWORD=?";
		int x = -1;

		try {
			pstmt = conn.prepareStatement(quary);

			pstmt.setString(1, email);
			pstmt.setString(2, password);

			rs = pstmt.executeQuery();
			
			if(rs.next())
				x = rs.getInt("COUNT");
			
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		
		return x;
	}

	@Override
	public int getRating(String email, Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String quary = "SELECT RATING FROM MEMBER WHERE EMAIL=?";
		int x = -1;

		try {
			pstmt = conn.prepareStatement(quary);

			pstmt.setString(1, email);

			rs = pstmt.executeQuery();
			
			if(rs.next())
				x = rs.getInt("rating");
			
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		
		return x;
	}
}
