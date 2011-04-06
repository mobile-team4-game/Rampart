package mobile.team4.game;

import java.util.ArrayList;

public class Castle extends GameObject {
	ArrayList<WallPiece> wallPieces;
	
	Castle(int x, int y)
	{
		// is super implied?
		super.setPosition(x, y);
	}
	
	/*
	boolean addWallPiece(WallPiece wp)
	{
		if (GameMap.get_at(wp.getPosition().get_x(), wp.getPosition().get_y()) == 0)
		{
			wp.setPosition(-1,-1);	
			return (false);
		}
		return true;
	}
	*/
	
	public boolean isFullyEnclosed()
	{
	  return (false);
	}
}
