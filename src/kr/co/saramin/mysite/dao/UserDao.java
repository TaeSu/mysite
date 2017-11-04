package kr.co.saramin.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import kr.co.saramin.mysite.vo.UserVo;

public class UserDao {
	private Connection getConnection() throws ClassNotFoundException, SQLException {
		Connection conn = null;
		
		//1. 드라이브 로딩
		Class.forName("com.mysql.jdbc.Driver");

		//2. Connection 하기
		String url = "jdbc:mysql://localhost:3306/webdb";
		conn = DriverManager.getConnection(url, "webdb", "webdb");
		
		return conn;
	}

	public boolean insert(UserVo vo) {
		boolean result = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			//3. Statement 생성
			String sql = "insert into user values(null, ?, ?, password(?), ?)";
			pstmt = conn.prepareStatement(sql);
			
			//4. binding
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPassword());
			pstmt.setString(4, vo.getGender());
			
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
}
