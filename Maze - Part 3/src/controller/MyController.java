/*
 * 
 */
package controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
	private String[] args;
	private int i;	
	
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
		commands.put("generate 3d maze", new Generate(m,v));
		commands.put("display", new DisplayMaze(m,v));
		commands.put("display cross section by", new DisplayCross(m,v));
		commands.put("save maze", new SaveMaze(m,v));
		commands.put("load maze", new LoadMaze(m,v));
		commands.put("maze size", new MazeSize(m,v));
		commands.put("file size", new FileSize(m,v));
		commands.put("solve", new Solve(m,v));
		commands.put("display solution", new DisplaySolution(m,v));
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
		args = null;
		i = 1;
		ArrayList<String> paramsList = new ArrayList<String>();
		while(!commands.containsKey(input))
		{
			args = input.split(" ");
			paramsList.add(input.substring(input.lastIndexOf(" ") + 1));
			input = input.substring(0, input.lastIndexOf(" "));
			++i;
		}
		if(input.equals(""))
		{
			v.printMessage(COMMAND_ERR);
		}
		else
		{
			Collections.reverse(paramsList);
			String[] params = new String[paramsList.size()];
			paramsList.toArray(params);
			commands.get(input).doCommand(params);
		}
	}
				
}

