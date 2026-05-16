package sprint3;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javafx.application.Platform;
import sprint3model.TournamentServerModel;
/**
 * moves/actions receiver endpoint from TournamentServer
 * 
 * Connects Sprint 2 and S3 Model 
 */
@SpringBootApplication
@RestController
public class ActionsViewerReciever
{

	public static TournamentServerModel model;
	
	public static void setModel(TournamentServerModel m) {
		model = m;
	}
	
	//Move message from prev sprint. Facilitates the updates for the UI display
	@PutMapping("/move")
	public void moveReciever(@RequestBody String move) {
		Platform.runLater(() -> {model.setNextMove(move);
	
		});
	}
}
