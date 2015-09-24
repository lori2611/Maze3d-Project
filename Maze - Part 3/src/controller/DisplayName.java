package controller;

import model.Model;
import view.View;

public class DisplayName extends AbstractCommand {

	/**
	 * C'tor
	 * @param m
	 * @param v
	 */
	public DisplayName(Model m, View v) {
		super(m, v);
	}

	@Override
	/**
	 * DoCommand - print all mazes with the specified name
	 * according to MVC pattern.
	 */
	public void doCommand(String[] args) {
		m.printMaze(args);
	}

}
