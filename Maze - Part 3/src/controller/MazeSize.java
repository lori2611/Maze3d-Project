/*
 * 
 */
package controller;

import model.Model;
import view.View;

public class MazeSize extends AbstractCommand {

	/**
	 * Instantiates a new maze size.
	 * @param m the m
	 * @param v the v
	 */
	public MazeSize(Model m, View v) {
		super(m, v);
	}

	@Override
	/**
	 * DoCommand - this will calculate the size of the maze in bytes
	 */
	public void doCommand(String[] params) {
		m.calcMazeSize(params);
	}
}
