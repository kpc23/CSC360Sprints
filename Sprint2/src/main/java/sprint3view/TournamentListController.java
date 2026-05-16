package sprint3view;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import sprint3model.TournamentServerModel;
import sprint4.ActiveTournamentFilter;
import sprint4.AllTournamentFilter;
import sprint4.OpenRegistrationFilter;
import sprint4.TournamentInfo;

public class TournamentListController
{
	@FXML
	ListView<TournamentInfo> tournamentListView;

	TournamentServerModel model;

	public void setModel(TournamentServerModel tm)
	{
		this.model = tm;

		tournamentListView.setItems(model.getAllTournamentsList());
	}

	@FXML
	public void onClickView()
	{
		TournamentInfo selected = tournamentListView.getSelectionModel().getSelectedItem();

		if (selected != null)
		{
			model.viewTournament(selected.name());
		}
	}

	@FXML
	public void onClickBack()
	{
		model.showServerPicker();
	}
	

	/**
	 * Sprint 4
	 */
	@FXML
	public void onClickOpenTournamentsFilter() {
		ArrayList<TournamentInfo> filtered = model.getFilteredTournaments(new OpenRegistrationFilter());
	
		tournamentListView.setItems(FXCollections.observableArrayList(filtered));
	}
	
	@FXML
	public void onClickActiveTournamentsFilter() {
		ArrayList<TournamentInfo> filtered = model.getFilteredTournaments(new ActiveTournamentFilter());
		
		tournamentListView.setItems(FXCollections.observableArrayList(filtered));

	}
	
	@FXML
	public void onClickAllTournamentsFilter() {
		ArrayList<TournamentInfo> filtered = model.getFilteredTournaments(new AllTournamentFilter());
		
		tournamentListView.setItems(FXCollections.observableArrayList(filtered));

	}
}
