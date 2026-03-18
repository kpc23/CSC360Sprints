/**
 * 
 */
package simulator;

/**
 * returns 0 or 1 depending on what it chose previously. thus alternating.
 */
public class AlternatingBot extends Participant
{
	int previousChoice = 0;

	public AlternatingBot(String name)
	{
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	int makeChoice(int actions)
	{
		// TODO Auto-generated method stub
		if (previousChoice == 0)
		{
			previousChoice = 1;
		} else
		{
			previousChoice = 0;
		}

		return previousChoice;
	}
}
