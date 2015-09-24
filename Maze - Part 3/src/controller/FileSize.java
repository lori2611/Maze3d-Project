package controller;

import model.Model;
import view.View;

public class FileSize extends AbstractCommand {

	public FileSize(Model m, View v) {
		super(m, v);
	}

	@Override
	public void doCommand(String[] params) {
		m.calcFileSize(params);
	}

}
