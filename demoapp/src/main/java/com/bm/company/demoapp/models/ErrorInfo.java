package com.bm.company.demoapp.models;

public class ErrorInfo {

	private String message;
	private String errorCode;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public ErrorInfo() {}
	
	public ErrorInfo( String message, String errorCode) {
		this.message= message;
		this.errorCode = errorCode;
	}
	
	public ErrorInfo( String message ) {
		this.message = message;
	}
	
	
	
}
