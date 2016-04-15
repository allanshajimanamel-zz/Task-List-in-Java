package com.tasklist.edu.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.tasklist.edu.constants.Commands;
import com.tasklist.edu.dataobject.CommandDO;
import com.tasklist.edu.main.CommandUtil;

/**
 * Test class for {@link CommandUtil}
 *
 * @author ALLAN
 *
 */
public class CommandUtilTest {

	/**
	 * Test method for
	 * {@link com.tasklist.edu.main.CommandUtil#getCommandData(java.lang.String)}
	 * .
	 */
	@Test
	public void testGetCommandDataHelpSuccess() {
		CommandDO commandDO = CommandUtil.getCommandData("<help>");
		assertTrue(commandDO.getCommands() == Commands.HELP);
	}

	/**
	 * Test method for
	 * {@link com.tasklist.edu.main.CommandUtil#getCommandData(java.lang.String)}
	 * .
	 */
	@Test
	public void testGetCommandDataHelpFailure() {
		CommandDO commandDO = CommandUtil.getCommandData("<help> ");
		assertTrue(commandDO == null);
	}

	/**
	 * Test method for
	 * {@link com.tasklist.edu.main.CommandUtil#getCommandData(java.lang.String)}
	 * .
	 */
	@Test
	public void testGetCommandDataListSuccess() {
		CommandDO commandDO = CommandUtil.getCommandData("<list>");
		assertTrue(commandDO.getCommands() == Commands.LIST);
	}

	/**
	 * Test method for
	 * {@link com.tasklist.edu.main.CommandUtil#getCommandData(java.lang.String)}
	 * .
	 */
	@Test
	public void testGetCommandDataListFailure() {
		CommandDO commandDO = CommandUtil.getCommandData("<list> ");
		assertTrue(commandDO == null);
	}

	/**
	 * Test method for
	 * {@link com.tasklist.edu.main.CommandUtil#getCommandData(java.lang.String)}
	 * .
	 */
	@Test
	public void testGetCommandDataQuitSuccess() {
		CommandDO commandDO = CommandUtil.getCommandData("<quit>");
		assertTrue(commandDO.getCommands() == Commands.QUIT);
	}

	/**
	 * Test method for
	 * {@link com.tasklist.edu.main.CommandUtil#getCommandData(java.lang.String)}
	 * .
	 */
	@Test
	public void testGetCommandDataQuitFailure() {
		CommandDO commandDO = CommandUtil.getCommandData("<quit> ");
		assertTrue(commandDO == null);
	}

	/**
	 * Test method for
	 * {@link com.tasklist.edu.main.CommandUtil#getCommandData(java.lang.String)}
	 * .
	 */
	@Test
	public void testGetCommandDataAddSuccess() {
		CommandDO commandDO = CommandUtil.getCommandData("<add> 2");
		assertTrue(commandDO.getCommands() == Commands.ADD);
		assertTrue(!commandDO.getParam().isEmpty());
	}

	/**
	 * Test method for
	 * {@link com.tasklist.edu.main.CommandUtil#getCommandData(java.lang.String)}
	 * .
	 */
	@Test
	public void testGetCommandDataAddFailure() {
		CommandDO commandDO = CommandUtil.getCommandData("<add>  ");
		assertTrue(commandDO == null);
	}

	/**
	 * Test method for
	 * {@link com.tasklist.edu.main.CommandUtil#getCommandData(java.lang.String)}
	 * .
	 */
	@Test
	public void testGetCommandDataDoneSuccess() {
		CommandDO commandDO = CommandUtil.getCommandData("<done> 2");
		assertTrue(commandDO.getCommands() == Commands.DONE);
		assertTrue(!commandDO.getParam().isEmpty());
	}

	/**
	 * Test method for
	 * {@link com.tasklist.edu.main.CommandUtil#getCommandData(java.lang.String)}
	 * .
	 */
	@Test
	public void testGetCommandDataDoneFailure() {
		CommandDO commandDO = CommandUtil.getCommandData("<done>  ");
		assertTrue(commandDO == null);
	}

	/**
	 * Test method for
	 * {@link com.tasklist.edu.main.CommandUtil#getCommandData(java.lang.String)}
	 * .
	 */
	@Test
	public void testGetCommandDataTaskidSuccess() {
		CommandDO commandDO = CommandUtil.getCommandData("<taskid> 2");
		assertTrue(commandDO.getCommands() == Commands.TASKID);
		assertTrue(!commandDO.getParam().isEmpty());
	}

	/**
	 * Test method for
	 * {@link com.tasklist.edu.main.CommandUtil#getCommandData(java.lang.String)}
	 * .
	 */
	@Test
	public void testGetCommandDatataskidFailure() {
		CommandDO commandDO = CommandUtil.getCommandData("<taskid>  ");
		assertTrue(commandDO == null);
	}
}
