/*
 * 
 */
package controller;

import model.Model;
import view.View;

public class SaveMaze extends AbstractCommand {

	/**
	 * Instantiates a new save maze.
	 *
	 * @param m the m
	 * @param v the v
	 */
	public SaveMaze(Model m, View v) {
		super(m, v);
	}

	/**
	 * Save the the maze in a zipped way into the specified file
	 */
	@Override
	public void doCommand(String[] params) {
		m.saveMaze(params);
	}

}