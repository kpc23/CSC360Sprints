package sprint3;

public class UserInfo
{
	public String ip;
	public int port;

	/**
	 * @param ipaddress
	 * @param port
	 */
	public UserInfo(String ipaddress, int port)
	{
		this.ip = ipaddress;
		this.port = port;
	}

	/**
	 * @return the ip
	 */
	public String getIp()
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
