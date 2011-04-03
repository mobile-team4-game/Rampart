package mobile.team4.game;

public class Point {
	int x;
	int y;
	
	public Point(int i, int j) {
		x = i;
		y = j;
	}
	public Point(Point p) {
		x = p.x;
		y = p.y;
	}
	public void swap() {
		int temp = x;
		x = y;
		y = temp;
	}
}
