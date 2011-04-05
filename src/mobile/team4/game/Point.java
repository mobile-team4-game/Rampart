package mobile.team4.game;

public class Point {
	private int x;
	private int y;
	
	public Point(int i, int j) {
		x = i;
		y = j;
	}
	public Point(Point p) {
		x = p.x;
		y = p.y;
	}
	public void swap() {
		int temp = x;
		x = y;
		y = temp;
	}
	
	public int get_x()
	{
		return x;
	}
	
	public int get_y()
	{
		return y;
	}
}
