package sprint3view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import sprint3model.TournamentServerModel;

public class ConnectToServerController
{
	@FXML
	TextField ipAddress;
	@FXML
	TextField portNumber;

	TournamentServerModel model;

	public void setModel(TournamentServerModel tm)
	{
		this.model = tm;
	}

	/**
	 * connects to model server using text field ipAddress and port info
	 */
	public void onClickConnect()
	{

		try
		{
			model.connectToServer(ipAddress.getText(), Integer.parseInt(portNumber.getText()));

		} catch (Exception e)
		{
			e.printStackTrace();
		}

	}

}