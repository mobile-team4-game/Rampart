package mobile.team4.game;

import java.util.Random;

public class Player
{
	String playerId;
	private int inGame;
	public boolean isGameCreator;
	private static Player thisPlayer;
	
	public Player(String id)
	{
		this.playerId = id;
		
		if(Player.thisPlayer == null)
			Player.thisPlayer = this;
	}
	
	public Player()
	{
		Random r = new Random();
		r.setSeed(System.currentTimeMillis());

		this.playerId = Long.toString(Math.abs(r.nextLong()), 36);
		
		if(Player.thisPlayer == null)
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
