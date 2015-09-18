package view;

import controller.Controller;

public class MyView implements View {
	
	private Controller c;
	private CLI cli;
	
	public MyView(Controller c,CLI cli) {
		this.c = c;
		this.cli = cli;
	}
	
	public void start() {
		cli.run();
	}
}
