package sprint3view;

import org.testfx.assertions.api.Assertions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.util.WaitForAsyncUtils;

import sprint3model.TournamentServerModel;

import javafx.application.Platform;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * ConnectToServer Test controller and view
 */
@ExtendWith(ApplicationExtension.class)
public class ConnectToServerViewTest
{

	TournamentServerModel model;

	@Start // Before
	private void start(Stage stage)
	{
		model = new TournamentServerModel(stage);
		model.showServerPicker();
		stage.show();
	}

	private void clearTextField(FxRobot robot, String selector)
	{
		TextField tf = robot.lookup(selector).queryAs(TextField.class);

		Platform.runLater(() ->
		{
			tf.clear();
		});

		WaitForAsyncUtils.waitForFxEvents();

	}

	private void enterIpAddress(FxRobot robot, String text)
	{
		clearTextField(robot, "#IPTextField");
		robot.clickOn("#IPTextField");
		robot.write(text);
	}

	private void enterPortNumber(FxRobot robot, String text)
	{
		clearTextField(robot, "#PortNumberTextField");
		robot.clickOn("#PortNumberTextField");
		robot.write(text);
	}

	private void pressConnectToServerButton(FxRobot robot)
	{
		robot.clickOn("#ConnectToServerButton");
	}
	
	@Test
	public void testDisplayAndTextFields(FxRobot robot)
	{
		
		enterIpAddress(robot, "localhost");
		enterPortNumber(robot, String.valueOf("9000"));
		
		Assertions.assertThat(robot.lookup("#IPTextField").queryAs(TextField.class)).hasText("localhost");

		Assertions.assertThat(robot.lookup("#PortNumberTextField").queryAs(TextField.class)).hasText("9000");

		pressConnectToServerButton(robot);

		WaitForAsyncUtils.waitForFxEvents();

		
		ListView<?> lv = robot.lookup("#tournamentListView").queryAs(ListView.class);
		Assertions.assertThat(lv).isVisible();
	}

	
	@Test
	public void testBadPortDisplayAndTextFields(FxRobot robot)
	{
		
		enterIpAddress(robot, "localhost");
		enterPortNumber(robot, String.valueOf("invalid"));
		
		pressConnectToServerButton(robot);

		WaitForAsyncUtils.waitForFxEvents();
		
		Assertions.assertThat(robot.lookup("#IPTextField").queryAs(TextField.class)).hasText("localhost");

		Assertions.assertThat(robot.lookup("#PortNumberTextField").queryAs(TextField.class)).hasText("invalid");

		//should not switch views with invlid port
	}
	
	
//	@Test
//	public void checkIfListViewHasElements(FxRobot robot) throws Exception
//	{
//		enterIpAddress(robot, "localhost");
//		enterPortNumber(robot, String.valueOf(port));
//		pressConnectToServerButton(robot);
//		
//		ListView<?> lv = robot.lookup("#tournamentListView").queryAs(ListView.class);
//		
//
//		Assertions.assertThat(lv).hasListCell("tour1");
//		
//	}

}
