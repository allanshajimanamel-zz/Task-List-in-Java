package com.tasklist.edu.dataobject;

import com.tasklist.edu.constants.Commands;
import com.tasklist.edu.main.CommandUtil;
import com.tasklist.edu.main.TaskListMain;

/**
 * The class is a POJO data object class to transfer the deciphered command and
 * param between the {@link CommandUtil} and {@link TaskListMain}
 *
 * @author ALLAN
 *
 */
public class CommandDO {

	/**
	 * The command.
	 */
	private Commands commands;

	/**
	 * The command parameter
	 */
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
