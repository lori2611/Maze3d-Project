/*
 * 
 */
package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import algorithms.mazeGenerators.Maze3d;
import model.Model;
import view.View;

public class MyController implements Controller {

	/** The v. */
	private View v;
	
	/** The m. */
	private Model m;
	
	/** The commands. */
	HashMap<String,Command> commands;
	
	/**
	 * Instantiates a new my controller.
	 *
	 * @param m the m
	 * @param v the v
	 */
	public MyController(Model m, View v) {
		this.m = m;
		this.v = v;
		commands = new HashMap<String,Command>();
		commands.put("dir", new Dir(m,v));
		commands.put("generate", new Generate(m,v));
		commands.put("display", new DisplayName(m,v));
		commands.put("cross", new DisplayCross(m,v));
	}
	
	public HashMap<String, Command> getCommands() {
		return commands;
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

	public void passError(Exception e) {
		v.printError(e);
	}

	public void passMessage(String s) {
		v.printMessage(s);
	}

	@Override
	public void passMaze(Maze3d maze) {
		v.printMaze(maze);
	}
	
	public void passCrossSection(int[][] maze,int length, int width) {
		v.printCrossSection(maze,length,width);
	}
	
	public void analyzeCommand(String input) {
		String[] args = input.split(" ");
		String[] params = null;
		if(!commands.containsKey(args[0]))
		{
			v.printError(new IOException("Invalid Command"));
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
							commands.get("dir").doCommand(params);
						}
						else
						{
							v.printError(new IOException("Invalid number of parameters"));
						}
						break;
						
					case "generate":
						if(args.length == 7)
						{
							if(input.startsWith("generate 3d maze "))
							{
								input = input.replace("generate 3d maze ","");
								params = input.split(" ");
								commands.get("generate").doCommand(params);
							}
							else
							{
								v.printError(new IOException("Invalid Command"));
							}
						}
						else
						{
							v.printError(new IOException("Invalid number of parameters"));
						}
						break;
				}
			}
			else
			{
				if(input.startsWith("display cross section by "))
				{
					input = input.replace("display cross section by ", "");
					params = input.split(" ");
					if(params.length == 4)
					{
						commands.get("cross").doCommand(params);
					}
					else
					{
						v.printError(new IOException("Invalid number of parameters"));
					}
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
						commands.get("display").doCommand(params);
					}
					else
					{
						v.printError(new IOException("Invalid number of parameters"));
					}
				}
			}
		}
	}
}
