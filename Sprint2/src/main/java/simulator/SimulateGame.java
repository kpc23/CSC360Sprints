/**
 * Strategic Interaction Tournament Simulator (SITS)
 */
package simulator;

import java.util.ArrayList;

import org.springframework.web.client.RestClient;

public class SimulateGame
{	
	public static Tournament makeNewTournament(String name,
			boolean registrationOpen, boolean running) {
		
		Participant p1 = new SelfishBot("Selfish");
		Participant p2 = new SelflessBot("Selfless");
		Participant p3 = new AlternatingBot("Alternating");

		ArrayList<TourneyPlayer> players = new ArrayList<>();
		players.add(new TourneyPlayer(p1));
		players.add(new TourneyPlayer(p2));
		players.add(new TourneyPlayer(p3));

		Game game = new IteratedPrisonersDilemma(5);
		game.setTimeDelay(1);
		game.register(new ActionFileLogger());
		game.register(new ResultsFileLogger());

		Bracket bracket = new RoundRobinBracket(players);
		
		Tournament tournament = new Tournament(players, game, bracket);
		tournament.setName(name);
		tournament.setRegistrationOpen(registrationOpen);
		tournament.setRunning(running);
		
		return tournament;
	}
	
	public static ArrayList<Tournament> createTournaments(){
		ArrayList<Tournament> tournaments = new ArrayList<>();
		
		tournaments.add(makeNewTournament("OpenNotActiveTournament1", true, false));
		tournaments.add(makeNewTournament("ClosedActiveTournament2", false, true));
		tournaments.add(makeNewTournament("ClosedNotActiveTournament3", false, false));
		tournaments.add(makeNewTournament("OpenActiveTournament4", true, true));
		return tournaments;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		
		String serverIp = "10.14.1.70";
		int serverPort = 8080;
		
		ArrayList<Tournament> tournaments = createTournaments();
		RestClient tclient = RestClient.create("http://" + serverIp + ":" + serverPort);

		for(Tournament t: tournaments) {
			
			tclient.post()
			.uri("/addTournament/" + t.getName())
			.retrieve()
			.toBodilessEntity();
			
			try {
				Thread.sleep(10000);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			if(t.isRunning()) {
				tclient.get()
				.uri("/beginTournament/" + t.getName())
				.retrieve()
				.toBodilessEntity();
			}
		}
		
		
	}
	//end main
}
