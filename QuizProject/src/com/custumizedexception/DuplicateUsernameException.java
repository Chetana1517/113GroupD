package com.custumizedexception;

public class DuplicateUsernameException extends Throwable {
	DuplicateUsernameException(String msg){
		super(msg);
	}
}
