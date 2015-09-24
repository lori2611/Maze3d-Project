package controller;

import model.Model;
import view.View;

public class DisplayCross extends AbstractCommand {

	public DisplayCross(Model m, View v) {
		super(m, v);
	}

	@Override
	public void doCommand(String[] args) {
			m.displayCrossSection(args);
	}

}
