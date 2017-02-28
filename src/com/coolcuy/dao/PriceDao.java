package com.coolcuy.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.coolcuy.dto.PriceDto;

public interface PriceDao extends GenericDao<PriceDto> {
	public int getCount(Connection conn) throws SQLException;

}
