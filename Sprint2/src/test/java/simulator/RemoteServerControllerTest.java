package simulator;

import static org.assertj.core.api.Assertions.assertThat;
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
 * RemoteServerController Test 
 */
@SpringBootTest(
		webEnvironment = WebEnvironment.RANDOM_PORT, 
		classes = RemoteServerController.class)
@AutoConfigureRestTestClient

class RemoteServerControllerTest
{
	@Autowired
	private RestTestClient tClient;

	@Autowired
	private RemoteServerController remConServer;
	
//	@Autowired
//	private TournamentServer tServer;

	@LocalServerPort
	private int port;

	
	
	@BeforeEach
	void setUp() throws Exception
	{
		// selfless - 0, selfish - 1
		remConServer.setParticipant(new SelfishBot("selfish"));
	}

	//test set participant
	
	@Test
	void testSelfish()
	{
		String url = "/makeChoice/2";

		tClient.get()
		.uri(url)
		.exchange()
		.expectBody(String.class)
		.isEqualTo("1");
	}
	
	@Test
	void testSeelfless()
	{
		remConServer.setParticipant(new SelflessBot("selfless"));

		String url = "/makeChoice/2";

		tClient.get()
		.uri(url)
		.exchange()
		.expectBody(String.class)
		.isEqualTo("0");
	}
	
//	@Test
//	void testRegister()
//	{
//		ArrayList<TourneyPlayer> scoreboard = new ArrayList<>();
//		Game game = new IteratedPrisonersDilemma(5);
//		Bracket bracket = new RoundRobinBracket(scoreboard);
//		Tournament t1 = new Tournament(scoreboard, game, bracket);
//		
//		tServer.addTournament("tour1", t1);
//		tServer.beginRegistration(t1);
//		
//		RemoteClient client = new RemoteClient("http://localhost", port);
//		
//		client.registerForTournament("localhost", port, "tour1", "karla");
//		//check tournament is registered and that player name is too. 
//		assertEquals(1, t1.scoreboard.size());
//		assertTrue(tServer.getAvailableTournaments().containsKey("tour1"));
//		assertEquals("karla", t1.scoreboard.get(0).player.name); ///use loop k
//		
//		
//		
//	}

}
