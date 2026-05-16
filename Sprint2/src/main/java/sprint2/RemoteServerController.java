package sprint2;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.bind.annotation.*;

import simulator.Participant;

/**
 * RemoteServer utilizes the Strategy pattern to support remote player behavior.
 */

@SpringBootApplication
@RestController
public class RemoteServerController
{

	Participant participantType;

	public static void main(String[] args)
	{
		new SpringApplicationBuilder(RemoteServerController.class)
		.properties("server.address=0.0.0.0")// or 10.14.1.71
		.properties("server.port=8081")
				// .profiles("password")
				// .profiles("random")
				.run(args);
	}

	/**
	 * @return the participantType
	 */
	public Participant getParticipantType()
	{
		return participantType;
	}

	/**
	 * @param participantType the participantType to set
	 */
	public void setParticipantType(Participant participantType)
	{
		this.participantType = participantType;
	}

	public void setParticipant(Participant participant)
	{
		participantType = participant;
	}

	@GetMapping("/makeChoice/{actions}")
	public int makeChoice(@PathVariable int actions) throws Exception
	{
		if(participantType == null) {
			throw new Exception("Not found");
		}
		return participantType.makeChoice(actions);
	}
}
