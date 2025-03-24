package org.skypro.examApp.domain;

public class BadRequestException extends RuntimeException{
private int statusCode;

public BadRequestException(String message,int statusCode){
	super(message);
	this.statusCode=statusCode;
}

public int getStatusCode(){
	return statusCode;
}
}
