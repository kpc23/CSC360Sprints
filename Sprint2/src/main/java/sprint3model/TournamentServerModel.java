package sprint3model;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.web.client.RestClient;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sprint3.ViewTransitionalModelInterface;
import sprint3view.ActiveTournamentController;
import sprint3view.ConnectToServerController;
import sprint3view.TournamentListController;
import sprint4.TournamentFilterStrategy;
import sprint4.TournamentInfo;

public class TournamentServerModel implements ViewTransitionalModelInterface
{
	ObservableList<String> actionsMoveList;
	ObservableList<TournamentInfo> allTournamentsList;
	UserClient client;
	Stage stage;
	int port;
	String ipAddress;
	String currentTournamentSpectating;

	public TournamentServerModel(Stage stage)
	{
		this.stage = stage;
		this.actionsMoveList = FXCollections.observableArrayList();
		this.allTournamentsList = FXCollections.observableArrayList();
	}

	/**
	 * @return the client
	 */
	public UserClient getClient()
	{
		return client;
	}

	/**
	 * @return the currentTournamentSpectating Name
	 */
	public String getCurrentTournamentSpectating()
	{
		return currentTournamentSpectating;
	}

	/**
	 * @param currentTournamentSpectating the currentTournamentSpectating to set
	 */
	public void setCurrentTournamentSpectating(String currentTournamentSpectating)
	{
		this.currentTournamentSpectating = currentTournamentSpectating;
	}

	/**
	 * @param client the client to set
	 */
	public void setClient(UserClient client)
	{
		this.client = client;
	}

	/**
	 * @return the port
	 */
	public int getPort()
	{
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(int port)
	{
		this.port = port;
	}

	/**
	 * @return the ip
	 */
	public String getIp()
	{
		return ipAddress;
	}

	/**
	 * @param ip the ip to set
	 */
	public void setIp(String ip)
	{
		this.ipAddress = ip;
	}

	/**
	 * @return the moveList
	 */
	public ObservableList<String> getActionsMoveList()
	{
		return actionsMoveList;
	}

	/**
	 * @return the activeTournamentList
	 */
	public ObservableList<TournamentInfo> getAllTournamentsList()
	{
		return allTournamentsList;
	}

	/**
	 * @return the stage
	 */
	public Stage getStage()
	{
		return stage;
	}

	/**
	 * @param stage the stage to set
	 */
	public void setStage(Stage stage)
	{
		this.stage = stage;
	}

	/**
	 * @return the ipAddress
	 */
	public String getIpAddress()
	{
		return ipAddress;
	}

	/**
	 * @param ipAddress the ipAddress to set
	 */
	public void setIpAddress(String ipAddress)
	{
		this.ipAddress = ipAddress;
	}

	/**
	 * @param moveList the moveList to set
	 */
	public void setMoveList(ObservableList<String> actionsMoveList)
	{
		this.actionsMoveList = actionsMoveList;
	}

	/**
	 * @param activeTournamentList the activeTournamentList to set
	 */
	public void setActiveTournamentList(ObservableList<TournamentInfo> allTournamentsList)
	{
		this.allTournamentsList = allTournamentsList;
	}

	public void viewTournament(String tournamentName, String ip, int port)
	{
		this.ipAddress = ip;
		this.port = port;
		actionsMoveList.clear();

		if (client != null)
		{
			client.spectateTournament(tournamentName, "localhost", 9000);

		}

		this.setCurrentTournamentSpectating(tournamentName);
		showActiveTournament();

		if (client != null)
		{
			new Thread(() ->
			{
				client.beginTournament(tournamentName);
			}).start();

		}

	}

	public void unviewTournament(String tournamentName, String ip, int port) throws Exception
	{
		if (client != null && currentTournamentSpectating != null)
		{
			client.unspectateTournament(currentTournamentSpectating, "localhost", 9000);

		}

		actionsMoveList.clear();
		currentTournamentSpectating = null;
		showServerList();
	}

//	public void showTournament()
//	{
//		//could be useful later...?
//	}

	public void connectToServer(String ipAddress, int port) throws Exception
	{

		this.ipAddress = ipAddress;
		this.port = port;
		RestClient restclient = RestClient.create();
		this.client = new UserClient(ipAddress, port, restclient);
		tournamentsFromServer();
		try
		{
			showServerList();

		} catch (Exception e)
		{
			throw new Exception("Invalid");
		}

	}

	public void setNextMove(String move)
	{
		actionsMoveList.add(move);
	}

	public void resetList()
	{
		actionsMoveList.clear();
		allTournamentsList.clear();

	}

	public void tournamentsFromServer()
	{
		TournamentInfo[] tournaments = client.getTournaments();

		allTournamentsList.clear();

		for (TournamentInfo t : tournaments)
		{
			allTournamentsList.add(t);
		}
	}

	@Override
	public void showServerPicker()
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ConnectToServerController.class.getResource("../sprint3view/ConnectToServerView.fxml"));

		Parent view;

		try
		{
			view = loader.load();

			ConnectToServerController cont = loader.getController();
			cont.setModel(this);

			Scene s = new Scene(view);
			stage.setScene(s);

		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * switch the main view back to the list of available tournaments.
	 */
	@Override
	public void showServerList() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(TournamentListController.class.getResource("../sprint3view/TournamentListView.fxml"));

		Parent view;

		try
		{
			view = loader.load();

			TournamentListController cont = loader.getController();
			cont.setModel(this);

			Scene s = new Scene(view);

			stage.setScene(s);

		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void showActiveTournament()
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ActiveTournamentController.class.getResource("../sprint3view/ActiveTournamentView.fxml"));

		Parent view;

		try
		{
			view = loader.load();
			ActiveTournamentController cont = loader.getController();
			cont.setModel(this);
			Scene s = new Scene(view);
			stage.setScene(s);

		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Sprint 4 Filter System Implementation.
	 * 
	 */
	public ArrayList<TournamentInfo> getFilteredTournaments(TournamentFilterStrategy filterType)
	{
		ArrayList<TournamentInfo> filteredTournamentList = new ArrayList<>();

		for (TournamentInfo t : allTournamentsList)
		{

			if (filterType.filterResult(t))
			{
				filteredTournamentList.add(t);
			}
		}

		return filteredTournamentList;

	}
}
