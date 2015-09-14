/*
 * 
 */
package algorithms.mazeGenerators;

import java.util.Random;

import algorithms.mazeGenerators.Position;

/**
 * @author Lori Atar
 * Generate simple-maze.
 */
public class SimpleMaze3dGenerator extends AbstMaze3dGenerator {

	/* 
	 * Generate maze3d by selecting 2 positions and connect them
	 */
	public Maze3d generate(int x, int y, int z) {
		
		// Create temporary maze and position
		Maze3d maze = new Maze3d(x,y,z);
		Position p = new Position(0,0,0);
		
		// Create 'Random' object 
		Random random = new Random();
		
		// Fill the maze with (0) and (1)
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				for (int k = 0; k < z; k++) {
					p.setPosition(i, j, k);
					maze.setPositionValue(p, random.nextInt(2));
				}
			}
		}
		
		/* To make sure that our maze has a solution-
		 * we will get 2 random different numbers from 1 to 6 (1,2-up,down,3,4-right,left,5,6-front,back)
		 * each number present other wall in our maze.
		 * Then we will just find 2 random positions (one on each wall)
		 * and connect them.
		 */
		
		int px = -1,py = -1,pz = -1,count = -1,min,max;
		Position pStart = null,pEnd = null;
		int[] walls = new int[2];
		
		// Get 2 random numbers
		walls[0] = random.nextInt(6) + 1;
		walls[1] = random.nextInt(6) + 1;
		
		// Check that they are different
		while(walls[0] == walls[1])
		{
			walls[1] = random.nextInt(6) + 1;
		}
		
		for(int i : walls)
		{
		//	System.out.println("i:" + i);
			++count;
			switch(i)
			{
				case 1: pz = z-1;
				break;
				case 2: pz = 0;
				break;
				case 3: px = x-1;
				break;
				case 4: px = 0;
				break;
				case 5: py = y-1;
				break;
				case 6: py = 0;
				break;
			}
			
			// Pick random numbers for remaining spots
			if(px < 0)
				px = random.nextInt(x);
			if(py < 0)
				py = random.nextInt(y);
			if(pz < 0)
				pz = random.nextInt(z);
			
			// Set Start + End position
			if(count == 0)
				{
					pStart = new Position(px,py,pz);
					maze.setStartPosition(pStart);
				}
			else
				{
					pEnd = new Position(px,py,pz);	
					maze.setGoalPosition(pEnd);
				}	
			
			// Reset points
			px = -1;
			py = -1;
			pz = -1;
		}
		
		// Connect startZ to endZ 
		min = pStart.getZ();
		max = pEnd.getZ();
		if( min <= max)
			for (int i = min; i <= max; i++) {
				pStart.setZ(i);
				maze.setPositionValue(pStart, 0);
			}
		else
			for (int i = min; i >= max; i--) {
				pStart.setZ(i);
				maze.setPositionValue(pStart, 0);
			}
		
		// Connect startY to endY 
		min = pStart.getY();
		max = pEnd.getY();
		if( min <= max)
			for (int i = min; i <= max; i++) {
				pStart.setY(i);
				maze.setPositionValue(pStart, 0);
			}
		else
			for (int i = min; i >= max; i--) {
				pStart.setY(i);
				maze.setPositionValue(pStart, 0);
			}
		
		// Connect startX to endX 
		min = pStart.getX();
		max = pEnd.getX();
		if( min <= max)
			for (int i = min; i <= max; i++) {
				pStart.setX(i);
				maze.setPositionValue(pStart, 0);
			}
		else
			for (int i = min; i >= max; i--) {
				pStart.setX(i);
				maze.setPositionValue(pStart, 0);
			}
		
		return maze;
	}
}