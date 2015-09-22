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
	
	public void generateMaze(String[] args);
}
