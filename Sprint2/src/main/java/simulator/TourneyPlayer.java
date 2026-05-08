package simulator;

import java.util.ArrayList;

public class TourneyPlayer
{
	Participant player;
	int totalScore;
	ArrayList<TourneyPlayer> playersPlayed;

	public TourneyPlayer(Participant player)
	{
		this.player = player;
		this.totalScore = 0;
		this.playersPlayed = new ArrayList<>();
	}
	
	/**
	 * @return the totalScore
	 */
	public int getTotalScore()
	{
		return totalScore;
	}
	
	/**
	 * @param totalScore the totalScore to set
	 */
	public void setTotalScore(int totalScore)
	{
		this.totalScore = totalScore;
	}

	/**
	 * @return the playersPlayed
	 */
	public ArrayList<TourneyPlayer> getPlayersPlayed()
	{
		return playersPlayed;
	}

	/**
	 * @param playersPlayed the playersPlayed to set
	 */
	public void setPlayersPlayed(ArrayList<TourneyPlayer> playersPlayed)
	{
		this.playersPlayed = playersPlayed;
	}

	/**
	 * @param player the player to set
	 */
	public void setPlayer(Participant player)
	{
		this.player = player;
	}

	/**
	 * @return the player
	 */
	public Participant getPlayer()
	{
		return player;
	}
}
