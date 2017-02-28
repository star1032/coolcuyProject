package com.coolcuy.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.coolcuy.dto.CarDto;

public interface CarDao extends GenericDao<CarDto> {
	public int getCount(Connection conn) throws SQLException;
	public int getCountBySpot(String spotName, Connection conn) throws SQLException;
	public List<CarDto> getAllBySpot(String spotName, Connection conn) throws SQLException;
	public List<String> getAllType(String spotName, Connection conn) throws SQLException;
}
