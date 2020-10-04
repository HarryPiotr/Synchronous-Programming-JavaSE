import java.awt.*;
import java.awt.geom.Point2D;
import java.util.concurrent.Semaphore;

public class EndVertex extends Vertex {

	public EndVertex(int arg1, int arg2) {
		super(arg1, arg2);
	}

	public EndVertex(Point arg0) {
		super(arg0);
	}
	
	public boolean isEnd() {
		return true;
	}	
}
