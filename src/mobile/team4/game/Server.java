package mobile.team4.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

import android.util.Log;

/**
 * Server will handle all communication with the server. Here you
 * will make requests to send, update, receive information from
 * the server. This includes player information, game information,
 * coordinates and sources of shots that were fired, and WallPiece
 * information.
 *  
 * @author Martin Brown
 *
 */
public class Server 
{	
	/**
	 * This is the base URL for the server
	 */
	private static final String BASE_URL = "http://droidelicious.com/rampart/";
	private static final String FIRE_URL = BASE_URL + "fire.php?";
	private static final String WALL_URL = BASE_URL + "wall.php?";
	private static final String CANNON_URL = BASE_URL + "cannon.php?";
	private static final String GAME_STATE_URL = BASE_URL + "game_state.php?";
	
	private Server(){}
	
	private static class SingletonHolder
	{
		public static final Server INSTANCE = new Server();
	}
	
	public static Server getInstance()
	{
		return SingletonHolder.INSTANCE;
	}
	
	/**
	 * Let the server know where shots originate from
	 * and are targeted to
	 * @param target
	 */
	public void addFire(Point origin, Point target)
	{
		
	}
	
	/**
	 * 
	 * 
	 * @param wallPieces
	 * @param locations
	 */
	public void addWallPieces(Vector<WallPiece> wallPieces, Vector<Point> locations)
	{
		
	}
	
	/**
	 * 
	 * @param point
	 */
	public void addCannons(Vector<Point> locations)
	{
		
	}
	
	/**
	 * I'm assuming that there should be a thread calling this
	 * method every x milliseconds to refresh the game state
	 * 
	 * 
	 * @return
	 */
	public GameState updateGameState()
	{
		GameState gs = new GameState();
		
		
		
		return gs;
	}
	
	/**
	* Source: http://moazzam-khan.com/blog/?p=446
	* HttpGet - doesn't read cookies
	*
	* @param sUrl	
	* @return
	*/
	private String getUrlData(String sUrl)
	{
		String str;
		StringBuffer buff = new StringBuffer();
		URL url = null;
		BufferedReader in = null;
		
		try 
		{
			url = new URL(sUrl);
		} 
		catch (MalformedURLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try 
		{
			in = new BufferedReader(new InputStreamReader(url.openStream()));
		} 
		catch (IOException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Log.i("getUrl","url = " + sUrl);
		
		try 
		{
			while ((str = in.readLine()) != null) 
			{
				Log.i("getUrl",str);
				buff.append(str);
			}
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return buff.toString();
	}
}

/**
 * Where are the walls? Where did shots come from and
 * where are the shots targeted to?
 * 
 * @author Martin Brown
 *
 */
class GameState
{
	public Mode mode;
	public Vector<Fire> fires = new Vector<Fire>();
	public Vector<WallPiece> wallPieces = new Vector<WallPiece>();
}

class Fire
{
	public Point from;
	public Point to;
}

enum Mode {PAUSED, REBUILD, CANNONS, BATTLE};