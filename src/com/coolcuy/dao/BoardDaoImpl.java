package com.coolcuy.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.coolcuy.dto.BoardDto;

public class BoardDaoImpl implements BoardDao{
	final String URL = "jdbc:oracle:thin:@192.168.0.56:1521:XE";
	final String USER = "yun";
	final String PASSWORD = "1111";

	public BoardDaoImpl() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public int add(BoardDto object) {
		Connection conn = null;
		PreparedStatement pt = null;
		String quary = "INSERT INTO BOARD VALUES(SEQ_NUMBER.NEXTVAL,?,?,?,0,SYSDATE,?,?,0)";

		int x = -1;		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			pt = conn.prepareStatement(quary);

			pt.setString(1, object.getName()); 
			pt.setString(2, object.getSubject());
			pt.setString(3, object.getTextArea());
			pt.setString(4, object.getPass());
			pt.setString(5, object.getIp());
			x = pt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();			
		} finally {
			if (pt != null)
				try {
					pt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}

		return x;
		
	}

	@Override
	public int delete(int element) {
		Connection conn = null;
		PreparedStatement pt = null;
		String quary = null;
		try{
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			quary= "DELETE FROM BOARD WHERE NUM=?";
			pt= conn.prepareStatement(quary);
			pt.setInt(1, element);
			
			pt.executeUpdate();
		
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (pt != null)
				try {
					pt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
		
		
		return 0;
	}

	@Override
	public int deleteAll() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(BoardDto object) {
		Connection conn = null;
		PreparedStatement pt = null;
		String quary = null;
		
		try{
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			quary= "UPDATE BOARD SET NAME=?, SUBJECT=?, TEXTAREA=? WHERE NUM=?";
			pt= conn.prepareStatement(quary);
			pt.setString(1, object.getName());
			pt.setString(2, object.getSubject());
			pt.setString(3, object.getTextArea());
			pt.setInt(4, object.getNum());
			pt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (pt != null)
				try {
					pt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
		
		
		return 0;
	}

	@Override
	public BoardDto get(int element) {
		Connection conn = null;
		PreparedStatement pt = null;
		ResultSet rs= null;
		String quary = null;
		
		BoardDto dto = new BoardDto();
		
		try{
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			quary= "SELECT * FROM BOARD WHERE NUM=?";
			pt= conn.prepareStatement(quary);
			pt.setInt(1, element);
			rs=pt.executeQuery();
			if(rs.next()){
				dto.setNum(rs.getInt("num"));
				dto.setName(rs.getString("name"));
				dto.setSubject(rs.getString("subject"));
				dto.setTextArea(rs.getString("textArea"));
				dto.setPos(rs.getInt("pos"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setPass(rs.getString("pass"));
				dto.setCnt(rs.getInt("cnt"));
				dto.setIp(rs.getString("ip"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (pt != null)
				try {
					pt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
		
		return dto;
	}

	@Override
	public List<BoardDto> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalCount(String keyField, String keyWord) {
		Connection conn = null;
		PreparedStatement pt = null;
		ResultSet rs= null;
		String quary = null;
		int totalCount=0;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			if(keyWord.equals("null")|| keyWord.equals("")){
				quary= "select count(num) from Board";
				pt = conn.prepareStatement(quary);
			}else{
				quary="select count(num) from Board where ? like %?%";
				pt = conn.prepareStatement(quary);
				pt.setString(1, keyField);
				pt.setString(2, keyWord);
			}
			rs = pt.executeQuery();			
			if(rs.next()){
				totalCount= rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();			
		} finally {
			if (pt != null)
				try {
					pt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}

		return totalCount;
		

		
	}

	@Override
	public List<BoardDto> getBoardList(String keyField, String keyWord, int start, int end) {
		Connection conn = null;
		PreparedStatement pt = null;
		ResultSet rs= null;
		String quary = null;
			List<BoardDto> vlist= new ArrayList<BoardDto>();
			try{
				conn = DriverManager.getConnection(URL, USER, PASSWORD);
				if(keyWord.equals("null")|| keyWord.equals("")){
					quary="select * from(select rownum rnum, a.num , a.name, a.subject, a.pos, a.regdate, a.cnt from (select * from board order by num desc) a ) where rnum BETWEEN ? and ? ";
						pt= conn.prepareStatement(quary);
						pt.setInt(1, start);
						pt.setInt(2, end);
				}else{
					quary= "select * from board";
						pt=conn.prepareStatement(quary);
						pt.setString(1, "%"+keyWord+ "%");
						pt.setInt(2, start);
						pt.setInt(3, end);
				}
				rs=pt.executeQuery();
				while(rs.next()){
					BoardDto dto= new BoardDto();
					dto.setNum(rs.getInt("num"));
					dto.setName(rs.getString("name"));
					dto.setSubject(rs.getString("subject"));
					dto.setPos(rs.getInt("pos"));
					dto.setRegdate(rs.getString("regdate"));
					dto.setCnt(rs.getInt("cnt"));
					vlist.add(dto);
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if (pt != null)
					try {
						pt.close();
					} catch (SQLException e) {
					}
				if (conn != null)
					try {
						conn.close();
					} catch (SQLException e) {
					}
			}
		
		return vlist;
		
	}

	@Override
	public void upCount(int num) {
		Connection conn= null;
		PreparedStatement pt= null;
		String quary= null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			quary= "UPDATE BOARD SET CNT=CNT+1 WHERE NUM=?";
			pt= conn.prepareStatement(quary);
			pt.setInt(1, num);
			pt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();			
		} finally {
			if (pt != null)
				try {
					pt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
	}

}
