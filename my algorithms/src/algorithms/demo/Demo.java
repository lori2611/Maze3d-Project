/*
 * 
 */
package algorithms.demo;

import algorithms.mazeGenerators.Maze3d;
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
/**
 * 
 * @author Lori Atar
 * The class Demo
 */
public class Demo {

	/**
	 * Run.
	 */
	public static void Run()
	{
		// Create the maze 
		MyMaze3dGenerator mg = new MyMaze3dGenerator();
		Maze3d maze = mg.generate(6,6,2);
		System.out.println("The maze: \n" + maze);
		System.out.println("Start Position: " + maze.getStartPosition());
		System.out.println("Goal Position: " + maze.getGoalPosition() + "\n");
		
		// Create 'searchable' maze to solve the algorithm
		Searchable<Position> sm = new Maze3dSearchable(maze);
		
		// BFS solution
		Searcher<Position> bfs = new BFS<Position>();
		Solution<Position> sol1 = bfs.search(sm);
		//System.out.println(sol1);
		System.out.println("BFS - Number of nodes evaluated: " + bfs.getNumberOfNodesEvaluated());
		
		// AStar - Manhattan distance solution
		Heuristic<Position> ManhattanDistance = new MazeManhattanDistance();
		Searcher<Position> astar_manhattan = new AStar<Position>(ManhattanDistance);
		Solution<Position> sol2 = astar_manhattan.search(sm);
		//System.out.println(sol2);
		System.out.println("AStar - ManhattanDistance - Number of nodes evaluated: " + astar_manhattan.getNumberOfNodesEvaluated());
		
		// AStar - Air distance solution
		Heuristic<Position> AirDistance = new MazeAirDistance();
		Searcher<Position> astar2 = new AStar<Position>(AirDistance);
		Solution<Position> sol3 = astar2.search(sm);
		System.out.println("AStar - AirDistance - Number of nodes evaluated: " + astar2.getNumberOfNodesEvaluated() + "\n");
		
		// Prints solution
		System.out.println(sol3);
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {			
		Run();
		System.out.println("DONE");
	}

}
