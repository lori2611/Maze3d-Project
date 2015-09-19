package model;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import controller.Controller;

public class MyModel implements Model {

	private Controller c;
	
	public MyModel(Controller c) {
		this.c = c;
	}
	
	public void dir(String path) {
		File file = new File(path);
		String[] files = file.list();
		c.passDir(files);
	}
	
}
