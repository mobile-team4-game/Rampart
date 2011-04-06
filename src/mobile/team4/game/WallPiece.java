package mobile.team4.game;

public class WallPiece extends GameObject {
	// changing it so wall piece is one individual square of a wall
	// shapes will be represented w/ individual shape classes w/ rotate methods 
	// and all that
	
	public WallPiece(Point p) {
		type = GameObject.Type.Wall;
		position = p;
	}
	
	public WallPiece(int x, int y) {
		type = GameObject.Type.Wall;
		Point p = new Point(x, y);
		setPosition(p);
	}

	
	
	/*
	public enum Shape {Square, Line, Z};	
	//ArrayList<Point> shape;
	
	public static ArrayList<Point> Line = mine was 200
		new ArrayList<Point>(
			Arrays.asList(new Point(0, 0), new Point(1, 0), new Point(2, 0))
		);
	
	public static ArrayList<Point> Square = 
		new ArrayList<Point>(
			Arrays.asList(new Point(0, 0))
		);

	public static ArrayList<Point> Z = 
		new ArrayList<Point>(
				Arrays.asList(new Point(0, 0), new Point(1, 0), new Point(1, 1), new Point(2, 1))
		);
	
	public static ArrayList<Point> L =array
		new ArrayList<Point> (
				Arrays.asList(new Point)
	
	/*
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
	*/
	
}
