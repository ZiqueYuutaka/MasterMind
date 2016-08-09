package popup;

import javafx.scene.layout.BorderPane;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

public class AlertWindow extends BorderPane{
	private Button btn;
	private Label message;
	private BorderPane labelPane;
	private BorderPane btnPane;

	public AlertWindow(Button btn, String msg){
		
		this.btn = btn;
		message = new Label(msg);
		labelPane = new BorderPane();
		btnPane = new BorderPane();

		//message.setText("Would you like to start a new game?");

		labelPane.setCenter(message);
		labelPane.setAlignment(message,Pos.CENTER);

		btnPane.setCenter(btn);
		btnPane.setAlignment(btn, Pos.CENTER);

		setCenter(labelPane);
		setBottom(btnPane);


	}

	
}