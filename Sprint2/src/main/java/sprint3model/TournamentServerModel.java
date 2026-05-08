package sprint3model;

import java.io.IOException;

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

public class TournamentServerModel implements ViewTransitionalModelInterface
{
	ObservableList<String> moveList;
	ObservableList<String> activeTournamentList;
	UserClient client;
	Stage stage;
	int port;
	String ipAddress;

	public TournamentServerModel(Stage stage)
	{
		this.stage = stage;
		this.moveList = FXCollections.observableArrayList();
		this.activeTournamentList = FXCollections.observableArrayList();
	}

	/**
	 * @return the client
	 */
	public UserClient getClient()
	{
		return client;
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
	public ObservableList<String> getMoveList()
	{
		return moveList;
	}

	/**
	 * @return the activeTournamentList
	 */
	public ObservableList<String> getActiveTournamentList()
	{
		return activeTournamentList;
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
	public void setMoveList(ObservableList<String> moveList)
	{
		this.moveList = moveList;
	}

	/**
	 * @param activeTournamentList the activeTournamentList to set
	 */
	public void setActiveTournamentList(ObservableList<String> activeTournamentList)
	{
		this.activeTournamentList = activeTournamentList;
	}

	public void viewTournament(String tournamentName, String ip, int port)
	{
		this.ipAddress = ip;
		this.port = port;

		if(client!=null) {
			client.spectateTournament(tournamentName, ip, port);

		}
		showActiveTournament();
	}

	public void unviewTournament(String tournamentName, String ip, int port) throws Exception
	{
		moveList.clear();
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
		moveList.add(move);
	}

	public void resetList()
	{
		moveList.clear();
		activeTournamentList.clear();

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
}
