package sprint3model;

import java.net.InetAddress;

import org.springframework.web.client.RestClient;

public class UserClient
{

	InetAddress ip;
	int port;
	RestClient client;
	/**
	 * @param ip
	 * @param port
	 * @param client
	 */
	public UserClient(InetAddress ip, int port, RestClient client)
	{
		super();
		this.ip = ip;
		this.port = port;
		this.client = client;
	}
	/**
	 * @return the ip
	 */
	public InetAddress getIp()
	{
		return ip;
	}
	/**
	 * @param ip the ip to set
	 */
	public void setIp(InetAddress ip)
	{
		this.ip = ip;
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
	
	
}
