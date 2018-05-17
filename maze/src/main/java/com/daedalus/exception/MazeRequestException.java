package com.daedalus.exception;

public class MazeRequestException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public MazeRequestException() {
		super();
	}

	public MazeRequestException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public MazeRequestException(String message, Throwable cause) {
		super(message, cause);
	}

	public MazeRequestException(String message) {
		super(message);
	}

	public MazeRequestException(Throwable cause) {
		super(cause);
	}
}
