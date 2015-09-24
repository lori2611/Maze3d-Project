/*
 * 
 */
package algorithms.mazeGenerators;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;

/**
 * @author Lori Atar
 * Maze3d object.
 */
public class Maze3d{

	protected int[][][] maze3d; //The maze3d
	
	protected int x; // Maze's x
	protected int y; // Maze's y
	protected int z; // Maze's z
	
	protected Position pStart; // Start Position
	protected Position pEnd; // Goal Position
	
	
	/**
	 * Instantiates a new maze3d.
	 *
	 * @param x the x
	 * @param y the y
	 * @param z the z
	 */
	public Maze3d(int x, int y, int z) {
		maze3d = new int[x][y][z];
		this.x = x;
		this.y = y;
		this.z = z;
		this.pStart = null;
		this.pEnd = null;
	}
	
	/**
	 * This c'tor will create a maze using only bytes array which include all
	 * maze's details.
	 * @param b
	 */
	public Maze3d(byte[] b) {
		
		ByteBuffer buf = ByteBuffer.wrap(b);
		buf.position(0);
		
		// Set start position from first 12 bytes
		Position p = new Position(buf.getInt(), buf.getInt(), buf.getInt());
		this.setStartPosition(p);
		
		// Set end position from the other 12 bytes
		p = new Position(buf.getInt(), buf.getInt(), buf.getInt());
		this.setGoalPosition(p);
		
		//System.out.println("start: " + this.getStartPosition() + " ,end: " + this.getGoalPosition());
		
		// Set maze bounds
		this.x = buf.getInt();
		this.y = buf.getInt();
		this.z = buf.getInt();

		// Create the maze with the specified bounds
		maze3d = new int[x][y][z];
		
		// Set maze content
		for (int i = 0; i < this.z; i++) {
			for (int j = 0; j < this.x; j++) {
				for (int k = 0; k < this.y; k++) {
					
					// Read bytes into the maze3d
					
					this.maze3d[j][k][i] = (int)buf.get();
				}
			}
		}
	}
	
	/**
	 * Gets the maze3d.
	 *
	 * @return the maze3d
	 */
	public int[][][] getMaze3d() {
		return maze3d;
	}

	/**
	 * Sets the maze3d.
	 *
	 * @param maze3d the new maze3d
	 */
	public void setMaze3d(int[][][] maze3d) {
		this.maze3d = maze3d;
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
	 * Sets the start position.
	 *
	 * @param p the new start position
	 */
	public void setStartPosition(Position p)
	{
		this.pStart = new Position(p);
	}
	
	/**
	 * Gets the start position.
	 *
	 * @return the start position
	 */
	public Position getStartPosition()
	{
		return this.pStart;
	}
	
	/**
	 * Sets the goal position.
	 *
	 * @param p the new goal position
	 */
	public void setGoalPosition(Position p)
	{
		this.pEnd = new Position(p);
	}
	
	/**
	 * Gets the goal position.
	 *
	 * @return the goal position
	 */
	public Position getGoalPosition() {
		return this.pEnd;
	}
	
	/**
	 * Gets the up.
	 *
	 * @param p the p
	 * @return the up
	 */
	public int getUp(Position p)
	{
		if(this.z-1 != p.getZ())
			return this.maze3d[p.getX()][p.getY()][p.getZ()+1];
		else
			return -1;
	}
	
	/**
	 * Gets the down.
	 *
	 * @param p the p
	 * @return the down
	 */
	public int getDown(Position p)
	{
		if(p.getZ() != 0)
			return this.maze3d[p.getX()][p.getY()][p.getZ()-1];
		else
			return -1;
	}
	
	/**
	 * Gets the front.
	 *
	 * @param p the p
	 * @return the front
	 */
	public int getFront(Position p)
	{
		if(this.y-1 != p.getY())
			return this.maze3d[p.getX()][p.getY()+1][p.getZ()];
		else
			return -1;
	}
	
	/**
	 * Gets the back.
	 *
	 * @param p the p
	 * @return the back
	 */
	public int getBack(Position p)
	{
		if(0 != p.getY())
			return this.maze3d[p.getX()][p.getY()-1][p.getZ()];
		else
			return -1;
	}
	
	/**
	 * Gets the right.
	 *
	 * @param p the p
	 * @return the right
	 */
	public int getRight(Position p)
	{
		if(this.x-1 != p.getX())
			return this.maze3d[p.getX()+1][p.getY()][p.getZ()];
		else
			return -1;
	}
	
	/**
	 * Gets the left.
	 *
	 * @param p the p
	 * @return the left
	 */
	public int getLeft(Position p)
	{
		if(0 != p.getX())
			return this.maze3d[p.getX()-1][p.getY()][p.getZ()];
		else
			return -1;
	}
	
	/**
	 * Sets the position value.
	 *
	 * @param p the p
	 * @param num the num
	 */
	// Get position in maze and set value in it;
	public void setPositionValue(Position p,int num)
	{
		this.maze3d[p.getX()][p.getY()][p.getZ()] = num;
	}
	
	/**
	 * Gets the position value.
	 *
	 * @param x the x
	 * @param y the y
	 * @param z the z
	 * @return the position value
	 */
	public int getPositionValue(int x,int y,int z)
	{
		return this.maze3d[x][y][z];
	}
	
	
	/**
	 * Gets the possible moves.
	 *
	 * @param p the p
	 * @return the possible moves
	 */
	public String[] getPossibleMoves(Position p)
	{
		String[] moves;
		ArrayList<Position> list = new ArrayList<Position>();
		int i=0;
		
		list = getPossibleMovesArr(list,p);
		moves = new String[list.size()];
		while(!list.isEmpty())
		{
			moves[i] = list.get(0).toString();
			list.remove(0);
			++i;
		}
		return moves;
	}
	
	/**
	 * Gets the possible moves arr.
	 *
	 * @param list the list
	 * @param p the p
	 * @return the possible moves arr
	 */
	public ArrayList<Position> getPossibleMovesArr(ArrayList<Position> list,Position p)
	{
		Position point = null;
		int px,py,pz;
		px = p.getX();
		py = p.getY();
		pz = p.getZ();
		
		if(px+1 < this.x && maze3d[px+1][py][pz] == 0)
		{
			point = new Position(px + 1,py,pz);
			list.add(point);
		}
		if(px-1 >= 0 && maze3d[px-1][py][pz] == 0)
		{
			point = new Position(px - 1,py,pz);
			list.add(point);
		}
		if(py+1 < this.y && maze3d[px][py+1][pz] == 0)
		{
			point = new Position(px,py+1,pz);
			list.add(point);
		}
		if(py-1 >= 0 && maze3d[px][py-1][pz] == 0)
		{
			point = new Position(px,py-1,pz);
			list.add(point);
		}
		if(pz+1 < this.z && maze3d[px][py][pz+1] == 0)
		{
			point = new Position(px,py,pz+1);
			list.add(point);
		}
		if(pz-1 >= 0 && maze3d[px][py][pz-1] == 0)
		{
			point = new Position(px,py,pz-1);
			list.add(point);
		}
		
		return list;
	}
	
	/* 
	 * Print maze3d
	 */
	public String toString()
	{
		String print = "\n";
		for (int i = 0; i < z; i++) {
			for (int j = 0; j < y; j++) {
				print += "[ ";
				for (int k = 0; k < x; k++) {
					if(k != x-1)
						print += maze3d[k][j][i] + ",";
					else
						print += maze3d[k][j][i];
				}
				print+= " ]  \n";
			}
			print += "\n";
		}
		return print;
	}

	/**
	 * Gets the cross section by x.
	 *
	 * @param i the i
	 * @return the cross section by x
	 * @throws IndexOutOfBoundsException the index out of bounds exception
	 */
	public int[][] getCrossSectionByX(int i) throws IndexOutOfBoundsException {
		try{
			int[][] temp = new int[this.y][this.z];
			for (int j = 0; j < this.y; j++) {
				for (int k = 0; k < this.z; k++) {
					temp[j][k] = this.maze3d[i][j][k];
				}
			}
			return temp;
		}catch(ArrayIndexOutOfBoundsException msg){
			throw msg;
		}
	}
	
	/**
	 * Gets the cross section by y.
	 *
	 * @param i the i
	 * @return the cross section by y
	 * @throws IndexOutOfBoundsException the index out of bounds exception
	 */
	public int[][] getCrossSectionByY(int i)  throws IndexOutOfBoundsException {
		try{
			int[][] temp = new int[this.x][this.z];
			for (int j = 0; j < this.x; j++) {
				for (int k = 0; k < this.z; k++) {
					temp[j][k] = this.maze3d[j][i][k];
				}
			}
			return temp;
		}catch(ArrayIndexOutOfBoundsException msg){
			throw msg;
		}
	}
	
	/**
	 * Gets the cross section by z.
	 *
	 * @param i the i
	 * @return the cross section by z
	 * @throws IndexOutOfBoundsException the index out of bounds exception
	 */
	public int[][] getCrossSectionByZ(int i) throws IndexOutOfBoundsException {
		try{
			int[][] temp = new int[this.x][this.y];
			for (int j = 0; j < this.x; j++) {
				for (int k = 0; k < this.y; k++) {
					temp[j][k] = this.maze3d[j][k][i];
				}
			}
			return temp;
		}catch(ArrayIndexOutOfBoundsException msg){
			throw msg;
		}
	}
	
	/**
	 * This method will convert all the maze details into a ByteArray
	 * It's include Start & End positions,size of the maze and his content.
	 * @return
	 * @throws IOException
	 */
	public byte[] toByteArray(){
		
		// Create array of bytes with the size of the maze + 36 (9 ints * 4 bytes)
		// The 9 int numbers belong the the size of the maze and to the Start & End positions
		byte[] b = new byte[(this.x*this.y*this.z)+36];
		ByteBuffer buf = ByteBuffer.wrap(b);
		
		// Add start position coordinates
		buf.putInt(this.pStart.getX());
		buf.putInt(this.pStart.getY());
		buf.putInt(this.pStart.getZ());
		
		// Add end position coordinates
		buf.putInt(this.pEnd.getX());
		buf.putInt(this.pEnd.getY());
		buf.putInt(this.pEnd.getZ());
		
		// Add size of maze
		buf.putInt(this.x);
		buf.putInt(this.y);
		buf.putInt(this.z);
		
		for (int i = 0; i < this.z; i++) {
			for (int j = 0; j < this.x; j++) {
				for (int k = 0; k < this.y; k++) {
					
					// Add the content of the maze to our byteBuffer
					buf.put((byte) this.maze3d[j][k][i]);
				}
			}
		}
		return b;
	}

	/**
	 * This method will check if maze we got as parameter is equal to our maze.
	 * it will compare every cell between the mazes;
	 * @param maze
	 * @return
	 */
	public boolean equals(Maze3d maze)
	{
		boolean isEqual = true;
		
		// Make sure that the specified maze has the same bounds as our maze
		if(maze.getX() == this.getX() && maze.getY() == this.getY() && maze.getZ() == this.getZ())
		{
			for (int i = 0; i < this.getZ(); i++) {
				for (int j = 0; j < this.getX(); j++) {
					for (int k = 0; k < this.getY(); k++) {
						if(this.maze3d[j][k][i] != maze.getPositionValue(j, k, i))
						{
							isEqual = false;
						}
					}
				}
			}
		}
		else
		{
			// If the bounds are different
			isEqual = false;
		}
		return isEqual;
	}


}

