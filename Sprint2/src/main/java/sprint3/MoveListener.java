package sprint3;

import org.springframework.web.client.RestClient;

import simulator.Observer;
import simulator.State;
import simulator.Subject;

/**
 * Sprint 3 new class
 */
public class MoveListener extends Observer
{

	State nextMove;
	UserInfo userInfo;
	RestClient client;
	@Override
	public void update(Subject subject)
	{
		
	}

	void notifyNextMove()
	{
		
	}
}
