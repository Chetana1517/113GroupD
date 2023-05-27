package com.jdbc.adminOperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class ReadRecords {
	public void readRecord() {
		Connection con=DBUtil.getCon();
		String query="select * from Question where QueId between 2 and 5";
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			pst=con.prepareStatement(query);
			
			rs=pst.executeQuery();
			System.out.println("Quize Records>>");
			while(rs.next()) {
				System.out.println("QueId>>"+rs.getInt(1));
				System.out.println("QuestionDescription>>"+rs.getString(2));
				System.out.println("A>>"+rs.getString(3));
				System.out.println("B>>"+rs.getString(4));
				System.out.println("C>>"+rs.getString(5));
				System.out.println("D>>"+rs.getString(6));
				System.out.println("CorrectAnswer>>"+rs.getString(7));
				System.out.println();
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (con != null || pst != null || rs!=null) {
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
}
