package sprint2;


import org.springframework.boot.builder.SpringApplicationBuilder;

public class ServerRunner
{

	public static void main(String[] args) {		
		new SpringApplicationBuilder(TournamentServer.class)
		.properties("server.address=10.14.1.70")
		.properties("server.port=8080")
		.run(args);
	}
	
}
