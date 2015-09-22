package model;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.MyMaze3dGenerator;
import controller.Controller;

public class MyModel implements Model {

	private Controller c;
	HashMap<String,ArrayList<Maze3d>> mazes;
	
	public MyModel() {
		this.mazes = new HashMap<String,ArrayList<Maze3d>>();
	}
	
	public void dir(String path) {
		File file = new File(path);
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
			// Add the new maze to the ArrayList
			for(String name : this.mazes.keySet())
			{
				if(name.equals(args[0]))
				{
					mazes.get(name).add(maze);
				}
			}
		}
		else
		{
			// Create new ArrayList and add the new name into the HashMap
			ArrayList<Maze3d> list = new ArrayList<Maze3d>();
			list.add(maze);
			mazes.put(args[0], list);	
		}
		String msg = "maze " + args[0] + " is ready";
		c.passMessage(msg);
	}
	
	public void printMazes(String[] args) {
		if(mazes.containsKey(args[0]))
		{
			c.passMazes(mazes.get(args[0]));
		}
		else
		{
			c.passMessage("Specified name doesn't exist.");
		}
	}

}
