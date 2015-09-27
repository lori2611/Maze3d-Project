/*
 * 
 */
package controller;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import model.Model;
import view.View;

public interface Controller {
	
	/**
	 * Set Model.
	 * @param m the new m
	 */
	public void setM(Model m);
	
	/**
	 * Set View.
	 * @param v the new v
	 */
	public void setV(View v);
	
	/**
	 * pass files & folders of 'DIR' command to the view.
	 * @param files the files
	 */
	public void passDir(String[] files);
	
	/**
	 * This method will get the user's input and analyze which command did he try to use.
	 * If the commands exist and the syntax is right - it will activate the command with the specified parameters.
	 * Else - it will throw an exception or print an appropriate message.
	 *
	 * @param input the input
	 */
	public void analyzeCommand(String input);
	
	/**
	 * According to the MVC pattern - it will pass a message between the model and the view.
	 * @param s the s
	 */
	public void passMessage(String s);
	
	/**
	 * According to the MVC pattern - it will pass the right maze from the model to the view.
	 * @param maze the maze
	 */
	public void passMaze(Maze3d maze);
	
	/**
	 * According to the MVC pattern - it will pass an exception to the 'view'.
	 * @param e the e
	 */
	public void passError(Exception e);
	
	/**
	 * According to the MVC pattern - will pass one cross of the maze to the view.
	 *
	 * @param maze the maze
	 * @param length the length
	 * @param width the width
	 */
	public void passCrossSection(int[][] maze,int length, int width);
	
	/**
	 * According to the MVC pattern - Pass maze size to the view.
	 * @param size the size
	 */
	public void passMazeSize(int size);
	
	/**
	 * According to the MVC pattern - Pass file size to the view.
	 * @param l the l
	 */
	public void passFileSize(long l);
	
	/**
	 * Pass solution.
	 * @param sol the sol
	 */
	public void passSolution(Solution<Position> sol);
}
