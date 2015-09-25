package model;

import controller.Controller;

public interface Model {
	
	/**
	 * Set Controller
	 * @param c
	 */
	public void setC(Controller c);
	
	/**
	 * Search for all folders and files in specified path and pass them on
	 * @param path
	 */
	public void dir(String[] params);
	
	/**
	 * Generate new maze and save it in a HashMap with the specified details.
	 * @param args
	 */
	public void generateMaze(String[] params);
	
	/**
	 * Print all mazes which called by the specified name from the HashMap
	 * @param args
	 */
	public void displayMaze(String[] params);
	
	/**
	 * Display the cross section of the maze that the client ask for.
	 * This method will find the crossSection and will pass it according
	 * to the MVC pattern
	 * @param params
	 */
	public void displayCrossSection(String[] params);
	
	/**
	 * Save a specific maze in the specified file
	 * @param params
	 */
	public void saveMaze(String[] params);
	
	/**
	 * Load a specific maze from the specified file
	 * @param params
	 */
	public void loadMaze(String[] params);
	
	/**
	 * Calculates the size of the maze in bytes
	 * @param params
	 */
	public void calcMazeSize(String[] params);
	
	/**
	 * Calculates the size of the file which the maze was saved in
	 * @param params
	 */
	public void calcFileSize(String[] params);
	
	/**
	 * Solve the maze and save the solution in a HashMap
	 * @param params
	 */
	public void solveMaze(String[] params);
	
	/**
	 * Display the solution from the HashMap
	 * @param params
	 */
	public void displaySolution(String[] params);
	
	/**
	 * Safe exit - waiting for all the threads to be terminated.
	 * @param params
	 */
	public void exit(String[] params);
}
