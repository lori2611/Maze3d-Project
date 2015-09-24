/*
 * 
 */
package controller;

import java.util.HashMap;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import model.Model;
import view.View;


public class MyController implements Controller {

	private View v; // View
	private Model m; // Model
	HashMap<String,Command> commands; // HashMap with the name of the command and the matching object
	
	// Declare all warning - messages
	public static final String COMMAND_ERR = "Invalid Command \n";	
	public static final String NumOfParams_ERR= "Invalid number of parameters \n";	
	
	/**
	 * Instantiates a new my controller.
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
		commands.put("save", new SaveMaze(m,v));
		commands.put("load", new LoadMaze(m,v));
		commands.put("maze", new MazeSize(m,v));
		commands.put("file", new FileSize(m,v));
		commands.put("solve", new Solve(m,v));
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
	
	public void passMazeSize(int size) {
		v.printMazeSize(size);
	}
	
	public void passFileSize(long size) {
		v.printFileSize(size);
	}
	
	public void passSolution(Solution<Position> sol) {
		v.printSolution(sol);
	}
	
	public void analyzeCommand(String input) {
		String[] args = input.split(" ");
		String[] params = null;
		
		// Check if first word of command exist in HashMap
		if(!commands.containsKey(args[0]))
		{
			v.printMessage(COMMAND_ERR);
		}
		else
		{
				switch(args[0])
				{
				case "dir":
					if(args.length == 2)
					{
						// Cut the 'dir' from the command and send only the parameters
						input = input.replace("dir ","");
						params = input.split(" ");
						commands.get("dir").doCommand(params);
					}
					else
					{
						v.printMessage(NumOfParams_ERR);
					}
					break;
						
				case "generate":
					if(args.length == 7)
					{
						if(input.startsWith("generate 3d maze "))
						{
							// Cut the statement of the command and send only the parameters
							input = input.replace("generate 3d maze ","");
							params = input.split(" ");
							commands.get("generate").doCommand(params);
						}
						else
						{
							v.printMessage(COMMAND_ERR);
						}
					}
					else
					{
						v.printMessage(NumOfParams_ERR);
					}
					break;
					
				case "save":
					if(args.length == 4)
					{
						if(input.startsWith("save maze "))
						{
							input = input.replace("save maze ", "");
							params = input.split(" ");
							commands.get("save").doCommand(params);
						}
						else
							{
								v.printMessage(COMMAND_ERR);
							}
					}
					else
					{
						v.printMessage(NumOfParams_ERR);
					}
					break;
					
				case "load":
					if(args.length == 4)
					{
						if(input.startsWith("load maze "))
						{
							input = input.replace("load maze ", "");
							params = input.split(" ");
							commands.get("load").doCommand(params);
						}
						else
						{
							v.printMessage(COMMAND_ERR);
						}
					}
					else
					{
						v.printMessage(NumOfParams_ERR);
					}
					break;
					
				case "maze":
					if(args.length == 3)
					{
						if(input.startsWith("maze size "))
						{
							input = input.replace("maze size ", "");
							params = input.split(" ");
							commands.get("maze").doCommand(params);
						}
						else
						{
							v.printMessage(COMMAND_ERR);
						}
					}
					else						{
						v.printMessage(NumOfParams_ERR);
					}
					break;
					
				case "file":
					if(args.length == 3)
					{
						if(input.startsWith("file size "))
						{
							input = input.replace("file size ", "");
							params = input.split(" ");
							commands.get("file").doCommand(params);
						}
						else
						{
							v.printMessage(COMMAND_ERR);
						}
					}
					else
					{
						v.printMessage(NumOfParams_ERR);
					}
					break;
					
				case "solve":
					if(args.length == 3)
					{
						input = input.replace("solve ", "");
						params = input.split(" ");
						commands.get("solve").doCommand(params);
					}
					else
					{
						v.printMessage(NumOfParams_ERR);
					}
					break;
					
				case "display":
					if(input.startsWith("display cross section by "))
					{
						// Cut the statement of the command and send only the parameters
						input = input.replace("display cross section by ", "");
						params = input.split(" ");
						if(params.length == 4)
						{
							commands.get("cross").doCommand(params);
						}
						else
						{
							v.printMessage(NumOfParams_ERR);
						}
					}
					else if (input.startsWith("display solution "))
					{
						// Cut the statement of the command and send only the parameters
						input = input.replace("display solution ", "");
						params = input.split(" ");
						/**
						 * Body
						 */
					}
					else
					{
						// Cut the statement of the command and send only the parameters
						input = input.replace("display ", "");
						params = input.split(" ");
						if(params.length == 1)
						{
							commands.get("display").doCommand(params);
						}
						else
						{
							v.printMessage(NumOfParams_ERR);
						}
					}
				}	
			}
		}
	}

