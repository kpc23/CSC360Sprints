package sprint3view;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.util.WaitForAsyncUtils;

import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sprint3model.TournamentServerModel;
import sprint4.TournamentInfo;

@ExtendWith(ApplicationExtension.class)
class TournamentListViewTest
{
	TournamentServerModel model;
//	tournaments.add(makeNewTournament("Open, Not Active Tournament 1", true, false));
//	tournaments.add(makeNewTournament("Closed, Active Tournament 2", false, true));
//	tournaments.add(makeNewTournament("Closed, Not Active Tournament 3", false, false));
//	tournaments.add(makeNewTournament("Open, Active Tournament 4", true, true));

	@Start
	private void start(Stage stage)
	{
		model = new TournamentServerModel(stage);
		model.getAllTournamentsList().add(
				new TournamentInfo("Open, Not Active Tournament 1", true, false));

		model.getAllTournamentsList().add(
				new TournamentInfo("Closed, Active Tournament 2", false, true));
		
		model.getAllTournamentsList().add(
				new TournamentInfo("Closed, Not Active Tournament 3", false, false));
		
		model.getAllTournamentsList().add(
				new TournamentInfo("Open, Active Tournament 4", true, true));

		
		try
		{
			model.showServerList();
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stage.show();
	}

	// select tournament button
	void pressSelectButton(FxRobot robot)
	{
		robot.clickOn("#spectateButton");
	}

	// back button/unview
	void pressConnectToNewServerButton(FxRobot robot)
	{
		robot.clickOn("#connectToNewServerButton");
	}

	@Test
	public void testDisplay(FxRobot robot)
	{
		ListView<?> lv = robot.lookup("#tournamentListView").queryAs(ListView.class);
		Assertions.assertThat(lv).hasExactlyNumItems(4);
	}

	@Test
	public void testSpectateButton(FxRobot robot)
	{
		robot.clickOn("Closed, Active Tournament 2");
		pressSelectButton(robot);

		WaitForAsyncUtils.waitForFxEvents();

		ListView<?> lv = robot.lookup("#ActionsListView").queryAs(ListView.class);
		Assertions.assertThat(lv).isVisible();
	}

	@Test
	public void testBackButton(FxRobot robot)
	{
		pressConnectToNewServerButton(robot);

		WaitForAsyncUtils.waitForFxEvents();

		Assertions.assertThat(robot.lookup("#IPTextField").queryAs(TextField.class)).isVisible();
		Assertions.assertThat(robot.lookup("#PortNumberTextField").queryAs(TextField.class)).isVisible();

	}
	


	
	@Test
	public void testOpenRegFilterButton(FxRobot robot)
	{
		robot.clickOn("#OpenRegistrationButton");
		WaitForAsyncUtils.waitForFxEvents();

		ListView<?> lv = robot.lookup("#tournamentListView").queryAs(ListView.class);
		Assertions.assertThat(lv).hasExactlyNumItems(2);
		
		
	}
	
	@Test
	public void testActiveTournFilterButton(FxRobot robot)
	{
		robot.clickOn("#ActiveTournamentsButton");
		WaitForAsyncUtils.waitForFxEvents();

		ListView<?> lv = robot.lookup("#tournamentListView").queryAs(ListView.class);
		Assertions.assertThat(lv).hasExactlyNumItems(2);
		
		
	}
	
	@Test
	public void testAllFilterButton(FxRobot robot)
	{
		robot.clickOn("#AllTournamentsButton");
		WaitForAsyncUtils.waitForFxEvents();

		ListView<?> lv = robot.lookup("#tournamentListView").queryAs(ListView.class);
		Assertions.assertThat(lv).hasExactlyNumItems(4);
		
		
	}
}
