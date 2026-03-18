package simulator;

import java.net.InetAddress;
import org.springframework.web.client.RestClient;

public class Proxy extends Participant
{
	InetAddress ipaddress;
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
		this.ipaddress = ipaddress;
		this.port = port;
	}

	public int makeChoice(int actions) {
		
	}
	
	public void updateMemory(State state) {
		
	}
}
