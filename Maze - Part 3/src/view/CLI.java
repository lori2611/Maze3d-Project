/*
 * 
 */
package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import controller.Command;

public class CLI implements Runnable{
	
	private BufferedReader in;
	private PrintWriter out;
	
	// HashMap include String - the name of the command,Command - the action the user choose
	private HashMap<String,Command> commands; 	
	
	
	/**
	 * Instantiates a new cli.
	 *
	 * @param in the in
	 * @param out the out
	 * @param commands the commands
	 */
	public CLI(BufferedReader in,PrintWriter out,HashMap<String, Command> commands) {	
		this.in = in;
		this.out = out;
		this.commands = commands;
	}
	
	/**
	 * Run.
	 */
	@Override
	public void run(){
		try {
			System.out.println("Please enter your command: ");
			String input = in.readLine();
			while(!input.equals("exit"))
			{
				System.out.println("Please enter your command: ");
				input = in.readLine();
				if(commands.containsKey(input))
				{
					commands.get(input).doCommand();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
