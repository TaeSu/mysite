package kr.co.saramin.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import kr.co.saramin.mysite.vo.GuestbookVo;

public class GuestbookDao {
	
	public boolean insert(GuestbookVo vo) {
		boolean result = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			//1. 드라이브 로딩
			Class.forName("com.mysql.jdbc.Driver");
			
			//2. Connection 하기
			String url = "jdbc:mysql://localhost:3306/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			//3. Statement 생성
			String sql = "insert into guestbook values (null, ?, now(), ?, password(?))";
			pstmt = conn.prepareStatement(sql);
			
			//4. binding
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getMessage());
			pstmt.setString(3, vo.getPassword());
			
			//5. SQL문 실행
			int updateCount = pstmt.executeUpdate();
			result = (updateCount > 0);
			System.out.println("insert " + updateCount + "개 성공");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public boolean delete(Long no, String password) {

		boolean result = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			//1. 드라이브 로딩
			Class.forName("com.mysql.jdbc.Driver");
			
			//2. Connection 하기
			String url = "jdbc:mysql://localhost:3306/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			//3. Statement 생성
			String sql = "delete from guestbook where no=? and password = password(?);";
			pstmt = conn.prepareStatement(sql);
			
			//4. binding
			pstmt.setLong(1, no);
			pstmt.setString(2, password);
			
			//5. SQL문 실행
			int updateCount = pstmt.executeUpdate();
			result = (updateCount > 0);
			System.out.println("delete " + updateCount + "개 성공");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public List<GuestbookVo> getList() {
		List<GuestbookVo> list = new ArrayList<GuestbookVo>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			//1. 드라이브 로딩
			Class.forName("com.mysql.jdbc.Driver");
			
			//2. Connection 하기
			String url = "jdbc:mysql://localhost:3306/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			//3. Statement 생성
			stmt = conn.createStatement();
			
			//4. SQL문 실행
			String sql =
					"select no, name, date_format(reg_date, '%Y-%m-%d'), message" + 
					"  from guestbook" + 
					" order by reg_date desc";
			rs = stmt.executeQuery(sql);
			
			//5. fetch row
			while (rs.next()) {
				Long no = rs.getLong("no");
				String name = rs.getString(2);
				Date regDate = rs.getDate(3);
				String message = rs.getString(4);
				
				GuestbookVo vo = new GuestbookVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setRegDate(regDate);
				vo.setMessage(message);
				list.add(vo);
			}			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return list;
	}
}
