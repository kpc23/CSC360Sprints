package sprint3;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.client.RestTestClient;

import sprint3model.TournamentServerModel;

@SpringBootTest(
		webEnvironment=WebEnvironment.RANDOM_PORT,
		classes = ActionsViewerReciever.class) 
@AutoConfigureRestTestClient
class ActionsViewerRecieverTest
{
	
	@Autowired
	private RestTestClient tClient;
	

//	private TournamentServerModel model;
	
//	@BeforeEach
//	void setUp() {
//		
//	}
	@Test
	void testNetworkForMoves()
	{
		ActionsViewerReciever.model = new TournamentServerModel(null);
		tClient.put().uri("/move").body("Round 1: P1 -> 1 VS P2 -> 0").exchange().expectStatus().is2xxSuccessful();
	
	}

}
