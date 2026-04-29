package simulator;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.bind.annotation.*;
/**
 * RemoteServer utilizes the Strategy pattern 
 * to support remote player behavior.
 * 
 * 
 */

@SpringBootApplication
@RestController
public class RemoteServerController
{

	Participant participantType;

	public static void main(String[] args)
	{
		new SpringApplicationBuilder(RemoteServerController.class)
				// .profiles("password")
				// .profiles("random")
				.run(args);
	}
	
	public void setParticipant(Participant participant)
	{
		participantType = participant;
	}

	@GetMapping("/makeChoice/{actions}")
	public int makeChoice(@PathVariable int actions)
	{
		//should i add a try-catch?
		//return 3;
		return participantType.makeChoice(actions);
	}

	//hmm do i need to update memory here too?
	
}
