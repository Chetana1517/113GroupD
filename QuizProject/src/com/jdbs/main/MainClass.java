package com.jdbs.main;

import java.util.Scanner;

import com.jdbc.operations.DatabaseOperation;

public class MainClass {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int input;
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
						Menu.displayStudentMenu();
						System.out.println("Enter your choice:");
						choiceOfStudent=sc.nextInt();
					
						switch(choiceOfStudent) {
						case 1:
							System.out.println("Welcome to Student Registration");
							DatabaseOperation operation=new DatabaseOperation();
							operation.registerStudent();
							break;
						case 2:
							System.out.println("Welcome toStudent Login");
							break;
						case 3:
							System.out.println("Quiz is Started");
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
						Menu.displayAdminMenu();
						System.out.println("Please enter your choice:");
						choiceOfAdmin=sc.nextInt();
					
						switch(choiceOfAdmin) {
							case 1:
								System.out.println("1.Add Questions");
								break;
							case 2:
								System.out.println("2.Update Questions");
								break;
							case 3:
								System.out.println("3.Delete Questions");
								break;
							case 4:
								System.out.println("4.Display all Questions");
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
	}
}
