/*
 * 
 */
package controller;

import model.Model;
import view.View;

public class FileSize extends AbstractCommand {

	/**
	 * Instantiates a new file size.
	 *
	 * @param m the m
	 * @param v the v
	 */
	public FileSize(Model m, View v) {
		super(m, v);
	}

	@Override
	/**
	 * DoCommand - this will calculate the size of the specified file.
	 */
	public void doCommand(String[] params) {
		m.calcFileSize(params);
	}

}
