package boot;

import java.io.IOException;
import controller.MyController;
import model.MyModel;
import view.MyView;

public class Run {
	
	public static void main(String[] args) {
		
		MyModel m = new MyModel();
		MyView v = new MyView();
		MyController c = new MyController(m,v);
		v.setC(c);
		m.setC(c);
		try {
			v.start();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}	
}


