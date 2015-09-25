package controller;

import model.Model;
import view.View;

public class DisplayCross extends AbstractCommand {

	/**
	 * Instantiates a new display cross.
	 * @param m the m
	 * @param v the v
	 */
	public DisplayCross(Model m, View v) {
		super(m, v);
	}

	@Override
	/**
	 * DoCommand - display one cross of the maze according to the user's input.
	 */
	public void doCommand(String[] args) {
			m.displayCrossSection(args);
	}

}
