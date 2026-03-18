/**
 * 
 */
package simulator;

import java.util.ArrayList;

/**
 * 
 */
public abstract class Bracket
{
	ArrayList<TourneyPlayer> participantList;

	public Bracket(ArrayList<TourneyPlayer> players)
	{
		this.participantList = players;
	}

	public abstract Tuple nextPair(State state);
}
