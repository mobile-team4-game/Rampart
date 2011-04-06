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
 * coordinates and sources of shots that were shotd, and WallPiece
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
	
	private static final String TIMER_URL = BASE_URL + "timer.php?";
	
	private static final String MODE_URL = BASE_URL + "mode.php?";
	private static final String GAME_URL = BASE_URL + "game.php?";
	
	private static final String FIRE_URL = BASE_URL + "shot.php?";
	
	private static final String ADD_WALL_URL = BASE_URL + "add_wall.php?";
	private static final String ADD_CANNON_URL = BASE_URL + "add_cannon.php?";
	private static final String ADD_CASTLE_URL = BASE_URL + "add_castle.php?";
	
	private static final String REMOVE_WALL_URL = BASE_URL + "remove_wall.php?";
	private static final String REMOVE_CANNON_URL = BASE_URL + "remove_cannon.php?";
	private static final String REMOVE_CASTLE_URL = BASE_URL + "remove_castle.php?";
	
	private static final String GAME_STATE_URL = BASE_URL + "game_state.php?";
	
	private float lastUpdate;
	
	private Server(){}
	
	private static class SingletonHolder
	{
		public static final Server INSTANCE = new Server();
	}
	
	public static Server getInstance()
	{
		return SingletonHolder.INSTANCE;
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
	
	public void startTimer()
	{
		String sUrl = TIMER_URL + "start=1";
		this.LogUrl(sUrl);
		
		this.getUrlData(sUrl);
	}
	
	public int getElapsedTime()
	{
		int elapsed = -1;
		String sUrl = TIMER_URL + "get=1";
		this.LogUrl(sUrl);
		elapsed = Integer.parseInt(this.getUrlData(sUrl));
		return elapsed;
	}
	
	public void stopTimer()
	{
		String sUrl = TIMER_URL + "reset=1";
		this.LogUrl(sUrl);
		this.getUrlData(sUrl);
	}
	
	public void setGameMode(GameState.Mode mode)
	{
		String sUrl = MODE_URL + "game_id=" + Player.getThisPlayer().getGameId() + 
						"&mode=" + mode.toString();
		
		this.LogUrl(sUrl);
		
		this.getUrlData(sUrl);
	}
	
	/**
	 * Let the server know where shots originate from
	 * and are targeted to
	 * @param target
	 */
	public void addShot(Point origin, Point target)
	{
		String sUrl = 	FIRE_URL + 
						"from_x=" + origin.get_x() + 
						"&from_y=" + origin.get_y() +
						"&to_x=" + target.get_x() +
						"&to_y=" + target.get_y() +
						"&game_id=" + Player.getThisPlayer().getGameId() +
						"&player_id=" + Player.getThisPlayer().playerId;
		
		this.LogUrl(sUrl);
		
		this.getUrlData(sUrl);
	}
	
	private void addGameObjects(Vector<Point> locations, String sUrl)
	{
		Point point;
		
		for(Iterator<Point> it = locations.iterator(); it.hasNext();)
		{
			point = it.next();
			sUrl += "x[]=" + point.get_x() + "&y[]=" + point.get_y() + "&";
		}
		
		sUrl +=  	"game_id=" + Player.getThisPlayer().getGameId() +
					"&player_id=" + Player.getThisPlayer().playerId;
		
		this.LogUrl(sUrl);
		
		this.getUrlData(sUrl);
	}
	
	private void removeGameObject(Point location, String sUrl)
	{
		sUrl += "x=" + location.get_x() + 
						"&y=" + location.get_y() + 
						"&game_id=" + Player.getThisPlayer().getGameId();
		
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
		this.addGameObjects(locations, ADD_WALL_URL);
	}
	
	/**
	 * 
	 * @param point
	 */
	public void addCannons(Vector<Point> locations)
	{
		this.addGameObjects(locations, ADD_CANNON_URL);
	}
	
	public void addCastle(Point location)
	{
		Vector<Point> castle = new Vector<Point>();
		castle.add(location);
		this.addGameObjects(castle, ADD_CASTLE_URL);
	}
	
	public void removeWall(Point point)
	{
		this.removeGameObject(point, REMOVE_WALL_URL);
	}
	
	public void removeCannon(Point point)
	{
		this.removeGameObject(point, REMOVE_CANNON_URL);
	}
	
	public void removeCastle(Point point)
	{
		this.removeGameObject(point, REMOVE_CASTLE_URL);
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
		Player player = Player.getThisPlayer();
		String sUrl = GAME_STATE_URL + "game_id=" + player.getGameId() + "&player_id=" + player.playerId + "&last_update=" + this.lastUpdate;
		GameState gameState = new GameState();
		
		this.LogUrl(sUrl);
		
		String sState = this.getUrlData(sUrl);
		
		JSONObject json, jShot, jWall, jCannon;
		JSONArray shots, walls, cannons;
		String serverTime;
		String mode;
		
		Shot shot;
		Point p;
		
		try 
		{
			json = new JSONObject(sState);

			serverTime = json.optString("serverTime");
			mode = json.optString("mode");
			
			shots = json.getJSONArray("shots");
			walls = json.getJSONArray("walls");
			cannons = json.getJSONArray("cannons");
			
			gameState.elapsedTime = json.optInt("elapsedTime");
			gameState.serverTime = this.lastUpdate = Float.valueOf(serverTime);
			gameState.mode = GameState.Mode.valueOf(mode);
			
			int i;
			
			for(i = 0; i < shots.length(); i++)
			{
				jShot = shots.getJSONObject(i);
				JSONObject from, to;
				from = jShot.getJSONObject("from");
				to = jShot.getJSONObject("to");
				shot = new Shot(new Point(from.optInt("x"),from.optInt("y")), 
								new Point(to.optInt("x"), to.optInt("y")));
				gameState.shots.add(shot);
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