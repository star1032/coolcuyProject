package com.coolcuy.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.coolcuy.dto.SpotDto;
import com.coolcuy.dto.SpotNameDto;

public interface SpotDao extends GenericDao<SpotDto> {
	int getCount(Connection conn) throws SQLException;
	public List<SpotNameDto> getAllSpotName(Connection conn) throws SQLException;
}
