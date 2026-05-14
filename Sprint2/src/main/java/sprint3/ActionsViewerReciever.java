package sprint3;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javafx.application.Platform;
import sprint3model.TournamentServerModel;
//moves/actions receiver endpoint 
@SpringBootApplication
@RestController
public class ActionsViewerReciever
{

	public static TournamentServerModel model;
	
	public static void setModel(TournamentServerModel m) {
		model = m;
	}
	
	@PutMapping("/move")
	public void moveReciever(@RequestBody String move) {
		Platform.runLater(() -> model.setNextMove(move));
	}
}
