package com.tasklist.edu.constants;

/**
 * @author ALLAN
 *
 */
public enum Commands {
	HELP("<help>"), ADD("<add> "), LIST("<list>"), TASKID("<taskid> "), DONE(
			"<done> "), QUIT("<quit>");

	public String value;

	private Commands(String value) {
		this.value = value;
	}
}
