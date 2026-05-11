package sprint4;

public class OpenRegistrationFilter implements TournamentFilterStrategy
{

	@Override
	public boolean filterResult(TournamentInfo tournament)
	{
		// TODO Auto-generated method stub
		return tournament.registrationOpen();
	}
	

}
