import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import uiFunctions.UIFunctions;
import uiFunctions.Constants;

public class MasterMindUI extends BorderPane implements Constants{

	//Reference for UIFunctions
	UIFunctions uiFunctions = null;
	TextArea textArea = null;
	Button[] btns = null;
	TextField guessField = null;
	StackPane[] numberField = null;
	Rectangle numberBox = null;
	String[] btnNames = {"New", "Load", "Save", "Answer", "Guess", "Show Answer", 
						"Start New Game"};
	Label[] testLabels = null;
	
	VBox btnPane = null;
	HBox numberDisplay = null;
	HBox guessTextArea = null;
	ScrollPane incorrectGuesses = null;

	public MasterMindUI(){

/*		testLV = new ListView<>(FXCollections.observableArrayList(btnNames));*/
		textArea = new TextArea();
//		textArea.setEditable(false);
		Button[] btns = new Button[NUM_BTN];
		TextField guessField = new TextField();
		numberField = new StackPane[NUM_OF_RECTANGLES];
		testLabels = new Label[NUM_OF_RECTANGLES];

		VBox btnPane = new VBox(5);
		btnPane.setPadding(new Insets(0,5,5,5));

		HBox numberDisplay = new HBox(25);
		numberDisplay.setPadding(new Insets(5,5,5,5));
		numberDisplay.setAlignment(Pos.CENTER);

		HBox guessTextArea = new HBox(5);
		guessTextArea.setPadding(new Insets(5,5,5,5));
		guessTextArea.setAlignment(Pos.CENTER);
		
		setPadding(new Insets(10, 10, 10, 10));

		for(int i = 0; i < btns.length; i++){
			btns[i] = new Button(btnNames[i]);
			btns[i].setMaxWidth(BTN_WIDTH);
		}

		//Set up the number display labels
		for(int i = 0; i <testLabels.length;i++){
			testLabels[i] = new Label();
		}

		//Give functionality to ui elements
		uiFunctions = new UIFunctions(guessField, btns, textArea, testLabels);

		ScrollPane incorrectGuesses = new ScrollPane(uiFunctions.getListView());
		incorrectGuesses.setPadding(new Insets(0,5,5,5));
		incorrectGuesses.setMaxWidth(100);
		incorrectGuesses.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

		btnPane.getChildren().addAll(btns[NEW_BTN],
									btns[LOAD_BTN],
									btns[SAVE_BTN],
									btns[ANSWER_BTN]);

		setLeft(btnPane);

		//Put the guess button with textfield in guessTextArea
		guessTextArea.getChildren().add(guessField);
		guessTextArea.getChildren().add(btns[GUESS_BTN]);

		setBottom(guessTextArea);

		for(int i = 0; i < numberField.length; i++){
			numberField[i] = new StackPane();
			numberBox = new Rectangle();
			numberBox.setWidth(REC_WIDTH);
			numberBox.setHeight(RECT_HEIGHT);
			numberBox.setFill(Color.TRANSPARENT);
			numberBox.setStroke(Color.BLACK);
			numberField[i].getChildren().add(numberBox);
			numberField[i].getChildren().add(testLabels[i]);
		}

		//give the numbers array to uifunctions

		numberDisplay.getChildren().addAll(numberField);

		setTop(numberDisplay);

		//Get listview form uifunctions

		setRight(incorrectGuesses);

//		textArea.appendText("Hello");
		setCenter(textArea);
		
	}
}