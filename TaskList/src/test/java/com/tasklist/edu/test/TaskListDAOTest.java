package com.tasklist.edu.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.tasklist.edu.dataobject.Todo;
import com.tasklist.edu.exception.DBConnectionException;
import com.tasklist.edu.exception.NoTaskFoundException;
import com.tasklist.edu.main.TaskListDAO;

/**
 * Test class to {@link Test} {@link TaskListDAO}.
 *
 * @author ALLAN
 *
 */
public class TaskListDAOTest {

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
	 * Test method for {@link com.tasklist.edu.main.TaskListDAO#getConnection()}
	 *
	 * @throws DBConnectionException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	@Test
	public void testGetConnection() throws InstantiationException,
	IllegalAccessException, ClassNotFoundException, SQLException,
	DBConnectionException {
		TaskListDAO dao = new TaskListDAO();
		Connection connection = dao.getConnection();
		if (connection == null) {
			Assert.fail("Could not establish connection to database.");
		}
	}

	/**
	 * Test method for {@link com.tasklist.edu.main.TaskListDAO#setupDB()}.
	 *
	 * @throws DBConnectionException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	@Test
	public void testSetupDB() throws InstantiationException,
	IllegalAccessException, ClassNotFoundException, SQLException,
	DBConnectionException {
		Assert.assertTrue(TaskListDAO.setupDB());
	}

	/**
	 * Test method for
	 * {@link com.tasklist.edu.main.TaskListDAO#addToDo(java.lang.String)}.
	 *
	 * @throws Exception
	 */
	@Test
	public void testAddToDo() throws Exception {
		setUp();
		TaskListDAO dao = new TaskListDAO();
		int initialCount = dao.getCount();
		dao.addToDo("Task 5");
		int finalCount = dao.getCount();
		Assert.assertTrue((initialCount + 1) == (finalCount));
	}

	/**
	 * Test method for {@link com.tasklist.edu.main.TaskListDAO#getToDoList()}.
	 *
	 * @throws Exception
	 */
	@Test
	public void testGetToDoList() throws Exception {
		setUp();
		TaskListDAO dao = new TaskListDAO();
		int count = dao.getCount();
		int fetchCount = dao.getToDoList().size();
		Assert.assertTrue(count == fetchCount);
	}

	/**
	 * Test method for
	 * {@link com.tasklist.edu.main.TaskListDAO#getTask(java.lang.Integer)}.
	 *
	 * @throws Exception
	 */
	@Test
	public void testGetTaskSuccess() throws Exception {
		setUp();
		TaskListDAO dao = new TaskListDAO();
		Todo todo = dao.getTask(1);
		Assert.assertTrue(todo != null);
	}

	/**
	 * Test method for
	 * {@link com.tasklist.edu.main.TaskListDAO#getTask(java.lang.Integer)}.
	 *
	 * @throws Exception
	 */
	@Test(expected = SQLException.class)
	public void testGetTaskFailure() throws Exception {
		setUp();
		TaskListDAO dao = new TaskListDAO();
		dao.getTask(5);
	}

	/**
	 * Test method for
	 * {@link com.tasklist.edu.main.TaskListDAO#deleteTask(java.lang.Integer)}.
	 *
	 * @throws Exception
	 */
	@Test
	public void testDeleteTaskSuccess() throws Exception {
		setUp();
		TaskListDAO dao = new TaskListDAO();
		int initialCount = dao.getCount();
		dao.deleteTask(2);
		int finalCount = dao.getCount();
		Assert.assertTrue((initialCount - 1) == finalCount);
	}

	/**
	 * Test method for
	 * {@link com.tasklist.edu.main.TaskListDAO#deleteTask(java.lang.Integer)}.
	 *
	 * @throws Exception
	 */
	@Test(expected = NoTaskFoundException.class)
	public void testDeleteTaskFailure() throws Exception {
		setUp();
		TaskListDAO dao = new TaskListDAO();
		dao.deleteTask(5);
	}

	/**
	 * Test method for {@link com.tasklist.edu.main.TaskListDAO#getCount()}.
	 *
	 * @throws Exception
	 */
	@Test
	public void testGetCount() throws Exception {
		setUp();
		int count = new TaskListDAO().getCount();
		Assert.assertTrue(count == 4);
	}

}
