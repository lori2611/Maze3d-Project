package controller;

import model.Model;
import view.View;

public class Generate extends AbstractCommand implements Runnable {

	public String[] params;
	
	/**
	 * C'tor
	 * @param m
	 * @param v
	 */
	public Generate(Model m, View v) {
		super(m, v);
	}

	@Override
	/**
	 * DoCommand - generate new maze with the specified name and size.
	 * according to MVC pattern,the model will pass a message through the controller
	 * when the maze is ready.  
	 */
	public void doCommand(String[] params) {
		this.params = params;
		Thread t = new Thread(this);
		t.start();
	}

	@Override
	/**
	 * This method will run in a different thread and will generate new maze
	 * with bounds which the client defined.
	 */
	public void run() {
		m.generateMaze(this.params);
	}

}
