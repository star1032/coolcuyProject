package com.coolcuy.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.coolcuy.dao.CarDao;
import com.coolcuy.dao.CarDaoImpl;
import com.coolcuy.dto.CarDto;
import com.coolcuy.exception.NotFoundSpotException;
import com.coolcuy.jdbc.connection.ConnectionProvider;
import com.coolcuy.util.JdbcUtil;

public class CarServiceImpl implements CarService{
	private CarDao dao = new CarDaoImpl();
	
	@Override
	public int add(CarDto object) {
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
	public CarDto update(CarDto object) {
		Connection conn = null;
		CarDto updatedCar = null;
		int x = -1;
		
		CarDto car = get(object.getCarNumber());
		
		if(car != null){
			try{
				conn = ConnectionProvider.getConnection();
				x = dao.update(object, conn);
				
				if(x == 1)
					updatedCar = get(object.getCarNumber());
				
			}catch(SQLException e){
				throw new RuntimeException(e);
			}finally{
				JdbcUtil.close(conn);
			}
		}else{
			throw new NotFoundSpotException();
		}
		
		return updatedCar;
	}

	@Override
	public CarDto get(String element) {
		Connection conn = null;
		CarDto car = null;
		
		try{
			conn = ConnectionProvider.getConnection();
			car = dao.get(element, conn);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}finally{
			JdbcUtil.close(conn);
		}
		
		return car;
	}

	@Override
	public List<CarDto> getAll() {
		Connection conn = null;
		List<CarDto> cars = null;
		
		try{
			conn = ConnectionProvider.getConnection();
			cars = dao.getAll(conn);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}finally{
			JdbcUtil.close(conn);
		}
		
		return cars;
	}

	@Override
	public List<CarDto> getAllBySpot(String spotName) {
		Connection conn = null;
		List<CarDto> cars = null;
		
		try{
			conn = ConnectionProvider.getConnection();
			cars = dao.getAllBySpot(spotName, conn);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}finally{
			JdbcUtil.close(conn);
		}
		
		return cars;
	}

	@Override
	public int getCountBySpot(String spotName) {
		Connection conn = null;
		int x = -1;
		
		try{
			conn = ConnectionProvider.getConnection();
			x = dao.getCountBySpot(spotName, conn);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}finally{
			JdbcUtil.close(conn);
		}
		
		return x;
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
	public List<String> getAllType(String spotName) {
		Connection conn = null;
		List<String> getType = null;
		
		try{
			conn = ConnectionProvider.getConnection();
			getType = dao.getAllType(spotName, conn);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}finally{
			JdbcUtil.close(conn);
		}
		
		return getType;
	}
	
	

}
