package algorithms.search;

/**
 * 
 * @author Lori Atar
 * State will help as present current state in out problem
 * and include generic code using <T> parameter.
 * @param <T>
 */
public class State<T> implements Comparable<State<T>>{
	
	/** The state. */
	private T state; // The state represented by a string
	
	/** The cost. */
	private int cost; // Cost to reach this state
	
	/** The came from. */
	private State<T> cameFrom; // The state we came from to this state
	
	/**
	 * Instantiates a new state.
	 */
	public State() {
		this.state = null;
		this.cost = 0;
		this.cameFrom = null;
	}
	
	/**
	 * Instantiates a new state.
	 *
	 * @param s the s
	 */
	public State(State<T> s) {
		this.state = s.state;
		this.cost = s.cost;
		this.cameFrom = s.cameFrom;
	}

	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public T getState() {
		return state;
	}
	
	/**
	 * Sets the state.
	 *
	 * @param state the new state
	 */
	public void setState(T state) {
		this.state = state;
	}
	
	/**
	 * Gets the cost.
	 *
	 * @return the cost
	 */
	public int getCost() {
		return cost;
	}
	
	/**
	 * Sets the cost.
	 *
	 * @param cost the new cost
	 */
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	/**
	 * Gets the came from.
	 *
	 * @return the came from
	 */
	public State<T> getCameFrom() {
		return cameFrom;
	}
	
	/**
	 * Sets the came from.
	 *
	 * @param cameFrom the new came from
	 */
	public void setCameFrom(State<T> cameFrom) {
		this.cameFrom = cameFrom;
	}
	
	/* 
	 * override equals method
	 */
	public boolean equals(Object obj)
	{
		if(obj == null || obj.getClass() != this.getClass())
			return false;
		State<T> state = (State<T>)obj;
		if(state.getState().equals(this.state))
			return true;
		else
			return false;
	}
	
	/* 
	 * override hashcode
	 */
	public int hashCode()
	{
		return this.state.hashCode();
	}
	
	/* 
	 *CompareTo override 'comparable' interface',return positive number
	 *if the current state has bigger cost
	 *Returns a negative number if current state has smaller cost
	 */
	@Override
	public int compareTo(State<T> state) {
		return this.cost - state.getCost();
	}
}
