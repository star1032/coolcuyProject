package com.coolcuy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.coolcuy.dto.EventDto;
import com.coolcuy.jdbc.connection.ConnectionProvider;
import com.coolcuy.util.JdbcUtil;

public class EventDaoImpl implements EventDao {

	public EventDaoImpl() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public int add(EventDto object, Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		String query = "INSERT INTO EVENT VALUES ('#EV'||LPAD(SEQ_EVENT.NEXTVAL, 5, 0),"
				+ " ?, TO_DATE(?, 'YYYY-MM-DD'),"
				+ " TO_DATE(?, 'YYYY-MM-DD'),"
				+ " TO_DATE(?, 'YYYY-MM-DD'),"
				+ " ?, ?, ?, ?, ?, ?,"
				+ " SYSDATE)";
		
		int x = -1;
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, object.getEventName());
			pstmt.setDate(2, object.getEventNoticeDay());
			pstmt.setDate(3, object.getEventStart());
			pstmt.setDate(4, object.getEventEnd());
			pstmt.setString(5, object.getAvailableSpot());
			pstmt.setString(6, object.getSaleName());
			pstmt.setString(7, object.getRestriction());
			pstmt.setString(8, object.getLimitType());
			pstmt.setString(9, object.getNote());
			pstmt.setString(10, object.getImageName());
			
			x = pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
		
		return x;
	}

	@Override
	public int delete(String element, Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		String query = "DELETE FROM EVENT WHERE EVENTNUMBER = ?";
		
		int x = -1 ;

		try {
			pstmt = conn.prepareStatement(query);
			
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
		String query = "DELETE EVENT";
		
		int x = -1;
		try{
			pstmt = conn.prepareStatement(query);
			
			x = pstmt.executeUpdate();
		} finally{
			JdbcUtil.close(pstmt);
		}
		
		return x;
	}

	@Override
	public int update(EventDto object, Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		int x = -1;
		
		String query = "UPDATE EVENT SET EVENTNAME=?,"
				+ " EVENTNOTICEDAY=TO_DATE(?, 'YYYY-MM-DD'),"
				+ " EVENTSTART=TO_DATE(?, 'YYYY-MM-DD'),"
				+ " EVENTEND=TO_DATE(?, 'YYYY-MM-DD'),"
				+ " AVAILABLESPOT=?, SALENAME=?,"
				+ " RESTRICTION=?, LIMITTYPE=?, NOTE=?, IMAGENAME=?"
				+ " WHERE EVENTNUMBER=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, object.getEventName());
			pstmt.setDate(2, object.getEventNoticeDay());
			pstmt.setDate(3, object.getEventStart());
			pstmt.setDate(4, object.getEventEnd());
			pstmt.setString(5, object.getAvailableSpot());
			pstmt.setString(6, object.getSaleName());
			pstmt.setString(7, object.getRestriction());
			pstmt.setString(8, object.getLimitType());
			pstmt.setString(9, object.getNote());
			pstmt.setString(10, object.getImageName());
			pstmt.setString(11, object.getEventNumber());
			
			x = pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
		
		return x;
	}

	@Override
	public EventDto get(String element, Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query ="SELECT EVENTNUMBER, EVENTNAME,"
				+ " TO_CHAR(EVENTNOTICEDAY, 'YYYY-MM-DD') AS EVENTNOTICEDAY,"
				+ " TO_CHAR(EVENTSTART, 'YYYY-MM-DD') AS EVENTSTART,"
				+ " TO_CHAR(EVENTEND, 'YYYY-MM-DD') AS EVENTEND,"
				+ " AVAILABLESPOT, SALENAME, RESTRICTION,"
				+ " LIMITTYPE, NOTE, IMAGENAME,"
				+ " TO_CHAR(REGDATE, 'YYYY-MM-DD HH24:MI') AS REGDATE"
				+ " FROM EVENT WHERE EVENTNUMBER=?";
		
		EventDto event = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, element);
			
			rs=pstmt.executeQuery();
			
			event = new EventDto();
			
			if(rs.next()){
				event.setEventNumber(rs.getString("eventNumber"));
				event.setEventName(rs.getString("eventName"));
				event.setEventNoticeDay(rs.getDate("eventNoticeDay"));
				event.setEventStart(rs.getDate("eventStart"));
				event.setEventEnd(rs.getDate("eventEnd"));
				event.setAvailableSpot(rs.getString("availableSpot"));
				event.setSaleName(rs.getString("saleName"));
				event.setRestriction(rs.getString("restriction"));
				event.setLimitType(rs.getString("limitType"));
				event.setNote(rs.getString("note"));
				event.setImageName(rs.getString("imageName"));
				event.setRegDate(rs.getString("regDate"));
			}
			
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
				
		return event;
	}

	@Override
	public List<EventDto> getAll(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "SELECT EVENTNUMBER, EVENTNAME,"
				+ " TO_CHAR(EVENTNOTICEDAY, 'YYYY-MM-DD') AS EVENTNOTICEDAY,"
				+ " TO_CHAR(EVENTSTART, 'YYYY-MM-DD') AS EVENTSTART,"
				+ " TO_CHAR(EVENTEND, 'YYYY-MM-DD') AS EVENTEND,"
				+ " AVAILABLESPOT, SALENAME, RESTRICTION, LIMITTYPE, NOTE, IMAGENAME,"
				+ " TO_CHAR(REGDATE, 'YYYY-MM-DD HH24:MI') AS REGDATE"
				+ " FROM EVENT ORDER BY EVENTNUMBER ASC";
		
		List<EventDto> events = new ArrayList<EventDto>();
		
		try{
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				EventDto event = new EventDto(
						rs.getString("eventNumber"),
						rs.getString("eventName"),
						rs.getDate("eventNoticeDay"),
						rs.getDate("eventStart"),
						rs.getDate("eventEnd"),
						rs.getString("availableSpot"),
						rs.getString("saleName"),
						rs.getString("restriction"),
						rs.getString("limitType"),
						rs.getString("note"),
						rs.getString("imageName"),
						rs.getString("regDate")
						);
				events.add(event);
				
			}
					
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		
		return events;
	}

	@Override
	public int updateImage(EventDto object, Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		
		String query="UPDATE EVENT SET IMAGENAME=?"
				+ " WHERE EVENTNUMBER=?";
		
		int x = -1;
		
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, object.getImageName());
			pstmt.setString(2, object.getEventNumber());
					
		} finally {
			JdbcUtil.close(pstmt);
		}
		
		return x;
	}

	@Override
	public int getCount(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "SELECT COUNT(EVENTNUMBER) FROM EVENT";
		
		int count = -1;
		try {
			
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(query);
			
			rs = pstmt.executeQuery();
			
			if(rs.next())
				count = rs.getInt(1);
			
			
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		
		return count;
	}
	
	@Override
	public List<EventDto> getByCondition(String startSpot, String startDate, String endDate, Connection conn) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
				
		String query = "SELECT * FROM EVENT WHERE AVAILABLESPOT='전지점' OR AVAILABLESPOT=? "
				+ "AND TO_DATE(?, 'YYYY-MM-DD') >= EVENTSTART "
				+ "AND TO_DATE(?, 'YYYY-MM-DD') <= EVENTEND";
		
		List<EventDto> list = new ArrayList<EventDto>();
		
		try {
			conn = ConnectionProvider.getConnection();
			
			System.out.println(startSpot);
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, startSpot);
			pstmt.setString(2, startDate.substring(0, startDate.length()-4));
			pstmt.setString(3, endDate.substring(0, startDate.length()-4));
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				EventDto event = new EventDto(
						rs.getString("eventNumber"),
						rs.getString("eventName"),
						rs.getDate("eventNoticeDay"),
						rs.getDate("eventStart"),
						rs.getDate("eventEnd"),
						rs.getString("availableSpot"),
						rs.getString("saleName"),
						rs.getString("restriction"),
						rs.getString("note"),
						rs.getString("imageName"),
						rs.getString("limitType"),
						rs.getString("regDate")
						);

				System.out.println(event.getLimitType());
				list.add(event);				
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		
		return list;
	}

}
