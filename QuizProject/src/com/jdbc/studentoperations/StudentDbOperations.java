package com.jdbc.studentoperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.jdbc.studentclass.Student;
import com.jdbc.util.DBUtil;
import com.mysql.cj.protocol.x.SyncFlushDeflaterOutputStream;

public class StudentDbOperations {
	Scanner sc;
	Connection con = null;
	PreparedStatement pstmt = null;
	//Method to register the student
	public void registerStudent() {
		int i = 0;
		
		try {
			con = DBUtil.getCon();
			String query = "insert into student(sid,fname,lname,username,passwd,emailId,mobileNo)values(?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(query);
			sc = new Scanner(System.in);
			
			System.out.println("Enter your id:");
			int sid = sc.nextInt();
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

			Student s = new Student(sid,firstname, lastname, uname, passwd, email, mobileno);
			pstmt.setInt(1, s.getSid());
			pstmt.setString(2, s.getFname());
			pstmt.setString(3, s.getLname());
			pstmt.setString(4, s.getUsername());
			pstmt.setString(5, s.getPassword());
			pstmt.setString(6, s.getEmailId());
			pstmt.setLong(7, s.getMobileNo());

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
	public int studentLogin() {
		int studid=0;
		try {
			con = DBUtil.getCon();
			//query to add student login details 
			String query = "select * from student where username=? and passwd=?";
			pstmt = con.prepareStatement(query);
			
			Scanner scan = new Scanner(System.in);
			/*System.out.println("Enter your id:");
			int sid=scan.nextInt();*/
			System.out.println("Enter username:");
			String usname = scan.next();
			System.out.println("Enter password:");
			String passwrd = scan.next();
			
			Student s1 = new Student(usname,passwrd);
			
			//pstmt.setInt(1, s1.getSid());
			pstmt.setString(1, s1.getUsername());
			pstmt.setString(2, s1.getPassword());
			
			ResultSet result = pstmt.executeQuery();
			if(result.next()) {
				studid=result.getInt(1);
				System.out.println("Here..."+studid);
				String setStudId="insert into answer (sId)values(?)";
				pstmt=con.prepareStatement(setStudId);
				pstmt.setInt(1, studid);
				
				int i=pstmt.executeUpdate();
				System.out.println("Login successful..");
			}else {
				System.out.println("Login failed please check your username or password.");
			}
			
			/*while(result.next()) {
				int studid=result.getInt(1);
				System.out.println("Here..."+studid);
				String setStudId="insert into answer (sId)values(?)";
				pstmt=con.prepareStatement(setStudId);
				pstmt.setInt(1, studid);
				
				int i=pstmt.executeUpdate();
				
			}*/
			
			
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
		return studid;
		
	}
	// Methos to start the quiz....
	public void startQuiz(int id) {

		try {
			con = DBUtil.getCon();
			// query to fetch questions from database.
			String query = "select QuestionDescription,A,B,C,D from question;";
			pstmt = con.prepareStatement(query);

			ResultSet result = pstmt.executeQuery();
			int q=1;
			while (result.next()) {
				
				System.out.println(q+++")" + result.getString(1));
				System.out.println("A)"+result.getString(2));
				System.out.println("B)"+result.getString(3));
				System.out.println("C)"+result.getString(4));
				System.out.println("D)"+result.getString(5));
				
				System.out.println("Enter Your answer>>");
				sc=new Scanner(System.in);
				String answer=sc.next();
				//update answer set submitedAnswer=? where sId=?
				
				String ansQuery="insert into answer (submitedAnswer,sId)values(?,?);";
				//String ansQuery="insert into answer (submitedAnswer,sId)values(?) where sId=?";
				pstmt=con.prepareStatement(ansQuery);
				pstmt.setString(1, answer);
				pstmt.setInt(2, id);
				int i=pstmt.executeUpdate();
				if(i>0) {
					System.out.println("Your answer submitted sucessfully...");
				}
				else {
					System.out.println("Your answer not submitted sucessfully...");
				}
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
