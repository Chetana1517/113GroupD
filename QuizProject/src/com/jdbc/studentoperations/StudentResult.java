package com.jdbc.studentoperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.jdbc.util.DBUtil;

public class StudentResult {
	StudentDbOperations stud=null;
	// method to calculate total marks
	public void getStudentResult(int id) {
		PreparedStatement pst=null;
		 stud=new StudentDbOperations();
		int totalMarks=0;
		try {
			if(stud.login || stud.played) {
				Connection con=DBUtil.getCon();
				String query="select sum(sMarks) from answer where sId=?;";
				pst=con.prepareStatement(query);
				pst.setInt(1, id);
				ResultSet rs=pst.executeQuery();
				if(rs.next()) {
					totalMarks=rs.getInt(1);
					//System.out.println("Total marks>>"+totalMarks);
					setStudentResult(id,totalMarks);
				}
			}else {
				System.out.println("Oops..! It seems you have not logged in. "
				+ "\nPlease Login with your username and password to see the result.");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	// method to set records of result into result table.
	public void setStudentResult(int id, int totalMarks) {
		PreparedStatement pst = null;
		stud=new StudentDbOperations();
		try {
			if (stud.login || stud.played) {
				String grade = "";
				Connection con = DBUtil.getCon();

				String query = "insert into result(sId,fname,lname,totalMarks,grade) values(?,(select student.fname from student where student.sid=?),(select student.lname from student where student.sid=?),?,?);";
				pst = con.prepareStatement(query);
				pst.setInt(1, id);
				pst.setInt(2, id);
				pst.setInt(3, id);
				pst.setInt(4, totalMarks);

				if (totalMarks >= 8 && totalMarks <= 10) {
					grade = "A";
				} else if (totalMarks >= 6 && totalMarks <= 8) {
					grade = "B";
				} else if (totalMarks >= 4 && totalMarks <= 6) {
					grade = "C";
				} else {
					grade = "Fail";
				}

				pst.setString(5, grade);
				pst.executeUpdate();

				System.out.println("Result saved to result table.");

				showResult(id);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// method to get records of result from result table.
	public void showResult(int id) {
		stud=new StudentDbOperations();
		PreparedStatement pst = null;

		try {
			if (stud.login || stud.played) {
				Connection con = DBUtil.getCon();
				String getQuery = "select * from result where sId=?";
				pst = con.prepareStatement(getQuery);
				// System.out.println("student id:"+id);
				pst.setInt(1, id);

				ResultSet rs = pst.executeQuery();
				System.out.println("*****Quiz Result*****");
				System.out.println("Id\tFirstname\tLastname\t TotalMarks\tGrade");
				while (rs.next()) {
					System.out.print(rs.getInt(1) + "\t");
					System.out.print(rs.getString(2) + "\t");
					System.out.print("\t" + rs.getString(3) + "\t");
					System.out.print("\t" + rs.getInt(4) + "\t");
					System.out.println("\t" + rs.getString(5) + "\t");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
