package driver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.MyMaze3dGenerator;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;

public class Run {

	public static void main(String[] args) throws FileNotFoundException, Exception {
		
		// Generate maze
		MyMaze3dGenerator mg = new MyMaze3dGenerator();
		Maze3d maze = mg.generate(6,6,2);
		
		// save it to a file
		OutputStream out=new MyCompressorOutputStream(new FileOutputStream("1.maz"));
		out.write(maze.toByteArray());
		out.flush();
		out.close();
		
		// Read from the file
		InputStream in=new MyDecompressorInputStream(new FileInputStream("1.maz"));
		byte b[]=new byte[maze.toByteArray().length];
		in.read(b);
		in.close();
		
		// Create new maze with our new c'tor which get bytes array
		Maze3d loaded=new Maze3d(b);
		
		// Check that the mazes are equal
		System.out.println(loaded.equals(maze));
	}

}
