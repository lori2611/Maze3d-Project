package presenter;

import java.util.Observable;
import java.util.Observer;

import model.Model;
import view.View;

public class Presenter implements Observer{

	private Model m;
	private View v;
	
	public Presenter(Model m, View v) {
		this.m = m;
		this.v = v;
	}
	
	@Override
	public void update(Observable o, Object args) {
		if(o==this.m)
		{
			System.out.println("This is model");
		}	
		if(o==this.v)
		{
			System.out.println("This is ui");
		}
	}

}
