package com.tasklist.edu.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.tasklist.edu.constants.SQLConstants;
import com.tasklist.edu.dataobject.Todo;
import com.tasklist.edu.dataobject.TodoList;
import com.tasklist.edu.exception.DBConnectionException;
import com.tasklist.edu.exception.NoTaskFoundException;

/**
 * The class controls and contains all the SQL operations performed by the
 * application.
 *
 * @author ALLAN
 *
 */
public class TaskListDAO {

	/**
	 * The method is used to get the connection to the database.
	 *
	 * @return An instance of {@link Connection}
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws DBConnectionException
	 */
	public Connection getConnection() throws InstantiationException,
	IllegalAccessException, ClassNotFoundException, SQLException,
	DBConnectionException {
		Connection conn = null;
		Class.forName(SQLConstants.DRIVER).newInstance();
		// Creates the databse if it does not exist.
		conn = DriverManager.getConnection(SQLConstants.DB_URL);

		if (conn == null) {
			throw new DBConnectionException();
		} else {
			return conn;
		}
	}

	/**
	 * The method is used to setup the database before use.
	 *
	 * @return {@link true} if successful else {@link false}
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws DBConnectionException
	 */
	public static boolean setupDB() throws InstantiationException,
	IllegalAccessException, ClassNotFoundException, SQLException,
	DBConnectionException {
		// get the connection.
		Connection conn = new TaskListDAO().getConnection();
		try {
			// create the table.
			Statement statement = conn.createStatement();
			statement.executeUpdate(SQLConstants.CREATE_TABLE_SCRIPT);
		} catch (SQLException e) {
			if (e.getSQLState().equals(SQLConstants.TABLE_ERROR_CODE)) {
				// Do nothing. The database table already exists.
			} else {
				throw e;
			}
		} finally {
			if (conn != null)
				conn.close();
		}
		return true;
	}

	/**
	 * Inserts the to-do task into the database.
	 *
	 * @param todo
	 *            The to-do task to create.
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws DBConnectionException
	 */
	public void addToDo(String todo) throws InstantiationException,
	IllegalAccessException, ClassNotFoundException, SQLException,
	DBConnectionException {
		Connection conn = getConnection();
		try {
			PreparedStatement pStatement = conn
					.prepareStatement(SQLConstants.INSERT_SCRIPT);
			pStatement.setString(1, todo);
			pStatement.executeUpdate();
		} finally {
			if (conn != null)
				conn.close();
		}
	}

	/**
	 * The method fetches all the to-do tasks
	 *
	 * @return an instance of {@link TodoList}
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws DBConnectionException
	 */
	public TodoList getToDoList() throws InstantiationException,
	IllegalAccessException, ClassNotFoundException, SQLException,
	DBConnectionException {
		ResultSet rs = null;
		TodoList list = new TodoList();
		Connection conn = getConnection();
		try {
			PreparedStatement pStatement = conn
					.prepareStatement(SQLConstants.SELECT_ALL_SCRIPT);
			rs = pStatement.executeQuery();
			while (rs.next()) {
				Todo todo = new Todo(rs.getString(SQLConstants.TODO_COLUMN));
				list.add(todo);
			}
		} finally {
			if (conn != null)
				conn.close();
		}
		return list;
	}

	/**
	 * Gets the particular task whoes position in the list if given.
	 *
	 * @param num
	 *            The position on the list
	 * @return an instance of {@link Todo}
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws DBConnectionException
	 */
	public Todo getTask(Integer num) throws InstantiationException,
	IllegalAccessException, ClassNotFoundException, SQLException,
	DBConnectionException {
		Todo todo = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		try {
			// Get the full list.
			PreparedStatement pStatement = conn.prepareStatement(
					SQLConstants.SELECT_ALL_SCRIPT,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = pStatement.executeQuery();

			// move the cursor to the given position.
			rs.absolute(num - 1);
			if (rs.next()) {
				todo = new Todo();
				todo.setTodo(rs.getString(SQLConstants.TODO_COLUMN));
			}
		} finally {
			if (conn != null)
				conn.close();
		}
		return todo;
	}

	/**
	 * Deletes the particular task whoes position in the list if given.
	 *
	 * @param num
	 *            The position on the list
	 * @throws SQLException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws NoTaskFoundException
	 * @throws DBConnectionException
	 */
	public void deleteTask(Integer num) throws SQLException,
	InstantiationException, IllegalAccessException,
	ClassNotFoundException, NoTaskFoundException, DBConnectionException {
		Integer id = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		try {
			// Get the whole list
			PreparedStatement pStatement1 = conn.prepareStatement(
					SQLConstants.SELECT_ALL_SCRIPT,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = pStatement1.executeQuery();

			// Move the cursor to the given position and fetch the id of the
			// task.
			rs.absolute(num - 1);
			if (rs.next()) {
				id = rs.getInt(SQLConstants.ID_COLUMN);
			}
			if (id != null) {
				// Delete the task with the particular id.
				PreparedStatement pStatement2 = conn
						.prepareStatement(SQLConstants.DELETE_TASK_SCRIPT);
				pStatement2.setInt(1, id);
				pStatement2.executeUpdate();
			} else {
				throw new NoTaskFoundException();
			}
		} finally {
			if (conn != null)
				conn.close();
		}
	}

	/**
	 * Gets the current total number of to-do tasks in the database.
	 *
	 * @return The total number of to-do tasks in the database.
	 * @throws SQLException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws DBConnectionException
	 */
	public int getCount() throws SQLException, InstantiationException,
	IllegalAccessException, ClassNotFoundException,
	DBConnectionException {
		int count = 0;
		Connection conn = getConnection();
		try {
			PreparedStatement pStatement = conn
					.prepareStatement(SQLConstants.COUNT_SCRIPT);
			ResultSet rs = pStatement.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} finally {
			if (conn != null)
				conn.close();
		}
		return count;
	}
}
