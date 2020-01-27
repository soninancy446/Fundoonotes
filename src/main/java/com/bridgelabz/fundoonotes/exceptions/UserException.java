package com.bridgelabz.fundoonotes.exceptions;

public class UserException extends RuntimeException{
private static final long serialVersionUID = 1L;
private String message;


@Override
public String getMessage() {
return message;
}
public void setMessage(String message) {
this.message = message;
}

public UserException(String message) {
super(message);
this.message=message;

}


}
