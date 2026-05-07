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
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Participant p1 = new SelfishBot("Selfish");
		Participant p2 = new SelflessBot("Selfless");
		Participant p3 = new AlternatingBot("Alternating");

		ArrayList<TourneyPlayer> players = new ArrayList<>();
		players.add(new TourneyPlayer(p1));
		players.add(new TourneyPlayer(p2));
		players.add(new TourneyPlayer(p3));

		Game game = new IteratedPrisonersDilemma(5);

		game.register(new ActionFileLogger());
		game.register(new ResultsFileLogger());

		Bracket bracket = new RoundRobinBracket(players);
		Tournament tournament = new Tournament(players, game, bracket);
		tournament.playTournament();
	}

}
