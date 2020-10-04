import java.awt.*;
import java.awt.geom.Point2D;
import java.util.concurrent.Semaphore;

import javax.swing.ImageIcon;

public class Car extends Thread {

	protected Point myCo;
	private Point myStart;
	private Vertex myStartVertex;
	private Point myTarget;
	private Vertex myTargetVertex;
	private int stage = 1;
	private int dir[];
	private Thread td;
	private int mySpeed = 1;
	private int myDirection;
	private int myPreviousDirection;
	public boolean isCrossing;
	public boolean isLive;
	
	
	public Car(int arg1[]) {
		
		dir = arg1;
		myCo = new Point(Graph.v[dir[0]].getMyCo());
		myStart = Graph.v[dir[0]].getMyCo();
		myStartVertex = Graph.v[dir[0]];
		myTargetVertex = Graph.v[dir[1]];
		myTarget = Graph.v[dir[1]].getMyCo();
		
		isLive = true;
		isCrossing = false;
		
		switch(dir[0]) {
		case 0:
			myDirection = 7;
			break;
		case 1:
			myDirection = 7;
			break;
		case 12:
			myDirection = 9;
			break;
		case 15:
			myDirection = 3;
			break;
		case 24:
			myDirection = 1;
			break;
		}
		myPreviousDirection = myDirection;
		
		td = new Thread(this);
		td.start();
	}
	
	public void move() {
				
		for(int j = 0; j < Panel.car.size(); j++) {
						
			Car foreignCar = Panel.car.get(j);
			if(foreignCar.equals(this)) continue;
			if(myCo != null && myTarget != null && foreignCar.myCo != null) {
				if(Graph.isBetween(myCo, foreignCar.getMyCo(), myDirection)) {
					try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
					return;
				}
			}
		}
				
		if(myTargetVertex.contains(myCo) && !isCrossing && !myTargetVertex.isEnd()) {
			
			try {
				if(Graph.lightsOn) myTargetVertex.signals[myDirection].acquire(); 
				myTargetVertex.sem.acquire();
				
				} catch (InterruptedException e) {
				e.printStackTrace();
			}
			myTargetVertex.myColor = Color.RED;
			isCrossing = true;
		}
		
		if(isCrossing && !myTargetVertex.contains(myCo) && !myStartVertex.contains(myCo)) {
			if(Graph.lightsOn) myStartVertex.signals[myPreviousDirection].release();
			myStartVertex.sem.release();
			myStartVertex.myColor = Color.WHITE;
			isCrossing = false;
		}
		
		if(myTarget.x - myCo.x > 0 && myCo.y == myTarget.y) {
			myDirection = 3;
			myCo.translate(mySpeed, 0);
		}
		else
		if(myTarget.x - myCo.x < 0 && myCo.y == myTarget.y) {
			myDirection = 9;
			myCo.translate(-mySpeed, 0);
		}
		else
		if(myCo.x == myTarget.x && myTarget.y - myCo.y > 0) {
			myDirection = 7;
			myCo.translate(0, mySpeed);
		}
		else
		if(myCo.x == myTarget.x && myTarget.y - myCo.y < 0) {
			myDirection = 1;
			myCo.translate(0, -mySpeed);
		}
		else
		if(myTarget.x - myCo.x == myTarget.y - myCo.y && myTarget.x - myCo.x > 0) {
			myDirection = 5;
			myCo.translate(mySpeed, mySpeed);
		}
		else
		if(myTarget.x - myCo.x == myTarget.y - myCo.y && myTarget.x - myCo.x < 0) {
			myDirection = 10;
			myCo.translate(-mySpeed, -mySpeed);
		}
		else
		if(myTarget.x - myCo.x == -(myTarget.y - myCo.y) && myTarget.x - myCo.x > 0) {
			myDirection = 2;
			myCo.translate(mySpeed, -mySpeed);
		}
		else
		if(myTarget.x - myCo.x == -(myTarget.y - myCo.y) && myTarget.x - myCo.x < 0) {
			myDirection = 8;
			myCo.translate(-mySpeed, mySpeed);
		}
		else
		if(myTarget.x - myCo.x == 2*(myTarget.y - myCo.y) && myTarget.x - myCo.x > 0) {
			myDirection = 4;
			myCo.translate(2 * mySpeed, mySpeed);
		}
		else
		if(2*(myTarget.x - myCo.x) == myTarget.y - myCo.y && myTarget.x - myCo.x > 0) {
			myDirection = 6;
			myCo.translate(mySpeed, 2 * mySpeed);
		}
		
		if(myCo.x == myTarget.x && myCo.y == myTarget.y && !Graph.v[dir[stage]].isEnd()) {
			myStart = Graph.v[dir[stage]].getMyCo();
			myStartVertex = Graph.v[dir[stage]];
			myTarget = Graph.v[dir[++stage]].getMyCo();
			myTargetVertex = Graph.v[dir[stage]];
			myPreviousDirection = myDirection;
		}
		
		if(myCo.x == myTarget.x && myCo.y == myTarget.y && Graph.v[dir[stage]].isEnd()) {
			try {Panel.sem.acquire();} catch (InterruptedException e1) {e1.printStackTrace();}
			isLive = false;
			Panel.sem.release();
		}
		
	}
	
	public void paintComponent(Graphics graphics) {
		graphics.drawImage(new ImageIcon(Graph.carImages[myDirection - 1]).getImage(), myCo.x - 25, myCo.y - 25, null);
		if(Graph.showCrossBoxes) {
			graphics.setColor(Color.YELLOW);
			graphics.drawString("" + myDirection, myCo.x, myCo.y);
		}
	}
	
	public void run() {
		while(isLive) {
			move();
			try {Thread.sleep(10);} catch (InterruptedException e) {e.printStackTrace();}
		}
	}
	
	public void setMyCo(Point p) {
		myCo = p;
	}
	
	public Point getMyCo() {
		return myCo;
	}
	
	public Point getMyTarget() {
		return myTarget;
	}
	
	public int getMyDirection() {
		return myDirection;
	}
}
