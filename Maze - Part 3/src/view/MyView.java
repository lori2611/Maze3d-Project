package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;

import controller.Command;
import controller.Controller;

public class MyView implements View {
	
	private Controller c;
	private CLI cli;
	private HashMap<String, Command> commands;
	
	public MyView() {
		this.cli = new CLI(new BufferedReader(new InputStreamReader(System.in)), new PrintWriter(System.out)); 
	}
	
	public void setC(Controller c) {
		this.c = c;
	}
	
	public void start() throws IOException{
		try{
			cli.run();
		} catch(Exception e) {
			throw e;
		}
	}
	
	public void printDir(String[] files) {
		for(String file : files)
		{
			System.out.println(file);
		}
	}
	
	public void printError(Exception e) {
		e.printStackTrace();
	}
	
	public void setCommands(HashMap<String, Command> commands) {
		this.commands = commands;
		cli.setCommands(commands);
	}
}
