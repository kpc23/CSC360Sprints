package sprint2;

import java.util.ArrayList;

import org.springframework.boot.builder.SpringApplicationBuilder;

import simulator.SimulateGame;
import simulator.Tournament;

public class ServerRunner
{

	public static void main(String[] args) {
		
		ArrayList<Tournament> ts = SimulateGame.createTournaments();
		
		for(Tournament t : ts) {
			TournamentServer.availableTournaments.put(t.getName(), t);
			TournamentServer.registrationStatus.put(t,  t.isRegistrationOpen());
		}
		
		new SpringApplicationBuilder(TournamentServer.class).run(args);
	}
	
}
