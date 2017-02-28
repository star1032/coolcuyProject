package com.coolcuy.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.coolcuy.dto.CarDto;
import com.coolcuy.dto.RentDto;

public interface RentDao extends TransGenericDao<RentDto>{
	public List<RentDto> getAllByEmail(String email, Connection conn)throws SQLException;
	public List<RentDto> getAllByStartSpot(String spot, Connection conn)throws SQLException;
	public List<RentDto> getAllByEndSpot(String spot, Connection conn)throws SQLException; 
	public List<RentDto> getAllByCar(String carNumber, Connection conn)throws SQLException;
	public List<RentDto> getAll(Connection conn)throws SQLException;
	public List<CarDto> getRentAbleCar(String startSpot, String startDate, String endDate, String type, Connection conn) throws SQLException;
}