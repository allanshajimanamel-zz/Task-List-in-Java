package com.tasklist.edu.constants;

/**
 * @author ALLAN
 *
 */
public class ConsoleTexts {
	public static final String WELCOME_MESSAGE = "Task list application started.\nPlease use help command to view list of commands available.";

	public static final String ENTER_COMMAND_SYMBOL = "INPUT: ";

	public static final String OUTPUT = "OUTPUT: ";

	public static final String CONSOLE_ERROR = "ERROR: Could not execute command";

	public static final String UNKOWN_COMMAND = "ERROR: unknown command";

	public static final String HELP = "The following commands are availabe:\n"
			+ "1. help\n"
			+ "Lists out the available commands for the application.\n"
			+ "Usage: <help>\n" + "2. add\n"
			+ "Adds a new task to the to-do list\n" + "Usage: <add> <id of task>\n"
			+ "3. list\n" + "Lists out the to-do list\n" + "Usage: <list>\n"
			+ "4. taskid\n" + "Lists the provided task whose id is provided.\n"
			+ "Usage: <taskid> <id of task>\n" + "5. done\n"
			+ "Removes the task whoes id is provided from the to-do list.\n"
			+ "Usage: <done> <id of task>\n" + "6. quit\n"
			+ "Quits the application.\n" + "Usage: <quit>";

	public static final String EMPTY_LIST = "There are no to-do items";

	public static final String APP_ERROR = "ERROR: Could not open application";

	public static final String EMPTY_TODO = "ERROR: Empty to-do not allowed";

	public static final String INVALID_TASK_ID = "ERROR: Invalid task id entered";

	public static final String ERROR_SAVE_FAIL = "ERROR: Failed to save data";

	public static final String ERROR_ADD_FAIL = "ERROR: Failed to add to-do to list";

	public static final String ERROR_LIST_FAIL = "ERROR: Failed to load to-do list";

	public static final String ERROR_TASKID_FAIL = "ERROR: Failed to load to-do task";

	public static final String ERROR_DONE_FAIL = "ERROR: Failed to mark task as done";

	public static final String TASK_ADDED = "Task added with id ";
}
