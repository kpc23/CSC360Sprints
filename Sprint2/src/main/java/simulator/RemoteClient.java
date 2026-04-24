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

	/**
	 * Constructor
	 */
	public RemoteClient()
	{
		client = RestClient.create();
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
		//is this url ok?
		String url = "http://" + ipaddress + ":" + port + "/register/" + tournamentName + "/" + playerName;
		
		//GET Request
		client.get()
		.uri(url)
		.retrieve()
		.body(String.class);
	}
}