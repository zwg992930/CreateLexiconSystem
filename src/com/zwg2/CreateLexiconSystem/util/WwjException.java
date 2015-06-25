package com.zwg2.CreateLexiconSystem.util;

public class WwjException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String resultMsg;
    public WwjException(String resultMsg){
    	this.resultMsg=resultMsg;
    }
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return resultMsg;
	}
}
