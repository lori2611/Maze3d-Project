package controller;

import model.Model;
import view.View;

public class Solve extends AbstractCommand {

	public Solve(Model m, View v) {
		super(m, v);
	}

	@Override
	public void doCommand(String[] params) {
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				m.solveMaze(params);
			}
		} ).start();
	}
}


