package com.wildcodeschool.festivalorleansjoue.services;


public class AppExceptionService extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private String msg;
	
	public AppExceptionService(String msg) {
		
		this.msg = msg;
	}
	
	public String setErrorMsg(String msg) {
    	
        return this.msg;
    }

    public String getMsg() {
    	
        return this.msg;
    }	
}
