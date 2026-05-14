/**
 * Strategic Interaction Tournament Simulator (SITS)
 */
package simulator;

import java.util.ArrayList;
/**
 * 
 */
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
		
		ArrayList<Tournament> tournaments = createTournaments();

		Tournament tournament = tournaments.get(0);

		tournament.playTournament();		

	}

}
