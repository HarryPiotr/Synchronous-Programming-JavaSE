import java.awt.*;

public class Hitbox implements Runnable{

	private Car myCar;
	private Polygon box;
	private Thread td;

	public Hitbox(Car arg0) {
		myCar = arg0;
		
		td = new Thread(this);
		td.start();
	}
	
	public void paintHitbox(Graphics graphics) {
		if(box != null) graphics.drawPolygon(box);
	}
	
	public void run() {
		while(myCar.isLive) {
			
			switch(myCar.getMyDirection()) {
			
			case 1:
				int tab[][] = 
			{
				{myCar.getMyCo().x - 15, myCar.getMyCo().x + 15, myCar.getMyCo().x + 15, myCar.getMyCo().x - 15},
				{myCar.getMyCo().y + 40, myCar.getMyCo().y + 40, myCar.getMyCo().y + 15, myCar.getMyCo().y + 15}
			};
				box = new Polygon(tab[0],tab[1],4);
			}
			
		}
	}
}
