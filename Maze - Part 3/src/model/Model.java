package model;

import controller.Controller;

public interface Model {

	/**
	 * Search for all folders and files in specified path and pass them on
	 * @param path
	 */
	public void dir(String path);
	
	/**
	 * Set Controller
	 * @param c
	 */
	public void setC(Controller c);
	
	/**
	 * Generate new maze and save it in a HashMap with the specified details.
	 * @param args
	 */
	public void generateMaze(String[] params);
	
	/**
	 * Print all mazes which called by the specified name from the HashMap
	 * @param args
	 */
	public void printMaze(String[] params);
	
	public void displayCrossSection(String[] params);
	
	public void saveMaze(String[] params);
	
	public void loadMaze(String[] params);
	
	public void calcMazeSize(String[] params);
	
	public void calcFileSize(String[] params);
	
	public void solveMaze(String[] params);
}
