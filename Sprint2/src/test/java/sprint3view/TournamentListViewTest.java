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

@ExtendWith(ApplicationExtension.class)
class TournamentListViewTest
{
	TournamentServerModel model;

	@Start
	private void start(Stage stage)
	{
		model = new TournamentServerModel(stage);
		model.getActiveTournamentList().add("t1");
		model.getActiveTournamentList().add("t2");

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
		Assertions.assertThat(lv).hasExactlyNumItems(2);
		Assertions.assertThat(lv).hasListCell("t1");
		Assertions.assertThat(lv).hasListCell("t2");
	}

	@Test
	public void testSpectateButton(FxRobot robot)
	{
		robot.clickOn("t2");
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

}
