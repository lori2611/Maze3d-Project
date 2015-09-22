package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.MyMaze3dGenerator;
import controller.Controller;

public class MyModel implements Model {

	private Controller c;
	HashMap<String,Maze3d> mazes;
	
	public MyModel() {
		this.mazes = new HashMap<String,Maze3d>();
	}
	
	public void dir(String path) {
		File file = new File(path);
		
		// Save all files names in an array
		String[] files = file.list();
		c.passDir(files);
	}
	
	public void setC(Controller c) {
		this.c = c;
	}
	
	public void generateMaze(String[] args) {
		
		// Set maze bounds in x, y, z
		int x = Integer.parseInt(args[1]), y = Integer.parseInt(args[2]), z = Integer.parseInt(args[3]);
		
		// Create and generate new maze with specified bounds
		MyMaze3dGenerator mg = new MyMaze3dGenerator();
		Maze3d maze= mg.generate(x, y, z);
		
		// Check if the name of the maze already exist
		if(this.mazes.containsKey(args[0]))
		{
			c.passMessage("The specified name is already in use.");
			return;
		}
		else
		{
			mazes.put(args[0], maze);	
		}
		String msg = "maze " + args[0] + " is ready";
		c.passMessage(msg);
	}
	
	public void printMaze(String[] args) {
		
		// Check if the specified name exist in the HashMap
		if(mazes.containsKey(args[0]))
		{
			c.passMaze(mazes.get(args[0]));
		}
		else
		{
			// Pass appropriate message
			c.passMessage("Specified name doesn't exist.");
		}
	}

	public void displayCrossSection(String[] params) {
		
		// Check that the name of the maze exist in the HashMap
		if(mazes.containsKey(params[3]))
		{
			// Check for the specified cross section
			switch(params[0].toLowerCase())
			{
				case "x":	
					c.passCrossSection(mazes.get(params[3]).getCrossSectionByX(Integer.parseInt(params[1])),mazes.get(params[3]).getY(),mazes.get(params[3]).getZ());
					break;
				case "y":
					c.passCrossSection(mazes.get(params[3]).getCrossSectionByY(Integer.parseInt(params[1])),mazes.get(params[3]).getX(),mazes.get(params[3]).getZ());
					break;
				case "z":
					c.passCrossSection(mazes.get(params[3]).getCrossSectionByZ(Integer.parseInt(params[1])),mazes.get(params[3]).getX(),mazes.get(params[3]).getY());
					break;
				default:
					c.passError(new IOException("Cannot find cross by " + params[0]));
					break;
			}
		}
		else
		{
			// Name of the maze doesn't exist
			c.passMessage("Specified name doesn't found");
		}
	}
}
