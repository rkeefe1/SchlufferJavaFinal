package com.example.demo.messages;

public class ServiceErrorException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8775425115234502062L;

	public ServiceErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceErrorException(String message) {
        super(message);
    }

}