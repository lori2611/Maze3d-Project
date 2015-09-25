/*
 * 
 */
package controller;

import model.Model;
import view.View;

public class LoadMaze extends AbstractCommand {

	/**
	 * Instantiates a new load maze.
	 * @param m the m
	 * @param v the v
	 */
	public LoadMaze(Model m, View v) {
		super(m, v);
	}

	@Override
	/**
	 * DoCommand - this will load a maze from a specific file and save it
	 * in out HashMap
	 */
	public void doCommand(String[] params) {
		m.loadMaze(params);
	}

}
