import java.awt.*;
import java.util.*;
import java.util.concurrent.Semaphore;

import javax.swing.*;

public class Panel extends JPanel implements Runnable{
	
	protected static ArrayList<Car> car = new ArrayList<>();
	public static Semaphore sem = new Semaphore(1);
	protected static Point lastClick;
	protected static Point lastMouseHover = new Point(0, 0);
	private Overwatch ov;
	protected static Rectangle button[] = new Rectangle[2];
	
	public Panel() {
		
		addMouseListener(new MouseHandler());
		addMouseMotionListener(new MouseHandler());
		
		Thread thread = new Thread(this);
		thread.start();
		
		button[0] = new Rectangle(1260, 800, 140, 30);
		button[1] = new Rectangle(1100, 800, 140, 30);
								
		ov = new Overwatch(this);
	}
	
	public void paintComponent(Graphics graphics) {
		graphics.drawImage(new ImageIcon("imgs/skrzyzowanie.png").getImage(), 0, 0, 1450, 850, null);
		graphics.setColor(Color.WHITE);
		if(Graph.showCrossBoxes) {
			for(int i = 0; i < 25; i++) {
				Graph.v[i].paintComponent(graphics);
			}
		}
		for(int i = 0; i < car.size(); i++) {
			
			try {sem.acquire();} catch (InterruptedException e1) {e1.printStackTrace();}
			
			if(car.get(i).isLive) car.get(i).paintComponent(graphics);
			else {
				
				try {car.get(i).join();} catch (InterruptedException e) {e.printStackTrace();}
				car.remove(i);
			}
			
			sem.release();
		}
		ov.paintLights(graphics);
		graphics.setColor(Color.GRAY);
		graphics.fillRect(1260, 800, 140, 30);
		graphics.setColor(Color.BLACK);
		graphics.drawRect(1260, 800, 140, 30);
		graphics.setColor(Color.WHITE);
		if(button[0].contains(lastMouseHover)) {
			graphics.setColor(new Color(255, 255, 255, 120));
			graphics.fillRect(1260, 800, 140, 30);
		}
		graphics.drawString("Pokaz sekcje krytyczne", 1270, 820);
		
		graphics.setColor(Color.GRAY);
		graphics.fillRect(1100, 800, 140, 30);
		graphics.setColor(Color.BLACK);
		graphics.drawRect(1100, 800, 140, 30);
		graphics.setColor(Color.WHITE);
		if(button[1].contains(lastMouseHover)) {
			graphics.setColor(new Color(255, 255, 255, 120));
			graphics.fillRect(1100, 800, 140, 30);
		}
		graphics.drawString("Wlacz swiatla", 1110, 820);
		
	}
	
	public void run() {
		while(true) {
			if(car.size() < Graph.allowedSize) car.add(new Car(Graph.getRandomPath()));
			repaint();
		}
	}
}
