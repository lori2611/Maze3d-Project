package algorithms.search;

import algorithms.mazeGenerators.Position;

/**
 * @author Lori Atar
 * The Class MazeAirDistance.
 * calculates the air-distance from the current position to the goal.
 */
public class MazeAirDistance implements Heuristic<Position> {

	/* 
	 * Calculate the distance by calculating the air-distance
	 */
	@Override
	public double h(State<Position> s1, State<Position> s2) {
		// Save the difference between each 2 points (x to x, y to y, z to z)
		double calc=0,sx,sy,sz;
		
		// Calculate difference using 'abs' method to check that the value is positive
		sx = Math.abs(s1.getState().getX() - s2.getState().getX());
		sy = Math.abs(s1.getState().getY() - s2.getState().getY());
		sz = Math.abs(s1.getState().getZ() - s2.getState().getZ());
		
		// Calculate the sum of squared distance 
		calc = Math.pow(sx, 2) + Math.pow(sy, 2) + Math.pow(sz, 2);
		
		// Calculate nth root
		calc = Math.pow(calc, 0.5);
		
		// Return air distance
		return calc;
	}

}
