package view;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;

import controller.Command;
import controller.Controller;

public class MyView implements View {
	
	private Controller c;
	private CLI cli;
	private HashMap<String, Command> commands;
	
	public MyView(Controller c) {
		this.c = c;
		this.cli = new CLI(new BufferedReader(new InputStreamReader(System.in)), new PrintWriter(System.out)); 
	}
	
	public void start() {
		cli.run();
	}
	
	public void printDir(String[] files) {
		for(String file : files)
		{
			System.out.println(file);
		}
	}
}
