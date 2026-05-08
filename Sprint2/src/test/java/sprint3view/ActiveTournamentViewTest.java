package sprint3view;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.util.WaitForAsyncUtils;

import javafx.application.Platform;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import sprint3model.TournamentServerModel;

@ExtendWith(ApplicationExtension.class)
class ActiveTournamentViewTest
{
	
	TournamentServerModel model;
	
	@Start 
	private void start(Stage stage)
	{
		model = new TournamentServerModel(stage);
		model.showActiveTournament();

		stage.show();
	}
	
	//back button 
	void pressBackToTournamentListButton(FxRobot robot)
	{
		robot.sleep(1000);

		robot.clickOn("#BackToTournamentListButton");
	}
	
	
	@Test
	public void testDisplay(FxRobot robot)
	{		
		robot.sleep(1000);

		ListView<?> lv = robot.lookup("#ActionsListView").queryAs(ListView.class);
		Assertions.assertThat(lv).hasExactlyNumItems(0);
		
		Platform.runLater(()->{

			model.getMoveList().add("Round 1: Player 1 -> 1");	
		
		});
		
		WaitForAsyncUtils.waitForFxEvents();
		
		Assertions.assertThat(lv).hasExactlyNumItems(1);
		
		robot.sleep(1000);

		Platform.runLater(()->{

			model.getMoveList().add("Round 1: Player 2 -> 0");	
		
		});

		WaitForAsyncUtils.waitForFxEvents();

		robot.sleep(1000);
		Assertions.assertThat(lv).hasExactlyNumItems(2);
		Assertions.assertThat(lv).hasListCell("Round 1: Player 1 -> 1");
		Assertions.assertThat(lv).hasListCell("Round 1: Player 2 -> 0");
	}
	
	
	@Test
	public void testBackButton(FxRobot robot)
	{
		robot.sleep(1000);

		pressBackToTournamentListButton(robot);
		
		WaitForAsyncUtils.waitForFxEvents();

		ListView<?> lv = robot.lookup("#tournamentListView").queryAs(ListView.class);
		Assertions.assertThat(lv).isVisible();

	}
	
}
