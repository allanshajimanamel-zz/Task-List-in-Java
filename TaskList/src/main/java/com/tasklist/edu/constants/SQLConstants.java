package com.tasklist.edu.constants;

/**
 * @author ALLAN
 *
 */
public class SQLConstants {
	public static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";

	public static final String DB_URL = "jdbc:derby:TaskListDB/taskdb;create=true";

	public static final String CREATE_TABLE_SCRIPT = "CREATE TABLE todo_list (todo VARCHAR(45), creation_time TIMESTAMP, "
			+ "id INT not null primary key GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1))";

	public static final String INSERT_SCRIPT = "INSERT INTO todo_list VALUES (?,CURRENT_TIMESTAMP,DEFAULT)";

	public static final String SELECT_ALL_SCRIPT = "SELECT * FROM todo_list ORDER BY creation_time ASC";

	public static final String DELETE_TASK_SCRIPT = "DELETE FROM todo_list WHERE id = ?";

	public static final String COUNT_SCRIPT = "SELECT COUNT(*) FROM todo_list";

	public static final String TABLE_ERROR_CODE = "X0Y32";

	public static final String TODO_COLUMN = "todo";

	public static final String ID_COLUMN = "id";
}
