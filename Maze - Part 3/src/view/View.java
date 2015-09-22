package view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import algorithms.mazeGenerators.Maze3d;
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
	
	/**
	 * Pass client's input to the controller according to the MVC pattern
	 * @param input
	 */
	public void passInput(String input);
	
	/**
	 * Print message to the client
	 * @param s
	 */
	public void printMessage(String s);
	
	/**
	 * Print the maze from the HashMap
	 * @param arrayList
	 */
	public void printMaze(Maze3d maze);
	
	/**
	 * Print the cross of the maze
	 * @param maze
	 */
	public void printCrossSection(int[][] maze, int length, int width);
}
