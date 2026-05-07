package simulator;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class SimulateGameTest
{

	/**
	 * Test 100% coverage Tournament Participant Game TourneyPlayer State Selfish,
	 * Selfless, Alternating bots Bracket Tuple IteratedPrisonersDilemma Subject
	 * 47.2% (100% in next test)
	 */
	@Test
	void TournamentTest()
	{
		ArrayList<TourneyPlayer> players = new ArrayList<>();
		Game game = new IteratedPrisonersDilemma(5);
		Bracket bracket = new RoundRobinBracket(players);
		Tournament tournament = new Tournament(players, game, bracket);

		Participant p1 = new SelfishBot("selfish");
		Participant p2 = new SelflessBot("selfless");
		Participant p3 = new AlternatingBot("alternating");

		tournament.registerPlayer(p1);
		tournament.registerPlayer(p2);
		tournament.registerPlayer(p3);

		assertEquals(3, players.size());
		assertEquals("selfish", players.get(0).player.name);
		assertEquals("selfless", players.get(1).player.name);
		assertEquals("alternating", players.get(2).player.name);
		assertEquals("selfish", tournament.scoreboard.get(0).player.name);
		assertEquals("selfless", tournament.scoreboard.get(1).player.name);
		assertEquals("alternating", tournament.scoreboard.get(2).player.name);

		tournament.playTournament();

		assertEquals(38, players.get(0).totalScore); // selfish 25+13=38
		assertEquals(9, players.get(1).totalScore); // selfless 0+19=19
		assertEquals(22, players.get(2).totalScore); // alternating 3+19=22
	}

	/**
	 * Test 100% coverage for Logging Observer, Subject, ActionFileLogger,
	 * ResultFileLogger
	 */
	@Test
	void loggingTest()
	{
		Game game = new IteratedPrisonersDilemma(2);
		Participant p1 = new SelfishBot("selfish");
		Participant p2 = new SelflessBot("selfless");

		ActionFileLogger actionLogger = new ActionFileLogger();
		ResultsFileLogger resultsLogger = new ResultsFileLogger();

		game.register(actionLogger);
		game.register(resultsLogger);
		game.play(p1, p2);
		assertEquals(2, game.listeners.size());

		assertEquals("Round " + (game.currentState.round + 1) + " PLAYER 1 ACTION = " + game.currentState.p1Action
				+ " || PLAYER 2 ACTION = " + game.currentState.p2Action, actionLogger.getMessage());
		assertEquals("Game Score Result: " + game.currentState.p1Score + " vs " + game.currentState.p2Score,
				resultsLogger.getMessage());

		game.deregister(actionLogger);
		game.deregister(resultsLogger);
		assertEquals(0, game.listeners.size());
	}

	/**
	 * test to make sure simulator does not crash. Test 100% coverage
	 */
	@Test
	void simulateGameMainTest()
	{

		SimulateGame sg = new SimulateGame();
		String[] args = {};
		assertDoesNotThrow(() ->
		{
			sg.main(args);
		});
	}

	/**
	 * test exceptions
	 */
	@Test
	void exceptionTest()
	{
		Game game = new IteratedPrisonersDilemma(2);
		Participant p1 = new SelfishBot("selfish");

		assertThrows(IllegalArgumentException.class, () -> game.play(p1, null));

		ArrayList<TourneyPlayer> players = new ArrayList<>();
		Bracket bracket = new RoundRobinBracket(players);
		Tournament tournament = new Tournament(players, game, bracket);

		assertThrows(IllegalArgumentException.class, () -> tournament.registerPlayer(null));
	}

}
