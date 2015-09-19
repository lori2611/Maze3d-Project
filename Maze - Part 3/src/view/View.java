package view;

import java.io.IOException;
import java.util.HashMap;

import controller.Command;
import controller.Controller;

public interface View {

	/**
	 * Start the view
	 * @throws IOException
	 */
	public void start() throws IOException;
	
	/**
	 * Prints the result of 'dir' command
	 * @param files
	 */
	public void printDir(String[] files);
	
	/**
	 * Print the error which updated by the controller
	 * @param e
	 */
	public void printError(Exception e);
	
	/**
	 * Set Commands HashMap
	 * @param commands
	 */
	public void setCommands(HashMap<String, Command> commands);
	
	/**
	 * Set Controller
	 * @param c
	 */
	public void setC(Controller c);
}
