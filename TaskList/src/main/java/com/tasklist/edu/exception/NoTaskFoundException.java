package com.tasklist.edu.exception;

import com.tasklist.edu.constants.ConsoleTexts;

/**
 * The exception is thrown if we enter an invalid task id as parameter to
 * command.
 *
 * @author ALLAN
 *
 */
public class NoTaskFoundException extends Exception {

	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = 138956467354125568L;

	/**
	 * Constructor
	 *
	 * @param msg
	 *            The message associated with the exception.
	 */
	public NoTaskFoundException(String msg) {
		super(msg);
	}

	/**
	 * Constructor
	 */
	public NoTaskFoundException() {
		super(ConsoleTexts.INVALID_TASK_ID);
	}
}
