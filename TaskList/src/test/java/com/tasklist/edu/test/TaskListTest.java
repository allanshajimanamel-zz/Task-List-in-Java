package com.tasklist.edu.test;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Test;

import com.tasklist.edu.dataobject.Todo;
import com.tasklist.edu.dataobject.TodoList;
import com.tasklist.edu.main.TaskListMain;

/**
 * @author ALLAN
 *
 */
public class TaskListTest {

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		File file = new File("todolist.ser");
		file.deleteOnExit();
	}

	/**
	 * Test method for {@link com.tasklist.edu.main.TaskListMain#loadList()}.
	 *
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@Test
	public void testLoadList() throws ClassNotFoundException, IOException {
		assertTrue(new TaskListMain().loadList());
	}

	/**
	 * Test method for
	 * {@link com.tasklist.edu.main.TaskListMain#addToList(java.lang.String)}.
	 */
	@Test
	public void testAddToListSuccess() {
		TodoList list = getTodoList();
		new TaskListMain(list).addToList("task 5");
		assertTrue(list.size() == 5);
	}

	/**
	 * Test method for
	 * {@link com.tasklist.edu.main.TaskListMain#addToList(java.lang.String)}.
	 */
	@Test
	public void testAddToListFailure() {
		TodoList list = getTodoList();
		new TaskListMain(list).addToList("");
		assertTrue(list.size() == 4);
	}

	/**
	 * Test method for
	 * {@link com.tasklist.edu.main.TaskListMain#taskDone(java.lang.String)}.
	 */
	@Test
	public void testTaskDoneSuccess() {
		TodoList list = getTodoList();
		new TaskListMain(list).taskDone("2");
		assertTrue(list.size() == 3);
	}

	/**
	 * Test method for
	 * {@link com.tasklist.edu.main.TaskListMain#taskDone(java.lang.String)}.
	 */
	@Test(expected = NumberFormatException.class)
	public void testTaskDoneFailure1() {
		TodoList list = getTodoList();
		new TaskListMain(list).taskDone("aaa");
		assertTrue(list.size() == 4);
	}

	/**
	 * Test method for
	 * {@link com.tasklist.edu.main.TaskListMain#taskDone(java.lang.String)}.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testTaskDoneFailure2() {
		TodoList list = getTodoList();
		new TaskListMain(list).taskDone("8");
		assertTrue(list.size() == 4);
	}

	private TodoList getTodoList() {
		TodoList list = new TodoList();
		for (int i = 0; i < 4; i++) {
			Todo todo = new Todo("task " + (i + 1));
			list.add(todo);
		}
		return list;
	}
}
