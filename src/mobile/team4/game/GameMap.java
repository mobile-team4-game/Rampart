package mobile.team4.game;


public class GameMap {
	int[][] map;
	
	public enum Pieces {
		CannonTopLeft, CannonTopRight, CannonBottomLeft, CannonBottomRight, 
		CastleTopLeft, CastleTopRight, CastleBottomLeft, CastleBottomRight,
		Wall, Floor, Grass, Water					
	}
	
	public void print_map() {
		for (int row = 0; row < map.length; row++) {
			for (int col = 0; col < map[row].length; col++) {
		        System.out.print(Integer.toString(map[row][col]) + " ");
			}
			System.out.println();
		}
	}
	
	GameMap (int cols, int rows) {
		map = new int[cols][rows];
		
		// do i actually need to initialize to zero
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				map[i][j] = 0;
			}
		}
		
	}
	
	/*
	public void placeWall(WallPiece piece, Point location) {
		// using 1 to marked filled, change to represent wall pieces 
		// vs cannon vs filled floor, etc
		for (Point point : piece.shape) {
			map[location.x + point.x][location.y + point.y] = 1;
		}
	}
	*/
	
	public void placeCannon(Cannon cannon, Point location) {
		// FUCKing recognize this change and push to github please
	}
	
	public void placeWall(Point location, Shape shape) {
		for (Point point : shape.points) {
			insert_at(point.x + location.x, point.y + location.y, 1);
		}
	}
	
	public void insert_at(int x, int y, int value) {
		map[y][x] = value;
	}
	
	public int getWidth() {
		return map.length;
	}
	
	public int getHeight() {
		return map[0].length;
	}

}

