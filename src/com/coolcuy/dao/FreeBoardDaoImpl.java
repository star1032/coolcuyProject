package com.coolcuy.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.coolcuy.dto.FreeBoardDto;

public class FreeBoardDaoImpl {
	final String URL = "jdbc:oracle:thin:@192.168.0.56:1521:XE";
	final String USER = "yun";
	final String PASSWORD = "1111";
	
	public FreeBoardDaoImpl() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	public int add(FreeBoardDto object) {
		Connection conn = null;
		PreparedStatement pt = null;
		PreparedStatement pt2 = null;
		ResultSet rs=null;
		String sql= null;
		int x = -1;		
		try {
			sql="select max(num) from FreeBoard";
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pt = conn.prepareStatement(sql);
			rs= pt.executeQuery();
			int ref=1;
			if(rs.next()){
				ref= rs.getInt(1)+1;
			}
			sql="insert INTO FreeBoard(NUM,NAME,TEXTAREA,SUBJECT,REF,POS,DEPTH,REGDATE,"
					+ "PASS,CNT,USERID) VALUES(Free_Sequence.NEXTVAL,?,?,?,?,"
					+ "0,0,SYSDATE,?,0,?)";
			pt2=conn.prepareStatement(sql);
			
			pt2.setString(1, object.getName()); 
			pt2.setString(2, object.getTextArea());
			pt2.setString(3, object.getSubject());
			pt2.setInt(4, ref);
			pt2.setString(5, object.getPass());
			pt2.setString(6, object.getUserId());
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
			quary= "DELETE FROM FreeBoard WHERE ref=?";
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
	public int deleteReply(int element) {
		Connection conn = null;
		PreparedStatement pt = null;
		String quary = null;
		try{
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			quary= "DELETE FROM FreeBoard WHERE num=?";
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


	public int update(FreeBoardDto object) {
		Connection conn = null;
		PreparedStatement pt = null;
		String quary = null;
		
		try{
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			quary= "UPDATE FreeBoard SET NAME=?, SUBJECT=?, TEXTAREA=? WHERE NUM=?";
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

	public FreeBoardDto get(int element) {
		Connection conn = null;
		PreparedStatement pt = null;
		ResultSet rs= null;
		String quary = null;
		
		FreeBoardDto dto = new FreeBoardDto();
		
		try{
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			quary= "SELECT * FROM FreeBoard WHERE NUM=?";
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
				dto.setUserId(rs.getString("userId"));
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

	public int getTotalCount(String keyField, String keyWord) {
		Connection conn = null;
		PreparedStatement pt = null;
		ResultSet rs= null;
		String quary = null;
		int totalCount=0;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			if(keyWord.equals("null")|| keyWord.equals("")){
				quary= "select count(num) from FreeBoard where pos=0";
				pt = conn.prepareStatement(quary);
			}else{
				quary="select count(num) from FreeBoard where ? like %?%";
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

	public List<FreeBoardDto> getFreeBoardList(String keyField, String keyWord, int start, int end) {
		Connection conn = null;
		PreparedStatement pt = null;
		ResultSet rs= null;
		String quary = null;
			List<FreeBoardDto> vlist= new ArrayList<FreeBoardDto>();
			try{
				conn = DriverManager.getConnection(URL, USER, PASSWORD);
				if(keyWord.equals("null")|| keyWord.equals("")){
					quary="select * from(select rownum rnum, a.num , a.name, a.subject, a.pos, a.ref, a.regdate, a.cnt, a.userid from (select * from FreeBoard order by ref desc) a where pos=0 ) where rnum BETWEEN ? and ?  ORDER BY ref desc, pos Asc";
						pt= conn.prepareStatement(quary);
						pt.setInt(1, start);
						pt.setInt(2, end);
				}else{
					quary= "select * from FreeBoard WHERE "+ keyField +" like ? ORDER BY REF DESC, POS LIMIT ?, ? ";
						pt=conn.prepareStatement(quary);
						pt.setString(1, "%"+ keyWord + "%");
						pt.setInt(2, start);
						pt.setInt(3, end);
				}
				rs=pt.executeQuery();
				while(rs.next()){
					FreeBoardDto dto= new FreeBoardDto();
					dto.setNum(rs.getInt("num"));
					dto.setName(rs.getString("name"));
					dto.setSubject(rs.getString("subject"));
					dto.setPos(rs.getInt("pos"));
					dto.setRegdate(rs.getString("regdate"));
					dto.setCnt(rs.getInt("cnt"));
					dto.setUserId(rs.getString("userId"));
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

	public List<FreeBoardDto> getRepleByRef(int ref){
		Connection conn = null;
		PreparedStatement pt = null;
		ResultSet rs= null;
		String quary = null;
		List<FreeBoardDto> vlist= new ArrayList<FreeBoardDto>();
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			quary="  select * From FreeBoard Where ref=? AND depth !=0 ORDER BY ref desc, pos asc ,depth asc , num asc";
			pt=conn.prepareStatement(quary);
			pt.setInt(1, ref);
			rs=pt.executeQuery();
			while(rs.next()){
				FreeBoardDto dto= new FreeBoardDto();
				dto.setNum(rs.getInt("num"));
				dto.setRef(rs.getInt("ref"));
				dto.setName(rs.getString("name"));
				dto.setSubject(rs.getString("subject"));
				dto.setPos(rs.getInt("pos"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setCnt(rs.getInt("cnt"));
				dto.setDepth(rs.getInt("depth"));
				dto.setTextArea(rs.getString("textarea"));
				dto.setUserId(rs.getString("userId"));
				vlist.add(dto);
			}
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
		
		return vlist;
	}

	
	
	public void upCount(int num) {
		Connection conn= null;
		PreparedStatement pt= null;
		String quary= null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			quary= "UPDATE FreeBoard SET CNT=CNT+1 WHERE NUM=?";
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

	public int reply(FreeBoardDto object) {
		Connection conn= null;
		PreparedStatement pt= null;
		String quary= null;
		int x=0;
		
		PreparedStatement getSeqPstmt = null;		
		String getSeqQuery = "SELECT FREE_SEQUENCE.CURRVAL as seq from freeBoard";
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			quary= "INSERT INTO FreeBoard(NUM,NAME,TEXTAREA,SUBJECT,REF,POS,DEPTH,REGDATE,PASS,CNT,UserId)"
					+ " VALUES(Free_Sequence.NEXTVAL,?,?,?,?,?,?,SYSDATE,?,0,?)";
			int depth= object.getDepth() +1;
			pt= conn.prepareStatement(quary);
			pt.setString(1, object.getName());
			pt.setString(2, object.getTextArea());
			pt.setString(3, object.getSubject());
			pt.setInt(4, object.getRef());
			pt.setInt(5, object.getPos());
			pt.setInt(6, depth);
			pt.setString(7, object.getPass());
			pt.setString(8, object.getUserId());
			pt.executeUpdate();
			
			getSeqPstmt = conn.prepareStatement(getSeqQuery);
			rs = getSeqPstmt.executeQuery();
			
			if(rs.next()){
				 x = rs.getInt("seq");
				System.out.println(x);
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
		return x;
		
	}
	public int[] rereply(FreeBoardDto object) {
		Connection conn= null;
		PreparedStatement pt= null;
		String quary= null;
		int x=0;
		
		PreparedStatement getSeqPstmt = null;		
		String getSeqQuery = "SELECT FREE_SEQUENCE.CURRVAL as seq from freeBoard";
		ResultSet rs = null;
		
		
		
		int[] depthNum={0,0};
		int depth=0;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			quary= "INSERT INTO FreeBoard(NUM,NAME,TEXTAREA,SUBJECT,REF,POS,DEPTH,REGDATE,PASS,CNT,UserId)"
					+ " VALUES(Free_Sequence.NEXTVAL,?,?,?,?,?,?,SYSDATE,?,0,?)";
		    depth= object.getDepth() +1;
			pt= conn.prepareStatement(quary);
			pt.setString(1, object.getName());
			pt.setString(2, object.getTextArea());
			pt.setString(3, object.getSubject());
			pt.setInt(4, object.getRef());
			pt.setInt(5, object.getPos());
			pt.setInt(6, depth);
			pt.setString(7, object.getPass());
			pt.setString(8, object.getUserId());
			pt.executeUpdate();
			
			getSeqPstmt = conn.prepareStatement(getSeqQuery);
			rs = getSeqPstmt.executeQuery();
			
			if(rs.next()){
				 x = rs.getInt("seq");
				System.out.println(x);
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
		depthNum[0]=depth;
		depthNum[1]=x;
		return depthNum;
				
	}

	public void replyUp(int num, int ref) {
		Connection conn= null;
		PreparedStatement pt= null;
		String quary= null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			quary="UPDATE FreeBoard SET POS = POS + 1 + ? WHERE num = ?";
			pt=conn.prepareStatement(quary);
			pt.setInt(1, ref);
			pt.setInt(2, num);
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
	public void depthOnlyUp(int ref, int pos, int depth) {
		Connection conn= null;
		PreparedStatement pt= null;
		String quary= null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			quary="UPDATE FreeBoard SET DEPTH = DEPTH + 1 WHERE REF = ? AND POS = ? AND DEPTH > ?";
			pt=conn.prepareStatement(quary);
			pt.setInt(1, ref);
			pt.setInt(2, pos);
			pt.setInt(3, depth);
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
	
	public void depthUp(int ref, int pos,int myDepth, int depth,int thisNum,int replyNum) {
		Connection conn= null;
		PreparedStatement pt= null;
		String quary= null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			quary="UPDATE FreeBoard SET DEPTH = DEPTH + 2 WHERE REF = ? AND DEPTH >= ? AND POS = ? AND DEPTH < ? AND NUM < ? AND NUM > ? ";
			pt=conn.prepareStatement(quary);
			pt.setInt(1, ref);
			pt.setInt(2, myDepth);
			pt.setInt(3, pos);
			pt.setInt(4, depth);
			pt.setInt(5, thisNum);
			pt.setInt(6, replyNum);
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
	
	
	public int maxPos(int ref){
		Connection conn= null;
		PreparedStatement getSeqPstmt = null;		
		String getSeqQuery = "select max(pos) as posNum from freeboard where ref=?";
		ResultSet rs = null;
		int x=0;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			getSeqPstmt=conn.prepareStatement(getSeqQuery);
			getSeqPstmt.setInt(1, ref);
			rs = getSeqPstmt.executeQuery();
			
			if(rs.next()){
				 x = rs.getInt("posNum");
				System.out.println(x);
			}
		
		
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (getSeqPstmt != null)
				try {
					getSeqPstmt.close();
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
	

	
	
}
