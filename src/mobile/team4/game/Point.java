package mobile.team4.game;

public class Point {
	public Point(int i, int j) {
		x = i;
		y = j;
	}
	public Point(Point p) {
		x = p.x;
		y = p.y;
	}
	int x;
	int y;
}
