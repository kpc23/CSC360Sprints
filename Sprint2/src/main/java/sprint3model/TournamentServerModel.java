package sprint3model;

import java.net.InetAddress;
import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.scene.Scene;
import sprint3.ViewTransitionalModelInterface;

public class TournamentServerModel implements ViewTransitionalModelInterface
{
	ObservableList<String> moveList;
	ArrayList<String> activeTournamentList = new ArrayList<>();
	UserClient client;
	Scene scene;
	
	
	/**
	 * @param moveList
	 * @param activeTournamentList
	 * @param client
	 * @param scene
	 */
	public TournamentServerModel(ObservableList<String> moveList, ArrayList<String> activeTournamentList,
			UserClient client, Scene scene)
	{
		this.moveList = moveList;
		this.activeTournamentList = activeTournamentList;
		this.client = client;
		this.scene = scene;
	}

	public void viewTournament(String tournamentName, InetAddress ip, int port) {
		activeTournamentList.contains(tournamentName);
	}
	
	public void unviewTournament(String tournamentName, InetAddress ip, int port) {
		moveList.clear();
		showActiveTournament();
	}
	
	public void showTournament() {
		
	}
	
	public void connectToServer(String ip, int port) {
		
		
	}
	
	public void setNextMove(String move) {
		moveList.add(move);
	}
	
	public void resetList() {
		moveList.clear();
		activeTournamentList.clear();
		
	}

	@Override
	public void showServerPicker()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showServerList()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showActiveTournament()
	{
		// TODO Auto-generated method stub
		
	}
	
	
}
