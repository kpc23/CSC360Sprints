package simulator;

import java.util.ArrayList;
import java.util.List;

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
}
