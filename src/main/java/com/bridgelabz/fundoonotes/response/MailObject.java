package com.bridgelabz.fundoonotes.response;

import java.io.Serializable;


import org.springframework.stereotype.Component;

import lombok.Data;

import lombok.ToString;

@Data
@ToString
@Component
public class MailObject implements Serializable {

private static final long serialVersionUID = 1L;
String email;
String subject;
String message;


}
