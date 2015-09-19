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
	
	/**
	 * Run.
	 */
	@Override
	public void run(){
		try {
			System.out.println("Please enter your command: ");
			String input = in.readLine();
			String[] params = input.split(" ");
			} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
