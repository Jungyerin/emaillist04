package com.jx372.emaillist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jx372.emaillist.vo.EmailListVo;

@Repository
public class EmailListDao {

	private Connection getConnection() throws SQLException {

		Connection conn = null;

		try {
			// 1. 드라이버 로딩
			Class.forName("com.mysql.jdbc.Driver");

			// 2.connection 하기
			String url = "jdbc:mysql://localhost:3306/webdb?characterEncoding=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("sql driver를 찾을 수 없습니다.");
		}
		return conn;

	}

	public boolean insert(EmailListVo vo) {
		Connection conn = null;
		PreparedStatement pstmt=null;

		try {
			conn = getConnection();
			
			String sql="insert into emaillist values(null,?,?,?)";
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getFirstName());
			pstmt.setString(2, vo.getLastName());
			pstmt.setString(3, vo.getEmail());	
			
			int count = pstmt.executeUpdate();
			
			return count==1;
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				//생성된 것의 역순위로 자원정리
				if(pstmt==null){
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return false;
	}
	
	public List<EmailListVo> getList(){
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		List<EmailListVo> list=new ArrayList<EmailListVo>();
		
		try{
			conn=getConnection();
			
			stmt=conn.createStatement();
			
			String sql="select no,first_name,last_name,email from emaillist order by no desc";
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				Long no=rs.getLong(1);
				String firstName=rs.getString(2);
				String lastName=rs.getString(3);
				String email=rs.getString(4);
				
				EmailListVo vo=new EmailListVo();
				vo.setNo(no);
				vo.setFirstName(firstName);
				vo.setLastName(lastName);
				vo.setEmail(email);
				
				list.add(vo);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				if(rs!=null){
					rs.close();
				}
				if(stmt!=null){
					stmt.close();
				}
				if(conn!=null){
					conn.close();
				}
				
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		
		return list;
	}

}
