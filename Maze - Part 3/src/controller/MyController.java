package controller;

import java.util.HashMap;

import model.Model;
import view.View;

public class MyController implements Controller {

	private View v;
	private Model m;
	HashMap<String,Command> commands;
	
	/**
	 * C'tor
	 * @param m
	 * @param v
	 */
	public MyController(Model m, View v) {
		this.m = m;
		this.v = v;
		commands = new HashMap<String,Command>();
		initCommands();
	}
	
	/**
	 * Return Commands HashMap
	 */
	public HashMap<String, Command> getCommands() {
		return commands;
	}
	
	/**
	 * Set view
	 */
	public void setV(View v) {
		this.v = v;
	}

	/**
	 * Set Model
	 */
	public void setM(Model m) {
		this.m = m;
	}
	
	/**
	 * Initialize commands into the controller
	 */
	public void initCommands() {
		commands.put("dir", new Dir(m,v));
		v.setCommands(this.commands);
	}

	/**
	 * Pass 'DIR' result to the view
	 */
	public void passDir(String[] files) {
		v.printDir(files);
	}
	
	/**
	 * Pass error exception to the view
	 * @param e
	 */
	public void passError(Exception e)
	{
		v.printError(e);
	}
}
