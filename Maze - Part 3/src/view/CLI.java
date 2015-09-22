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
	
	public PrintWriter getOut() {
		return out;
	}

	public void start()
	{
		Thread t = new Thread(this);
		t.start();
	}
	
	@Override
	public void run(){
		String input;
		String[] params = null;
		out.println("Please enter your command: ");
		out.flush();		
		try {
			input = in.readLine();
			params = input.split(" ");
			while(!params[0].equals("exit"))
			{
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
