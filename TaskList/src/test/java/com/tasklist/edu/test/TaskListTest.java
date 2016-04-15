package com.tasklist.edu.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.tasklist.edu.exception.DBConnectionException;
import com.tasklist.edu.exception.NoTaskFoundException;
import com.tasklist.edu.main.TaskListDAO;
import com.tasklist.edu.main.TaskListMain;

/**
 * @author ALLAN
 *
 */
public class TaskListTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		Connection conn = null;
		TaskListDAO dao = null;
		try {
			dao = new TaskListDAO();
			conn = dao.getConnection();
			TaskListDAO.setupDB();
			PreparedStatement ps = conn
					.prepareStatement("DELETE FROM todo_list");
			ps.executeUpdate();
			dao.addToDo("Task 1");
			dao.addToDo("Task 2");
			dao.addToDo("Task 3");
			dao.addToDo("Task 4");
		} finally {
			if (conn != null)
				conn.close();
		}

	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		Connection conn = null;
		TaskListDAO dao = null;
		try {
			dao = new TaskListDAO();
			conn = dao.getConnection();
			PreparedStatement ps = conn
					.prepareStatement("DELETE FROM todo_list");
			ps.executeUpdate();
		} finally {
			if (conn != null)
				conn.close();
		}
	}

	/**
	 * Test method for
	 * {@link com.tasklist.edu.main.TaskListMain#addToList(java.lang.String)}.
	 *
	 * @throws Exception
	 */
	@Test
	public void testAddTaskSuccess() throws Exception {
		setUp();
		TaskListDAO dao = new TaskListDAO();
		int initialCount = dao.getCount();
		new TaskListMain().addToList("Task 5");
		int finalCount = dao.getCount();
		Assert.assertTrue((initialCount + 1) == (finalCount));
	}

	/**
	 * Test method for
	 * {@link com.tasklist.edu.main.TaskListMain#addToList(java.lang.String)}.
	 *
	 * @throws Exception
	 */
	@Test
	public void testAddTaskFailure() throws Exception {
		setUp();
		TaskListDAO dao = new TaskListDAO();
		int initialCount = dao.getCount();
		new TaskListMain().addToList("");
		int finalCount = dao.getCount();
		Assert.assertTrue((initialCount) == (finalCount));
	}

	/**
	 * Test method for
	 * {@link com.tasklist.edu.main.TaskListMain#taskDone(java.lang.String)}.
	 *
	 * @throws Exception
	 */
	@Test
	public void testTaskDoneSuccess() throws Exception {
		setUp();
		TaskListDAO dao = new TaskListDAO();
		int initialCount = dao.getCount();
		new TaskListMain().taskDone("2");
		int finalCount = dao.getCount();
		Assert.assertTrue((initialCount - 1) == finalCount);
	}

	/**
	 * Test method for
	 * {@link com.tasklist.edu.main.TaskListMain#taskDone(java.lang.String)}.
	 *
	 * @throws DBConnectionException
	 * @throws NoTaskFoundException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	@Test(expected = NumberFormatException.class)
	public void testTaskDoneFailure1() throws InstantiationException,
	IllegalAccessException, ClassNotFoundException, SQLException,
	NoTaskFoundException, DBConnectionException {
		new TaskListMain().taskDone("aaa");
	}

	/**
	 * Test method for
	 * {@link com.tasklist.edu.main.TaskListMain#taskDone(java.lang.String)}.
	 *
	 * @throws DBConnectionException
	 * @throws NoTaskFoundException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	@Test(expected = NoTaskFoundException.class)
	public void testTaskDoneFailure2() throws InstantiationException,
	IllegalAccessException, ClassNotFoundException, SQLException,
	NoTaskFoundException, DBConnectionException {
		new TaskListMain().taskDone("8");
	}

}
