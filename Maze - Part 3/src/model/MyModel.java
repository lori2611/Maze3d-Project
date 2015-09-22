package model;

import java.io.File;
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
		String[] files = file.list();
		c.passDir(files);
	}
	
	public void setC(Controller c) {
		this.c = c;
	}
	
	public void generateMaze(String[] args) {
		int x = Integer.parseInt(args[1]), y = Integer.parseInt(args[2]), z = Integer.parseInt(args[3]);
		MyMaze3dGenerator mg = new MyMaze3dGenerator();
		Maze3d maze= mg.generate(x, y, z);
		mazes.put(args[0], maze);
		String msg = "maze " + args[0] + " is ready";
		c.passMessage(msg);
	}

}
