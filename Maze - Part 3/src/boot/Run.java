package boot;

import java.io.IOException;
import java.lang.instrument.ClassDefinition;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.util.jar.JarFile;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.MyMaze3dGenerator;
import controller.MyController;
import model.MyModel;
import view.MyView;

public class Run {
	public class ObjectSizeFetcher {
		public  Instrumentation instrumentation;

		public  void premain(String args, Instrumentation inst) {
	        instrumentation = inst;
	    }

	    public long getObjectSize(Object o) {
	        return instrumentation.getObjectSize(o);
	    }
	}
	
	public static void main(String[] args) {
		
		MyModel m = new MyModel();
		MyView v = new MyView();
		MyController c = new MyController(m,v);
		v.setC(c);
		m.setC(c);
		try {
			v.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
}


