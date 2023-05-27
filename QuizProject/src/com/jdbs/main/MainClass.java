package com.jdbs.main;

import java.util.Scanner;

import com.jdbc.adminOperations.AdminDbOperations;
import com.jdbc.studentoperations.StudentDbOperations;

public class MainClass {
	//main method
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int input;
		int cont;
		int choice;
		int choiceOfStudent;
		int choiceOfAdmin;
		 int id = 0;
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
						
						Menu.displayStudentMenu(); //To display student menu
						System.out.println("Enter your choice:");
						choiceOfStudent=sc.nextInt();
					
						switch(choiceOfStudent) {
						case 1:
							System.out.println("Welcome to Student Registration");
							studOp.registerStudent();
							break;
						case 2:
							System.out.println("Welcome toStudent Login");
						    id=studOp.studentLogin();
							break;
						case 3:
							System.out.println("Quiz is Started");
							studOp.startQuiz(id);
							break;
						case 4:
							System.out.println("Quiz result");
							break;
						default:
							System.out.println("Enter the valid input...");
						}
						//------------------------------------------------------
						System.out.println("Do you want to continue?(1->continue/0->stop):");
						input = sc.nextInt();
						if (input == 0) {
							break;
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
					}while(input==1);
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
								adminOp.addQuestion();
								break;
							case 2:
								System.out.println("Update Questions");
								adminOp.updateQuestion();
								break;
							case 3:
								System.out.println("Delete Questions");
								adminOp.deleteQuestion();
								break;
							case 4:
								System.out.println("Display all Questions");
								adminOp.displayQuestion();
								break;
							case 5:
								System.out.println("Result of all students");
								break;
							case 6:
								System.out.println("Check score of student");
								break;
							case 7:
								System.out.println("exit");
							default:
								System.out.println("Enter the valid input...");
						}
						//--------------------------------------------
						System.out.println("Do you want to continue?(1->continue/0->stop):");
						cont = sc.nextInt();
						if (cont == 0) {
							break;
						} 
						else if (cont > 1) {
							System.out.println("Invalid input");

							while (cont!=1) {
								System.out.println("Please Enter 1 to continue or 0 to stop:");
								input = sc.nextInt();
								if (cont == 0) {
									break;
								}
							}
						}
					}while(cont==1);
					break;
			default:
					System.out.println("Please enter valid input..");
			}
			
			//-------------------------------------------------------------
			System.out.println("Do you want Student Login or Admin Login again?\n(1->continue/0->stop):");
			input = sc.nextInt();
			if (input == 0) {
				break;
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
		} while (input==1);
		System.out.println("Thank you!");
	}
}
