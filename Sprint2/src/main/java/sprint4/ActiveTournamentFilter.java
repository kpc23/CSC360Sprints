package sprint4;

import simulator.Tournament;

public class ActiveTournamentFilter implements TournamentFilterStrategy
{

	@Override
	public boolean filterResult(Tournament tournament)
	{
		// TODO Auto-generated method stub
		return tournament.isRunning();
	}

}
