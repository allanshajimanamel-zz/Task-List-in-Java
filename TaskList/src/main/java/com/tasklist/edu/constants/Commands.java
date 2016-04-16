package com.tasklist.edu.constants;

/**
 * The enum class represents the available commands and their syntax.
 *
 * @author ALLAN
 */
public enum Commands {
	HELP("<help>"), ADD("<add> "), LIST("<list>"), TASKID("<taskid> "), DONE(
			"<done> "), QUIT("<quit>"), SAVE("<save>");

	/**
	 * Value associated with the command
	 */
	public String value;

	/**
	 * Constructor
	 *
	 * @param value
	 */
	private Commands(String value) {
		this.value = value;
	}
}
