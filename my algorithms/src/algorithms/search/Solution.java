/*
 * 
 */
package algorithms.search;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

/**
 * 
 * @author Lori Atar
 * The solution class include Queue which will save our solution (steps from
 * this start state to the goal state)
 * and will print it from the start to the end (LIFO). 
 * @param <T>
 */
public class Solution<T>{
	
	/** The path. */
	private Deque<State<T>> path;
	
	/**
	 * Instantiates a new solution.
	 */
	public Solution() {
		path = new ArrayDeque<State<T>>();
	}
	
	/**
	 * Adds the.
	 *
	 * @param state the state
	 */
	public void add(State<T> state)
	{
		if(state != null)
		path.add(state);
	}

	/* 
	 * Print solution
	 */
	public String toString()
	{
		Iterator<State<T>> i = path.descendingIterator();
		String sol = new String();
		while(i.hasNext())
		{
			sol+= i.next().getState().toString();
			sol += "\n";
		}
		return sol;
	}
}
