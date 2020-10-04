import java.awt.*;
import java.util.concurrent.Semaphore;

public class Vertex extends Rectangle {
	
	private Point myCo;
	public Semaphore sem;
	public Color myColor = Color.WHITE;
	public Semaphore signals[] = new Semaphore[11];
	
	public Vertex(int arg0, int arg1) {
		myCo = new Point(arg0, arg1);
		setBounds(myCo.x - 40, myCo.y - 40, 80, 80);
		for(int i = 0; i < 11; i++) signals[i] = new Semaphore(1);
		this.sem = new Semaphore(1);		
	}
	
	public boolean isStart() {
		return false;
	}
	
	public boolean isEnd() {
		return false;
	}
	
	public Vertex(Point arg0) {
		myCo = arg0;
	}
	
	public Point getMyCo() {
		return myCo;
	}

	public void setMyCo(Point myCo) {
		this.myCo = myCo;
	}
	
	public void paintComponent(Graphics graphics) {
		if(sem.availablePermits() == 0) graphics.setColor(Color.RED);
		else graphics.setColor(Color.WHITE);
		graphics.drawRect(this.x, this.y, this.width, this.height);
		graphics.setColor(Color.YELLOW);
		graphics.drawString("" + sem.availablePermits(), this.x, this.y + 10);
	}
	
}
