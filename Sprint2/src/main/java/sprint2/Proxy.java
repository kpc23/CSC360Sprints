package sprint2;

import java.net.InetAddress;
import org.springframework.web.client.RestClient;

import simulator.Participant;

/**
 * The Proxy class extends the Participant class and supports hosting remote
 * player participants and their registration.
 */
public class Proxy extends Participant
{
	InetAddress IPAddress;
	String name;
	int port;
	RestClient client;

	/**
	 * @param name
	 * @param ipaddress
	 * @param name2
	 * @param port
	 */
	public Proxy(String name, InetAddress ipaddress, int port)
	{
		super(name);
		this.IPAddress = ipaddress;
		this.port = port;
		client = RestClient.create();
	}

	/**
	 * Proxy uses a RestClient to perform a GET request to the user's RemoteServer.
	 * 
	 */
	public int makeChoice(int actions)
	{
		// need to revise... is url ok?
		String url = "http://" + IPAddress + ":" + port + "/makeChoice/" + actions;

		return Integer.parseInt(client.get().uri(url).retrieve().body(String.class));
	}

//	/**
//	 * The Proxy will perform a PUT to the RemoteServer 
//	 * so that the remote Participant may update its memory attribute, 
//	 * using this function updateMemory().
//	 * 
//	 *  intended to translate the stored data in a State class to pass to 
//	 *  the RemoteServer’s Participant. 
//	 *  
//	 * @param state has p1 and p2 name, score, and action for each.
//	 */
//	public void updateMemory(State state)
//	{
//		//need to revise -- unsure how to implement put
//		client.put().body(state).retrieve();
//	}
}
