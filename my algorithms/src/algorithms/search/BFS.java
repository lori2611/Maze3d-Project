/*
 * 
 */
package algorithms.search;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Lori Atar
 * 
 * This class include search algorithm based on BFS pseudo-code.
 * this will help as solving the maze by check the cost of each step.
 * @param <T>
 */
public class BFS<T> extends CommonSearcher<T> {

	/* 
	 * Search algorithm based on BFS
	 */
	@Override
	public Solution<T> search(Searchable<T> s) {
		s.getStartState().setCost(0);
		openList.add(s.getStartState());
		
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
					state.setCost(n.getCost()+state.getCost());
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

