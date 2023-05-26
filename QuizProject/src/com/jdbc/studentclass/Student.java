package com.jdbc.studentclass;

public class Student {
	//private int id;
	private String fname;
	private String lname;
	private String username;
	private String password;
	private String emailId;
	private Long mobileNo;
	
	Student(){
		
	}
	public Student(String fname,String lname,String username,String password,String emailId,Long mobileNo){
		this.fname=fname;
		this.lname=lname;
		this.username=username;
		this.password=password;
		this.emailId=emailId;
		this.mobileNo=mobileNo;
	}
	
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Long getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(Long mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
}
