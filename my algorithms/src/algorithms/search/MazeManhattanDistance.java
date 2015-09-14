package algorithms.search;

import algorithms.mazeGenerators.Position;

/**
 * @author Lori Atar
 * The Class MazeManhattanDistance.
 * calculates the Manhattan-distance from the current position to the goal.
 */
public class MazeManhattanDistance implements Heuristic<Position> {

	/* 
	 * Calculate the distance by calculating the Manhattan-distance
	 */
	@Override
	public double h(State<Position> s1, State<Position> s2) {
		
		// Save the difference between each 2 points (x to x, y to y, z to z)
		int sx,sy,sz;
		
		// Calculate difference using 'abs' method to check that the value is positive
		sx = Math.abs(s1.getState().getX() - s2.getState().getX());
		sy = Math.abs(s1.getState().getY() - s2.getState().getY());
		sz = Math.abs(s1.getState().getZ() - s2.getState().getZ());
		
		// Return Manhattan distance
		return (sx+sy+sz);
	}

}
