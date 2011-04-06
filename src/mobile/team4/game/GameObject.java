package mobile.team4.game;

public class GameObject {
	protected Point position;
	protected Type type;
	
	public static enum Type {Wall, Floor, Grass, Water, Castle, Cannon}
	
	public Type getType() {
		return type;
	}
	
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

