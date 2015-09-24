package controller;

import model.Model;
import view.View;

public abstract class AbstractCommand implements Command{
	
	//view,model
	protected View v;
	protected Model m;
	
	/**
	 * c'tor
	 * @param m
	 * @param v
	 */
	public AbstractCommand(Model m, View v) {
		this.m = m;
		this.v = v;
	}
	
	@Override
	/**
	 * doCommand will activate the specified command which called by the client.
	 */
	public abstract void doCommand(String[] args);

}
