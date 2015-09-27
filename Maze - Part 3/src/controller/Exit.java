/*
 * 
 */
package controller;

import model.Model;
import view.View;

public class Exit extends AbstractCommand {

	/**
	 * Instantiates a new exit.
	 * @param m the m
	 * @param v the v
	 */
	public Exit(Model m, View v) {
		super(m, v);
	}

	@Override
	/**
	 * This will exit the program when all the threads of the commands will terminate
	 */
	public void doCommand(String[] params) {
		m.exit(params);
	}

}
