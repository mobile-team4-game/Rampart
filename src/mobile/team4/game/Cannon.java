package mobile.team4.game;


public class Cannon extends GameObject {
	private Boolean isFiring;
	Boolean isSurrounded;
	
	public Cannon(Point pos) {
		position = pos;
		type = Type.Cannon;
		isFiring = false;
	}

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
	
	public void setFiring(boolean t) {
		isFiring = t;
	}
}
