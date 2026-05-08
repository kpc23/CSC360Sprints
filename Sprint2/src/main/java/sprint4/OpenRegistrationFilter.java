package sprint4;

import simulator.Tournament;

public class OpenRegistrationFilter implements TournamentFilterStrategy
{

	@Override
	public boolean filterResult(Tournament tournament)
	{
		// TODO Auto-generated method stub
		return tournament.isRegistrationOpen();
	}

}
