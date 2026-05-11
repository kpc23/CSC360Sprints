package sprint4;

public record TournamentInfo(String name, boolean registrationOpen, boolean running) 
{

	@Override
	public String toString()
	{
		return name;
	}
	
}
