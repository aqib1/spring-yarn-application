package com.example.yarnapplication.exceptions;

/**
 * @author Aqib_Javed
 *
 */
public class MapperInternalException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -475177059687180249L;

	public MapperInternalException(String message) {
		super(message);
	}

	public MapperInternalException(String message, Throwable e) {
		super(message, e);
	}

	public MapperInternalException(Throwable e) {
		this(e.getMessage(), e);
	}

}
