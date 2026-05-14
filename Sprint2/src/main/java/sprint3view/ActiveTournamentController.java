package sprint3view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import sprint3model.TournamentServerModel;

public class ActiveTournamentController
{
	@FXML
	ListView<String> ActionsListView;
	@FXML
	Label TournamentNameLabel;
	
	TournamentServerModel model;
	

	public void setModel(TournamentServerModel tm)
	{
		this.model = tm;
		TournamentNameLabel.setText(model.getCurrentTournamentSpectating());
		ActionsListView.setItems(model.getActionsMoveList());
	}

	public void onClickBack()
	{
		try
		{
			model.unviewTournament(model.getCurrentTournamentSpectating(), model.getIp(), model.getPort());

		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
