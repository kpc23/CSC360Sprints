package simulator;

/**
 * result
 */
public class ResultsFileLogger extends Observer
{
	String messageUpdate;

	public String getMessage()
	{
		return messageUpdate;
	}

	@Override
	public void update(Subject subject, State s)
	{
		messageUpdate = "Game Score Result: " 
					+ s.p1Score 
					+ " vs " 
					+ s.p2Score;
		System.out.println(messageUpdate);
	}

}
