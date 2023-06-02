package com.jdbs.main;

import java.util.Scanner;

import com.jdbc.adminOperations.AdminDbOperations;
import com.jdbc.studentoperations.StudentDbOperations;
import com.jdbc.studentoperations.StudentResult;

public class MainClass {
	static int id;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int input,in;
		int choice;
		int choiceOfStudent;
		int choiceOfAdmin;
		
		do {
			System.out.println("*****Welcome to Java Quiz*****");
			System.out.println("1.Student");
			System.out.println("2.Admin");
			System.out.println("Enter who you are:");
			choice = sc.nextInt();
			switch (choice) {
			case 1:
					do {
						StudentDbOperations studOp=new StudentDbOperations();
						
						Menu.displayStudentMenu(); //To display Student menu.
						System.out.println("Enter your choice:");
						choiceOfStudent=sc.nextInt();
					
						switch(choiceOfStudent) {
						case 1:
							System.out.println("Welcome to Student Registration");
							studOp.registerStudent();//Method call for student registration.
							break;
						case 2:
							System.out.println("Welcome to Student Login");
						    id=studOp.studentLogin();//Method call for student login.
							break;
						case 3:
							//method call to start the quiz.
							studOp.startQuiz(id);
							break;
						case 4:
							//method call to show the result to the student.
							StudentResult sresult=new StudentResult();
							sresult.getStudentResult(id);
							break;
						default:
							System.out.println("Enter the valid input...");
						}
						//------------------------------------------------------
						System.out.println("Do you want to continue?(1->continue/0->stop):");
						input = sc.nextInt();
						in=continueOrNot(input);//method call for continue or stop.
					}while(in==1);
					break;
					
				case 2:
					do {
						AdminDbOperations adminOp=new AdminDbOperations();
						
						Menu.displayAdminMenu();// To display Admin menu
						System.out.println("Please enter your choice:");
						choiceOfAdmin=sc.nextInt();
					
						switch(choiceOfAdmin) {
							case 1:
								System.out.println("Add Questions");
								adminOp.addQuestion();//Method call to add questions to db.
								break;
							
							case 2:
								System.out.println("Display all Questions");
								adminOp.displayQuestion();//To display questions from db.
								break;
							case 3:
								System.out.println("Result of all students");
								adminOp.displayAllRecord();//TO display all records in ascending order.
								break;
							case 4:
								System.out.println("Check score of particular student");
								adminOp.displayResult();//To display result of particular student.
								break;
							default:
								System.out.println("Enter the valid input...");
						}
						//--------------------------------------------
						System.out.println("Do you want to continue?(1->continue/0->stop):");
						input = sc.nextInt();
						in=continueOrNot(input);//method call for continue or stop.
					}while(in==1);
					break;
			default:
					System.out.println("Please enter valid input..");
			}
			//-------------------------------------------------------------
			System.out.println("Do you want Student Login or Admin Login again?\n(1->continue/0->stop):");
			input = sc.nextInt();
			in=continueOrNot(input);//method call for continue or stop.
		} while (in==1);
		System.out.println("Thank you!");
	}
	
	
	public static int continueOrNot(int input){
		Scanner sc=new Scanner(System.in);
		if (input == 0) {
			return input;
		} 
		else if (input > 1) {
			System.out.println("Invalid input");

			while (input!=1) {
				System.out.println("Please Enter 1 to continue or 0 to stop:");
				input = sc.nextInt();
				if (input == 0) {
					break;
				}
			}
		}
		return input;
	}
}
