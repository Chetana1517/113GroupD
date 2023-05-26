package com.jdbc.operations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.jdbc.studentclass.Student;
import com.jdbc.util.DBUtil;

public class DatabaseOperation {
	public void registerStudent() {
		Connection con = null;
		PreparedStatement pstmt = null;

		int i = 0;
		try {
			con = DBUtil.getCon();
			String query = "insert into student(fname,lname,username,password,emailId,mobileNo)values(?,?,?,?,?,?)";
			pstmt = con.prepareStatement(query);
			Scanner sc = new Scanner(System.in);

			System.out.println("Enter your firstname:");
			String firstname = sc.next();
			System.out.println("Enter your lastname:");
			String lastname = sc.next();
			System.out.println("Enter username:");
			String uname = sc.next();
			System.out.println("Enter password:");
			String passwd = sc.next();
			System.out.println("Enter your email Id:");
			String email = sc.next();
			System.out.println("Enter your mobile no:");
			Long mobileno = sc.nextLong();

			Student s = new Student(firstname, lastname, uname, passwd, email, mobileno);

			pstmt.setString(1, s.getFname());
			pstmt.setString(2, s.getLname());
			pstmt.setString(3, s.getUsername());
			pstmt.setString(4, s.getPassword());
			pstmt.setString(5, s.getEmailId());
			pstmt.setLong(6, s.getMobileNo());

			i = pstmt.executeUpdate();
			if (i > 0) {
				System.out.println("Registration Successfull...");
			} else {
				System.out.println("Registration Unsuccessfull...");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null || pstmt != null) {
					con.close();
					pstmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}