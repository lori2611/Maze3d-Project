package controller;

import model.Model;
import view.View;

public class DisplaySolution extends AbstractCommand {

	public DisplaySolution(Model m, View v) {
		super(m, v);
	}

	@Override
	public void doCommand(String[] params) {
		m.displaySolution(params);
	}

}
