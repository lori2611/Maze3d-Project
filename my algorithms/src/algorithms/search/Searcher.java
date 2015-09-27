package algorithms.search;

/**
 * 
 * @author Lori Atar
 *
 * This interface will define for each 'searcher' object
 * the 'search' method and the efficiency of the searh (NumberOfNodesEvaluated).
 *
 * @param <T>
 */
public interface Searcher<T> {

	// The search method
	public Solution<T> search(Searchable<T> s);
	
	// Get how many nodes are evaluated by the algorithm
	public int getNumberOfNodesEvaluated();
}
