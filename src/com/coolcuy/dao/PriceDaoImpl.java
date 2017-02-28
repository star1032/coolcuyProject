package com.coolcuy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.coolcuy.dto.PriceDto;
import com.coolcuy.util.JdbcUtil;

public class PriceDaoImpl implements PriceDao {
	public PriceDaoImpl() {
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int add(PriceDto object, Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		String query = "INSERT INTO PRICELIST VALUES(?, ?, ?, ?, ?, ?, SYSDATE)";
		
		int x = -1;
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, object.getCarType());
			pstmt.setString(2, object.getCarName());
			pstmt.setInt(3, object.getStandardCharge());
			pstmt.setInt(4, object.getWeekDayCharge());
			pstmt.setInt(5, object.getWeekEndCharge());
			pstmt.setInt(6, object.getPeakSeasonCharge());
			
			x = pstmt.executeUpdate();
			
			conn.commit();
		} finally {
			JdbcUtil.close(pstmt);
		}
		
		return x;
	}

	@Override
	public int delete(String element, Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		String query = "DELETE FROM PRICELIST WHERE CARNAME=?";
		
		int x = -1;
		
		try{
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, element);
			x = pstmt.executeUpdate();
			
		}finally {
			JdbcUtil.close(pstmt);
		}
		return x;
	}

	@Override
	public int deleteAll(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		String query = "DELETE PRICELIST";
		int x = -1;
		
		try{
			pstmt = conn.prepareStatement(query);
			
			x = pstmt.executeUpdate();
		}finally {
			JdbcUtil.close(pstmt);
		}
		
		return x;
		
	}

	@Override
	public int update(PriceDto object, Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		int x = -1;
		
		String query = "UPDATE PRICELIST SET CARTYPE=?, STANDARDCHARGE=?, WEEKDAYCHARGE=?"
				+ ", WEEKENDCHARGE=?, PEAKSEASONCHARGE=? WHERE CARNAME=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, object.getCarType());
			pstmt.setInt(2, object.getStandardCharge());
			pstmt.setInt(3, object.getWeekDayCharge());
			pstmt.setInt(4, object.getWeekEndCharge());
			pstmt.setInt(5, object.getPeakSeasonCharge());
			
			x = pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
				
		return x;
	}

	@Override
	public PriceDto get(String element, Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "SELECT CARTYPE, CARNAME, STANDARDCHARGE,"
				+ " WEEKDAYCHARGE, WEEKENDCHARGE, PEAKSEASONCHARGE, TO_CHAR(REGDATE, 'YYYY-MM-DD')AS REGDATE"
				+ " FROM PRICELIST "
				+ " WHERE CARNAME=?";
		
		PriceDto price = null;
		
		try { 
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, element);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				price = new PriceDto(
					   rs.getString("carType"),
					   rs.getString("carName"),
					   rs.getInt("standardCharge"),
					   rs.getInt("weekDayCharge"),
					   rs.getInt("weekEndCharge"),
					   rs.getInt("peakSeasonCharge"),
					   rs.getString("regDate")
					   );
			}

		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		
		return price;
	}
	
//	public static void main(String[] args) throws SQLException {
//		PriceListDao dao = new PriceListDaoImpl();
//		List<PriceListDto> prices = dao.getAll(ConnectionProvider.getConnection());
//		System.out.println(prices.get(0).getCarName());
//	}
//	
	@Override
	public List<PriceDto> getAll(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		String query = "SELECT CARTYPE, CARNAME, STANDARDCHARGE, WEEKDAYCHARGE,"
				+ " WEEKENDCHARGE, PEAKSEASONCHARGE, TO_CHAR(REGDATE, 'YYYY-MM-DD')AS REGDATE"
				+ " FROM PRICELIST"
				+ " ORDER BY REGDATE ASC";
		
		List<PriceDto> prices = null;
		
		try{
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			prices = new ArrayList<PriceDto>();
			
			while(rs.next()){
				
				
				PriceDto price = new PriceDto(
						rs.getString("carType"),
						rs.getString("carName"),
						rs.getInt("standardCharge"),
						rs.getInt("weekDayCharge"),
						rs.getInt("weekEndCharge"),
						rs.getInt("peakSeasonCharge"),
						rs.getString("regDate")
						);

				prices.add(price);
			}
			
		}finally {
				JdbcUtil.close(pstmt);
			    JdbcUtil.close(rs);
			}
		
		return prices;
	}

	@Override
	public int getCount(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "SELECT COUNT(CARNAME) FROM PRICELIST";
		
		int count = -1;
		
		try{
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
