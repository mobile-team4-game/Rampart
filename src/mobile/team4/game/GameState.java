package mobile.team4.game;

import java.util.Iterator;
import java.util.Vector;


/**
 * Where are the walls? Where did shots come from and
 * where are the shots targeted to?
 * 
 * @author Martin Brown
 *
 */
public class GameState
{
	public static enum Mode {REBUILD, CANNONS, BATTLE, PAUSED};
	public double serverTime;
	public Mode mode;
	public Vector<Shot> shots = new Vector<Shot>();
	public Vector<Point> walls = new Vector<Point>();
	public Vector<Point> cannons = new Vector<Point>();
	public Vector<Point> castles = new Vector<Point>();
	
	public String toString()
	{
		String s = "serverTime = " + serverTime + "\n";
		s += "mode = " + mode.toString() + "\n";
		
		Shot f;
		Point p;
		
		for(Iterator<Shot> it = shots.iterator(); it.hasNext();)
		{
			f = it.next();
			s += "shot: <" + f.position.x + "," + f.position.y + "> , < " + f.target.x + "," + f.target.y + ">\n";
		}
		
		for(Iterator<Point> it = walls.iterator(); it.hasNext();)
		{
			p = it.next();
			s += "wall: <" + p.x + ", " + p.y + ">\n";
		}
		
		for(Iterator<Point> it = cannons.iterator(); it.hasNext();)
		{
			p = it.next();
			s += "cannon: <" + p.x + ", " + p.y + ">\n";
		}
		
		for(Iterator<Point> it = castles.iterator(); it.hasNext();)
		{
			p = it.next();
			s += "castle: <" + p.x + ", " + p.y + ">\n";
		}
		
		return s;
	}
}
