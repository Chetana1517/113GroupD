package com.jdbc.studentoperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.jdbc.studentclass.Student;
import com.jdbc.util.DBUtil;

public class StudentDbOperations {
	Connection con = null;
	PreparedStatement pstmt = null;
	//Method to register the student
	public void registerStudent() {
		int i = 0;
		try {
			con = DBUtil.getCon();
			String query = "insert into student(fname,lname,username,passwd,emailId,mobileNo)values(?,?,?,?,?,?)";
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
	
	public void studentLogin() {
		
		try {
			con = DBUtil.getCon();
			//query to add student login details 
			String query = "select passwd from student where username=?";
			pstmt = con.prepareStatement(query);
			
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter username:");
			String usname = scan.next();
			System.out.println("Enter password:");
			String passwrd = scan.next();
			
			Student s1 = new Student(usname,passwrd);
			
			pstmt.setString(1, s1.getUsername());
			pstmt.setString(2, s1.getPassword());
			
			ResultSet result = pstmt.executeQuery();
			String pass = "";
			while (result.next()) {
				pass = result.getString(1);
			}
			
			if(passwrd.equals(pass)) {
				
				System.out.println("Login successfully done ");
			}
			else {
				System.out.println("You are not registered");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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

	// Methos to start the quiz....
	public void startQuiz() {

		try {
			con = DBUtil.getCon();
			// query to fetch questions from database.
			String query = "select QuestionDescription,A,B,C,D from question;";
			pstmt = con.prepareStatement(query);

			ResultSet result = pstmt.executeQuery();

			while (result.next()) {
				System.out.println("1)" + result.getString(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
