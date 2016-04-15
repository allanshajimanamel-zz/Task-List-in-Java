package com.tasklist.edu.main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.tasklist.edu.constants.ConsoleTexts;
import com.tasklist.edu.dataobject.CommandDO;
import com.tasklist.edu.dataobject.Todo;
import com.tasklist.edu.dataobject.TodoList;

/**
 * Hello world!
 *
 */
public class TaskListMain {

	private static final String SER_FILE = "todolist.ser";

	private TodoList list;

	public TaskListMain() {

	}

	public TaskListMain(TodoList list) {
		this.list = list;
	}

	public static void main(String[] args) {
		System.out.println(ConsoleTexts.WELCOME_MESSAGE);
		new TaskListMain().start();
	}

	private void start() {
		boolean loadSuccess = false;
		BufferedReader bufferRead = null;
		try {
			loadSuccess = loadList();
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
							addToList(commandDO.getParam());
							break;
						case LIST:
							printList();
							break;
						case TASKID:
							try {
								printTask(commandDO.getParam());
							} catch (NumberFormatException e) {
								System.out
								.println(ConsoleTexts.INVALID_TASK_ID);
							} catch (IndexOutOfBoundsException e) {
								System.out
								.println(ConsoleTexts.INVALID_TASK_ID);
							}
							break;
						case DONE:
							try {
								taskDone(commandDO.getParam());
							} catch (NumberFormatException e) {
								System.out
								.println(ConsoleTexts.INVALID_TASK_ID);
							} catch (IndexOutOfBoundsException e) {
								System.out
								.println(ConsoleTexts.INVALID_TASK_ID);
							}
							break;
						case SAVE:
							try {
								saveList();
							} catch (FileNotFoundException e) {
								System.out
								.println(ConsoleTexts.ERROR_SAVE_FAIL);
							} catch (IOException e) {
								System.out
								.println(ConsoleTexts.ERROR_SAVE_FAIL);
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
		} catch (IOException e) {
			System.out.println(ConsoleTexts.APP_ERROR);
		} catch (ClassNotFoundException e) {
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

	public boolean loadList() throws IOException, ClassNotFoundException {
		ObjectInputStream oi;
		try {
			oi = new ObjectInputStream(new FileInputStream(SER_FILE));
			Object data = oi.readObject();
			this.list = (TodoList) data;
			oi.close();
		} catch (FileNotFoundException e) {
			// An already serialized object does not exist so initializing .
			this.list = new TodoList();
			try {
				saveList();
			} catch (FileNotFoundException ee) {
				System.out.println(ConsoleTexts.ERROR_SAVE_FAIL);
			} catch (IOException ee) {
				System.out.println(ConsoleTexts.ERROR_SAVE_FAIL);
			}
		}
		return true;
	}

	public void printHelp() {
		System.out.println(ConsoleTexts.HELP);
	}

	public void addToList(String param) {
		System.out.print(ConsoleTexts.OUTPUT);
		if (param.isEmpty()) {
			System.out.println(ConsoleTexts.EMPTY_TODO);
		} else {
			Todo todo = new Todo(param);
			this.list.addLast(todo);
			System.out.println(ConsoleTexts.TASK_ADDED+this.list.size());
		}
	}

	public void printList() {
		System.out.println(ConsoleTexts.OUTPUT);
		if (this.list.size() > 0) {
			for (int i = 0; i < this.list.size(); i++) {
				System.out.println((i + 1) + ". " + this.list.get(i).getTodo());
			}
		} else {
			System.out.println(ConsoleTexts.EMPTY_LIST);
		}
	}

	public void printTask(String param) {
		System.out.println(ConsoleTexts.OUTPUT);
		Integer id = Integer.valueOf(param);
		System.out.println("Task: " + this.list.get(id - 1).getTodo());
	}

	public void taskDone(String param) {
		Integer id = Integer.valueOf(param);
		// To check for valid task id.
		this.list.get(id - 1);
		this.list.remove(id - 1);
		printList();
	}

	public void saveList() throws FileNotFoundException, IOException {
		ObjectOutputStream outputStream;
		outputStream = new ObjectOutputStream(new FileOutputStream(SER_FILE));
		outputStream.writeObject(list);
		outputStream.close();
		System.out.println(ConsoleTexts.SAVE_SUCCESS);
	}
}
