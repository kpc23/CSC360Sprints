/**
 * 
 */
package simulator;

/**
 * 
 */
public class State
{

	/**
	 * @param p1Name
	 * @param p1Score
	 * @param p1Action
	 * @param p2Name
	 * @param p2Score
	 * @param p2Action
	 * @param round
	 */
	public State(State state)
	{
		this.p1Name = state.p1Name;
		this.p1Score = state.p1Score;
		this.p1Action = state.p1Action;
		this.p2Name = state.p2Name;
		this.p2Score = state.p2Score;
		this.p2Action = state.p2Action;
		this.round = state.round;
	}

	public State()
	{
		// TODO Auto-generated constructor stub
	}

	public String p1Name;
	public int p1Score;
	public int p1Action;

	public String p2Name;
	public int p2Score;
	public int p2Action;

	public int round;
}
