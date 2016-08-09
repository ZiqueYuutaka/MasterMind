package popup;

import javafx.scene.layout.BorderPane;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

public class ConfirmShowAnswerWindow extends BorderPane{
	private Button btnShowAnswer;
	private Label showAnswerMsg;
	private BorderPane labelPane;
	private BorderPane btnPane;

	public ConfirmShowAnswerWindow(Button btn){
		
		btnShowAnswer = btn;
		showAnswerMsg = new Label();
		labelPane = new BorderPane();
		btnPane = new BorderPane();

		showAnswerMsg.setText("Are you sure you want to see the answer?");

		labelPane.setCenter(showAnswerMsg);
		labelPane.setAlignment(showAnswerMsg,Pos.CENTER);

		btnPane.setCenter(btnShowAnswer);
		btnPane.setAlignment(btnShowAnswer, Pos.CENTER);

		setCenter(labelPane);
		setBottom(btnPane);


	}

	
}