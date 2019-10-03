package com.example.yarnapplication.exceptions;

/**
 * @author Aqib_Javed
 *
 */
public class InvalidRequestParamException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1321862138925631060L;

	public InvalidRequestParamException(String message) {
		super(message);
	}

	public InvalidRequestParamException(String message, Throwable e) {
		super(message, e);
	}

	public InvalidRequestParamException(Throwable e) {
		this(e.getMessage(), e);
	}

}
