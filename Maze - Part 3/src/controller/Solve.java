package controller;

import model.Model;
import view.View;

public class Solve extends AbstractCommand implements Runnable {

	private String[] params;

	public Solve(Model m, View v) {
		super(m, v);
	}

	@Override
	public void doCommand(String[] params) {
		this.params = params;
		Thread t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		m.solveMaze(this.params);
	}

}
