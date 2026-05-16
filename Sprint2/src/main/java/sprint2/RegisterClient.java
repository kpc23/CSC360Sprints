package sprint2;

public class RegisterClient
{

	public static void main(String[] args)
	{
		Proxy p = new Proxy("RemoteNewBotClient", "10.14.1.80", 8000);
		RemoteClient client = new RemoteClient(p.getIPAddress(), p.getPort());
		
		client.registerForTournament("10.14.1.70", 8080, "OpenActiveTournament4", p.getName());

		System.out.println("You are registered!");
	}

}
