/*
 * 
 */
package controller;

import model.Model;
import view.View;

public class DisplaySolution extends AbstractCommand {

	/**
	 * Instantiates a new display solution.
	 * @param m the m
	 * @param v the v
	 */
	public DisplaySolution(Model m, View v) {
		super(m, v);
	}

	@Override
	/**
	 * DoCommand - this will display the solution of the specified maze.
	 */
	public void doCommand(String[] params) {
		m.displaySolution(params);
	}

}
