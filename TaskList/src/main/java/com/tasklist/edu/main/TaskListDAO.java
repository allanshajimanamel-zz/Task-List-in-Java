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
 * @author ALLAN
 *
 */
public class TaskListDAO {

	public Connection getConnection() throws InstantiationException,
	IllegalAccessException, ClassNotFoundException, SQLException,
	DBConnectionException {
		Connection conn = null;
		Class.forName(SQLConstants.DRIVER).newInstance();
		conn = DriverManager.getConnection(SQLConstants.DB_URL);

		if (conn == null) {
			throw new DBConnectionException();
		} else {
			return conn;
		}
	}

	public static boolean setupDB() throws InstantiationException,
	IllegalAccessException, ClassNotFoundException, SQLException,
	DBConnectionException {
		Connection conn = new TaskListDAO().getConnection();
		try {
			Statement statement = conn.createStatement();
			statement.executeUpdate(SQLConstants.CREATE_TABLE_SCRIPT);
		} catch (SQLException e) {
			if (e.getSQLState().equals(SQLConstants.TABLE_ERROR_CODE)) {
				// Do nothing.
			} else {
				throw e;
			}
		} finally {
			if (conn != null)
				conn.close();
		}
		return true;
	}

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

	public Todo getTask(Integer num) throws InstantiationException,
	IllegalAccessException, ClassNotFoundException, SQLException,
	DBConnectionException {
		Todo todo = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		try {
			PreparedStatement pStatement = conn.prepareStatement(
					SQLConstants.SELECT_ALL_SCRIPT,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = pStatement.executeQuery();
			if (rs.next()) {
				rs.absolute(num);
				todo = new Todo();
				todo.setTodo(rs.getString(SQLConstants.TODO_COLUMN));
			}
		} finally {
			if (conn != null)
				conn.close();
		}
		return todo;
	}

	public void deleteTask(Integer num) throws SQLException,
	InstantiationException, IllegalAccessException,
	ClassNotFoundException, NoTaskFoundException, DBConnectionException {
		Integer id = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		try {
			PreparedStatement pStatement1 = conn.prepareStatement(
					SQLConstants.SELECT_ALL_SCRIPT,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = pStatement1.executeQuery();

			rs.absolute(num);
			if (rs.next()) {
				id = rs.getInt(SQLConstants.ID_COLUMN);
			}
			if (id != null) {
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
