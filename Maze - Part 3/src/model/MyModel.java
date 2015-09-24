package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.HashMap;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Maze3dSearchable;
import algorithms.mazeGenerators.MyMaze3dGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.search.AStar;
import algorithms.search.BFS;
import algorithms.search.Heuristic;
import algorithms.search.MazeAirDistance;
import algorithms.search.MazeManhattanDistance;
import algorithms.search.Searchable;
import algorithms.search.Searcher;
import algorithms.search.Solution;
import controller.Controller;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;

public class MyModel implements Model {

	private Controller c;
	HashMap<String,Maze3d> mazes;
	
	public MyModel() {
		this.mazes = new HashMap<String,Maze3d>();
	}
	
	public void setC(Controller c) {
		this.c = c;
	}
	
	public void dir(String path) {
		try {
		String[] files = null;
		File file = new File(path);
		// Save all files names in an array
		files = file.list();
		if(files.length == 0)
		{
			c.passMessage("Folder is empty ");
		}
		c.passDir(files);
		} catch (Exception e) {
			c.passMessage("Invalid Path ");
		}
	}
	
	public void generateMaze(String[] params) {
		
		// Set maze bounds in x, y, z
		int x = Integer.parseInt(params[1]), y = Integer.parseInt(params[2]), z = Integer.parseInt(params[3]);
		
		// Create and generate new maze with specified bounds
		MyMaze3dGenerator mg = new MyMaze3dGenerator();
		Maze3d maze= mg.generate(x, y, z);
		
		// Check if the name of the maze already exist
		if(this.mazes.containsKey(params[0]))
		{
			c.passMessage("The specified name is already in use.");
			return;
		}
		else
		{
			mazes.put(params[0], maze);	
		}
		String msg = "maze " + params[0] + " is ready";
		c.passMessage(msg);
	}
	
	public void printMaze(String[] params) {
		
		// Check if the specified name exist in the HashMap
		if(mazes.containsKey(params[0]))
		{
			c.passMaze(mazes.get(params[0]));
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
				// Pass the right cross section into the controller
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
	
	public void saveMaze(String[] params) {
		if(mazes.containsKey(params[0]))
		{
			try {
				// Try to save the maze into the file
				OutputStream out=new MyCompressorOutputStream(new FileOutputStream(params[1]));
				out.write(mazes.get(params[0]).toByteArray());
				out.flush();
				out.close();
			} catch (Exception e) {
				
				// Send error to the controller
				c.passError(e);
			}
		}
		else
		{
			// Name of the maze doesn't exist
			c.passMessage("Specified name doesn't found");
		}
	}
	
	public void loadMaze(String[] params) {
		int x,y,z;
		try {
			// Try to load the maze from the file
			MyDecompressorInputStream in=new MyDecompressorInputStream(new FileInputStream(params[0]));
			
			// At the start array we will insert the details of the maze
			// start position, end position, bounds of array
			byte[] start = new byte[36];
			in.read(start,0,start.length);
			ByteBuffer wrap = ByteBuffer.wrap(start);
			wrap.position(24);
			
			// Keep the bounds of the array
			x = wrap.getInt();
			y = wrap.getInt();
			z = wrap.getInt();
			
			// Create array by the size of the maze
			byte[] end =new byte[x*y*z];
			in.read(end,0,end.length);
			
			// Create full array with all the data of the maze (start details and his pathway data)
			byte[] full = new byte[start.length + end.length];
			System.arraycopy(start, 0, full, 0, start.length);
			System.arraycopy(end, 0, full, start.length, end.length);
			
			// Add the maze to the HashMap
			mazes.put(params[1], new Maze3d(full));
			in.close();
		} catch (Exception e) {
			
			// Send error to the controller
			c.passError(e);
		}
	}
	
	public void calcMazeSize(String[] params) {
		if(mazes.containsKey(params[0]))
		{
			Maze3d maze = mazes.get(params[0]);
			c.passMazeSize((maze.toByteArray()).length);
		}
		else
		{
			c.passMessage("Specified name doesn't found");
		}
	}
	
	public void calcFileSize(String[] params) {
		File file = new File(params[0]);
		if(file.exists())
		{
			c.passFileSize(file.length());
		}
		else
		{
			c.passMessage("File doesn't exist");
		}
	}
	
	public void solveMaze(String[] params) {
		
		String mazeName = params[0];
		String algorithm = params[1].toLowerCase();
		Searcher<Position> algo = null;
		Solution<Position> sol = null;
		
		if(mazes.containsKey(mazeName))
		{
			Searchable<Position> sm = new Maze3dSearchable(mazes.get(mazeName));
			if(algorithm.equals("bfs"))
			{
				algo = new BFS<Position>();
			}
			else if(algorithm.equals("manhattan"))
			{
				Heuristic<Position> ManhattanDistance = new MazeManhattanDistance();
				algo = new AStar<Position>(ManhattanDistance);
			}
			else if(algorithm.equals("air"))
			{
				Heuristic<Position> AirDistance = new MazeAirDistance();
				algo = new AStar<Position>(AirDistance);
			}
			sol = algo.search(sm);
			c.passMessage("solution for " + mazeName + " is ready");
		}
		else
		{
			c.passMessage("Specified name doesn't found");
		}
	}
}
