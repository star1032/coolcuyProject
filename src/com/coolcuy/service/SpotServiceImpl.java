package com.coolcuy.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.coolcuy.dao.SpotDao;
import com.coolcuy.dao.SpotDaoImpl;
import com.coolcuy.dto.SpotDto;
import com.coolcuy.dto.SpotNameDto;
import com.coolcuy.exception.NotFoundSpotException;
import com.coolcuy.jdbc.connection.ConnectionProvider;
import com.coolcuy.util.JdbcUtil;

public class SpotServiceImpl implements SpotService{
	private SpotDao dao = new SpotDaoImpl();
	
	public SpotServiceImpl() {}

	@Override
	public int add(SpotDto object) {
		Connection conn = null;
		int x = -1;
		try {
			conn = ConnectionProvider.getConnection();
			x = dao.add(object, conn);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally{
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
		}catch(SQLException e){
			throw new RuntimeException(e);
		}finally{
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
		}catch(SQLException e){
			throw new RuntimeException(e);
		}finally{
			JdbcUtil.close(conn);
		}
		
		return x;
	}
	
	@Override
	public SpotDto update(SpotDto object) {
		Connection conn = null;
		SpotDto updatedSpot = null;
		int x = -1;
		
		SpotDto spot = get(object.getSpotName());
		
		if(spot != null){
			try{
				conn = ConnectionProvider.getConnection();
				x = dao.update(object, conn);
				
				if(x == 1)
					updatedSpot = get(object.getSpotName());
				
			}catch(SQLException e){
				throw new RuntimeException(e);
			}finally{
				JdbcUtil.close(conn);
			}
		}else{
			throw new NotFoundSpotException();
		}
		
		return updatedSpot;
		
	}

	@Override
	public SpotDto get(String element) {
		Connection conn = null;
		SpotDto spot = null;
		
		try{
			conn = ConnectionProvider.getConnection();
			spot = dao.get(element, conn);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}finally{
			JdbcUtil.close(conn);
		}
		
		return spot;
	}

	@Override
	public List<SpotDto> getAll() {
		Connection conn = null;
		List<SpotDto> spots = null;
		
		try{
			conn = ConnectionProvider.getConnection();
			spots = dao.getAll(conn);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}finally{
			JdbcUtil.close(conn);
		}
		
		return spots;
	}

	@Override
	public int getCount() {
		Connection conn = null;
		int x = -1;
		
		try{
			conn = ConnectionProvider.getConnection();
			x = dao.getCount(conn);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}finally{
			JdbcUtil.close(conn);
		}
		
		return x;
	}
	
	@Override
	public List<SpotNameDto> getAllSpotName() {
		Connection conn = null;
		try{
			conn = ConnectionProvider.getConnection();
			
			return dao.getAllSpotName(conn);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}finally{
			JdbcUtil.close(conn);
		}
	}
}