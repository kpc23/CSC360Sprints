package sprint2;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import simulator.Tournament;
import sprint3.MoveListener;

/**
 * Tournaments can be added to the availableTournaments HashMap through the
 * addTournament() method, which takes the key-value pair arguments. Meanwhile,
 * TournamentServer’s main() is intended to launch the server
 * 
 * TournamentServer allows for tournaments to be set up and run by interacting 
 * solely with the TournamentServer class rather than directly calling each Tournament.
 * 
 * The TournamentServer class acts as the central network server and manages 
 * a HashMap of Tournament objects. 
 * 
 */
@SpringBootApplication
@RestController
public class TournamentServer
{
	/**
	 * The key value is a String which acts as the Tournament’s title/label. 
	 * A Tournament can be opened or closed for registration using the beginRegistration() 
	 * and endRegistration() methods, which take an argument for the specific Tournament, accessible via HashMap.
	 */
	HashMap<String, Tournament> availableTournaments;
	HashMap<Tournament, Boolean> registrationStatus; //true --- open, false -- closed

	ArrayList<MoveListener> spectators; //sprint 3 implementation
	
	public static void main(String[] args)
	{
		new SpringApplicationBuilder(TournamentServer.class)
				// .profiles("password")
				// .profiles("random")
				.run(args);
	}
	
	/**
	 * constructor
	 */
	public TournamentServer()
	{
		availableTournaments = new HashMap<>();
		registrationStatus = new HashMap<>();
	}
	

	/**
	 * Add tournaments for either registration or availability
	 * @param str
	 * @param tournament
	 */
	public void addTournament(String str, Tournament tournament)
	{
		availableTournaments.put(str, tournament);
		registrationStatus.put(tournament, false);
	}
	
	

	/**
	 * @return the availableTournaments
	 */
	public HashMap<String, Tournament> getAvailableTournaments()
	{
		return availableTournaments;
	}

	/**
	 * @param availableTournaments the availableTournaments to set
	 */
	public void setAvailableTournaments(HashMap<String, Tournament> availableTournaments)
	{
		this.availableTournaments = availableTournaments;
	}

	/**
	 * Begins registration phase
	 * @param tournament
	 */
	public void beginRegistration(Tournament tournament)
	{
		registrationStatus.put(tournament, true);

	}

	/**
	 * Ends the registration phase
	 * @param tournament
	 */
	public void endRegistration(Tournament tournament)
	{
		registrationStatus.put(tournament, false);

	}

	/**
	 * Users can remotely register for these tournaments using 
	 * their IP address, name, and port information via the register() method.
	 * 
	 * @param ipaddress
	 * @param playerName
	 * @param port
	 * @param tournamentName
	 * @throws Exception 
	 */
	@GetMapping("/register/{ipaddress}/{port}/{tournamentName}/{playerName}")
	public void register(@PathVariable String ipaddress, 
			@PathVariable String playerName, 
			@PathVariable int port, 
			@PathVariable String tournamentName) throws Exception
	{
		Tournament t = availableTournaments.get(tournamentName);

		if (t == null)
		{
			throw new Exception("Invalid Tournament");
		}

		if (registrationStatus.get(t))
		{
			// while reg is open..
			InetAddress ipadd;
			try
			{
				ipadd = InetAddress.getByName(ipaddress);
				Proxy proxy = new Proxy(playerName, ipadd, port);
				t.registerPlayer(proxy);
				
			} catch (UnknownHostException e)
			{
				// TODO Auto-generated catch block
				throw new Exception("Invalid IP Address");
			}
			
		} else
		{
			// if registration is closed
			throw new Exception("Unavailable: Registration is closed.");
		}
	}

	/**
	 * The method showTournament() displays a list of all tournaments 
	 * currently in the registration phase
	 */
	public void showTournament()
	{
		for (String key : availableTournaments.keySet())
		{
			Tournament t = availableTournaments.get(key);

			if (registrationStatus.get(t))
			{
				System.out.println(key);
			}
		}
	}

	/**
	 * To begin a tournament, the playTournament() 
	 * method calls play() from the Tournament passed.
	 * 
	 * @param tournament
	 */
	public void beginTournament(Tournament tournament)
	{
		tournament.playTournament();
	}
	
	/**
	 * Sprint 3 Implementations
	 */
	public void spectateTournament(String name, InetAddress ip, int port) {
		
	}
	
	public void stopSpectateTournament(String name, InetAddress ip, int port) {
		
	}
}
