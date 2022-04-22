package com.ty.endlessevent.exception;

public class NoIdFoundException extends RuntimeException {
	 
	private String message = "Given id is not present";

	public NoIdFoundException(String message) {
		
		this.message = message;
	}
	
	NoIdFoundException(){
		
	}
	
	@Override
	public String getMessage() {
		return message;
	}
	
	
}
