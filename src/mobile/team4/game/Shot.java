package mobile.team4.game;

public class Shot extends GameObject {

	Point target;
	double x;
	double y;
	public Shot(Point pos, Point tar) {
		position = pos;
		target = tar;
		x = position.get_x();
		y = position.get_y();
	}
	
	public Point getTarget() {
		return target;
	}
}
