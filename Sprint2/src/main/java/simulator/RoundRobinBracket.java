/**
 * 
 */
package simulator;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public class RoundRobinBracket extends Bracket
{
//	ArrayList<TourneyPlayer> participantList;

	int firstParticipantCounter;
	int secondParticipantCounter;

	public RoundRobinBracket(ArrayList<TourneyPlayer> players)
	{
		super(players);
		firstParticipantCounter = 0;
		secondParticipantCounter = 1;
	}

	@Override
	/**
	 * Each player plays each other once for Round Robin.
	 */
	public Tuple nextPair(State state)
	{
		// ensure if we should be done making pairs aka all have been matched
		// if the "first" one is now the last one then yes and just return empty tuple.
		if (firstParticipantCounter >= participantList.size() - 1)
		{
			return null;
		}
		// otherwise return pair
		Tuple pair = new Tuple(participantList.get(firstParticipantCounter),
				participantList.get(secondParticipantCounter));

		secondParticipantCounter++;
		// if the second pair is at size then we have matched first with all the rest,
		// so update first... and second.
		if (secondParticipantCounter >= participantList.size())
		{
			firstParticipantCounter++;
			secondParticipantCounter = firstParticipantCounter + 1;
		}

		return pair;
	}

}
