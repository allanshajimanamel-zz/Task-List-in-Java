package com.tasklist.edu.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import com.tasklist.edu.constants.ConsoleTexts;
import com.tasklist.edu.dataobject.CommandDO;
import com.tasklist.edu.dataobject.Todo;
import com.tasklist.edu.dataobject.TodoList;
import com.tasklist.edu.exception.DBConnectionException;
import com.tasklist.edu.exception.NoTaskFoundException;

/**
 * This is the main class of the application. it contains the main method from
 * where the execution starts.
 *
 * @author ALLAN
 *
 */
public class TaskListMain {

	/**
	 * The main method, which is the starting point of the application.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		new TaskListMain().start();
	}

	/**
	 * The method is called controls the processing in the application.
	 */
	private void start() {
		boolean loadSuccess = false;
		BufferedReader bufferRead = null;
		try {
			// setup the database.
			loadSuccess = TaskListDAO.setupDB();
			System.out.println(ConsoleTexts.WELCOME_MESSAGE);
			// Infinite loop which is broken out of when the user enters the
			// quit command.
			cmdloop: while (loadSuccess) {
				System.out.print(ConsoleTexts.ENTER_COMMAND_SYMBOL);
				bufferRead = new BufferedReader(
						new InputStreamReader(System.in));
				try {
					// read the user command input.
					String cmdString = bufferRead.readLine();
					// get the command details.
					CommandDO commandDO = CommandUtil.getCommandData(cmdString);
					if (commandDO != null) {
						// perform task related to the input command.
						switch (commandDO.getCommands()) {
						case HELP:
							printHelp();
							break;
						case ADD:
							try {
								addToList(commandDO.getParam());
							} catch (InstantiationException e) {
								System.out.println(ConsoleTexts.ERROR_ADD_FAIL);
							} catch (IllegalAccessException e) {
								System.out.println(ConsoleTexts.ERROR_ADD_FAIL);
							} catch (ClassNotFoundException e) {
								System.out.println(ConsoleTexts.ERROR_ADD_FAIL);
							} catch (SQLException e) {
								System.out.println(ConsoleTexts.ERROR_ADD_FAIL);
							} catch (DBConnectionException e) {
								System.out.println(ConsoleTexts.ERROR_ADD_FAIL);
							}
							break;
						case LIST:
							try {
								printList();
							} catch (InstantiationException e) {
								System.out
								.println(ConsoleTexts.ERROR_LIST_FAIL);
							} catch (IllegalAccessException e) {
								System.out
								.println(ConsoleTexts.ERROR_LIST_FAIL);
							} catch (ClassNotFoundException e) {
								System.out
								.println(ConsoleTexts.ERROR_LIST_FAIL);
							} catch (SQLException e) {
								System.out
								.println(ConsoleTexts.ERROR_LIST_FAIL);
							} catch (DBConnectionException e) {
								System.out
								.println(ConsoleTexts.ERROR_LIST_FAIL);
							}
							break;
						case TASKID:
							try {
								printTask(commandDO.getParam());
							} catch (NumberFormatException e) {
								System.out
								.println(ConsoleTexts.INVALID_TASK_ID);
							} catch (InstantiationException e) {
								System.out
								.println(ConsoleTexts.ERROR_TASKID_FAIL);
							} catch (IllegalAccessException e) {
								System.out
								.println(ConsoleTexts.ERROR_TASKID_FAIL);
							} catch (ClassNotFoundException e) {
								System.out
								.println(ConsoleTexts.ERROR_TASKID_FAIL);
							} catch (SQLException e) {
								System.out
								.println(ConsoleTexts.ERROR_TASKID_FAIL);
							} catch (DBConnectionException e) {
								System.out
								.println(ConsoleTexts.ERROR_TASKID_FAIL);
							}
							break;
						case DONE:
							try {
								taskDone(commandDO.getParam());
							} catch (NumberFormatException e) {
								System.out
								.println(ConsoleTexts.INVALID_TASK_ID);
							} catch (NoTaskFoundException e) {
								System.out
								.println(ConsoleTexts.INVALID_TASK_ID);
							} catch (InstantiationException e) {
								System.out
								.println(ConsoleTexts.ERROR_DONE_FAIL);
							} catch (IllegalAccessException e) {
								System.out
								.println(ConsoleTexts.ERROR_DONE_FAIL);
							} catch (ClassNotFoundException e) {
								System.out
								.println(ConsoleTexts.ERROR_DONE_FAIL);
							} catch (SQLException e) {
								System.out
								.println(ConsoleTexts.ERROR_DONE_FAIL);
							} catch (DBConnectionException e) {
								System.out
								.println(ConsoleTexts.ERROR_DONE_FAIL);
							}
							break;
						case QUIT:
							break cmdloop;
						default:
							System.out.println(ConsoleTexts.UNKOWN_COMMAND);
							break;
						}
					} else {
						System.out.println(ConsoleTexts.UNKOWN_COMMAND);
					}
				} catch (IOException e) {
					System.out.println(ConsoleTexts.CONSOLE_ERROR);
				} catch (IllegalArgumentException e) {
					// Have to catch this runtime exception as conversion of
					// input string to the enum will throw this error if the
					// input String cannot be converted to the enum constant.
					// This results from invalid input. This is done so as to
					// not break the application on known runtime exception.
					System.out.println(ConsoleTexts.UNKOWN_COMMAND);
				} catch (IndexOutOfBoundsException e) {
					// Can be thrown when checking for command parameters.
					System.out.println(ConsoleTexts.UNKOWN_COMMAND);
				}
			}
		} catch (InstantiationException e1) {
			System.out.println(ConsoleTexts.APP_ERROR);
		} catch (IllegalAccessException e1) {
			System.out.println(ConsoleTexts.APP_ERROR);
		} catch (ClassNotFoundException e1) {
			System.out.println(ConsoleTexts.APP_ERROR);
		} catch (SQLException e1) {
			System.out.println(ConsoleTexts.APP_ERROR);
		} catch (DBConnectionException e1) {
			System.out.println(ConsoleTexts.APP_ERROR);
		} finally {
			if (bufferRead != null) {
				try {
					bufferRead.close();
				} catch (IOException e) {
					// Intentionally left blank.
				}
			}
		}
	}

	/**
	 * The method prints out the contents for the help command.
	 */
	public void printHelp() {
		System.out.println(ConsoleTexts.HELP);
	}

	/**
	 * The method adds the provided to-do task to the list.
	 *
	 * @param param
	 *            the data for the to-do task.
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws DBConnectionException
	 */
	public void addToList(String param) throws InstantiationException,
	IllegalAccessException, ClassNotFoundException, SQLException,
	DBConnectionException {
		System.out.print(ConsoleTexts.OUTPUT);
		if (param.isEmpty()) {
			System.out.print(ConsoleTexts.EMPTY_TODO);
		} else {
			TaskListDAO dao = new TaskListDAO();
			dao.addToDo(param);
			System.out.println(ConsoleTexts.TASK_ADDED + dao.getCount());
		}
	}

	/**
	 * The method prints the to-do tasks in the list.
	 *
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws DBConnectionException
	 */
	public void printList() throws InstantiationException,
	IllegalAccessException, ClassNotFoundException, SQLException,
	DBConnectionException {
		TodoList list = new TaskListDAO().getToDoList();
		System.out.println(ConsoleTexts.OUTPUT);
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				System.out.println((i + 1) + ". " + list.get(i).getTodo());
			}
		} else {
			System.out.println(ConsoleTexts.EMPTY_LIST);
		}
	}

	/**
	 * The method prints the to-do task whoes position in the list is given.
	 *
	 * @param param
	 *            position of the to-do task in list
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws DBConnectionException
	 */
	public void printTask(String param) throws InstantiationException,
	IllegalAccessException, ClassNotFoundException, SQLException,
	DBConnectionException {
		Integer id = Integer.valueOf(param);
		Todo todo = new TaskListDAO().getTask(id);
		if (todo != null) {
			System.out.println("Task: " + todo.getTodo());
		} else {
			System.out.println(ConsoleTexts.INVALID_TASK_ID);
		}
	}

	/**
	 * The method removes the to-do task whoes position in the list is provided.
	 *
	 * @param param
	 *            position of the to-do task in list
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws NoTaskFoundException
	 * @throws DBConnectionException
	 */
	public void taskDone(String param) throws InstantiationException,
	IllegalAccessException, ClassNotFoundException, SQLException,
	NoTaskFoundException, DBConnectionException {
		Integer id = Integer.valueOf(param);
		new TaskListDAO().deleteTask(id);
		printList();
	}
}
