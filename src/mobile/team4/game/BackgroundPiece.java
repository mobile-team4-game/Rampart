package mobile.team4.game;

public class BackgroundPiece extends GameObject {
	
	public enum Type { Grass, Water }
	
	Type type;
	
	public BackgroundPiece(Type t) {
		type = t;
	}
	
	public Type getType() {
		return type;
	}
	
}
