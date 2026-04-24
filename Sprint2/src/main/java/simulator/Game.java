/**
 * 
 */
package simulator;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public abstract class Game extends Subject
{
	int actions; // number actions players can choose
	State currentState; // game current state
	int roundsTaken; // counter for the number of rounds

	/**
	 * Match play between two players
	 * 
	 * @param p1 - player 1
	 * @param p2 - player 2
	 */
	public void play(Participant p1, Participant p2)
	{
		if (p1 == null || p2 == null)
		{
			throw new IllegalArgumentException("Invalid player");
		}
		currentState = new State();

		roundsTaken = 0;

		p1.clearMemory();
		p2.clearMemory();

		while (!endGame())
		{
			currentState.round = roundsTaken;
			// get player action choices
			int action1 = p1.makeChoice(actions);
			int action2 = p2.makeChoice(actions);

			// keep track of choice
			currentState.p1Action = action1;
			currentState.p2Action = action2;
			State state = new State(currentState);
			// score the actions
			scoreActions(action1, action2);
			p1.addMemory(state);
			p2.addMemory(state);
			hook();
			notifyObservers();
			roundsTaken++;

		}
		hookEndGame();
	}

	public void hook()
	{
	}

	public void hookEndGame()
	{

	}

	public abstract boolean endGame();

	public abstract void scoreActions(int action1, int action2);

}
