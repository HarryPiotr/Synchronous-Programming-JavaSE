import java.awt.Point;
import java.util.concurrent.Semaphore;

public class SplitVertex extends Vertex {

	public SplitVertex(int arg0, int arg1) {
		super(arg0, arg1);
		this.sem = new Semaphore(2);
		for(int i = 0; i < 11; i++) this.signals[i] = new Semaphore(2);
	}
	
	public SplitVertex(Point arg0) {
		super(arg0);
		this.sem = new Semaphore(2);
		for(int i = 0; i < 11; i++) this.signals[i] = new Semaphore(2);
	}

}
