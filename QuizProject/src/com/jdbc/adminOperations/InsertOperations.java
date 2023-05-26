package com.jdbc.adminOperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;


public class InsertOperations {
	public static void main(String[] args) throws SQLException {
		
		Connection con = DBUtil.getCon();
		System.out.println("	This is Admin	");
		String query = "insert into Question values (?,?,?,?,?,?,?,?)";
		PreparedStatement pst = con.prepareStatement(query);
		System.out.println("How many records you want to insert?");
		Scanner sc = new Scanner(System.in);
		int noOfRecords = sc.nextInt();
		if (noOfRecords > 0) {
			for (int i = 0; i < noOfRecords; i++) {
				System.out.println("Enter Question Id>>");
				int QueId = sc.nextInt();
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
				String CorrectAnswer = sc.nextLine();
				System.out.println("Enter marks>>");
				int Marks = sc.nextInt();

				pst.setInt(1, QueId);
				pst.setString(2, QuestionDescription);
				pst.setString(3, A);
				pst.setString(4, B);
				pst.setString(5, C);
				pst.setString(6, D);
				pst.setString(7, CorrectAnswer);
				pst.setInt(8, Marks);

				pst.executeUpdate();
			}

			System.out.println("Records inserted successfully.");
		} else {
			System.out.println("Enter positive value");
		}
		pst.close();
		con.close();
	}

		
	}

