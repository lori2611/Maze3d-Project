package controller;

import java.util.ArrayList;
import java.util.HashMap;

import algorithms.mazeGenerators.Maze3d;
import model.Model;
import view.View;

public interface Controller {

	/**
	 * Return HashMap
	 * @return
	 */
	public HashMap<String, Command> getCommands();
	
	/**
	 * Set Model
	 * @param m
	 */
	public void setM(Model m);
	
	/**
	 * Set View
	 * @param v
	 */
	public void setV(View v);
	
	/**
	 * pass files & folders of 'DIR' command to the view
	 * @param files
	 */
	public void passDir(String[] files);
	
	/**
	 * This method will get the user's input and analyze which command did he try to use.
	 * If the commands exist and the syntax is right - it will activate the command with the specifies parameters.
	 * Else - it will throw an exception or print an appropriate message.
	 * @param input
	 */
	public void analyzeCommand(String input);
	
	/**
	 * According to the MVC pattern - it will pass a message between the model and the view
	 * @param s
	 */
	public void passMessage(String s);
	
	/**
	 * According to the MVC pattern - it will pass the right maze from the model to the view
	 * @param arrayList
	 */
	public void passMaze(Maze3d maze);
	
	/**
	 * According to the MVC pattern - it will pass an exception to the 'view'
	 * @param e
	 */
	public void passError(Exception e);
	
	/**
	 * According to the MVC pattern - will pass one cross of the maze to the view
	 * @param maze
	 */
	public void passCrossSection(int[][] maze,int length, int width);
}
