package com.jdbc.adminOperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import com.jdbc.util.DBUtil;

public class InsertQuestion {

	public void addQuestion() {
		Connection con = null;
		PreparedStatement pst = null;
		Scanner sc=null;
		try {
			con = DBUtil.getCon();
			System.out.println("	This is Admin	");
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
					
				}
				System.out.println("Questions added to the database successfully.");
				
			} else {
				System.out.println("Please Enter positive value.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (con != null || pst != null) {
					con.close();
					pst.close();
					//sc.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
