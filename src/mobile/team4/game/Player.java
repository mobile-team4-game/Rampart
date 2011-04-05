package mobile.team4.game;

public class Player
{
	String playerId;
	private int inGame;
	private static Player thisPlayer;
	
	public Player(String id)
	{
		this.playerId = id;
		
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
