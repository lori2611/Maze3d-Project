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
	
	public void analyzeCommand(String input);
	
	public void passMessage(String s);
	
	public void passMazes(ArrayList<Maze3d> arrayList);
	
	public void passError(Exception e);
}
