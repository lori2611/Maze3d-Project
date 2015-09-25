/*
 * 
 */
package controller;

import model.Model;
import view.View;

public abstract class AbstractCommand implements Command{
	
	/** The v. */
	//view,model
	protected View v;
	
	/** The m. */
	protected Model m;
	
	/**
	 * c'tor.
	 *
	 * @param m the m
	 * @param v the v
	 */
	public AbstractCommand(Model m, View v) {
		this.m = m;
		this.v = v;
	}
	
	@Override
	/**
	 * doCommand will activate the specified command which called by the client.
	 */
	public abstract void doCommand(String[] params);

}
