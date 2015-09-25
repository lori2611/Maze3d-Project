/*
 * 
 */
package controller;

import java.util.ArrayList;
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
	private HashMap<String,Command> commands; // HashMap with the name of the command and the matching object

	// Declare all warning - messages
	public static final String COMMAND_ERR = "Invalid Command ";	
	
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
		commands.put("exit", new Exit(m,v));

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
	 * Pass Dir command output to the view
	 */
	public void passDir(String[] files) {
		v.printDir(files);
	}

	/**
	 * Pass Exceptions to the view
	 */
	public void passError(Exception e) {
		v.printError(e);
	}

	/**
	 * Pass Messages to the view
	 */
	public void passMessage(String s) {
		v.printMessage(s);
	}

	/**
	 * Pass maze to the view
	 */
	public void passMaze(Maze3d maze) {
		v.printMaze(maze);
	}
	
	/**
	 * Pass cross section of the maze to the view
	 * the view will print the result of the method
	 */
	public void passCrossSection(int[][] maze,int length, int width) {
		v.printCrossSection(maze,length,width);
	}
	
	/**
	 * Pass the size of the maze to the view
	 */
	public void passMazeSize(int size) {
		v.printMazeSize(size);
	}

	/**
	 * Pass the size of the file to the view
	 */
	public void passFileSize(long size) {
		v.printFileSize(size);
	}

	/**
	 * Pass the solution of the required maze to the view
	 */
	public void passSolution(Solution<Position> sol) {
		v.printSolution(sol);
	}
	
	/**
	 * This method will get the input of the client and analyze it.
	 * It will figure the right command and will activate it.
	 */
	public void analyzeCommand(String input) {
		ArrayList<String> paramsList = new ArrayList<String>();
		
		// Check if the input is a command in the HashMap
		while(!commands.containsKey(input))
		{
			// Delete the last word of the input and save it in the ArrayList
			paramsList.add(input.substring(input.lastIndexOf(" ") + 1));
			input = input.substring(0, input.lastIndexOf(" "));
		}
		if(input.equals(""))
		{
			// Print message to the client - invalid command
			v.printMessage(COMMAND_ERR);
		}
		else
		{
			// Reverse the ArrayList and convert to String array
			Collections.reverse(paramsList);
			String[] params = new String[paramsList.size()];
			paramsList.toArray(params);
			
			// Activat command
			commands.get(input).doCommand(params);
		}
	}		
}

