import java.awt.*;
import java.util.*;

public class Overwatch implements Runnable{
	
	public Vertex v[] = new Vertex[24];
	private Color lightColors[] = {Color.RED, Color.RED, Color.RED, Color.RED, Color.RED, Color.RED, Color.RED, Color.RED, Color.RED, Color.RED, Color.RED, Color.RED, Color.RED};
	private Panel myPanel;
	
	public Overwatch(Panel arg0) {
		myPanel = arg0;
		
		try {
			Graph.v[7].signals[7].acquire();
			Graph.v[7].signals[9].acquire();
			Graph.v[17].signals[7].acquire();
			Graph.v[17].signals[3].acquire();
			Graph.v[8].signals[7].acquire();
			Graph.v[8].signals[9].acquire();
			Graph.v[18].signals[1].acquire();
			Graph.v[18].signals[3].acquire();
			Graph.v[18].signals[4].acquire();
			Graph.v[9].signals[9].acquire();
			Graph.v[9].signals[10].acquire();
			Graph.v[10].signals[1].acquire();
			Graph.v[10].signals[9].acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		new Thread(this).start();
	}
	
	private void waitLonger() {
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void waitShorter() {
		try {
			Thread.sleep(750);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void paintLights(Graphics graphics) {
		if(Graph.lightsOn) {
			graphics.setColor(lightColors[0]);
			graphics.fillOval(395, 235, 6, 6);
			graphics.fillOval(435, 235, 6, 6);
			graphics.setColor(lightColors[1]);
			graphics.fillOval(448, 249, 6, 6);
			graphics.fillOval(448, 289, 6, 6);
			graphics.setColor(lightColors[2]);
			graphics.fillOval(395, 562, 6, 6);
			graphics.fillOval(435, 562, 6, 6);
			graphics.setColor(lightColors[3]);
			graphics.fillOval(381, 575, 6, 6);
			graphics.fillOval(381, 615, 6, 6);
			graphics.setColor(lightColors[4]);
			graphics.fillOval(488, 235, 6, 6);
			graphics.fillOval(528, 235, 6, 6);
			graphics.setColor(lightColors[5]);
			graphics.fillOval(543, 249, 6, 6);
			graphics.fillOval(543, 289, 6, 6);
			graphics.setColor(lightColors[6]);
			graphics.fillOval(787, 575, 6, 6);
			graphics.fillOval(787, 615, 6, 6);
			graphics.setColor(lightColors[7]);
			graphics.fillOval(884, 629, 6, 6);
			graphics.fillOval(924, 629, 6, 6);
			graphics.setColor(lightColors[8]);
			graphics.fillOval(784, 552, 6, 6);
			graphics.fillOval(854, 552, 6, 6);
			graphics.setColor(lightColors[9]);
			graphics.fillOval(812, 249, 6, 6);
			graphics.fillOval(812, 289, 6, 6);
			graphics.setColor(lightColors[10]);
			graphics.fillOval(773, 313, 6, 6);
			graphics.fillOval(828, 313, 6, 6);
			graphics.setColor(lightColors[11]);
			graphics.fillOval(884, 303, 6, 6);
			graphics.fillOval(924, 303, 6, 6);
			graphics.setColor(lightColors[12]);
			graphics.fillOval(938, 249, 6, 6);
			graphics.fillOval(938, 289, 6, 6);
		}
	}
	
	public void run() {
		while(true) {
			if(Graph.lightsOn) {

				//Stage1:
				System.out.println("Faza1");			
				Graph.v[7].signals[7].release();
				Graph.v[17].signals[7].release();
				Graph.v[8].signals[7].release();
				Graph.v[18].signals[1].release();
				Graph.v[9].signals[10].release();
				Graph.v[10].signals[1].release();
				
				lightColors[0] = Color.GREEN;
				lightColors[2] = Color.GREEN;
				lightColors[4] = Color.GREEN;
				lightColors[7] = Color.GREEN;
				lightColors[10] = Color.GREEN;
				lightColors[11] = Color.GREEN;
				
				waitLonger();
				
				try {
					Graph.v[7].signals[7].acquire();
					Graph.v[17].signals[7].acquire();
					Graph.v[8].signals[7].acquire();
					Graph.v[18].signals[1].acquire();
					Graph.v[9].signals[10].acquire();
					Graph.v[10].signals[1].acquire();
					
					lightColors[0] = Color.RED;
					lightColors[2] = Color.RED;
					lightColors[4] = Color.RED;
					lightColors[7] = Color.RED;
					lightColors[10] = Color.RED;
					lightColors[11] = Color.RED;
										
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				waitShorter();
				lightColors[1] = Color.ORANGE;
				lightColors[3] = Color.ORANGE;
				lightColors[5] = Color.ORANGE;
				lightColors[6] = Color.ORANGE;
				lightColors[9] = Color.ORANGE;
				lightColors[12] = Color.ORANGE;
				waitShorter();
				
				//Stage2:
								
				System.out.println("Faza2");
				Graph.v[7].signals[9].release();
				Graph.v[8].signals[9].release();
				Graph.v[9].signals[9].release();
				Graph.v[17].signals[3].release();
				Graph.v[18].signals[3].release();
				Graph.v[10].signals[9].release();
				
				lightColors[1] = Color.GREEN;
				lightColors[3] = Color.GREEN;
				lightColors[5] = Color.GREEN;
				lightColors[6] = Color.GREEN;
				lightColors[9] = Color.GREEN;
				lightColors[12] = Color.GREEN;
				
				waitLonger();
				
				try {
					Graph.v[7].signals[9].acquire();
					Graph.v[8].signals[9].acquire();
					Graph.v[9].signals[9].acquire();
					Graph.v[17].signals[3].acquire();
					Graph.v[18].signals[3].acquire();
					Graph.v[10].signals[9].acquire();
					
					lightColors[1] = Color.RED;
					lightColors[3] = Color.RED;
					lightColors[5] = Color.RED;
					lightColors[6] = Color.RED;
					lightColors[9] = Color.RED;
					lightColors[12] = Color.RED;
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				waitShorter();
				lightColors[0] = Color.ORANGE;
				lightColors[2] = Color.ORANGE;
				lightColors[8] = Color.ORANGE;
				lightColors[10] = Color.ORANGE;
				waitShorter();
				
				//Stage3:
				System.out.println("Faza3");
				Graph.v[7].signals[7].release();
				Graph.v[17].signals[7].release();
				Graph.v[18].signals[4].release();
				Graph.v[9].signals[10].release();
				
				lightColors[0] = Color.GREEN;
				lightColors[2] = Color.GREEN;
				lightColors[8] = Color.GREEN;
				lightColors[10] = Color.GREEN;
				
				waitLonger();
				
				try {
					Graph.v[7].signals[7].acquire();
					Graph.v[17].signals[7].acquire();
					Graph.v[18].signals[4].acquire();
					Graph.v[9].signals[10].acquire();
					
					lightColors[0] = Color.RED;
					lightColors[2] = Color.RED;
					lightColors[8] = Color.RED;
					lightColors[10] = Color.RED;
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				waitShorter();
				lightColors[0] = Color.ORANGE;
				lightColors[2] = Color.ORANGE;
				lightColors[4] = Color.ORANGE;
				lightColors[7] = Color.ORANGE;
				lightColors[10] = Color.ORANGE;
				lightColors[11] = Color.ORANGE;
				waitShorter();
			}						
		}
	}
}
