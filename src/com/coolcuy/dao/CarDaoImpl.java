package com.coolcuy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.coolcuy.dto.CarDto;
import com.coolcuy.util.JdbcUtil;

public class CarDaoImpl implements CarDao {

	public CarDaoImpl() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public int add(CarDto object, Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		String query = "INSERT INTO CAR (CARNUMBER, TIMEMONEY, TYPE, REGDATE, "
				+ "BRAND, SEAT, OPTIONS, OILTYPE, CARNAME, "
				+ "YEARMODEL, KILOMETER, SPOTNAME, BABYSEAT) "
				+ "VALUES(?,?,?,SYSDATE,?,?,?,?,?,?,?,?,?)";
		
//		CARCONDITION -> 1. 泥섎━ �셿猷�, 2. �젙鍮� �븘�슂�븿. 3. �룓李� Car�뀒�씠釉붽낵 Associate �븷 �뀒�씠釉� 異붽� �슂留�
		
		int x = -1;
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, object.getCarNumber()); 
			pstmt.setInt(2, object.getTimeMoney());
			pstmt.setString(3, object.getType());
			pstmt.setString(4, object.getBrand());
			pstmt.setString(5, object.getSeat());
			pstmt.setString(6, object.getOptions());
			pstmt.setString(7, object.getOilType());
			pstmt.setString(8, object.getCarName());
			pstmt.setString(9, object.getYearModel());
			pstmt.setString(10, object.getKilometer());
			pstmt.setString(11, object.getSpotName());
			pstmt.setString(12, object.getBabySeat());

			x = pstmt.executeUpdate();
			
		} finally {
			JdbcUtil.close(pstmt);
		}

		return x;
	}

		
	

	@Override
	public int delete(String element, Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		String quary = "DELETE FROM CAR WHERE CARNUMBER=?";

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
	public int deleteAll(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		String quary = "DELETE FROM CAR";
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
	public int update(CarDto object, Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		String quary = "UPDATE CAR SET BRAND=?, TIMEMONEY=?, TYPE=?, REGDATE=SYSDATE, SEAT=?, OPTIONS=?, OILTYPE=?, CARNAME=?, YEARMODEL=?, SPOTNAME=?, KILOMETER=?, BABYSEAT=?"
				+ " WHERE CARNUMBER=?";
		int x = -1;

		try {
			pstmt = conn.prepareStatement(quary);

			pstmt.setString(1, object.getBrand());
			pstmt.setInt(2, object.getTimeMoney());
			pstmt.setString(3, object.getType());
			pstmt.setString(4, object.getSeat());
			pstmt.setString(5, object.getOptions());
			pstmt.setString(6, object.getOilType());
			pstmt.setString(7, object.getCarName());
			pstmt.setString(8, object.getYearModel());
			pstmt.setString(9, object.getSpotName());
			pstmt.setString(10, object.getKilometer());
			pstmt.setString(11, object.getBabySeat());
			pstmt.setString(12, object.getCarNumber());

			x = pstmt.executeUpdate();
		}finally {
			JdbcUtil.close(pstmt);
		}

		return x;
		
	}

	@Override
	public CarDto get(String element, Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		
		String quary = "SELECT CARNUMBER, TIMEMONEY, TYPE, TO_CHAR(REGDATE, 'YYYY-MM-DD HH24:MI:SS') AS REGDATE, "
				+ " BRAND, SEAT, OPTIONS, OILTYPE, CARNAME, "
				+ " YEARMODEL, KILOMETER, SPOTNAME, BABYSEAT" 
				+ " FROM CAR WHERE CARNUMBER=?";
		
		CarDto car = null;
		
		try {
			pstmt = conn.prepareStatement(quary);

			pstmt.setString(1, element);
			
			rs=pstmt.executeQuery();
			
			car = new CarDto();
			
			if(rs.next()){
				car.setCarNumber(rs.getString("carNumber"));
				car.setTimeMoney(rs.getInt("timeMoney"));
				car.setType(rs.getString("type"));
				car.setRegDate(rs.getString("regDate"));
				car.setBrand(rs.getString("brand"));
				car.setSeat(rs.getString("seat"));
				car.setOptions(rs.getString("options"));
				car.setOilType(rs.getString("oilType"));
				car.setCarName(rs.getString("carName"));
				car.setYearModel(rs.getString("yearModel"));
				car.setKilometer(rs.getString("kilometer"));
				car.setSpotName(rs.getString("spotName"));
				car.setBabySeat(rs.getString("babySeat"));
				
			}
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}

		
		return car;
	}

	@Override
	public List<CarDto> getAll(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "SELECT CARNUMBER, TIMEMONEY, TYPE, TO_CHAR(REGDATE, 'YYYY-MM-DD HH24:MI:SS') AS REGDATE, "
				+ " BRAND, SEAT, OPTIONS, OILTYPE, CARNAME, "
				+ " YEARMODEL, KILOMETER, SPOTNAME, BABYSEAT"
				+ " FROM CAR"
				+ " ORDER BY CARNUMBER ASC";
		
		List<CarDto> cars = new ArrayList<CarDto>();
		
		try {
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				CarDto car = new CarDto(
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
				
				cars.add(car);
			}
			
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}

		return cars;
		
	}

	@Override
	public int getCount(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String quary = "SELECT COUNT(CARNUMBER) FROM CAR";

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
	public int getCountBySpot(String spotName, Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = -1;
		
		String query = "SELECT COUNT(*) FROM CAR WHERE SPOTNAME=?";
		
		try{
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, spotName);
			
			rs = pstmt.executeQuery();
			
			rs.next();
			count = rs.getInt(1);
		}finally{
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		
		return count;
	}

	@Override
	public List<CarDto> getAllBySpot(String spotName, Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CarDto> cars = new ArrayList<CarDto>();
		
		String query = "SELECT CARNUMBER, TIMEMONEY, TYPE, TO_CHAR(REGDATE, 'YYYY-MM-DD HH24:MI:SS') AS REGDATE, "
				+ " BRAND, SEAT, OPTIONS, OILTYPE, CARNAME, "
				+ " YEARMODEL, KILOMETER, SPOTNAME, BABYSEAT"
				+ " FROM CAR"
				+ " WHERE SPOTNAME=?"
				+ " ORDER BY CARNUMBER ASC";
		
		try{
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, spotName);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				CarDto car = new CarDto(
						rs.getString("CARNUMBER"),
						rs.getString("BRAND"),
						rs.getInt("TIMEMONEY"),
						rs.getString("TYPE"),
						rs.getString("REGDATE"),
						rs.getString("SEAT"),
						rs.getString("OPTIONS"),
						rs.getString("OILTYPE"),
						rs.getString("CARNAME"),
						rs.getString("YEARMODEL"),		
						rs.getString("KILOMETER"),		
						rs.getString("SPOTNAME"),
						rs.getString("BABYSEAT")
					);
				
				cars.add(car);
			}
		}finally{
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		
		return cars;
	}
	
	@Override
	public List<String> getAllType(String spotName, Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<String> getType = null;
		
		String query = "SELECT TYPE FROM CAR WHERE SPOTNAME=? GROUP BY TYPE";
		try{
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, spotName);
			
			rs = pstmt.executeQuery();
			getType = new ArrayList<String>();
			
			while(rs.next()){
				getType.add(rs.getString(1));
			}
		}finally{
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		
		return getType;
	}	
}
