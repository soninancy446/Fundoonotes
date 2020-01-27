package com.bridgelabz.fundoonotes.exceptions;

import java.util.List;

import com.bridgelabz.fundoonotes.entity.Note;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class ErrorResponse{

private int status;
private String message;
List<Note> details;
private Object object;




public ErrorResponse(int status,String message,Object object){
	this.status = status;
	this.message=message;
	this.object = object;
}
public ErrorResponse(int status,String message) {
	this.status=status;
	this.message=message;
}

}

