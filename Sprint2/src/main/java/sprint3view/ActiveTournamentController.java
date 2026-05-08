package sprint3view;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import sprint3model.TournamentServerModel;

public class ActiveTournamentController
{
	@FXML
	ListView<String> ActionsListView;

	TournamentServerModel model;

	public void setModel(TournamentServerModel tm)
	{
		this.model = tm;
		ActionsListView.setItems(model.getMoveList());
	}

	public void onClickBack()
	{
		try
		{
			model.unviewTournament(null, model.getIp(), model.getPort());

		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
