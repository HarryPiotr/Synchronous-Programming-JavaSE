import java.awt.Point;

public class LargeVertex extends Vertex {
	
	public LargeVertex(int arg0, int arg1) {
		super(arg0, arg1);
		setBounds(arg0 - 100, arg1 - 40, 140, 80);
	}
	
	public LargeVertex(Point arg0) {
		super(arg0);
		setBounds(arg0.x - 100, arg0.y - 40, 140, 80);
	}
}
