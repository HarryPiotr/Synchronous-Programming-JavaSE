import java.awt.Point;
import java.util.Random;

public class Graph {

	public static boolean lightsOn = false;
	public static boolean showCrossBoxes = false;
	public static int allowedSize = 40;
	public static String carImages[] =
		{
				"imgs/car1.png",
				"imgs/car2.png",
				"imgs/car3.png",
				"imgs/car4.png",
				"imgs/car5.png",
				"imgs/car6.png",
				"imgs/car7.png",
				"imgs/car8.png",
				"imgs/car9.png",
				"imgs/car10.png"
		};
	public static int ignoredDistance = 0;
	public static int allowedDistance = 50;
	public static Vertex v[] = new Vertex[25];
	public static int buffer[][] = 
		{
				{9, 9}, {9, 9}, {9, 9}, {9, 9}, {9, 9}, {9, 9}, {9, 9}, {9, 9}
		};
	public static int bufferIndicator = 0;
	public static int paths[][][] =
		{
				{
					{0, 3, 6, 5},
					{0, 3, 7, 17, 23},
//					{0, 3, 7, 17, 18, 19, 20},
//					{0, 3, 7, 17, 18, 14, 10, 4, 2}
				},
				{
//					{1, 8, 7, 6, 5},
//					{1, 8, 7, 17, 21, 23},
					{1, 8, 13, 18, 20},
					{1, 8, 13, 18, 14, 10, 4, 2}
				},
				{
					{12, 11, 4, 2},
					{12, 11, 10, 9, 8, 7, 6, 5},
					{12, 11, 10, 9, 8, 7, 17, 23},
					{12, 11, 10, 9, 8, 13, 18, 19, 20}
				},
				{
					{15, 16, 21, 23},
					{15, 16, 17, 18, 19, 20},
					{15, 16, 17, 18, 14, 10, 4, 2}, 
					{15, 16, 17, 18, 14, 9, 8, 7, 6, 5}
				},
				{
					{24, 22, 19, 20},
					{24, 22, 18, 14, 10, 4, 2}, 
					{24, 22, 18, 14, 9, 8, 7, 6, 5},
					{24, 22, 18, 14, 9, 8, 7, 17, 21, 23}
				}
		};
	
	static {
		v[0] = new StartVertex(418, -20);
		v[1] = new StartVertex(511, -20);
		v[2] = new EndVertex(910, -20);
		v[3] = new SplitVertex(418, 140);
		v[4] = new Vertex(910, 140);
		v[5] = new EndVertex(-20, 270);
		v[6] = new Vertex(288, 270);
		v[7] = new Vertex(418, 270);
		v[8] = new Vertex(511, 270);
		v[9] = new Vertex(765, 270);
		v[10] = new Vertex(910, 270);
		v[11] = new SplitVertex(1040, 270);
		v[12] = new StartVertex(1470, 270);
		v[13] = new SmallVertex(598, 444);
		v[14] = new SplitVertex(910, 415);
		v[15] = new StartVertex(-20, 600);
		v[16] = new SplitVertex(288, 600);
		v[17] = new Vertex(418, 600);
		v[18] = new LargeVertex(910, 600);
		v[19] = new Vertex(1040, 600);
		v[20] = new EndVertex(1470, 600);
		v[21] = new Vertex(418, 730);
		v[22] = new SplitVertex(910, 730);
		v[23] = new EndVertex(418, 870);
		v[24] = new StartVertex(910, 870);
	}
	
	public static int[] getRandomPath() {
		
		outer:
		while(true) {
			Random r = new Random();
			int a = r.nextInt(5);
			int b;
			if(a < 2) {
				b = r.nextInt(2);
			}
			else
			b = r.nextInt(4);
			
			for(int i = 0; i < 4; i++) if(a == buffer[i][0] && b == buffer[i][1]) continue outer;
			
			buffer[bufferIndicator % 8][0] = a;
			buffer[bufferIndicator % 8][1] = b;
			bufferIndicator = (bufferIndicator + 1) % allowedSize;
			return paths[a][b];
		}		
	}
	public static boolean isBetween(Point arg0, Point arg1, int direction) {
		
		switch(direction) {
		case 1:
			if(arg0.x == arg1.x && arg0.y - arg1.y < allowedDistance && arg0.y - arg1.y > ignoredDistance && arg0.y - arg1.y > 0) return true;
			break;
		case 2:
			if(arg1.x - arg0.x < allowedDistance && arg1.x - arg0.x > ignoredDistance && arg0.y - arg1.y < allowedDistance && arg0.y - arg1.y > ignoredDistance && arg1.x - arg0.x == arg0.y - arg1.y) return true;
			break;
		case 3:
			if(arg0.y == arg1.y && arg1.x - arg0.x < allowedDistance && arg1.x - arg0.x > 0) return true;
			break;
		case 4:
			if(arg1.x - arg0.x < allowedDistance && arg1.x - arg0.x > ignoredDistance && arg1.y - arg0.y < allowedDistance && arg1.y - arg0.y > ignoredDistance && arg1.x - arg0.x == 2*(arg1.y - arg0.y)) return true;
			break;
		case 5:
			if(arg1.x - arg0.x < allowedDistance && arg1.x - arg0.x > ignoredDistance && arg1.y - arg0.y < allowedDistance && arg1.y - arg0.y > ignoredDistance && arg1.x - arg0.x == arg1.y - arg0.y) return true;
			break;
		case 6:
			if(arg1.x - arg0.x < allowedDistance && arg1.x - arg0.x > ignoredDistance && arg1.y - arg0.y < allowedDistance && arg1.y - arg0.y > ignoredDistance && 2*(arg1.x - arg0.x) == arg1.y - arg0.y) return true;
			break;
		case 7:
			if(arg0.x == arg1.x && arg1.y - arg0.y < allowedDistance && arg1.y - arg0.y > ignoredDistance && arg1.y - arg0.y > 0) return true;
			break;
		case 8:
			if(arg0.x - arg1.x < allowedDistance && arg0.x - arg1.x > ignoredDistance && arg1.y - arg0.y < allowedDistance && arg1.y - arg0.y > ignoredDistance && arg0.x - arg1.x == arg1.y - arg0.y) return true;
			break;
		case 9:
			if(arg0.y == arg1.y && arg0.x - arg1.x < allowedDistance && arg0.x - arg1.x > 0) return true;
			break;
		case 10:
			if(arg0.x - arg1.x < allowedDistance && arg0.x - arg1.x > ignoredDistance && arg0.y - arg1.y < allowedDistance && arg0.y - arg1.y > ignoredDistance && arg0.x - arg1.x == arg0.y - arg1.y) return true;
			break;
			
		}
		
		return false;
	}
}
