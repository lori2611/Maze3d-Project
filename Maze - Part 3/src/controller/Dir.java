package controller;

import model.Model;
import view.View;

public class Dir extends AbstractCommand {

	/**
	 * C'tor.
	 * @param m the m
	 * @param v the v
	 */
	public Dir(Model m, View v) {
		super(m, v);
	}

	/**
	 * DoCommand - print all folders and files in specified path 
	 * according to MVC pattern.
	 * @param params the params
	 */
	public void doCommand(String[] params) {
		m.dir(params);
	}
}
