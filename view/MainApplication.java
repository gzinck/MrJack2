package view;

import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.token.TokenConstants;
import controller.GameController;
import javafx.application.*;

/**
 * This is the class that initiates the application by extending
 * the <code>javafx.application.Application</code> class. It is
 * responsible for starting up the game controller and
 * loading in the view initially.
 *  
 * @author Graeme Zinck and Charles Jobin and Josh Cookson
 * @version 1.0
 *
 */
public class MainApplication extends Application {
	private static final String GAME_NAME = "Mr Jack";

	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/res/fxml/main.fxml"));
		Parent root = loader.load();
		GameView gv = loader.getController();
		stage.setScene(new Scene(root));
		stage.setTitle(GAME_NAME);
		stage.show();
		new GameController(gv.drawBoard(TokenConstants.TILE_FRAMEWORK), gv);
	}

}
