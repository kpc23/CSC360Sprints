package sprint3;

import javafx.application.Application;
import javafx.stage.Stage;
import sprint3model.TournamentServerModel;
//Sprint 3
public class Runner extends Application
{
	@Override
	public void start(Stage stage) throws Exception
	{
		TournamentServerModel model = new TournamentServerModel(stage);
		
		//Gives model access to receive list of moves for the selected tournament.
		ActionsViewerReciever.setModel(model);
		
		int viewPort=9000;
		model.setViewerPort(viewPort);
		
		//Computer 3 - Sprint 3 
		model.setViewerIp("10.14.1.71");
		//Receiver server is set to port 9000 (to be diff from localhost for now...)
		new org.springframework.boot.builder
		.SpringApplicationBuilder(ActionsViewerReciever.class)
		.properties("server.port=" + viewPort).run();
		
		model.showServerPicker();

		stage.show();
	}

	public static void main(String[] args)
	{
		launch(args);
	}

}
