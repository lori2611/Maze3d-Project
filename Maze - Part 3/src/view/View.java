package view;

import java.io.IOException;
import java.io.PrintWriter;
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
	 * Prints the result of 'DIR' command
	 * @param files
	 */
	public void printDir(String[] files);
	
	/**
	 * Print the error which updated by the controller
	 * @param e
	 */
	public void printError(Exception e);
	
	/**
	 * Set Controller
	 * @param c
	 */
	public void setC(Controller c);
	
	public void passInput(String input);
	
	public void printMessage(String s);
}
