package sprint3;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sprint3model.TournamentServerModel;
import sprint3view.ConnectToServerController;

public class Runner extends Application
{
	@Override
	public void start(Stage stage) throws Exception
	{
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(ConnectToServerController.class.getResource("../sprint3view/ConnectToServerView.fxml"));

			Parent view;
			view = loader.load();

			Scene s = new Scene(view);

			TournamentServerModel model = new TournamentServerModel(stage);
			ConnectToServerController cont = loader.getController();
			cont.setModel(model);

			stage.setScene(s);
			stage.show();

		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		launch(args);
	}
}
