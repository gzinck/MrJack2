package view;

import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.token.TokenConstants;
import controller.GameController;
import javafx.application.*;

public class MainApplication extends Application {
	private static final String GAME_NAME = "Mr Jack";
	private static GameController controller;

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
		controller = new GameController(gv.drawBoard(TokenConstants.TILE_FRAMEWORK), gv);
	}

}
