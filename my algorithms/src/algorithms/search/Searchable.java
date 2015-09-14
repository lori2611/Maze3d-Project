package algorithms.search;

import java.util.ArrayList;

/**
 * 
 * @author Lori Atar
 *
 * This interface will define to each 'searchable' object
 * the start-state , goal-state and all-possible-moves from
 * the current state.
 *
 * @param <T>
 */
public interface Searchable<T> {
	
	// Return start state
	public State<T> getStartState();
	
	// Return goal state
	public State<T> getGoalState();
	
	// Return all possible moves
	public ArrayList<State<T>> getAllPossibleStates(State<T> s);
	
	}
