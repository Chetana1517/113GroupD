package com.jdbc.adminOperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.jdbc.util.DBUtil;

public class AdminDbOperations {
	 // Method to add questions in question table of quizgame database. 
	Connection con=null;
	//instance block to establish the connection
    {
		 con = DBUtil.getCon();
	}
	public void addQuestion() {
		//Connection con = null;
		PreparedStatement pst = null;
		Scanner sc = null;
		try {
			//con = DBUtil.getCon();
			System.out.println("*****This is Admin*****");
			String query = "insert into Question(QuestionDescription,A,B,C,D,CorrectAnswer) values (?,?,?,?,?,?)";
			pst = con.prepareStatement(query);

			System.out.println("How many questions you want to add?");
			sc = new Scanner(System.in);
			int noOfRecords = sc.nextInt();

			if (noOfRecords > 0) {
				for (int i = 0; i < noOfRecords; i++) {
					sc.nextLine();
					System.out.println("Enter Questions>>");
					String QuestionDescription = sc.nextLine();
					System.out.println("Enter Option A>>");
					String A = sc.nextLine();
					System.out.println("Enter Option B>>");
					String B = sc.nextLine();
					System.out.println("Enter Option C>>");
					String C = sc.nextLine();
					System.out.println("Enter Option D>>");
					String D = sc.nextLine();
					System.out.println("Enter Answer>>");
					String CorrectAnswer = sc.next();

					pst.setString(1, QuestionDescription);
					pst.setString(2, A);
					pst.setString(3, B);
					pst.setString(4, C);
					pst.setString(5, D);
					pst.setString(6, CorrectAnswer);

					pst.executeUpdate();
					System.out.println("Question added");
				}
				System.out.println("Questions added to the database successfully.");

			} else {
				System.out.println("Please Enter positive value.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null || pst != null) {
					con.close();
					pst.close();
					// sc.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// Method to read all records of questions from question table of quizgame
	// database.
	public void displayQuestion() {
		//Connection con = DBUtil.getCon();
		String query = "select * from Question;";
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement(query);

			rs = pst.executeQuery();
			
			System.out.println("*****Question List*****");
			while (rs.next()) {
				System.out.println("QueId>>" + rs.getInt(1));
				System.out.println("QuestionDescription>>" + rs.getString(2));
				System.out.println("A>>" + rs.getString(3));
				System.out.println("B>>" + rs.getString(4));
				System.out.println("C>>" + rs.getString(5));
				System.out.println("D>>" + rs.getString(6));
				System.out.println("CorrectAnswer>>" + rs.getString(7));
				System.out.println();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (con != null || pst != null || rs != null) {
				try {
					rs.close();
					con.close();
					pst.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	//method to display result
	public void displayResult() {
		
		PreparedStatement pst=null;
		
		try {
			Scanner sc=new Scanner(System.in);
			//Connection con=DBUtil.getCon();
			String getQuery="select * from result where sId=?";
			pst=con.prepareStatement(getQuery);
			
			System.out.println("Enter the student id");
			int id=sc.nextInt();
			pst.setInt(1, id);
			
			ResultSet rs=pst.executeQuery();
			System.out.println("*****Quiz Result*****");
			System.out.println("Id\tFirstname\tLastname\tTotalMarks\tGrade");
			while(rs.next()) {
				System.out.print(rs.getInt(1)+"\t");
				System.out.print(rs.getString(2)+"\t");
				System.out.print("\t"+rs.getString(3)+"\t");
				System.out.print("\t"+rs.getInt(4)+"\t");
				System.out.println("\t"+rs.getString(5)+"\t");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
	
	//method to display record of all students.
	public void displayAllRecord() {
		PreparedStatement pst=null;
		
		try {
			Scanner sc=new Scanner(System.in);
			Connection con=DBUtil.getCon();
			String getQuery="select * from result order by totalMarks;";
			pst=con.prepareStatement(getQuery);
				
			ResultSet rs=pst.executeQuery();
			System.out.println("*****Quiz Result*****");
			System.out.println("Id\tFirstname\tLastname\tTotalMarks\tGrade");
			while(rs.next()) {
				System.out.print(rs.getInt(1)+"\t");
				System.out.print(rs.getString(2)+"\t");
				System.out.print("\t"+rs.getString(3));
				System.out.print("\t"+rs.getInt(4)+"\t");
				System.out.println("\t"+rs.getString(5)+"\t");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
