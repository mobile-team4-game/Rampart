package mobile.team4.game;

public class BackgroundPiece extends GameObject {
	
	public BackgroundPiece(Type t, Point pos) {
		position = pos;
		type = t;
	}
	
	public BackgroundPiece.Type getType() {
		return type;
	}
	
}
