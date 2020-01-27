package com.bridgelabz.fundoonotes.exceptions;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class UserExceptionController {

@ExceptionHandler(value = UserException.class)
public ResponseEntity<Object> exception(UserException exception) {
return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);

}

@ExceptionHandler(MethodArgumentNotValidException.class)
   public final ResponseEntity<ErrorResponse> handleConstraintViolation(
    MethodArgumentNotValidException ex, WebRequest request)
   {
    
    List<String> errors =ex.getBindingResult()
               .getFieldErrors()
               .stream()
               .map(x -> x.getDefaultMessage())
               .collect(Collectors.toList());
     
    ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), null, errors);
  return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
       

//    ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), errors);
//       return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
   }

}