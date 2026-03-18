/**
 * 
 */
package simulator;

/**
 * Actions from players from each round in a game.
 */
public class ActionFileLogger extends Observer
{
	String messageUpdate; 
	public String getMessage() {
		return messageUpdate;
	}
	@Override
	public void update(Subject subject)
	{
		Game game = (Game) subject;
		State s = game.currentState;
		messageUpdate = "Round " + (s.round+1) + " PLAYER 1 ACTION = " + s.p1Action + " || PLAYER 2 ACTION = " + s.p2Action;
		System.out.println(messageUpdate);
	}
}