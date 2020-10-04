import java.awt.Point;
import java.util.concurrent.Semaphore;

public class SmallVertex extends Vertex {

	public SmallVertex(int arg0, int arg1) {
		super(arg0, arg1);
		setBounds(arg0 - 35, arg1 - 35, 70, 70);
	}
	
	public SmallVertex(Point arg0) {
		super(arg0);
		setBounds(arg0.x - 35, arg0.y - 35, 70, 70);
	}
}
