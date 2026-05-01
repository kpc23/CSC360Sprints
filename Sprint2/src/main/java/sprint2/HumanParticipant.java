package sprint2;

import java.util.Scanner;

import simulator.Participant;

public class HumanParticipant extends Participant
{

	public HumanParticipant(String name)
	{
		super(name);
		// TODO Auto-generated constructor stub
	}

	public int makeChoice(int actions)
	{
		try (Scanner scan = new Scanner(System.in))
		{
			System.out.println("Enter action out of " + (actions - 1) + ": ");
			int chosenAction = scan.nextInt();

			while (chosenAction >= actions || chosenAction < 0)
			{
				System.out.println("Not a valid choice. Try Again: ");
				chosenAction = scan.nextInt();
			}

			return chosenAction;
		}
	}

}
