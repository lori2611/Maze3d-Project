/*
 * 
 */
package algorithms.search;

import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * The Class CommonSearcher.
 * include every similar methods and object that the 'searchers' will need.
 * @param <T> the generic type
 */
public abstract class CommonSearcher<T> implements Searcher<T> {

	/** The open list. */
	protected PriorityQueue<State<T>> openList;
	
	/** The close set. */
	protected HashSet<State<T>> closeSet;
	
	/** The evaluated nodes. */
	private int evaluatedNodes;
	
	 /**
 	 * Instantiates a new common searcher.
 	 */
	
 	public CommonSearcher() {
		 openList = new PriorityQueue<State<T>>();
		 closeSet = new HashSet<State<T>>();
		 evaluatedNodes=0;
	 }
	
	@Override
	public abstract Solution<T> search(Searchable<T> s);

	@Override
	public int getNumberOfNodesEvaluated(){
		return this.evaluatedNodes;
	}

	/**
	 * Pop open list and adds one to the number of evaluated nodes
	 *
	 * @return the state
	 */
	protected State<T> popOpenList() {
		  this.evaluatedNodes++;
		  return openList.poll();
	}
	
	/**
	 * Back trace.
	 *
	 * Return the solution of the problem by save the goal state
	 * and run from the end of solution to the start using 'cameFrom' values.
	 *
	 * @param goalState the goal state
	 * @param startState the start state
	 * @return the solution
	 */
	protected Solution<T> backTrace(State<T> goalState, State<T> startState) {
		Solution<T> sol = new Solution<T>();
		State<T> state = new State<T>(goalState);
		sol.add(state);
		
		// BackTrace from the goal-state to the start-state
		while(state.getCameFrom() != null)
		{
			state = new State<T>(state.getCameFrom());
			sol.add(state);
		}
		return sol;
	}

}
