package com.coolcuy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.coolcuy.dto.CarDto;
import com.coolcuy.dto.RentDto;
import com.coolcuy.exception.NotSupportedException;
import com.coolcuy.util.JdbcUtil;

public class RentDaoImpl implements RentDao{
	public RentDaoImpl() {}
	
	@Override
	public int add(RentDto rentDto, Connection conn) throws SQLException {
	
		PreparedStatement pstmt = null;
		int x = -1;
		String query = "INSERT INTO RENT VALUES("
					+ "SEQ_RENT.NEXTVAL, ?, ?, ?, ?, ?, ?, "
					+ "TO_DATE(?, 'YYYY-MM-DD HH24:MI'), TO_DATE(?, 'YYYY-MM-DD HH24:MI'), "
					+ "TO_DATE(?, 'YYYY-MM-DD HH24:MI'), TO_DATE(?, 'YYYY-MM-DD HH24:MI'), "
					+ "?, ?, SYSDATE)";
		
		try{
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, rentDto.getEmail());
			pstmt.setString(2, rentDto.getCarNumber());
			pstmt.setString(3, rentDto.getStartSpot());
			pstmt.setString(4, rentDto.getEndSpot());
			pstmt.setString(5, rentDto.getStartConfirm());
			pstmt.setString(6, rentDto.getEndConfirm());
			pstmt.setString(7, rentDto.getStartDate());
			pstmt.setString(8, rentDto.getEndDate());
			pstmt.setString(9, rentDto.getRealStartDate());
			pstmt.setString(10, rentDto.getRealEndDate());
			pstmt.setString(11, rentDto.getInsurance());
			pstmt.setString(12, rentDto.getReqBabySeat());
			
			x = pstmt.executeUpdate();
		}finally{
			JdbcUtil.close(pstmt);
		}
		
		return x;
	}

	@Override
	public RentDto get(int rentNumber, Connection conn) throws SQLException {
		RentDto rent = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT RENTNUMBER, EMAIL, CARNUMBER, "
				+ " STARTSPOT, ENDSPOT, STARTCONFIRM, ENDCONFIRM, "
				+ " TO_CHAR(STARTDATE, 'YYYY-MM-DD HH24:MI'), TO_CHAR(ENDDATE, 'YYYY-MM-DD HH24:MI')"
				+ " TO_DATE(REALSTARTDATE, 'YYYY-MM-DD HH24:MI'), TO_DATE(REALENDDATE, 'YYYY-MM-DD HH24:MI'), "
				+ " INSURANCE, REQBABYSEAT, TO_DATE(REGDATE, 'YYYY-MM-DD HH24:MI:SS')"
				+ " WHERE RENTNUMBER=?";
		
		try{
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, rentNumber);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				rent = new RentDto(
						rs.getInt("rentNumber"), 
						rs.getString("email"), 
						rs.getString("carNumber"), 
						rs.getString("startSpot"), 
						rs.getString("endSpot"), 
						rs.getString("startConfirm"), 
						rs.getString("endConfirm"), 
						rs.getString("startDate"), 
						rs.getString("endDate"), 
						rs.getString("realStartDate"), 
						rs.getString("realEndDate"), 
						rs.getString("insurance"), 
						rs.getString("reqBabySeat"), 
						rs.getString("regDate") 
						);
			}
		}finally{
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		
		return rent;
	}

	@Override
	public List<RentDto> getAllByEmail(String email, Connection conn) throws SQLException {
		List<RentDto> rents = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT RENTNUMBER, EMAIL, CARNUMBER, "
				+ " STARTSPOT, ENDSPOT, STARTCONFIRM, ENDCONFIRM, "
				+ " TO_CHAR(STARTDATE, 'YYYY-MM-DD HH24:MI'), TO_CHAR(ENDDATE, 'YYYY-MM-DD HH24:MI')"
				+ " TO_DATE(REALSTARTDATE, 'YYYY-MM-DD HH24:MI'), TO_DATE(REALENDDATE, 'YYYY-MM-DD HH24:MI'), "
				+ " INSURANCE, REQBABYSEAT, TO_DATE(REGDATE, 'YYYY-MM-DD HH24:MI:SS')"
				+ " WHERE EMAIL=?";
		
		try{			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			rents = new ArrayList<RentDto>();
			
			while(rs.next()){
				RentDto rent = new RentDto(
						rs.getInt("rentNumber"), 
						rs.getString("email"), 
						rs.getString("carNumber"), 
						rs.getString("startSpot"), 
						rs.getString("endSpot"), 
						rs.getString("startConfirm"), 
						rs.getString("endConfirm"), 
						rs.getString("startDate"), 
						rs.getString("endDate"), 
						rs.getString("realStartDate"), 
						rs.getString("realEndDate"), 
						rs.getString("insurance"), 
						rs.getString("reqBabySeat"), 
						rs.getString("regDate") 
						);
				
				rents.add(rent);
			}
		}finally{
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		
		return rents;
	}

	@Override
	public List<RentDto> getAllByStartSpot(String spot, Connection conn) throws SQLException {
		List<RentDto> rents = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT RENTNUMBER, EMAIL, CARNUMBER, "
				+ " STARTSPOT, ENDSPOT, STARTCONFIRM, ENDCONFIRM, "
				+ " TO_CHAR(STARTDATE, 'YYYY-MM-DD HH24:MI'), TO_CHAR(ENDDATE, 'YYYY-MM-DD HH24:MI')"
				+ " TO_DATE(REALSTARTDATE, 'YYYY-MM-DD HH24:MI'), TO_DATE(REALENDDATE, 'YYYY-MM-DD HH24:MI'), "
				+ " INSURANCE, REQBABYSEAT, TO_DATE(REGDATE, 'YYYY-MM-DD HH24:MI:SS')"
				+ " WHERE STARTSPOT=?";
		
		try{			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, spot);
			rs = pstmt.executeQuery();
			rents = new ArrayList<RentDto>();
			
			while(rs.next()){
				RentDto rent = new RentDto(
						rs.getInt("rentNumber"), 
						rs.getString("email"), 
						rs.getString("carNumber"), 
						rs.getString("startSpot"), 
						rs.getString("endSpot"), 
						rs.getString("startConfirm"), 
						rs.getString("endConfirm"), 
						rs.getString("startDate"), 
						rs.getString("endDate"), 
						rs.getString("realStartDate"), 
						rs.getString("realEndDate"), 
						rs.getString("insurance"), 
						rs.getString("reqBabySeat"), 
						rs.getString("regDate") 
						);
				
				rents.add(rent);
			}
		}finally{
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		
		return rents;
	}

	@Override
	public List<RentDto> getAllByEndSpot(String spot, Connection conn) throws SQLException {
		List<RentDto> rents = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT RENTNUMBER, EMAIL, CARNUMBER, "
				+ " STARTSPOT, ENDSPOT, STARTCONFIRM, ENDCONFIRM, "
				+ " TO_CHAR(STARTDATE, 'YYYY-MM-DD HH24:MI'), TO_CHAR(ENDDATE, 'YYYY-MM-DD HH24:MI')"
				+ " TO_DATE(REALSTARTDATE, 'YYYY-MM-DD HH24:MI'), TO_DATE(REALENDDATE, 'YYYY-MM-DD HH24:MI'), "
				+ " INSURANCE, REQBABYSEAT, TO_DATE(REGDATE, 'YYYY-MM-DD HH24:MI:SS')"
				+ " WHERE ENDSPOT=?";
		
		try{			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, spot);
			rs = pstmt.executeQuery();
			rents = new ArrayList<RentDto>();
			
			while(rs.next()){
				RentDto rent = new RentDto(
						rs.getInt("rentNumber"), 
						rs.getString("email"), 
						rs.getString("carNumber"), 
						rs.getString("startSpot"), 
						rs.getString("endSpot"), 
						rs.getString("startConfirm"), 
						rs.getString("endConfirm"), 
						rs.getString("startDate"), 
						rs.getString("endDate"), 
						rs.getString("realStartDate"), 
						rs.getString("realEndDate"), 
						rs.getString("insurance"), 
						rs.getString("reqBabySeat"), 
						rs.getString("regDate") 
						);
				
				rents.add(rent);
			}
		}finally{
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		
		return rents;
	}

	@Override
	public List<RentDto> getAllByCar(String carNumber, Connection conn) throws SQLException {
		List<RentDto> rents = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT RENTNUMBER, EMAIL, CARNUMBER, "
				+ " STARTSPOT, ENDSPOT, STARTCONFIRM, ENDCONFIRM, "
				+ " TO_CHAR(STARTDATE, 'YYYY-MM-DD HH24:MI'), TO_CHAR(ENDDATE, 'YYYY-MM-DD HH24:MI')"
				+ " TO_DATE(REALSTARTDATE, 'YYYY-MM-DD HH24:MI'), TO_DATE(REALENDDATE, 'YYYY-MM-DD HH24:MI'), "
				+ " INSURANCE, REQBABYSEAT, TO_DATE(REGDATE, 'YYYY-MM-DD HH24:MI:SS')"
				+ " WHERE CARNUMBER=?";
		
		try{			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, carNumber);
			rs = pstmt.executeQuery();
			rents = new ArrayList<RentDto>();
			
			while(rs.next()){
				RentDto rent = new RentDto(
						rs.getInt("rentNumber"), 
						rs.getString("email"), 
						rs.getString("carNumber"), 
						rs.getString("startSpot"), 
						rs.getString("endSpot"), 
						rs.getString("startConfirm"), 
						rs.getString("endConfirm"), 
						rs.getString("startDate"), 
						rs.getString("endDate"), 
						rs.getString("realStartDate"), 
						rs.getString("realEndDate"), 
						rs.getString("insurance"), 
						rs.getString("reqBabySeat"), 
						rs.getString("regDate") 
						);
				
				rents.add(rent);
			}
		}finally{
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		
		return rents;
	}

	@Override
	public List<RentDto> getAll(Connection conn) throws SQLException {
		List<RentDto> rents = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT RENTNUMBER, EMAIL, CARNUMBER, "
				+ " STARTSPOT, ENDSPOT, STARTCONFIRM, ENDCONFIRM, "
				+ " TO_CHAR(STARTDATE, 'YYYY-MM-DD HH24:MI'), TO_CHAR(ENDDATE, 'YYYY-MM-DD HH24:MI')"
				+ " TO_DATE(REALSTARTDATE, 'YYYY-MM-DD HH24:MI'), TO_DATE(REALENDDATE, 'YYYY-MM-DD HH24:MI'), "
				+ " INSURANCE, REQBABYSEAT, TO_DATE(REGDATE, 'YYYY-MM-DD HH24:MI:SS')";
		
		try{			
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			rents = new ArrayList<RentDto>();
			
			while(rs.next()){
				RentDto rent = new RentDto(
						rs.getInt("rentNumber"), 
						rs.getString("email"), 
						rs.getString("carNumber"), 
						rs.getString("startSpot"), 
						rs.getString("endSpot"), 
						rs.getString("startConfirm"), 
						rs.getString("endConfirm"), 
						rs.getString("startDate"), 
						rs.getString("endDate"), 
						rs.getString("realStartDate"), 
						rs.getString("realEndDate"), 
						rs.getString("insurance"), 
						rs.getString("reqBabySeat"), 
						rs.getString("regDate") 
						);
				
				rents.add(rent);
			}
		}finally{
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		
		return rents;
	}

	@Override
	public int delete(int rentNumber, Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		int x = -1;
		String query = "DELETE FROM RENT WHERE RENTNUMBER=?";
		
		try{	
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, rentNumber);
			pstmt.executeQuery();
		}finally{
			JdbcUtil.close(pstmt);
		}
		
		return x;
	}

	@Override
	public int deleteAll(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		int x = -1;
		String query = "DELETE FROM RENT";
		
		try{	
			pstmt = conn.prepareStatement(query);
			pstmt.executeQuery();
		}finally{
			JdbcUtil.close(pstmt);
		}
		
		return x;
	}

	@Override
	public int update(RentDto object, Connection conn) throws SQLException {
		throw new NotSupportedException();
	}
		
	@Override
	public List<CarDto> getRentAbleCar(String spot, String startDate, String endDate, String type, Connection conn)
			throws SQLException {
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		List<CarDto> getCars = new ArrayList<CarDto>();
		
		String query = "SELECT * FROM CAR "
				+ " WHERE CARNUMBER NOT IN "
				+ " (SELECT CARNUMBER FROM RENT "
				+ " WHERE TO_DATE(?, 'yyyy-mm-dd HH24:MI') <= ENDDATE "
				+ " AND TO_DATE(?, 'yyyy-mm-dd HH24:MI') >= STARTDATE) "
				+ " AND SPOTNAME=? "
				+ " AND TYPE=? ";
			
			try{
							
				pstmt = conn.prepareStatement(query);
				
				pstmt.setString(1, startDate);
				pstmt.setString(2, endDate);
				pstmt.setString(3, spot);
				pstmt.setString(4, type);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()){
					System.out.println("test");
					
					CarDto getCar = new CarDto(
							rs.getString("carNumber"),
							rs.getString("brand"),
							rs.getInt("timeMoney"),
							rs.getString("type"),
							rs.getString("regDate"),
							rs.getString("seat"),
							rs.getString("options"),
							rs.getString("oilType"),
							rs.getString("carName"),
							rs.getString("yearModel"),		
							rs.getString("kilometer"),		
							rs.getString("spotName"),
							rs.getString("babySeat")
						);
				
				getCars.add(getCar);
			}
		}finally{
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}		
		
			//
		return getCars;
	}
}