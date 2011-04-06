package mobile.team4.game;

public class Shot extends GameObject {

	Point target;
	double x;
	double y;
	public Shot(Point pos, Point tar) {
		position = pos;
		target = tar;
	}
}
