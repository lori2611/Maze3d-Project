/*
 * 
 */
package algorithms.mazeGenerators;

import algorithms.mazeGenerators.Position;

/**
 * 
 * @author Lori Atar
 * Current position in our maze will present as a vector includong x,y,z coordinates.
 */
public class Position {

	/** The x. */
	private int x;
	
	/** The y. */
	private int y;
	
	/** The z. */
	private int z;
	
	/**
	 * Instantiates a new position.
	 *
	 * @param x the x
	 * @param y the y
	 * @param z the z
	 */
	// c'tor
	public Position(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * Instantiates a new position.
	 *
	 * @param p the p
	 */
	// Copy c'tor
	public Position(Position p)
	{
		this.x = p.x;
		this.y = p.y;
		this.z = p.z;
	}
	
	/**
	 * Gets the x.
	 *
	 * @return the x
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Sets the x.
	 *
	 * @param x the new x
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * Gets the y.
	 *
	 * @return the y
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Sets the y.
	 *
	 * @param y the new y
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * Gets the z.
	 *
	 * @return the z
	 */
	public int getZ() {
		return z;
	}
	
	/**
	 * Sets the z.
	 *
	 * @param z the new z
	 */
	public void setZ(int z) {
		this.z = z;
	}
	
	/**
	 * Sets the position.
	 * Change position to given variables
	 * @param x the x
	 * @param y the y
	 * @param z the z
	 */
	public void setPosition(int x,int y,int z)
	{
		this.x = x;
		this.y = y;
		this.z = z;	
	}
	
	/* 
	 * Prints our position
	 */
	public String toString()
	{
		String point = "";
		point = point + "{" + x + "," + y + "," + z + "}";
		return point;
	}
	
	/* 
	 * Set the hash to hash the string of x+y+z
	 */
	public int hashCode()
	{
		String s = "";
		s = s + x + y + z;
		return s.hashCode();
	}
	
	/* 
	 * Check that all the coordinates are equal
	 */
	public boolean equals(Object obj)
	{
		if(obj == null || obj.getClass() != this.getClass())
			return false;
		Position p = ((Position)obj);
		if(p.getX() == x && p.getY() == y && p.getZ() == z)
			return true;
		else
			return false;
	}
}
