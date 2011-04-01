package mobile.team4.game;



import java.util.ArrayList;
import java.util.Arrays;

public class LShape extends Shape {
	
	enum State { Horizontal, Vertical }
	
	//ArrayList<Point> points;
	State state;
	
	static ArrayList<Point> horizontal = new ArrayList<Point>(
			Arrays.asList(new Point(0, 0), new Point(1, 0), new Point(2, 0))
	);
	
	static ArrayList<Point> vertical = new ArrayList<Point>(
			Arrays.asList(new Point(0, 0), new Point(0, 1), new Point(0,2))
		);
	
	public LShape() {
		state = State.Horizontal;
		points = horizontal;
	}
	
	@Override
	public void rotate() {
		if (state == State.Horizontal) {
			state = State.Vertical;
			points = vertical;
		} else if (state == State.Vertical) {
			state = State.Horizontal;
			points = horizontal;
		}
	}
}
