package sprint2;

import org.springframework.web.client.RestClient;
import simulator.Participant;

/**
 * The Proxy class extends the Participant class and supports hosting remote
 * player participants and their registration.
 */
public class Proxy extends Participant
{
	String IPAddress;
	String name;
	int port;
	RestClient client;

	/**
	 * @param name
	 * @param ipaddress
	 * @param name2
	 * @param port
	 */
	public Proxy(String name, String ipaddress, int port)
	{
		super(name);
		this.IPAddress = ipaddress;
		this.port = port;
		client = RestClient.create();
	}

	/**
	 * @return the iPAddress
	 */
	public String getIPAddress()
	{
		return IPAddress;
	}

	/**
	 * @param iPAddress the iPAddress to set
	 */
	public void setIPAddress(String iPAddress)
	{
		IPAddress = iPAddress;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return the port
	 */
	public int getPort()
	{
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(int port)
	{
		this.port = port;
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
	 * Proxy uses a RestClient to perform a GET request to the user's RemoteServer.
	 * 
	 */
	public int makeChoice(int actions)
	{
		// need to revise... is url ok?
		String url = "http://" + IPAddress + ":" + port + "/makeChoice/" + actions;

		return Integer.parseInt(client.get().uri(url).retrieve().body(String.class));
	}
}
