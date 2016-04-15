package com.tasklist.edu.main;

import com.tasklist.edu.constants.Commands;
import com.tasklist.edu.dataobject.CommandDO;

/**
 * @author ALLAN
 *
 */
public class CommandUtil {

	public static CommandDO getCommandData(String cmdString) {
		CommandDO command = null;
		if (cmdString == null) {
			return command;
		} else if (cmdString.regionMatches(0, Commands.ADD.value, 0, 6)
				&& !cmdString.substring(6).trim().isEmpty()) {
			command = new CommandDO();
			command.setCommands(Commands.ADD);
			command.setParam(cmdString.substring(6));
		} else if (cmdString.regionMatches(0, Commands.TASKID.value, 0, 9)
				&& !cmdString.substring(9).trim().isEmpty()) {
			command = new CommandDO();
			command.setCommands(Commands.TASKID);
			command.setParam(cmdString.substring(9));
		} else if (cmdString.regionMatches(0, Commands.DONE.value, 0, 7)
				&& !cmdString.substring(7).trim().isEmpty()) {
			command = new CommandDO();
			command.setCommands(Commands.DONE);
			command.setParam(cmdString.substring(7));
		} else if (cmdString.regionMatches(0, Commands.HELP.value, 0, 6)
				&& cmdString.length() == 6) {
			command = new CommandDO();
			command.setCommands(Commands.HELP);
		} else if (cmdString.regionMatches(0, Commands.LIST.value, 0, 6)
				&& cmdString.length() == 6) {
			command = new CommandDO();
			command.setCommands(Commands.LIST);
		} else if (cmdString.regionMatches(0, Commands.QUIT.value, 0, 6)
				&& cmdString.length() == 6) {
			command = new CommandDO();
			command.setCommands(Commands.QUIT);
		} else if (cmdString.regionMatches(0, Commands.SAVE.value, 0, 6)
				&& cmdString.length() == 6) {
			command = new CommandDO();
			command.setCommands(Commands.SAVE);
		}
		return command;
	}

}
