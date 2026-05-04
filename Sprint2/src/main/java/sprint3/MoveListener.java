package sprint3;

import org.springframework.web.client.RestClient;

import simulator.Game;
import simulator.Observer;
import simulator.State;
import simulator.Subject;

/**
 * Sprint 3 new class
 */
public class MoveListener extends Observer
{

	/**
	 * Every time a player makes a move in Game, 
	 * update() is invoked upon MoveListener, 
	 * which then calls its notify() method 
	 * and sends an HTTP request describing 
	 * the new action State to the spectator. 
	 */
	State nextMove; 
	UserInfo userInfo; // stores ip and port of spectator.
	RestClient client; //this way client can send updates to appropriate location.

	/**
	 * @param nextMove
	 * @param userInfo
	 * @param client
	 */
	public MoveListener(UserInfo userInfo)
	{
		this.userInfo = userInfo;
		this.client = RestClient.create(
				"http://" + userInfo.getIp().getHostAddress()
				+ ":" + userInfo.getPort()
				);
			
	}

	@Override
	/**
	 * 
	 */
	public void update(Subject subject)
	{
		Game game = (Game) subject;
		this.nextMove = new State(game.getCurrentState());
		notifyNextMove();
	}

	/**
	 * 
	 */
	void notifyNextMove()
	{
		client.put()
		.uri("/move")
		.body(nextMove)
		.retrieve()
		.body(String.class);
	}
}
