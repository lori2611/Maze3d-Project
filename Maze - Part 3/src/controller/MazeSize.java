package controller;

import model.Model;
import view.View;

public class MazeSize extends AbstractCommand {

	public MazeSize(Model m, View v) {
		super(m, v);
	}

	@Override
	public void doCommand(String[] params) {
		m.calcMazeSize(params);
	}
}
