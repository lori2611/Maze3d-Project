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
	private HashMap<String, Command> commands;
	
	/**
	 * Instantiates a new CLI.
	 *
	 * @param in the in
	 * @param out the out
	 * @param commands the commands
	 */
	public CLI(BufferedReader in,PrintWriter out) {	
		this.in = in;
		this.out = out;
	}
	
	public void setCommands(HashMap<String, Command> commands) {
		this.commands = commands;
	}
	
	@Override
	public void run(){
		
			out.println("Please enter your command: ");
			
		try {
			String input = in.readLine();
			String[] params = input.split(" ");
			if(!commands.containsKey(params[0]))
			{
				throw new IOException("Invalid Command");
			}
			else
			{
				
				if(!params[0].equals("display"))
				{
					switch(params[0])
					{
						case "dir":
							getObject(commands, "dir").doCommand(params);
							break;
					}
				}
				else
				{
					if(!commands.containsKey(params[1]))
					{
						throw new IOException("Invalid Command.");
					}
					else
					{
						//
					}
				}
			}
			
			} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Command getObject(HashMap<String, Command> commands,String c) {
		for(String command : commands.keySet())
		{
			if(command.equals(c))
			{
				return commands.get(command);
			}
		}
		System.out.println("Command doesnt found.");
		return null;
	}
}
