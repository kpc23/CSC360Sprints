package sprint2;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import simulator.Tournament;
import sprint3.MoveListener;
import sprint3.UserInfo;
import sprint4.TournamentInfo;

/**
 * Tournaments can be added to the availableTournaments HashMap through the
 * addTournament() method, which takes the key-value pair arguments. Meanwhile,
 * TournamentServer’s main() is intended to launch the server
 * 
 * TournamentServer allows for tournaments to be set up and run by interacting
 * solely with the TournamentServer class rather than directly calling each
 * Tournament.
 * 
 * The TournamentServer class acts as the central network server and manages a
 * HashMap of Tournament objects.
 * 
 */
@SpringBootApplication
@RestController
public class TournamentServer
{
	/**
	 * The key value is a String which acts as the Tournament’s title/label. A
	 * Tournament can be opened or closed for registration using the
	 * beginRegistration() and endRegistration() methods, which take an argument for
	 * the specific Tournament, accessible via HashMap.
	 */
	public static HashMap<String, Tournament> availableTournaments = new HashMap<>();
	public static HashMap<Tournament, Boolean> registrationStatus = new HashMap<>(); // true --- open, false -- closed
	public static ArrayList<MoveListener> spectators = new ArrayList<>(); // sprint 3 implementation

	public static void main(String[] args)
	{
		new SpringApplicationBuilder(TournamentServer.class)
				// .profiles("password")
				// .profiles("random")
				.run(args);
	}

	/**
	 * @return the registrationStatus
	 */
	public static HashMap<Tournament, Boolean> getRegistrationStatus()
	{
		return registrationStatus;
	}

	/**
	 * @param registrationStatus the registrationStatus to set
	 */
	public void setRegistrationStatus(HashMap<Tournament, Boolean> registrationStatus)
	{
		this.registrationStatus = registrationStatus;
	}

	/**
	 * @return the spectators
	 */
	public ArrayList<MoveListener> getSpectators()
	{
		return spectators;
	}

	/**
	 * @param spectators the spectators to set
	 */
	public void setSpectators(ArrayList<MoveListener> spectators)
	{
		this.spectators = spectators;
	}

	/**
	 * Add tournaments for either registration or availability
	 * 
	 * @param tournamentName
	 * @param tournament
	 */
	public void addTournament(String tournamentName, Tournament tournament)
	{
		tournament.setName(tournamentName);
		availableTournaments.put(tournamentName, tournament);
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
	 * 
	 * @param tournament
	 */
	public void beginRegistration(Tournament tournament)
	{
		registrationStatus.put(tournament, true);
		tournament.setRegistrationOpen(true);

	}

	/**
	 * Ends the registration phase
	 * 
	 * @param tournament
	 */
	public void endRegistration(Tournament tournament)
	{
		registrationStatus.put(tournament, false);
		tournament.setRegistrationOpen(false);

	}

	@GetMapping("/tournaments")
	public ArrayList<TournamentInfo> getTournaments(){
		ArrayList<TournamentInfo> tournaments = new ArrayList<>();
		
		for(Tournament t: availableTournaments.values()) {
			tournaments.add(new TournamentInfo(
					t.getName(),
					t.isRegistrationOpen(),
					t.isRunning()
					));
		}
		
		return tournaments;
	}
	
	/**
	 * Users can remotely register for these tournaments using their IP address,
	 * name, and port information via the register() method.
	 * 
	 * @param ipaddress
	 * @param playerName
	 * @param port
	 * @param tournamentName
	 * @throws Exception
	 */
	@GetMapping("/register/{ipaddress}/{port}/{tournamentName}/{playerName}")
	public void register(@PathVariable String ipaddress, @PathVariable String playerName, @PathVariable int port,
			@PathVariable String tournamentName) throws Exception
	{
		Tournament t = availableTournaments.get(tournamentName);

		if (t == null)
		{
			throw new Exception("Invalid Tournament");
		}

		if (registrationStatus.get(t))
		{
			Proxy proxy = new Proxy(playerName, ipaddress, port);
			t.registerPlayer(proxy);

		} else
		{
			// if registration is closed
			throw new Exception("Unavailable: Registration is closed.");
		}
	}

	/**
	 * The method showTournament() displays a list of all tournaments currently in
	 * the registration phase
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

	@GetMapping("/beginTournament/{tournamentName}")
	public void beginClientTournament(@PathVariable String tournamentName) throws Exception
	{
		Tournament t = availableTournaments.get(tournamentName);
		if(t == null) {
			throw new Exception("invalid");
		}
		beginTournament(t);
	}
	
	/**
	 * To begin a tournament, the playTournament() method calls play() from the
	 * Tournament passed.
	 * 
	 * @param tournament
	 */
	public void beginTournament(Tournament tournament)
	{
		tournament.setRunning(true);
		tournament.playTournament();
		tournament.setRunning(false);
	}

	/**
	 * Sprint 3 Implementations
	 */
	@GetMapping("/spectate/{tournamentName}/{ipaddress}/{port}")
	public void spectateTournament(@PathVariable String tournamentName, @PathVariable String ipaddress,
			@PathVariable int port) throws Exception
	{
		Tournament t = availableTournaments.get(tournamentName);

		if (t == null)
		{
			throw new Exception("Invalid Tournament");
		}

		// ip
		MoveListener listener = new MoveListener(new UserInfo(ipaddress, port));
		t.game.register(listener);
		spectators.add(listener);
	}

	@GetMapping("/stopSpectate/{tournamentName}/{ipaddress}/{port}")
	public void stopSpectateTournament(@PathVariable String tournamentName, @PathVariable String ipaddress,
			@PathVariable int port) throws Exception
	{

		Tournament t = availableTournaments.get(tournamentName);

		if (t == null)
		{
			throw new Exception("Invalid Tournament");
		}

		MoveListener rmListener = null;

		for (MoveListener ls : spectators)
		{
			if (ls.getUserInfo().getIp().equals(ipaddress) && ls.getUserInfo().getPort() == port)
			{
				rmListener = ls;
				break;
			}

		}

		if (rmListener != null)
		{
			t.game.deregister(rmListener);
			spectators.remove(rmListener);
		}
	}
}
