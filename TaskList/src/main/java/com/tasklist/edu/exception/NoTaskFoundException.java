package com.tasklist.edu.exception;

import com.tasklist.edu.constants.ConsoleTexts;

/**
 * @author ALLAN
 *
 */
public class NoTaskFoundException extends Exception {

	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = 138956467354125568L;

	public NoTaskFoundException(String msg) {
		super(msg);
	}

	public NoTaskFoundException() {
		super(ConsoleTexts.INVALID_TASK_ID);
	}
}
