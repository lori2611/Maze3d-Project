package driver;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.MyMaze3dGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.mazeGenerators.SimpleMaze3dGenerator;

public class Run {
	private static void testMazeGenerator(Maze3dGenerator mg){
		// prints the time it takes the algorithm to run
		System.out.println(mg.measureAlgorithmTime(5,3,5));
		// generate another 3d maze
		Maze3d maze=mg.generate(5,3,5);
		// get the maze entrance
		Position p=maze.getStartPosition();
		// print the position
		System.out.println(p); // format "{x,y,z}"
		// get all the possible moves from a position
		String[] moves=maze.getPossibleMoves(p);
		// print the moves
		for(String move : moves)
				System.out.println(move);
		// prints the maze exit position
		System.out.println(maze.getGoalPosition());
		try{
			// get 2d cross sections of the 3d maze
			int[][] maze2dx=maze.getCrossSectionByX(2);
			System.out.println("Cross byX: \n");
			printMaze2d(maze2dx.length, maze2dx[0].length, maze2dx);
			
			int[][] maze2dy=maze.getCrossSectionByY(2);
			System.out.println("Cross byY: \n");
			printMaze2d(maze2dy.length, maze2dy[0].length, maze2dy);
			
			int[][] maze2dz=maze.getCrossSectionByZ(0);
			System.out.println("Cross byZ: \n");
			printMaze2d(maze2dz.length, maze2dz[0].length, maze2dz);
			
			// this should throw an exception!
			maze.getCrossSectionByX(-1);
		} catch (IndexOutOfBoundsException e){
			System.out.println("good!");
		}
	}
	
//	public static void main(String[] args) {		
//		testMazeGenerator(new SimpleMaze3dGenerator());
//		testMazeGenerator(new MyMaze3dGenerator());	
//	}

	// Gets x,y and print maze2d
	private static void printMaze2d(int x,int y,int[][] maze2d)
	{
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				if(j+1 == y)
					System.out.print(maze2d[i][j]);
				else
					System.out.print(maze2d[i][j] + ",");
			}
			System.out.println();
		}
		System.out.println();
	}
}
