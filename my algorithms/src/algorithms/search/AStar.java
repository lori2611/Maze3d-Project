/*
 * 
 */
package algorithms.search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 * 
 * @author Lori Atar
 * This class include search algorithm based on AStar pseudo-code.
 * this will help as solving the maze using different heuristics methods.
 * @param <T>
 */
public class AStar<T> extends CommonSearcher<T>{

	/** The heuristics. */
	Heuristic<T> heuristics;
	
	/**
	 * Instantiates a new a star.
	 *
	 * @param heuristics the heuristics
	 */
	public AStar(Heuristic<T> heuristics)
	{
		this.heuristics = heuristics;
	}
	
	/* 
	 * Search algorithm based on AStar
	 */
	public Solution<T> search(Searchable<T> s) {
		s.getStartState().setCost(0);
		openList.add(s.getStartState());
		HashSet<State<T>> closeSet = new HashSet<State<T>>();
		
		// While the list is not empty
		while(openList.size() > 0)
		{
			State<T> n = popOpenList();
			closeSet.add(n);
			if(n.equals(s.getGoalState()) == true)
			{
				// BackTraces solution
				return backTrace(n,s.getStartState());
			}	
			
			// Save all successors in array-list
			ArrayList<State<T>> successors = s.getAllPossibleStates(n);
			
			// For-each successor add it to open list / check if he's better then existing state in the closeSet
			for(State<T> state : successors)
			{
				if(!closeSet.contains(state) && !openList.contains(state))
				{
					state.setCameFrom(n);
					state.setCost((int)(n.getCost() + heuristics.h(state, s.getGoalState())));
					openList.add(state);
				} else{
					if(openList.contains(state))
					{
						Iterator<State<T>> i = openList.iterator();
						while(i.hasNext())
						{
							if(i.next().equals(state))
							{
								if(i.next().compareTo(state) > 0)
								{
									openList.remove(i.next());
									openList.add(state);
								}
							}
						}
					}
				}
			}
		}
		System.out.println("No solution to the maze");
		return null;
	}

}
