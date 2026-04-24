package simulator;

import java.net.InetAddress;
import java.util.HashMap;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.bind.annotation.RestController;

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
	 */
	public void register(InetAddress ipaddress, String playerName, int port, String tournamentName)
	{
		Tournament t = availableTournaments.get(tournamentName);

		if (t == null)
		{
			System.out.println("Invalid Tournament.");
			return;
		}

		if (registrationStatus.get(t))
		{
			// while reg is open...
			Proxy proxy = new Proxy(playerName, ipaddress, port);
			t.registerPlayer(proxy);
		} else
		{
			// if registration is closed
			System.out.println("Unavailable: Registration is closed.");
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
}
