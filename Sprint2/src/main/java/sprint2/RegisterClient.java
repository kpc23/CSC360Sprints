package sprint2;

public class RegisterClient
{

	public static void main(String[] args)
	{
		RemoteClient client = new RemoteClient("10.14.1.80", 8000);
		
		client.registerForTournament("10.14.1.70", 8081, "NewRegisteredTournament", "RemoteNewBotClient");

		System.out.println("You are registered!");
	}

}
