package controller;

import java.util.HashMap;

import model.Model;
import view.View;

public class MyController implements Controller {

	private View v;
	private Model m;
	HashMap<String,Command> commands;
	
	public MyController() {
		commands = new HashMap<String,Command>();
		commands.put("dir", new Dir(m,v));
	}
	
	public void setV(View v) {
		this.v = v;
	}

	public void setM(Model m) {
		this.m = m;
	}

	public void passDir(String[] files) {
		v.printDir(files);
	}

	public HashMap<String, Command> getCommands() {
		return commands;
	}
	
	
}
