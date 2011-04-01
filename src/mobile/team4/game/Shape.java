package mobile.team4.game;
import java.util.ArrayList;


abstract class Shape extends GameObject {
	ArrayList<Point> points;
	abstract public void rotate();
	public void print() {
		for (Point p : points) {
			System.out.println(p.x + ", " + p.y);
		}
	}
}
