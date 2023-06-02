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
	static boolean login=false;
	static boolean played=false;
	//instance block to establish the connection.
	{	
		con = DBUtil.getCon();
	}
	
	// Method to register the student
	public void registerStudent() {
		int i = 0;

		try {
			//con = DBUtil.getCon();
			String query = "insert into student(fname,lname,username,passwd,emailId,mobileNo)values(?,?,?,?,?,?)";
			pstmt = con.prepareStatement(query);
			sc = new Scanner(System.in);

			System.out.println("Enter your firstname:");
			String firstname = sc.next();
			System.out.println("Enter your lastname:");
			String lastname = sc.next();
			System.out.println("Enter username:");
			String uname = sc.next();
			System.out.println("Enter password:");
			String passwd = sc.next();
			System.out.println("Enter your email Id:");
			String email = sc.next().toLowerCase();
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
				e.printStackTrace();
			}
		}
	}

	// Method for student login
	public int studentLogin() {
		int studid = 0;
		try {
			//con = DBUtil.getCon();
			// query to add student login details
			String query = "select * from student where username=? and passwd=?";
			pstmt = con.prepareStatement(query);

			Scanner scan = new Scanner(System.in);
	
			System.out.println("Enter username:");
			String usname = scan.next();
			System.out.println("Enter password:");
			String passwrd = scan.next();

			Student s1 = new Student(usname, passwrd);

			pstmt.setString(1, s1.getUsername());
			pstmt.setString(2, s1.getPassword());

			ResultSet result = pstmt.executeQuery();
			if (result.next()) {
				studid = result.getInt(1);
				login=true;
				System.out.println("Login successful..");
			} else {
				System.out.println("Login failed please check your username or password.");
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
				e.printStackTrace();
			}
		}
		return studid;
	}

	// Method to start the quiz....
	public void startQuiz(int id) {
		char answer;
		try {
			
			if(login==true) {
			//con = DBUtil.getCon();
			//check if previously played quiz.
			String getStudId="select * from answer where sId=?;";
		
			pstmt = con.prepareStatement(getStudId);
			System.out.println("Student id>>"+id);
			pstmt.setInt(1, id);
			ResultSet studId = pstmt.executeQuery();
			
				if(studId.next()) {
					System.out.println("from answer table>>"+studId.getInt(1));
					System.out.println("You have already attempted the quiz.");
				}
				else {
					System.out.println("Welcome to Quiz");
					// query to fetch questions from database.
					String query = "select QueId,QuestionDescription,A,B,C,D from question order by rand();";
					pstmt = con.prepareStatement(query);

					ResultSet result = pstmt.executeQuery();
					int q = 1;
					int questId = 0;
					while (result.next()) {
						questId = result.getInt(1);
						System.out.println(q++ + ")" + result.getString(2));
						System.out.println("A)" + result.getString(3));
						System.out.println("B)" + result.getString(4));
						System.out.println("C)" + result.getString(5));
						System.out.println("D)" + result.getString(6));

						System.out.println("Enter Your answer>>");
						sc = new Scanner(System.in);
						//String s=sc.next();
					    answer = sc.next().charAt(0);
						if(answer>=65 && answer<=68) {
							String ans=String.valueOf(answer);
							String ansQuery = "insert into answer (sId,QueId,submitedAnswer)values(?,?,?);";
							
							pstmt = con.prepareStatement(ansQuery);
							pstmt.setInt(1, id);
							pstmt.setInt(2, questId);
							pstmt.setString(3, ans);
							int i = pstmt.executeUpdate();
							if (i > 0) {
								System.out.println("Your answer submitted sucessfully...");
								calculateMarks(questId,id);//method call to calculate the marks.
								
							} else {
								System.out.println("Your answer not submitted sucessfully...");
							}
						}
						else {
							while(!(answer>=65 && answer<=68)) {
								sc = new Scanner(System.in);
								System.out.println("Invalid input please enter (A/B/C/D)>>");
							    answer = sc.next().charAt(0);
							}
							String ans=String.valueOf(answer);
							String ansQuery = "insert into answer (sId,QueId,submitedAnswer)values(?,?,?);";
							
							pstmt = con.prepareStatement(ansQuery);
							pstmt.setInt(1, id);
							pstmt.setInt(2, questId);
							pstmt.setString(3, ans);
							int i = pstmt.executeUpdate();
							if (i > 0) {
								System.out.println("Your answer submitted sucessfully...");
								calculateMarks(questId,id);
								
							} else {
								System.out.println("Your answer not submitted sucessfully...");
							}
						}
					}
				}	
				played=true;
			}
			else {
				System.out.println("Oops..! It seems you have not logged in. \nPlease Login with your username and password to play the quiz.");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Method to calculate marks
	public void calculateMarks(int questId,int id) {

		try {
			String correctAns = "";
			String subAns = "";
			//con = DBUtil.getCon();
			String selectAnswer = "select CorrectAnswer from question where QueId=?";
			pstmt = con.prepareStatement(selectAnswer);
			pstmt.setInt(1, questId);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				correctAns = rs.getString(1);
				// System.out.println("we get from question>>" + correctAns);
			}

			String subAnswer = "select submitedAnswer from answer where QueId=? and sId=?;";
			pstmt = con.prepareStatement(subAnswer);
			pstmt.setInt(1, questId);
			pstmt.setInt(2, id);
			ResultSet rs1 = pstmt.executeQuery();

			if (rs1.next()) {
				subAns = rs1.getString(1);
				// System.out.println("we get from answer>>" + subAns);
			}

			if (correctAns.equals(subAns)) {
				String valueQuery = "update answer set sMarks=? where QueId=?;";
				pstmt = con.prepareStatement(valueQuery);
				pstmt.setInt(1, 1);
				pstmt.setInt(2, questId);
				pstmt.executeUpdate();
				// System.out.println("Marks stored in database.");
			} else {
				String valueQuery = "update answer set sMarks=? where QueId=?;";
				pstmt = con.prepareStatement(valueQuery);
				pstmt.setInt(1, 0);
				pstmt.setInt(2, questId);
				pstmt.executeUpdate();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
