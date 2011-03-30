package mobile.team4.game;

import java.util.ArrayList;
import java.util.Arrays;

public class WallPiece {
	public enum Shape {Square, Line, Z};
	
	ArrayList<Point> shape;
	
	private ArrayList<Point> line = 
		new ArrayList<Point>(
			Arrays.asList(new Point(0, 0), new Point(1, 0), new Point(2, 0))
		);
	
	private ArrayList<Point> square = 
		new ArrayList<Point>(
			Arrays.asList(new Point(0, 0))
		);

	private ArrayList<Point> z = 
		new ArrayList<Point>(
				Arrays.asList(new Point(0, 0), new Point(1, 0), new Point(1, 1), new Point(2, 1))
		);
	
	WallPiece (Shape s) {
		switch (s) {
		case Line:
			shape = line;
			break;
		case Square:
			shape = square;
			break;
		case Z:
			shape = z;
			break;
		}
	}
}
