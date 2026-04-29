package simulator;

import org.springframework.web.client.RestClient;
/**
 * RemoteClient uses a RestClient to send GET requests 
 * to the origin TournamentServer, 
 * such as for registration or displaying tournament information.
 */
public class RemoteClient
{
	RestClient client;
	String serverAddress;
	int serverPort;

	/**
	 * Constructor
	 */
	public RemoteClient(String serverAddress, int sPort)
	{
		client = RestClient.create();
		//always the same server
		//so create here the connection between the client always connecting to the same server.
		
		
		this.serverAddress = serverAddress;
		this.serverPort = sPort;
	}

	/**
	 * RemoteClient uses a RestClient to send GET requests 
	 * to the origin TournamentServer.
	 * 
	 * @param ipaddress - Proxy IPAddress?
	 * @param port - Proxy port number?
	 * @param tournamentName
	 * @param playerName
	 */
	public void registerForTournament(String ipaddress, int port, 
			String tournamentName, String playerName)
	{
		///  register/{ipaddress}/{port}/{tournamentName}/{playerName}
		//    {ipaddress}:{port}/register/{tournamentName}/{playerName}
		String url = serverAddress + ":" + serverPort + "/register/" 
				+ ipaddress + "/" + port + "/" + tournamentName 
				+ "/" + playerName;
		
		System.out.println(url);
		//GET Request
		client.get()
		.uri(url)
		.retrieve()
		.body(String.class);
	}
	
	
	
}