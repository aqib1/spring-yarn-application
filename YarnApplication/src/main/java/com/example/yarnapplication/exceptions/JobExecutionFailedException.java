package com.example.yarnapplication.exceptions;

/**
 * @author Aqib_Javed
 *
 */
public class JobExecutionFailedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1321862138925631060L;

	public JobExecutionFailedException(String message) {
		super(message);
	}

	public JobExecutionFailedException(String message, Throwable e) {
		super(message, e);
	}

	public JobExecutionFailedException(Throwable e) {
		this(e.getMessage(), e);
	}

}
