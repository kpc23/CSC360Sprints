package simulator;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.servlet.client.RestTestClient;
/**
 * TournamentServer Test 
 */
@SpringBootTest(
		webEnvironment = WebEnvironment.RANDOM_PORT, 
		classes = TournamentServer.class)
@AutoConfigureRestTestClient
class TournamentServerTest
{
	@Autowired
	private RestTestClient tClient;

	
	@Autowired
	private TournamentServer tServer;

	@LocalServerPort
	private int port;

	Tournament t1;
	
	@BeforeEach
	void setUp() throws Exception
	{
		tServer.getAvailableTournaments().clear(); //avoid crossover
		ArrayList<TourneyPlayer> scoreboard = new ArrayList<>();
		Game game = new IteratedPrisonersDilemma(5);
		Bracket bracket = new RoundRobinBracket(scoreboard);
		t1 = new Tournament(scoreboard, game, bracket);
		tServer.addTournament("tour1", t1);
	}
	
	/**
	 * Registration open test
	 */
	@Test
	void testRegisterOpen()
	{		
		tServer.beginRegistration(t1);
		
		RemoteClient client = new RemoteClient("http://localhost", port);
		
		client.registerForTournament("localhost", port, "tour1", "karla");
		//check tournament is registered and that player name is too. 
		tServer.showTournament();

		assertEquals(1, t1.scoreboard.size());
		assertTrue(tServer.getAvailableTournaments().containsKey("tour1"));
		assertEquals("karla", t1.scoreboard.get(0).player.name); ///use loop k	
		
	}
	
	/**
	 * Registration closed
	 */
	@Test
	void testRegisterEnded()
	{		
		tServer.endRegistration(t1);
		
		RemoteClient client = new RemoteClient("http://localhost", port);
		
		client.registerForTournament("localhost", port, "tour1", "karla");
		//check tournament is registered and that player name is too. 
		assertEquals(0, t1.scoreboard.size());
	}
	
	@Test
	void testBeginTournament()
	{	
		ArrayList<TourneyPlayer> scoreboard = new ArrayList<>();

		Participant p1 = new SelfishBot("selfish");
		Participant p2 = new SelflessBot("selfless");
		
		scoreboard.add(new TourneyPlayer(p1));
		scoreboard.add(new TourneyPlayer(p2));
		
		Game game = new IteratedPrisonersDilemma(1);
		Bracket bracket = new RoundRobinBracket(scoreboard);
		t1 = new Tournament(scoreboard, game, bracket);
		
		tServer.beginTournament(t1);
		assertEquals(2, t1.scoreboard.size());
		assertTrue(scoreboard.get(0).totalScore > 0);
	}
	
}
