package sprint3;

import java.net.InetAddress;

public class UserInfo
{
	InetAddress ip;
	int port;
	
	/**
	 * @param ip
	 * @param port
	 */
	public UserInfo(InetAddress ip, int port)
	{
		this.ip = ip;
		this.port = port;
	}
	/**
	 * @return the ip
	 */
	public InetAddress getIp()
	{
		return ip;
	}
	/**
	 * @return the port
	 */
	public int getPort()
	{
		return port;
	}
	
	
}
