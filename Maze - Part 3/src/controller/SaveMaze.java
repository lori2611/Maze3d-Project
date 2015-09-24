package controller;

import model.Model;
import view.View;

public class SaveMaze extends AbstractCommand {

	public SaveMaze(Model m, View v) {
		super(m, v);
	}

	@Override
	/**
	 * Save the the maze in a zipped way into the specified file
	 */
	public void doCommand(String[] params) {
		m.saveMaze(params);
	}

}