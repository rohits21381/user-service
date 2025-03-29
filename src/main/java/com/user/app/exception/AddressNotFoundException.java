package com.user.app.exception;

public class AddressNotFoundException extends RuntimeException{
	
	public AddressNotFoundException(String message) {
		super(message);
	}
}
