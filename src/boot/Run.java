package boot;

import java.util.Observer;

import model.CommonModel;
import presenter.Presenter;
import view.CommonView;

public class Run {

	public static void main(String[] args) {

		CommonModel m = new CommonModel();
		CommonView v = new CommonView();
		Presenter p = new Presenter(m,v);
		m.addObserver(p);
		v.addObserver(p);
		m.notifyObservers();
		v.notifyObservers();
	}

}
