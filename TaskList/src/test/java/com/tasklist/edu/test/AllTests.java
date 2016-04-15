package com.tasklist.edu.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * The test suite for test cases present in {@link CommandUtilTest} and
 * {@link TaskListDAOTest}
 *
 * @author ALLAN
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ CommandUtilTest.class, TaskListDAOTest.class,
	TaskListTest.class })
public class AllTests {
	// Empty
}
