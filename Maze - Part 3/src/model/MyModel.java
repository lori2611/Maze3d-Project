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
	HashMap<String,Solution<Position>> solutions;
	
	public static final String NumOfParams_ERR= "Invalid number of parameters \n";
	
	public MyModel() {
		this.mazes = new HashMap<String,Maze3d>();
		this.solutions = new HashMap<String,Solution<Position>>();
	}
	
	public void setC(Controller c) {
		this.c = c;
	}
	
	public void dir(String[] params) {
		if(params.length == 1)
		{
			String path = params[0];
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
		else
		{
			c.passMessage(NumOfParams_ERR);
		}
	}
	
	public void generateMaze(String[] params) {
		if(params.length == 4)
		{
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				String mazeName = params[0];
				
				// Set maze bounds in x, y, z
				int x = Integer.parseInt(params[1]), y = Integer.parseInt(params[2]), z = Integer.parseInt(params[3]);
				
				// Create and generate new maze with specified bounds
				MyMaze3dGenerator mg = new MyMaze3dGenerator();
				Maze3d maze= mg.generate(x, y, z);
				
				// Check if the name of the maze already exist
				if(mazes.containsKey(mazeName))
				{
					c.passMessage("The specified name is already in use.");
					return;
				}
				else
				{
					mazes.put(mazeName, maze);	
				}
				c.passMessage("maze " + mazeName + " is ready");
			}
		} ).start();
		}
		else
		{
			c.passMessage(NumOfParams_ERR);
		}
	}
	
	public void displayMaze(String[] params) {
		if(params.length == 1)
		{
			String mazeName = params[0];
		
			// Check if the specified name exist in the HashMap
			if(mazes.containsKey(mazeName))
			{
				c.passMaze(mazes.get(mazeName));
			}
			else
			{
				// Pass appropriate message
				c.passMessage("Specified name doesn't exist.");
			}
		}
		else
		{
			c.passMessage(NumOfParams_ERR);
		}
	}

	public void displayCrossSection(String[] params) {
		if(params.length == 4)
		{
			String crossBy = params[0];
			String index = params[1];
			String mazeName = params[3];
		
			// Check that the name of the maze exist in the HashMap
			if(mazes.containsKey(params[3]))
			{
				// Check for the specified cross section
				switch(crossBy.toLowerCase())
				{
					// Pass the right cross section into the controller
					case "x":	
						c.passCrossSection(mazes.get(mazeName).getCrossSectionByX(Integer.parseInt(index)),mazes.get(mazeName).getY(),mazes.get(mazeName).getZ());
						break;
					case "y":
						c.passCrossSection(mazes.get(mazeName).getCrossSectionByY(Integer.parseInt(index)),mazes.get(mazeName).getX(),mazes.get(mazeName).getZ());
						break;
					case "z":
						c.passCrossSection(mazes.get(mazeName).getCrossSectionByZ(Integer.parseInt(index)),mazes.get(mazeName).getX(),mazes.get(mazeName).getY());
						break;
					default:
						c.passError(new IOException("Cannot find cross by " + crossBy));
						break;
				}
			}
			else
			{
				// Name of the maze doesn't exist
				c.passMessage("Specified name doesn't found");
			}
		}
		else
		{
			c.passMessage(NumOfParams_ERR);
		}
	}
	
	public void saveMaze(String[] params) {
		if(params.length == 2)
		{
			String mazeName = params[0];
			String fileName = params[1];
		
			if(mazes.containsKey(params[0]))
			{
				try {
					// Try to save the maze into the file
					OutputStream out=new MyCompressorOutputStream(new FileOutputStream(fileName));
					out.write(mazes.get(mazeName).toByteArray());
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
		else
		{
			c.passMessage(NumOfParams_ERR);
		}
	}
	
	public void loadMaze(String[] params) {
		if(params.length == 2)
		{
			int x,y,z;
			String fileName = params[0];
			String mazeName = params[1];
			try {
				
				// Try to load the maze from the file
				MyDecompressorInputStream in=new MyDecompressorInputStream(new FileInputStream(fileName));
				
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
				mazes.put(mazeName, new Maze3d(full));
				in.close();
			} catch (Exception e) {
				
				// Send error to the controller
				c.passError(e);
			}
		}
		else
		{
			c.passMessage(NumOfParams_ERR);
		}
	}
	
	public void calcMazeSize(String[] params) {
		if(params.length == 1)
		{
			String mazeName = params[0];
			if(mazes.containsKey(mazeName))
			{
				Maze3d maze = mazes.get(mazeName);
				c.passMazeSize((maze.toByteArray()).length);
			}
			else
			{
				c.passMessage("Specified name doesn't found");
			}
		}
		else
		{
			c.passMessage(NumOfParams_ERR);
		}
	}
	
	public void calcFileSize(String[] params) {
		if(params.length == 1)
		{
			String fileName = params[0];
			File file = new File(fileName);
			if(file.exists())
			{
				c.passFileSize(file.length());
			}
			else
			{
				c.passMessage("File doesn't exist");
			}	
		}
		else
		{
			c.passMessage(NumOfParams_ERR);
		}
	}
	
	public void solveMaze(String[] params) {
		if(params.length == 2)
		{
			new Thread(new Runnable() {
				
				@Override
				public void run() {
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
						solutions.put(mazeName, sol);
						c.passMessage("solution for " + mazeName + " is ready");
					}
					else
					{
						c.passMessage("Specified name doesn't found");
					}
				}
			} ).start();
		}
		else
		{
			c.passMessage(NumOfParams_ERR);
		}
	}

	@Override
	public void displaySolution(String[] params) {
		if(params.length == 1)
		{
			String mazeName = params[0];
			if(mazes.containsKey(mazeName))
			{
				if(solutions.containsKey(mazeName))
				{
					c.passSolution(solutions.get(mazeName));
				}
				else
				{
					c.passMessage("The maze has no solution");
				}
			}
			else
			{
				c.passMessage("Specified name doesn't found");
			}	
		}
		else
		{
			c.passMessage(NumOfParams_ERR);
		}
	}
}
