package sprint3;

import org.springframework.web.client.RestClient;

import simulator.Game;
import simulator.Observer;
import simulator.State;
import simulator.Subject;

/**
 * Sprint 3 new class - Game observer to send move updates to spectator UI. 
 * 
 * Game changes occur -> listener is notified, sends the move w/ PUT request.
 */
public class MoveListener extends Observer
{

	/**
	 * Every time a player makes a move in Game, update() is invoked upon
	 * MoveListener, which then calls its notify() method and sends an HTTP request
	 * describing the new action State to the spectator.
	 */
	State nextMove;
	UserInfo userInfo; // stores ip and port of spectator.
	RestClient client; // this way client can send updates to appropriate location.

	/**
	 * Constructor
	 * @param nextMove
	 * @param userInfo
	 * @param client
	 */
	public MoveListener(UserInfo userInfo)
	{
		this.userInfo = userInfo;
		this.client = RestClient.create("http://" + userInfo.getIp() + ":" + userInfo.getPort());

	}

	/**
	 * @return the userInfo
	 */
	public UserInfo getUserInfo()
	{
		return userInfo;
	}

	/**
	 * @return the nextMove
	 */
	public State getNextMove()
	{
		return nextMove;
	}

	/**
	 * @param nextMove the nextMove to set
	 */
	public void setNextMove(State nextMove)
	{
		this.nextMove = nextMove;
	}

	/**
	 * @return the client
	 */
	public RestClient getClient()
	{
		return client;
	}

	/**
	 * @param client the client to set
	 */
	public void setClient(RestClient client)
	{
		this.client = client;
	}

	/**
	 * @param userInfo the userInfo to set
	 */
	public void setUserInfo(UserInfo userInfo)
	{
		this.userInfo = userInfo;
	}

	@Override
	/**
	 * 
	 */
	public void update(Subject subject, State s)
	{
		this.nextMove = s;
		notifyNextMove();
	}

	/**
	 * 
	 */
	void notifyNextMove()
	{
		try {
			String moveUpdate = "Round: " + (nextMove.round + 1) + ": P1 -> " + nextMove.p1Action + " VS P2 -> " + nextMove.p2Action;
			client.put().uri("/move").body(moveUpdate).retrieve().body(String.class);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void endMessage(String string)
	{
		try {
			client.put().uri("/move").body(string).retrieve().body(String.class);

		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
}
