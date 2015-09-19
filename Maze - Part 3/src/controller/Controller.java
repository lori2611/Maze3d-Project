package controller;

import java.util.HashMap;

import model.Model;
import view.View;

public interface Controller {

	public HashMap<String, Command> getCommands();
	
	public void passDir(String[] files);
	
	public void setM(Model m);
	
	public void setV(View v);
}
