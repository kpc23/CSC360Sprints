package simulator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.servlet.client.RestTestClient;

import sprint2.Proxy;
import sprint2.RemoteServerController;

/**
 * RemoteServerController Test
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = RemoteServerController.class)
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

	// test set participant

	@Test
	void testSelfish()
	{
		String url = "/makeChoice/2";

		tClient.get().uri(url).exchange().expectBody(String.class).isEqualTo("1");
	}

	@Test
	void testSeelfless()
	{
		remConServer.setParticipant(new SelflessBot("selfless"));

		String url = "/makeChoice/2";

		tClient.get().uri(url).exchange().expectBody(String.class).isEqualTo("0");
	}

	// test proxy
	@Test
	void testProxyChoiceSelfish()
	{
		remConServer.setParticipant(new SelfishBot("selfish"));
		Proxy proxy = new Proxy("proxyParticipant", "localhost", port);

		int result = proxy.makeChoice(2);
		assertEquals(1, result);

	}

	@Test
	void testProxyChoiceSelfless()
	{
		remConServer.setParticipant(new SelflessBot("selfless"));
		Proxy proxy = new Proxy("proxyParticipant", "localhost", port);

		int result = proxy.makeChoice(2);
		assertEquals(0, result);
	}

}
