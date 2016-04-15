package com.tasklist.edu.dataobject;

import com.tasklist.edu.constants.Commands;

/**
 * @author ALLAN
 *
 */
public class CommandDO {
	private Commands commands;
	private String param;

	/**
	 * @return the commands
	 */
	public Commands getCommands() {
		return commands;
	}

	/**
	 * @param commands
	 *            the commands to set
	 */
	public void setCommands(Commands commands) {
		this.commands = commands;
	}

	/**
	 * @return the param
	 */
	public String getParam() {
		return param;
	}

	/**
	 * @param param
	 *            the param to set
	 */
	public void setParam(String param) {
		this.param = param;
	}
}
