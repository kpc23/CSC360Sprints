package sprint3model;

import org.springframework.web.client.RestClient;

public class UserClient
{
	public String ip;
	public int port;
	public RestClient client;

	/**
	 * @param ip
	 * @param port
	 * @param client
	 */
	public UserClient(String ip, int port, RestClient client)
	{
		super();
		this.ip = ip;
		this.port = port;
		this.client = client;
	}

	/**
	 * @return the ip
	 */
	public String getIp()
	{
		return ip;
	}

	/**
	 * @param ip the ip to set
	 */
	public void setIp(String ip)
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

	public void spectateTournament(String tourName, String ipAddress, int port)
	{
		String url = "http://" + this.ip + ":" + this.port + "/spectate/" + tourName + "/" + ipAddress + "/" + port;

		client.get().uri(url).retrieve().body(String.class);
	}

	public void unspectateTournament(String tourName, String ipAddress, int port)
	{
		String url = "http://" + this.ip + ":" + this.port + "/stopSpectate/" + tourName + "/" + ipAddress + "/" + port;

		client.get().uri(url).retrieve().body(String.class);
	}

}
