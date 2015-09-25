package controller;

import model.Model;
import view.View;

public class DisplayMaze extends AbstractCommand {

	/**
	 * C'tor
	 * @param m
	 * @param v
	 */
	public DisplayMaze(Model m, View v) {
		super(m, v);
	}

	@Override
	/**
	 * DoCommand - print all mazes with the specified name
	 * according to MVC pattern.
	 */
	public void doCommand(String[] args) {
		m.displayMaze(args);
	}

}
