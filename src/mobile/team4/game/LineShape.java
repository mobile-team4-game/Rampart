package mobile.team4.game;

import java.util.ArrayList;

public class LineShape extends Shape {

	enum State { Horizontal, Vertical }
	
	//ArrayList<Point> points;
	State state;
	
	static ArrayList<Point> points;
	
	public LineShape(int size) {
		points = new ArrayList<Point>();
		state = State.Horizontal;
		for (int i = 0; i < size; i++) {
			points.add(new Point(i, 0));
		}		
	}
	
	@Override
	public void rotate() {
		if (state == State.Horizontal) {
			state = State.Vertical;
		} else if (state == State.Vertical) {
			state = State.Horizontal;
		}

		for (Point p : points) {
			p.swap();
		}
	}

}
