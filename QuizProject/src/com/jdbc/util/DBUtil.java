package com.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	public static Connection getCon() {
		Connection con = null;
		try {
			// load the Driver class
			Class.forName("com.mysql.cj.jdbc.Driver");

			// establish the connection
			String url = "jdbc:mysql://localhost:3306/javaquiz";
			con = DriverManager.getConnection(url, "root", "Mysql@root15");

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
}

