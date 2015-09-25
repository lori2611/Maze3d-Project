/*
 * 
 */
package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class CLI{
	
	private BufferedReader in;
	private PrintWriter out;
	private View view;
	
	/**
	 * Instantiates a new CLI.
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
		new Thread(new Runnable() {			
			@Override
			public void run() {
				String input;
				out.println("----- Command Line -----");
				out.println("Please enter your command: ");
				out.flush();		
				try {
					input = in.readLine();
					
					// Run while the client doesn't insert "exit"
					while(!input.equals("exit"))
					{
						// Pass the input to the view according to the MVC pattern
						view.passInput(input);
						out.println("\nPlease enter your command: ");
						out.flush();
						input = in.readLine();
					}
					view.passInput(input);
					out.println("--- EXIT ---");
				} catch (IOException e) {	
					
					// Send the exception to the view.
					view.printMessage("Invalid Command");;
				}
				out.close();
			}
		} ).start();
	}
}
