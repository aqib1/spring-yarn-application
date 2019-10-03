package com.example.yarnapplication.exceptions;

public class InternalMalfunctionException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3236831051151433497L;

	public InternalMalfunctionException(String message) {
		super(message);
	}

	public InternalMalfunctionException(String message, Throwable e) {
		super(message, e);
	}

	public InternalMalfunctionException(Throwable e) {
		this(e.getMessage(), e);
	}
}
