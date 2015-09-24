package controller;

import model.Model;
import view.View;

public class Dir extends AbstractCommand {

	/**
	 * C'tor
	 * @param m
	 * @param v
	 */
	public Dir(Model m, View v) {
		super(m, v);
	}

	/**
	 * DoCommand - print all folders and files in specified path 
	 * according to MVC pattern.
	 */
	public void doCommand(String[] location) {
		m.dir(location[0]);
	}
}
