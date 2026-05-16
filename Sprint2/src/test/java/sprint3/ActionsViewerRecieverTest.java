package sprint3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.client.RestTestClient;

@SpringBootTest(
		webEnvironment=WebEnvironment.RANDOM_PORT,
		classes = ActionsViewerReciever.class) 
@AutoConfigureRestTestClient
class ActionsViewerRecieverTest
{
	
	@Autowired
	private RestTestClient tClient;
	

	@Test
	void testMovesNetwork()
	{
		tClient.put().uri("/move").body("Round 1: P1 -> 1 VS P2 -> 0");
	}

}
