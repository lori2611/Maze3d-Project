/*
 * 
 */
package controller;

import model.Model;
import view.View;

public class Solve extends AbstractCommand {

	/**
	 * Instantiates a new solve.
	 * @param m the m
	 * @param v the v
	 */
	public Solve(Model m, View v) {
		super(m, v);
	}

	@Override
	/**
	 * According to MVC pattern - send the command to the model
	 */
	public void doCommand(String[] params) {
		m.solveMaze(params);
	}
}


