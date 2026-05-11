package sprint3;

import javafx.application.Application;
import javafx.stage.Stage;
import sprint3model.TournamentServerModel;

public class Runner extends Application
{
	@Override
	public void start(Stage stage) throws Exception
	{
		TournamentServerModel model = new TournamentServerModel(stage);
		ActionsViewerReciever.setModel(model);
		
		new org.springframework.boot.builder
		.SpringApplicationBuilder(ActionsViewerReciever.class)
		.properties("server.port=9000").run();
		
		model.showServerPicker();

		stage.show();
	}

	public static void main(String[] args)
	{
		launch(args);
	}

}
