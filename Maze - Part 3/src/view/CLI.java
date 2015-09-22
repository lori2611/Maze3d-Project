/*
 * 
 */
package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import controller.Command;
import controller.Dir;

public class CLI implements Runnable{
	
	private BufferedReader in;
	private PrintWriter out;
	private View view;
	
	/**
	 * Instantiates a new CLI.
	 *
	 * @param in the in
	 * @param out the out
	 * @param view the view
	 */
	public CLI(BufferedReader in,PrintWriter out,View view) {	
		this.in = in;
		this.out = out;
		this.view = view;
	}
	
	/**
	 * Return the PrintWriter which defined by the client
	 * @return out
	 */
	public PrintWriter getOut() {
		return out;
	}

	/**
	 * Start the 'run' method of the CLI in different thread
	 */
	public void start()
	{
		Thread t = new Thread(this);
		t.start();
	}
	
	@Override
	/**
	 * Run will be activate in a different thread which will get the client command
	 * and activate the matching methods of the program.
	 */
	public void run(){
		String input;
		out.println("Please enter your command: ");
		out.flush();		
		try {
			input = in.readLine();
			
			// Run while the client doesn't insert "exit"
			while(!input.equals("exit"))
			{
				// Pass the input to the view according to the MVC pattern
				view.passInput(input);
				out.println("Please enter your command: ");
				out.flush();
				input = in.readLine();
			}
			out.println("bye bye");
		} catch (IOException e) {	
			
			// Send the exception to the view.
			view.printError(e);
		}
		out.close();
	}
}
