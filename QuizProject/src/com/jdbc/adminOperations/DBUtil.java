package com.jdbc.adminOperations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	public static Connection getCon() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/QuizGame?characterEncoding=utf8";
			con=DriverManager.getConnection(url, "root", "Harana21@");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}		
		return con;	
	}

}
