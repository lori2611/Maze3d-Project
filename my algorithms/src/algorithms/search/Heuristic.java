package algorithms.search;

/**
 * 
 * @author Lori Atar
 *
 * This interface will define how the heuristic method
 * will help as to solve the maze by calculate the way from current state
 * to the goal state - by different ways.
 *
 * @param <T>
 */
public interface Heuristic<T> {

	public double h(State<T> s1,State<T> s2);
}
