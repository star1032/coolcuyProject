package com.coolcuy.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.coolcuy.dao.PriceDao;
import com.coolcuy.dao.PriceDaoImpl;
import com.coolcuy.dto.PriceDto;
import com.coolcuy.jdbc.connection.ConnectionProvider;
import com.coolcuy.util.JdbcUtil;

public class PriceListServiceImpl implements PriceListService {

	PriceDao dao = new PriceDaoImpl();
	
	@Override
	public int add(PriceDto object) {
		Connection conn = null;
		int x = -1;
		
		try{
			conn = ConnectionProvider.getConnection();
			x = dao.add(object, conn);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
		
		return x;
	}

	@Override
	public int delete(String element) {
		Connection conn = null;
		int x = -1;
		
		try{
			conn = ConnectionProvider.getConnection();
			x = dao.delete(element, conn);
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally{
			JdbcUtil.close(conn);
		}
		return x;
	}

	@Override
	public int deleteAll() {
		Connection conn = null;
		int x = -1;
		
		try{
			conn = ConnectionProvider.getConnection();
			x = dao.deleteAll(conn);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}

		return x;
	}

	@Override
	public PriceDto update(PriceDto object) {
		String carName = object.getCarName();
		
		Connection conn = null;
		PriceDto updatePrice = null;
		int x = -1;
		
		PriceDto getPrice = get(carName);
		
		try {
			conn = ConnectionProvider.getConnection();
			
			if(getPrice != null){
				x = dao.update(object, conn);
				
				if(x == 1)
					updatePrice = get(carName);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
		
		return updatePrice;
	}

	@Override
	public PriceDto get(String element) {
		Connection conn = null;
		PriceDto getPrice = null;
		
		try{
			conn = ConnectionProvider.getConnection();
			
			getPrice = dao.get(element, conn);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
		
		return getPrice;
	}

	@Override
	public List<PriceDto> getAll() {
		Connection conn = null;
		List<PriceDto> getPrices = null;
		
		try{
			conn = ConnectionProvider.getConnection();
			
			getPrices = dao.getAll(conn);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
		
		return getPrices;
	}

	@Override
	public int getCount() {
		Connection conn = null;
		
		int x = -1;
		
		try{
			conn = ConnectionProvider.getConnection();
			
			x = dao.getCount(conn);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally{
			JdbcUtil.close(conn);
		}
		
		return x;
	}

}
