package com.bridgelabz.fundoonotes.response;

import java.io.Serializable;
import lombok.Data;


@Data
public class Response implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer status;
	
	private String response;
	
	private Object data;
//
//public Response(Integer status, String reponse)
//{   System.out.println(reponse);
//	//this.setStatus(status);
//	//this.setResponse(response);
//
//   this.status=status;
//	this.response=reponse;
//	}

public Response(Integer status)
{    
	this.setStatus(status);
	//this.setData(user);
	
	}
	
	public Response()
	{}

	public Response(Integer status, String response, Object data) {
//		this.setStatus(status);
//		this.setResponse(response);
//		this.setData(data);
		this.status=status;
		this.response=response;
		this.data=data;
	}
		
}