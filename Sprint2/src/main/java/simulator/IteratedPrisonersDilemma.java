/**
 * 
 */
package simulator;

/**
 * 
 */
public class IteratedPrisonersDilemma extends Game
{
	int maxRounds;

	/**
	 * constructor - IPD has 2 choices of actions.
	 */
	public IteratedPrisonersDilemma(int maxRounds)
	{
		this.maxRounds = maxRounds;
		actions = 2;
	}

	@Override
	public boolean endGame()
	{
		return roundsTaken >= maxRounds;
	}

	@Override
	public void scoreActions(int action1, int action2)
	{
		// 0 -> cooperate, 1 ->defect
		if (action1 == 0 && action2 == 0)
		{
			currentState.p1Score += 3;
			currentState.p2Score += 3;
		}
		if (action1 == 1 && action2 == 0)
		{
			currentState.p1Score += 5;
			currentState.p2Score += 0;

		}
		if (action1 == 0 && action2 == 1)
		{
			currentState.p1Score += 0;
			currentState.p2Score += 5;

		}
		if (action1 == 1 && action2 == 1)
		{
			currentState.p1Score += 1;
			currentState.p2Score += 1;
		}
	}

}
