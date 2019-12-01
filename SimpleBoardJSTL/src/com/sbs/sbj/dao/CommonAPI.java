package com.sbs.sbj.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommonAPI {
	/*
	public static void main(String[] args) {
		try {
			getCon();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/
	
	public static Connection getCon() throws Exception {
		final String URL = "jdbc:mysql://192.168.1.4:3306/jsp2?serverTimezone=UTC";
		final String USER = "teacher";
		final String PW = "1234";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection con = DriverManager.getConnection(URL, USER, PW);
		System.out.println("DB 접속 성공!!");
		return con;
	}
	
	public static void close(Connection con, PreparedStatement ps) {
		if(ps != null) {
			try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		
		if(con != null) {
			try { con.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
	}
	
	public static void close(Connection con, PreparedStatement ps, ResultSet rs) {
		if(rs != null) {
			try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		
		close(con, ps);
	}
	
}







