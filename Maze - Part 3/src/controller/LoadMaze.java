package controller;

import model.Model;
import view.View;

public class LoadMaze extends AbstractCommand {

	public LoadMaze(Model m, View v) {
		super(m, v);
	}

	@Override
	public void doCommand(String[] params) {
		m.loadMaze(params);
	}

}
