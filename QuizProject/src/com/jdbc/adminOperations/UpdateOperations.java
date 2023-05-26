package com.jdbc.adminOperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class UpdateOperations {

	public void updateRecord() {
		Connection con = null;
		PreparedStatement pstmt = null;
		int i = 0;
		try {
			con = DBUtil.getCon();
			String query = "update Question set Marks=?, QuestionDescription=? where QueId=?";
			pstmt = con.prepareStatement(query);
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter the count of records to you want to update:");
			int noOfRecords=sc.nextInt();
			if(noOfRecords>0) {
				for(int j=0;j<noOfRecords;j++) {
					
					pstmt.setInt(1, 0);
					pstmt.setInt(2, 1);
					pstmt.setInt(3, 2);

						
					i=pstmt.executeUpdate();
				}
				System.out.println(i+" record updated sucessfully...");
			}
			else{
				System.out.println("Enter the positive number");
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