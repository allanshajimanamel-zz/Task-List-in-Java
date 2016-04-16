package com.tasklist.edu.constants;

/**
 * The class hold all the constant messages to be shown on the console.
 *
 * @author ALLAN
 *
 */
public class ConsoleTexts {
	/**
	 * Constant for welcome message.
	 */
	public static final String WELCOME_MESSAGE = "Task list application started.\nPlease use <help> command to view list of commands available.";

	/**
	 * Constant for the input symbol.
	 */
	public static final String ENTER_COMMAND_SYMBOL = "INPUT: ";

	/**
	 * Constant for the output symbol.
	 */
	public static final String OUTPUT = "OUTPUT: ";

	/**
	 * Constant for error message to be shown when acommand fails to execute.
	 */
	public static final String CONSOLE_ERROR = "ERROR: Could not execute command";

	/**
	 * Constant for error message to be shown when an unknown command is
	 * entered.
	 */
	public static final String UNKOWN_COMMAND = "ERROR: unknown command";

	/**
	 * The content to be shown for help.
	 */
	public static final String HELP = "The following commands are availabe:\n"
			+ "1. help\n"
			+ "Lists out the available commands for the application.\n"
			+ "Usage: <help>\n" + "2. add\n"
			+ "Adds a new task to the to-do list\n"
			+ "Usage: <add> <task detail>\n" + "3. list\n"
			+ "Lists out the to-do list\n" + "Usage: <list>\n" + "4. taskid\n"
			+ "Lists the provided task whose id is provided.\n"
			+ "Usage: <taskid> <id of task>\n" + "5. done\n"
			+ "Removes the task whoes id is provided from the to-do list.\n"
			+ "Usage: <done> <id of task>\n" + "6. save\n"
			+ "Saves the data.\n" + "Usage: <save>\n" + "7. quit\n"
			+ "Quits the application.\n" + "Usage: <quit>";

	/**
	 * Message to be shown when there are no To-Do's.
	 */
	public static final String EMPTY_LIST = "There are no to-do items";

	/**
	 * Constant for error message to be shown when application failed to start.
	 */
	public static final String APP_ERROR = "ERROR: Could not open application";

	/**
	 * Constant for error message to be shown when an empty todo task is
	 * entered.
	 */
	public static final String EMPTY_TODO = "ERROR: Empty to-do not allowed";

	/**
	 * Constant for error message to be shown when an invalid task id is
	 * entered.
	 */
	public static final String INVALID_TASK_ID = "ERROR: Invalid task id entered";

	/**
	 * Constant for error message to be shown on failure to save data to file.
	 */
	public static final String ERROR_SAVE_FAIL = "ERROR: Failed to save data";

	/**
	 * Constant for message to be shown when a to-do task is successfully added
	 * to the list.
	 */
	public static final String TASK_ADDED = "Task added with id ";

	/**
	 * Constant for message to be shown when the to-do task list is successfully
	 * saved.
	 */
	public static final String SAVE_SUCCESS = "Data saved successfully";
}
