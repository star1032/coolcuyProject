package com.coolcuy.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.coolcuy.dto.QnADto;

public class QnADaoImpl{

	final String URL = "jdbc:oracle:thin:@192.168.0.56:1521:XE";
	final String USER = "yun";
	final String PASSWORD = "1111";
	
	public QnADaoImpl() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	public int add(QnADto object) {
		Connection conn = null;
		PreparedStatement pt = null;
		PreparedStatement pt2 = null;
		ResultSet rs=null;
		String sql= null;
		int x = -1;		
		try {
			sql="select max(num) from QnABOARD";
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pt = conn.prepareStatement(sql);
			rs= pt.executeQuery();
			int ref=1;
			if(rs.next()){
				ref= rs.getInt(1)+1;
			}
			sql="insert INTO QnABoard(NUM,NAME,TEXTAREA,SUBJECT,REF,POS,DEPTH,REGDATE,"
					+ "PASS,CNT) VALUES(QNA_SEQUENCE.NEXTVAL,?,?,?,?,"
					+ "0,0,SYSDATE,?,0)";
			pt2=conn.prepareStatement(sql);
			
			pt2.setString(1, object.getName()); 
			pt2.setString(2, object.getTextArea());
			pt2.setString(3, object.getSubject());
			pt2.setInt(4, ref);
			pt2.setString(5, object.getPass());
			x = pt2.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();			
		} finally {
			if (pt != null)
				try {
					pt.close();
				} catch (SQLException e) {
				}
			if (pt2 != null)
				try {
					pt2.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
		}

		return x;
	}

	public int delete(int element) {
		Connection conn = null;
		PreparedStatement pt = null;
		String quary = null;
		try{
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			quary= "DELETE FROM QNABOARD WHERE NUM=?";
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


	public int update(QnADto object) {
		Connection conn = null;
		PreparedStatement pt = null;
		String quary = null;
		
		try{
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			quary= "UPDATE QNABOARD SET NAME=?, SUBJECT=?, TEXTAREA=? WHERE NUM=?";
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

	public QnADto get(int element) {
		Connection conn = null;
		PreparedStatement pt = null;
		ResultSet rs= null;
		String quary = null;
		
		QnADto dto = new QnADto();
		
		try{
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			quary= "SELECT * FROM QNABOARD WHERE NUM=?";
			pt= conn.prepareStatement(quary);
			pt.setInt(1, element);
			rs=pt.executeQuery();
			if(rs.next()){
				dto.setNum(rs.getInt("num"));
				dto.setName(rs.getString("name"));
				dto.setSubject(rs.getString("subject"));
				dto.setTextArea(rs.getString("textArea"));
				dto.setPos(rs.getInt("pos"));
				dto.setRef(rs.getInt("ref"));
				dto.setDepth(rs.getInt("depth"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setPass(rs.getString("pass"));
				dto.setCnt(rs.getInt("cnt"));
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

	public List<QnADto> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getTotalCount(String keyField, String keyWord) {
		Connection conn = null;
		PreparedStatement pt = null;
		ResultSet rs= null;
		String quary = null;
		int totalCount=0;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			if(keyWord.equals("null")|| keyWord.equals("")){
				quary= "select count(num) from QNABoard";
				pt = conn.prepareStatement(quary);
			}else{
				quary="select count(num) from QNABoard where ? like %?%";
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

	public List<QnADto> getQnABoardList(String keyField, String keyWord, int start, int end) {
		Connection conn = null;
		PreparedStatement pt = null;
		ResultSet rs= null;
		String quary = null;
			List<QnADto> vlist= new ArrayList<QnADto>();
			try{
				conn = DriverManager.getConnection(URL, USER, PASSWORD);
				if(keyWord.equals("null")|| keyWord.equals("")){
					quary="select * from(select rownum rnum, a.num , a.name, a.subject, a.pos, a.ref, a.regdate, a.cnt from (select * from QNAboard order by ref desc) a ) where rnum BETWEEN ? and ?  ORDER BY ref desc, pos Asc";
						pt= conn.prepareStatement(quary);
						pt.setInt(1, start);
						pt.setInt(2, end);
				}else{
					quary= "select * from QNAboard WHERE "+ keyField +" like ? ORDER BY REF DESC, POS LIMIT ?, ? ";
						pt=conn.prepareStatement(quary);
						pt.setString(1, "%"+ keyWord + "%");
						pt.setInt(2, start);
						pt.setInt(3, end);
				}
				rs=pt.executeQuery();
				while(rs.next()){
					QnADto dto= new QnADto();
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

	public void upCount(int num) {
		Connection conn= null;
		PreparedStatement pt= null;
		String quary= null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			quary= "UPDATE QNABOARD SET CNT=CNT+1 WHERE NUM=?";
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

	public void reply(QnADto object) {
		Connection conn= null;
		PreparedStatement pt= null;
		String quary= null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			quary= "INSERT INTO QNABOARD(NUM,NAME,TEXTAREA,SUBJECT,REF,POS,DEPTH,REGDATE,PASS,CNT)"
					+ " VALUES(QNA_SEQUENCE.NEXTVAL,?,?,?,?,?,?,SYSDATE,?,0)";
			int depth= object.getDepth() +1;
			int pos= object.getPos()+1;
			pt= conn.prepareStatement(quary);
			pt.setString(1, object.getName());
			pt.setString(2, object.getTextArea());
			pt.setString(3, object.getSubject());
			pt.setInt(4, object.getRef());
			pt.setInt(5, pos);
			pt.setInt(6, depth);
			pt.setString(7, object.getPass());
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

	public void replyUp(int ref, int pos) {
		Connection conn= null;
		PreparedStatement pt= null;
		String quary= null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			quary="UPDATE QNABOARD SET POS = POS + 1 WHERE REF=? AND POS > ?";
			pt=conn.prepareStatement(quary);
			pt.setInt(1, ref);
			pt.setInt(2, pos);
			pt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
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
