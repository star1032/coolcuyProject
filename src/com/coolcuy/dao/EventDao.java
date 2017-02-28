package com.coolcuy.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.coolcuy.dto.EventDto;

public interface EventDao extends GenericDao<EventDto> {
	public int updateImage(EventDto object, Connection conn) throws SQLException;
	public int getCount(Connection conn) throws SQLException;
	public List<EventDto> getByCondition(String startSpot, String startDate, String endDate, Connection conn) throws SQLException;

}
