package simulator;

import java.util.ArrayList;

/**
 * 
 */
public abstract class Bracket
{
	ArrayList<TourneyPlayer> participantList;

	/**
	 * @return the participantList
	 */
	public ArrayList<TourneyPlayer> getParticipantList()
	{
		return participantList;
	}

	public Bracket(ArrayList<TourneyPlayer> players)
	{
		this.participantList = players;
	}

	public abstract Tuple nextPair(State state);
}
