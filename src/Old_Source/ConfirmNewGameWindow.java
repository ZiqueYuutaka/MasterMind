package popup;

import javafx.scene.layout.BorderPane;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

public class ConfirmNewGameWindow extends BorderPane{
	private Button btnNewGame;
	private Label newGameMsg;
	private BorderPane labelPane;
	private BorderPane btnPane;

	public ConfirmNewGameWindow(Button btn){
		
		btnNewGame = btn;
		newGameMsg = new Label();
		labelPane = new BorderPane();
		btnPane = new BorderPane();

		newGameMsg.setText("Would you like to start a new game?");

		labelPane.setCenter(newGameMsg);
		labelPane.setAlignment(newGameMsg,Pos.CENTER);

		btnPane.setCenter(btnNewGame);
		btnPane.setAlignment(btnNewGame, Pos.CENTER);

		setCenter(labelPane);
		setBottom(btnPane);


	}

	
}