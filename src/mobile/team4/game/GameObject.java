package mobile.team4.game;

public class GameObject {
	Point position;
	
	public void setPosition(int x, int y) {
		position = new Point(x, y);
	}
	
	public void setPosition(Point p) {
		position = p;
	}
	
	public Point getPosition() {
		return new Point(position);
	}
}

