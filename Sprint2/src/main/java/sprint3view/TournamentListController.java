package sprint3view;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import sprint3model.TournamentServerModel;

public class TournamentListController
{
	@FXML
	ListView<String> tournamentListView;

	TournamentServerModel model;

	public void setModel(TournamentServerModel tm)
	{
		this.model = tm;

		tournamentListView.setItems(model.getActiveTournamentList());
	}

	public void onClickView()
	{
		String selected = tournamentListView.getSelectionModel().getSelectedItem();

		if (selected != null)
		{
			model.viewTournament(selected, model.getIp(), model.getPort());
		}
	}

	public void onClickBack()
	{
		model.showServerPicker();
	}
}
