package com.coolcuy.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.coolcuy.dao.EventDao;
import com.coolcuy.dao.EventDaoImpl;
import com.coolcuy.dto.EventDto;
import com.coolcuy.jdbc.connection.ConnectionProvider;
import com.coolcuy.util.JdbcUtil;

public class EventServiceImpl implements EventService{
	
	EventDao dao = new EventDaoImpl();

	@Override
	public int add(EventDto object) {
		Connection conn = null;
		int x = -1;
		
		try {
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
		
		try {
			conn = ConnectionProvider.getConnection();
			x = dao.delete(element, conn);
			
		} catch (SQLException e) {
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
		
		return x;
	}

	@Override
	public int deleteAll() {
		Connection conn = null;
		int x = -1;
		
		try {
			conn = ConnectionProvider.getConnection();
			x = dao.deleteAll(conn);
		} catch (SQLException e) {
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
		
		return x;
	}

	@Override
	public EventDto update(EventDto object) {
		String eventNumber = object.getEventNumber();
		
		Connection conn = null;
		EventDto updateEvent = null;
		int x = -1;
		
		EventDto getEvent = get(eventNumber);
		
		try {
			conn = ConnectionProvider.getConnection();
			
			if(getEvent != null){
				x = dao.update(object, conn);
				
				if(x == 1)
					updateEvent = get(eventNumber);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
		
		return updateEvent;
	}

	@Override
	public EventDto get(String element) {
		Connection conn = null;
		EventDto getEvent = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			
			getEvent = dao.get(element, conn);
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
		
		return getEvent;
	}

	@Override
	public List<EventDto> getAll() {
		Connection conn = null;
		List<EventDto> getEvents = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			getEvents = dao.getAll(conn);
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
		
		return getEvents;
	}

	@Override
	public EventDto updateImage(EventDto object) {
		String eventNumber = object.getEventNumber();
		
		Connection conn = null;
		EventDto updateImage = null;
		int x = -1;
		
		EventDto getEvent = get(eventNumber);
		
		try {
			conn = ConnectionProvider.getConnection();
			
			if(getEvent != null){
				x = dao.updateImage(object, conn);
				
				if(x == 1)
					updateImage = get(eventNumber);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
		
		return updateImage;
	}

	@Override
	public int getCount() {
		Connection conn = null;
		
		int x = -1;
		
		try {
			conn = ConnectionProvider.getConnection();
			x = dao.getCount(conn);
			
		} catch (SQLException e) {
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
		
		return x;
	}

}
