/*
 * 
 */
package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Random;

/**
 * 
 * @author Lori Atar
 * Generate maze based on Prim's algorithm pseudo code
 */
public class MyMaze3dGenerator extends AbstMaze3dGenerator{

	/* 
	 * Generate maze based on Prim's algorithm pseudo code
	 */
	@Override
	public Maze3d generate(int x, int y, int z) {
		
		// Create temporary maze and position
		int px,py,pz,size,randomWall;
		Maze3d maze = new Maze3d(x,y,z);
		Position p = new Position(0, 0, 0);
		ArrayList<Position> walls = new ArrayList<Position>();
		
		// Fill the maze with walls (1)
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				for (int k = 0; k < z; k++) {
					p.setPosition(i, j, k);
					maze.setPositionValue(p,1);
				}
			}
		}
		
		// Create 'Random' object 
		Random random = new Random();
		
		// Choose random position to the start of the maze
		px = random.nextInt(x);
		py = random.nextInt(y);
		pz = random.nextInt(z);
		
		// Set our start position
		maze.setPositionValue(p, 0);
		
		// Add the walls around our point to 'walls' ArrayList
		addWalls(maze, p, walls);
		
		// Using prim's algorithm to create the maze
		while(!walls.isEmpty())
		{	
			// Choose Random wall from ArrayList
			size = walls.size();
			randomWall = random.nextInt(size);
			p = walls.get(randomWall);
			
			
			// Check if there is only one zero around the current wall
			if(isSingle(p, maze))
			{
				// Set current position as zero and add the last zero in the maze as 'GoalPosition'
				maze.setPositionValue(p, 0);
				maze.setGoalPosition(p);
				addWalls(maze, p, walls);
			}
			
			// Remove current position from walls ArrayList
			walls.remove(p);
		}
		
		// Choose random startPosition for MyMaze at the bottom of the maze
		do
		{
			px = random.nextInt(x);
			py = random.nextInt(y);
			if(maze.getPositionValue(px,py,0) == 0)
				{
					p = new Position(px,py,0);
					maze.setStartPosition(p);
				}
		}while(maze.getStartPosition() == null);
		
		return maze;
	}
	
	/**
	 * Check if there is only one zero around current cell
	 *
	 * @param p the p
	 * @param maze the maze
	 * @return true, if is single
	 */
	private boolean isSingle(Position p,Maze3d maze)
	{
		int count=0;
		
		if(maze.getRight(p)==0)
		{ 
			++count;
		}
		if(maze.getLeft(p)==0)
		{ 
			++count;
		}
		if(maze.getUp(p)==0 && count <= 1)
		{ 
			++count;
		}
		if(maze.getDown(p)==0 && count <= 1)
		{ 
			++count;
		}
		if(maze.getFront(p)==0 && count <= 1)
		{ 
			++count;
		}
		if(maze.getBack(p)==0 && count <= 1)
		{ 
			++count;
		}
		
		// Return boolean expression
		return (count == 1);
	}
	
	/**
	 * Adds the walls.
	 *
	 * @param maze the maze
	 * @param p the p
	 * @param walls the walls
	 */
	//Add the neighboring walls of the cell to the wall list
	private void addWalls(Maze3d maze,Position p,ArrayList<Position> walls)
	{
		Position temp = new Position(0, 0, 0);
		int px,py,pz;
		px = p.getX();
		py = p.getY();
		pz = p.getZ();
		
		// Check all the neighbors of current position and add walls to ArrayList
		if(maze.getRight(p)==1)
		{ 
			temp = new Position(px+1,py,pz);
			walls.add(temp);
		}
		if(maze.getLeft(p)==1)
		{ 
			temp = new Position(px-1,py,pz);
			walls.add(temp);
		}
		if(maze.getUp(p)==1)
		{ 
			temp = new Position(px,py,pz+1);
			walls.add(temp);
		}
		if(maze.getDown(p)==1)
		{ 
			temp = new Position(px,py,pz-1);
			walls.add(temp);
		}
		if(maze.getFront(p)==1)
		{ 
			temp = new Position(px,py+1,pz);
			walls.add(temp);
		}
		if(maze.getBack(p)==1)
		{ 
			temp = new Position(px,py-1,pz);
			walls.add(temp);
		}
	}
}
