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
			model.showServerPicker();
			
			stage.show();

	}
	
	public static void main(String[] args)
	{		
		launch(args);
	}

}
