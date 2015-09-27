package view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
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
	
	/**
	 * Print the size of the maze in bytes
	 * @param size
	 */
	public void printMazeSize(int size);
	
	/**
	 * Print the size of the file in bytes
	 * @param size
	 */
	public void printFileSize(long size);
	
	/**
	 * Print the solution on the specified maze
	 * @param sol
	 */
	public void printSolution(Solution<Position> sol);
}
