package model;

import java.io.File;

import controller.Controller;

public class MyModel implements Model {

	private Controller c;
	
	public MyModel() {}
	
	public void dir(String path) {
		File file = new File(path);
		String[] files = file.list();
		c.passDir(files);
	}
	
	public void setC(Controller c) {
		this.c = c;
	}
}
