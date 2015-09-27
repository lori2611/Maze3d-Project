package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import presenter.Presenter;

public class CommonModel extends Observable implements Model {

	private List<Presenter> observerList;
	
	public CommonModel() {
		this.observerList = new ArrayList<Presenter>();
	}
	
	public void addObserver(Presenter p) {
		this.observerList.add(p);
	}
	
	@Override
	public void notifyObservers() {
		
		// Notify all observers in list
		for (Observer o : observerList) {
			o.update(this, null);
		}
	}

	@Override
	public void getData() {
		// TODO Auto-generated method stub

	}

}
