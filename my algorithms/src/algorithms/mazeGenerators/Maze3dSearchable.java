/*
 * 
 */
package algorithms.mazeGenerators;

import java.util.ArrayList;

import algorithms.search.Searchable;
import algorithms.search.State;

/**
 * 
 * @author Lori Atar
 * Class adapter to make the maze 'searchable' object.
 */
public class Maze3dSearchable implements Searchable<Position> {

	/** The maze. */
	private Maze3d maze;
	
	/**
	 * Instantiates a new maze3d searchable.
	 *
	 * @param maze the maze
	 */
	public Maze3dSearchable(Maze3d maze) {
		this.setMaze(maze);
	}
	
	/* (non-Javadoc)
	 * @see algorithms.search.Searchable#getStartState()
	 */
	public State<Position> getStartState() {
		State<Position> state = new State<Position>();
		state.setState(maze.getStartPosition());
		return state;
	}

	/* (non-Javadoc)
	 * @see algorithms.search.Searchable#getGoalState()
	 */
	public State<Position> getGoalState() {
		State<Position> state = new State<Position>();
		state.setState(maze.getGoalPosition());
		return state;
	}

	/**
	 * Gets the maze.
	 *
	 * @return the maze
	 */
	public Maze3d getMaze() {
		return maze;
	}

	/**
	 * Sets the maze.
	 *
	 * @param maze the new maze
	 */
	public void setMaze(Maze3d maze) {
		this.maze = maze;
	}

	/* (non-Javadoc)
	 * @see algorithms.search.Searchable#getAllPossibleStates(algorithms.search.State)
	 */
	@Override
	public ArrayList<State<Position>> getAllPossibleStates(State<Position> s) {
		State<Position> state;
		ArrayList<State<Position>> state_list = new ArrayList<State<Position>>();
		ArrayList<Position> pos_list = new ArrayList<Position>();
		pos_list = maze.getPossibleMovesArr(pos_list, s.getState());
		
		// Convert every position into state and add it to the list
		for(Position p : pos_list)
		{
			state = new State<Position>();
			state.setState(p);
			state.setCost(1);
			state_list.add(state);
		}
		return state_list;
	}
	
}
