package com.tasklist.edu.dataobject;

import java.io.Serializable;

/**
 * @author ALLAN
 *
 */
public class Todo implements Serializable {

	/**
	 * Generated serialVersionUID.
	 */
	private static final long serialVersionUID = 9007056619725233250L;

	public Todo() {
	}

	public Todo(String param) {
		this.todo = param;
	}

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
