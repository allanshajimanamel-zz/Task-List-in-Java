package com.tasklist.edu.dataobject;

import java.io.Serializable;

/**
 * The class represents the single to-do task.
 *
 * @author ALLAN
 *
 */
public class Todo implements Serializable {

	/**
	 * Generated serialVersionUID.
	 */
	private static final long serialVersionUID = 9007056619725233250L;

	/**
	 * Constructor.
	 */
	public Todo() {
	}

	/**
	 * Constructor
	 *
	 * @param param
	 *            The to-do task data.
	 */
	public Todo(String param) {
		this.todo = param;
	}

	/**
	 * The task data.
	 */
	private String todo;

	/**
	 * @return the todo
	 */
	public String getTodo() {
		return todo;
	}

	/**
	 * @param todo
	 *            the todo to set
	 */
	public void setTodo(String todo) {
		this.todo = todo;
	}

}
