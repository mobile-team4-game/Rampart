package mobile.team4.game;


public class Cannon {
	private Boolean isFiring;
	Boolean isSurrounded;
	Point position = new Point(1, 2);
	
	public Boolean isFiring() {
		return isFiring;
	}
	
	public Boolean isSurrounded() {
		return isSurrounded;
	}
	
	public Point getPosition() {
		return new Point(position);
	}
	
	public void fire(Point target) {
		
	}
}
