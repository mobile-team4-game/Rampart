package mobile.team4.game;
import java.util.ArrayList;


abstract class Shape extends GameObject {
	Point position;
	ArrayList<Point> points;
	abstract public void rotate();
	
	public void print() {
		for (Point p : points) {
			System.out.println(p.get_x() + ", " + p.get_y());
		}
	}
	
	public Point getPosition() {
		return new Point(position);
	}
	
	public void setPosition(int x, int y) {
		position = new Point(x, y);
	}
	
	public void setPosition(Point p) {
		position = p;
	}
}
