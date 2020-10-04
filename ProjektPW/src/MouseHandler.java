import java.awt.Point;
import java.awt.event.*;

public class MouseHandler implements MouseMotionListener, MouseListener {

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		System.out.println("X: " + arg0.getX());
		System.out.println("Y: " + arg0.getY());
		System.out.println();
		Panel.lastClick = new Point(arg0.getPoint());
		if(Panel.button[0].contains(Panel.lastClick)) {
			if(Graph.showCrossBoxes) Graph.showCrossBoxes = false;
			else Graph.showCrossBoxes = true;
		}
		if(Panel.button[1].contains(Panel.lastClick)) {
			if(Graph.lightsOn) Graph.lightsOn = false;
			else Graph.lightsOn = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		Panel.lastMouseHover = new Point(arg0.getPoint());
	}

}
