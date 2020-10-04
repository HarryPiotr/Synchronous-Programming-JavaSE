import java.awt.*;
import java.awt.geom.Point2D;
import java.util.concurrent.Semaphore;

public class StartVertex extends Vertex {
	
	public StartVertex(int arg1, int arg2) {
		super(arg1, arg2);
	}
	
	public StartVertex(Point arg0) {
		super(arg0);
	}
	
	public boolean isStart() {
		return true;
	}
}
