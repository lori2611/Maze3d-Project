package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import controller.Controller;

public class MyView implements View {
	
	private Controller c;
	private CLI cli;
	
	public MyView() {
		this.cli = new CLI(new BufferedReader(new InputStreamReader(System.in)), new PrintWriter(System.out), this); 
	}
	
	public void passInput(String input) {
		c.analyzeCommand(input);
	}
	
	public void setC(Controller c) {
		this.c = c;
	}
	
	public void start() throws IOException{
		try{
			cli.start();
		} catch(Exception e) {
			throw e;
		}
	}
	
	public void printDir(String[] files) {
		for(String file : files)
		{
			// Print all files names from the array
			cli.getOut().println(file);
			cli.getOut().flush();
		}
	}
	
	public void printMessage(String s) {
		
		// Print the message
		cli.getOut().println(s);
		cli.getOut().flush();
	}
	
	public void printError(Exception e) {
		
		// Print exception stack trace
		cli.getOut().println(e.getMessage());
		cli.getOut().flush();
	}
	
	public void printMaze(Maze3d maze) {

		// Print the maze
		cli.getOut().println(maze);
		cli.getOut().flush();
	}
	
	public void printSolution(Solution<Position> sol) {
		
		// Print Solution
		cli.getOut().println(sol);
		cli.getOut().flush();
	}

	@Override
	public void printCrossSection(int[][] maze, int length, int width) {
		String s = "\n";
		
		// Build the cross of the maze as a string
		for (int i = 0; i < length; i++) {
			s += "[ ";
			for (int j = 0; j < width; j++) {
				if(j == width -1)
					s += maze[i][j];
				else
					s = s + maze[i][j] + ","; 
			}
			s += " ] \n";
		}
		
		// Print the cross of the maze
		cli.getOut().println(s);
		cli.getOut().flush();
	}
	
	public void printMazeSize(int size) {
		
		// Print the size of the maze in bytes
		cli.getOut().println(size + " bytes");
		cli.getOut().flush();
	}
	
	public void printFileSize(long size) {
		
		// Print the size of the file which saves maze 
		cli.getOut().println(size + " bytes");
		cli.getOut().flush();
	}
}
