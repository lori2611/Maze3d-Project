package algorithms.mazeGenerators;

public abstract class AbstMaze3dGenerator implements Maze3dGenerator {
	
	/* 
	 * Calculate the time of generate function
	 */
	public String measureAlgorithmTime(int x, int y, int z)
	{
		// Declare variables
		long timeMillisStart,timeMillisEnd;
		String Time;
		
		// Calculate the time of generate function
		timeMillisStart = System.currentTimeMillis();
		generate(x,y,z);
		timeMillisEnd = System.currentTimeMillis();
		
		// Convert time to string
		Time = Long.toString((timeMillisEnd - timeMillisStart));
		Time = Time + " MilliSeconds";
		return Time;
	}
}
