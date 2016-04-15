package com.tasklist.edu.constants;

import com.tasklist.edu.main.TaskListDAO;

/**
 * The class contains all the constants for SQL opertaions done in
 * {@link TaskListDAO}.
 *
 * @author ALLAN
 *
 */
public class SQLConstants {

	/**
	 * Constant for the derby driver.
	 */
	public static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";

	/**
	 * Constant for the DB url.
	 */
	public static final String DB_URL = "jdbc:derby:TaskListDB/taskdb;create=true";

	/**
	 * Constant for the create table script.
	 */
	public static final String CREATE_TABLE_SCRIPT = "CREATE TABLE todo_list (todo VARCHAR(45), creation_time TIMESTAMP, "
			+ "id INT not null primary key GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1))";

	/**
	 * Constant for the insert script.
	 */
	public static final String INSERT_SCRIPT = "INSERT INTO todo_list VALUES (?,CURRENT_TIMESTAMP,DEFAULT)";

	/**
	 * Constant for the select all script.
	 */
	public static final String SELECT_ALL_SCRIPT = "SELECT * FROM todo_list ORDER BY creation_time ASC";

	/**
	 * Constant for the delete task script.
	 */
	public static final String DELETE_TASK_SCRIPT = "DELETE FROM todo_list WHERE id = ?";

	/**
	 * Constant for the count all script.
	 */
	public static final String COUNT_SCRIPT = "SELECT COUNT(*) FROM todo_list";

	/**
	 * Constant for the SQLException status code when a table with specified
	 * name already exists.
	 */
	public static final String TABLE_ERROR_CODE = "X0Y32";

	/**
	 * Name of the column in table where the task data is stored.
	 */
	public static final String TODO_COLUMN = "todo";

	/**
	 * Name of the column in table where the task id is stored.
	 */
	public static final String ID_COLUMN = "id";
}
