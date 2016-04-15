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
 * Hello world!
 *
 */
public class TaskListMain {

	public static void main(String[] args) {
		System.out.println(ConsoleTexts.WELCOME_MESSAGE);
		new TaskListMain().start();
	}

	private void start() {
		boolean loadSuccess = false;
		BufferedReader bufferRead = null;
		try {
			loadSuccess = TaskListDAO.setupDB();
			cmdloop: while (loadSuccess) {
				System.out.print(ConsoleTexts.ENTER_COMMAND_SYMBOL);
				bufferRead = new BufferedReader(
						new InputStreamReader(System.in));
				try {
					String cmdString = bufferRead.readLine();
					CommandDO commandDO = CommandUtil.getCommandData(cmdString);
					if (commandDO != null) {
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

	public void printHelp() {
		System.out.println(ConsoleTexts.HELP);
	}

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

	public void taskDone(String param) throws InstantiationException,
	IllegalAccessException, ClassNotFoundException, SQLException,
	NoTaskFoundException, DBConnectionException {
		Integer id = Integer.valueOf(param);
		new TaskListDAO().deleteTask(id);
		printList();
	}
}
