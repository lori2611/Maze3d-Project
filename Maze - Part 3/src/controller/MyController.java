package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import algorithms.mazeGenerators.Maze3d;
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
		commands.put("dir", new Dir(m,v));
		commands.put("generate", new Generate(m,v));
		commands.put("display", new DisplayName(m,v));
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
	 * Pass 'DIR' result to the view
	 */
	public void passDir(String[] files) {
		v.printDir(files);
	}
	
	/**
	 * Pass error exception to the view
	 * @param e
	 */
	public void passError(Exception e) {
		v.printError(e);
	}
	
	public void passMessage(String s) {
		v.printMessage(s);
	}
	
	@Override
	public void passMazes(ArrayList<Maze3d> arrayList) {
		v.printMazes(arrayList);
		
	}
	
	public void analyzeCommand(String input) {
		String[] args = input.split(" ");
		String[] params = null;
		if(!commands.containsKey(args[0]))
		{
			IOException e = new IOException("Invalid Command");
			v.printError(e);
		}
		else
		{
			if(!input.startsWith("display"))
			{
				switch(args[0])
				{
					case "dir":
						if(args.length == 2)
						{
							input = input.replace("dir ","");
							params = input.split(" ");
							getObject(commands, "dir").doCommand(params);
						}
						else
						{
							v.printError(new IOException("Invalid number of parameters"));
						}
						break;
						
					case "generate":
						if(args.length == 7 && input.startsWith("generate 3d maze "))
						{
							input = input.replace("generate 3d maze ","");
							params = input.split(" ");
							getObject(commands, "generate").doCommand(params);
						}
						break;
				}
			}
			else
			{
				if(input.startsWith("display cross section by "))
				{
					
				}
				else if (input.startsWith("display solution "))
				{
					
				}
				else
				{
					input = input.replace("display ", "");
					params = input.split(" ");
					if(params.length == 1)
					{
						getObject(commands, "display").doCommand(params);
					}
					else
					{
						v.printError(new IOException("Invalid command."));
					}
				}
			}
		}
	}
	
	// HelpMethod - Search the right command in our 'commands' hashmap and
	public static Command getObject(HashMap<String, Command> commands,String c) {
		for(String command : commands.keySet())
		{
			if(command.equals(c))
			{
				return commands.get(command);
			}
		}
		return null;
	}
}
