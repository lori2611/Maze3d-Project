package controller;

import model.Model;
import view.View;

public class MyController implements Controller {

	private View v;
	private Model m;
	
	public MyController(View v, Model m) {
		this.v = v;
		this.m = m;
	}
}
