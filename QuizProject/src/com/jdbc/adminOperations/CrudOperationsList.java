package com.jdbc.adminOperations;



import java.util.Scanner;


public class CrudOperationsList {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		
		int x;
		do {
		System.out.println("****** ADMIN CRUD OPERATIONS******");
		
		System.out.println("1.Insert");
		System.out.println("2.Update");
		System.out.println("3.Delete");
		System.out.println("4.Select/Read");
		System.out.println("5.Exit");
		
		System.out.println("Enter your choice:");
		
		int choice=sc.nextInt();
		
		switch(choice) {
		case 1: InsertOperations insert=new InsertOperations();
			    insert.insertRecord();
			    break;
		case 2: UpdateOperations update=new UpdateOperations();
				update.updateRecord();
				break;
		case 3: DeleteOperations delete=new DeleteOperations();
				delete.deleteRecord();
				break;
		case 4: ReadRecords read=new ReadRecords();
				read.readRecord();
				break;
		case 5:
				break;
		default:
			System.out.println("Please enter valid input!");
		}
		System.out.println("do you want to continue?1:0");
		x = sc.nextInt();
		}while(x!=0);
		
	System.out.println("Thank You");	
		
	}
	
}
