package mobile.team4.game;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Iterator;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
	
	private static final String MODE_URL = BASE_URL + "mode.php?";
	private static final String GAME_URL = BASE_URL + "game.php?";
	
	private static final String FIRE_URL = BASE_URL + "fire.php?";
	
	private static final String ADD_WALL_URL = BASE_URL + "add_wall.php?";
	private static final String ADD_CANNON_URL = BASE_URL + "add_cannon.php?";
	
	private static final String REMOVE_WALL_URL = BASE_URL + "remove_wall.php?";
	private static final String REMOVE_CANNON_URL = BASE_URL + "remove_cannon.php?";
	
	private static final String GAME_STATE_URL = BASE_URL + "game_state.php?";
	
	private double lastUpdate;
	
	private Server(){}
	
	private static class SingletonHolder
	{
		public static final Server INSTANCE = new Server();
	}
	
	public static Server getInstance()
	{
		return SingletonHolder.INSTANCE;
	}
	
	public void updateGameMode(Mode mode)
	{
		String sUrl = MODE_URL + "game_id=" + Player.getThisPlayer().getGameId() + 
						"&mode=" + mode.toString();
		
		this.LogUrl(sUrl);
		
		this.getUrlData(sUrl);
	}
	
	public int newGame()
	{
		int gameId;
		String sUrl = GAME_URL + "player_id=" + Player.getThisPlayer().playerId;
		
		this.LogUrl(sUrl);
		
		gameId = Integer.parseInt(this.getUrlData(sUrl));
		Player.getThisPlayer().setGameId(gameId);
		
		return gameId;
	}
	
	/**
	 * Let the server know where shots originate from
	 * and are targeted to
	 * @param target
	 */
	public void addFire(Point origin, Point target)
	{
		String sUrl = 	FIRE_URL + 
						"from_x=" + origin.x + 
						"&from_y=" + origin.y +
						"&to_x=" + target.x +
						"&to_y=" + target.y +
						"&game_id=" + Player.getThisPlayer().getGameId() +
						"&player_id=" + Player.getThisPlayer().playerId;
		
		this.LogUrl(sUrl);
		
		this.getUrlData(sUrl);
	}
	
	/**
	 * 
	 * 
	 * @param wallPieces
	 * @param locations
	 */
	public void addWallPieces(Vector<Point> locations)
	{
		String sUrl = ADD_WALL_URL;
		Point point;
		
		for(Iterator<Point> it = locations.iterator(); it.hasNext();)
		{
			point = it.next();
			sUrl += "x[]=" + point.x + "&y[]=" + point.y + "&";
		}
		
		sUrl +=  	"game_id=" + Player.getThisPlayer().getGameId() +
					"&player_id=" + Player.getThisPlayer().playerId;
		
		this.LogUrl(sUrl);
		
		this.getUrlData(sUrl);
	}
	
	/**
	 * 
	 * @param point
	 */
	public void addCannons(Vector<Point> locations)
	{
		String sUrl = ADD_CANNON_URL;
		Point point;
		
		for(Iterator<Point> it = locations.iterator(); it.hasNext();)
		{
			point = it.next();
			sUrl += "x[]=" + point.x + "&y[]=" + point.y + "&";
		}
		
		sUrl +=  	"game_id=" + Player.getThisPlayer().getGameId() +
					"&player_id=" + Player.getThisPlayer().playerId;
		
		this.LogUrl(sUrl);
		
		this.getUrlData(sUrl);
	}
	
	public void removeWall(Point point)
	{
		String sUrl = REMOVE_WALL_URL + "x=" + point.x + 
						"&y=" + point.y + 
						"&game_id=" + Player.getThisPlayer().getGameId();
		this.LogUrl(sUrl);
		this.getUrlData(sUrl);
	}
	
	public void removeCannon(Point point)
	{
		String sUrl = REMOVE_CANNON_URL + "x=" + point.x + 
						"&y=" + point.y + 
						"&game_id=" + Player.getThisPlayer().getGameId();
		
		this.LogUrl(sUrl);
		this.getUrlData(sUrl);
	}
	
	/**
	 * I'm assuming that there should be a thread calling this
	 * method every x milliseconds to refresh the game state
	 * 
	 * 
	 * @return
	 */
	public GameState getGameState()
	{
		String sUrl = GAME_STATE_URL + "game_id=" + Player.getThisPlayer().getGameId();
		GameState gameState = new GameState();
		
		this.LogUrl(sUrl);
		
		String sState = this.getUrlData(sUrl);
		
		JSONObject json, jFire, jWall, jCannon;
		JSONArray fires, walls, cannons;
		String serverTime;
		String mode;
		
		Fire fire;
		Point p;
		
		try 
		{
			json = new JSONObject(sState);

			serverTime = json.optString("serverTime");
			mode = json.optString("mode");
			
			fires = json.getJSONArray("fires");
			walls = json.getJSONArray("walls");
			cannons = json.getJSONArray("cannons");
			
			gameState.serverTime = Double.valueOf(serverTime);
			gameState.mode = Mode.valueOf(mode);
			
			int i;
			
			for(i = 0; i < fires.length(); i++)
			{
				jFire = fires.getJSONObject(i);
				JSONObject from, to;
				from = jFire.getJSONObject("from");
				to = jFire.getJSONObject("to");
				fire = new Fire(new Point(from.optInt("x"),from.optInt("y")), 
								new Point(to.optInt("x"), to.optInt("y")));
				gameState.fires.add(fire);
			}
			
			for(i = 0; i < walls.length(); i++)
			{
				jWall = walls.getJSONObject(i);
				
				gameState.walls.add(new Point(jWall.optInt("x"), jWall.optInt("y")));
			}
			
			for(i = 0; i < cannons.length(); i++)
			{
				jCannon = cannons.getJSONObject(i);
				
				gameState.cannons.add(new Point(jCannon.optInt("x"), jCannon.optInt("y")));
			}
			
			Log.i("gameState", gameState.toString());
		} 
		catch (JSONException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Log.i("data",sState);
		
		return gameState;
	}
	
	private void LogUrl(String sUrl)
	{
		Log.i("GameServer",sUrl);
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
			
			try 
			{
				in = new BufferedReader(new InputStreamReader(url.openStream()));
				
				try 
				{
					while ((str = in.readLine()) != null) 
					{
						Log.i("getUrl",str);
						buff.append(str);
					}
				} 
				catch (Exception e)
				{
					// TODO Auto-generated catch block
					Log.i("getUrl",e.toString());
				}
			} 
			catch (Exception e) 
			{
				// TODO Auto-generated catch block
				Log.i("getUrl",e.toString());
			}
			
			return buff.toString();
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			Log.i("getUrl",e.toString());
		}
		
		Log.i("getUrl","return ''");
		
		return "";
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
	public double serverTime;
	public Mode mode;
	public Vector<Fire> fires = new Vector<Fire>();
	public Vector<Point> walls = new Vector<Point>();
	public Vector<Point> cannons = new Vector<Point>();
	
	public String toString()
	{
		String s = "serverTime = " + serverTime + "\n";
		s += "mode = " + mode.toString() + "\n";
		
		Fire f;
		Point p;
		
		for(Iterator<Fire> it = fires.iterator(); it.hasNext();)
		{
			f = it.next();
			s += "fire: <" + f.from.x + "," + f.from.y + "> , < " + f.to.x + "," + f.to.y + ">\n";
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
		
		return s;
	}
}

class Fire
{
	public Point from;
	public Point to;
	
	public Fire(Point source, Point dest)
	{
		from = source;
		to = dest;
	}
}

class Player
{
	String playerId;
	private int inGame;
	private static Player thisPlayer;
	
	public Player(String id, boolean isThisPlayer)
	{
		this.playerId = id;
		
		if(isThisPlayer)
			Player.thisPlayer = this;
	}
	
	public static Player getThisPlayer()
	{
		return thisPlayer;
	}
	
	public void setGameId(int gameId)
	{
		this.inGame = gameId;
	}
	
	public int getGameId()
	{
		return this.inGame;
	}
}

enum Mode {REBUILD, CANNONS, BATTLE, PAUSED};