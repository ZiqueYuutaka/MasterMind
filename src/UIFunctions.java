package uiFunctions;

import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import javafx.scene.paint.Color;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Modality;

import data.GuessData;
import xmldao.XmlDAO;
import uiFunctions.Constants;
import popup.AlertWindow;

public class UIFunctions implements Constants{
	//Listeners
	private ObservableList<String> guessList;//Links directly with ListView
	private ListView listView;
	private ArrayList<GuessData> guessDataList;
	private Button[] btns;
	private TextField tfs;
	private Label[] lbls;
	private TextArea textArea;
	
	int answer = 0;
	int[] answerHash = new int[4];
	//String testGuess = "4444";
	
	GuessData guessData;

	private XmlDAO xmlDAO;

	AlertWindow alert;
	Scene alertScene;
	Stage alertStage;

	public UIFunctions(){
		xmlDAO = new XmlDAO();
		guessList = null;
		listView = null;
		guessDataList = null;
		btns = null;
		tfs = null;
		textArea = null;
		guessData = null;
		alert = null;
		alertScene = null;
		alertStage = null;

		if(answer == 0){
			createRandomNumber();
		}
	}

	public UIFunctions(TextField tfs, Button[] btns, TextArea ta, Label[] lbls){
		this.tfs = tfs;
		textArea = ta;
		textArea.setEditable(false);
		this.btns = btns;
		this.lbls = lbls;

		xmlDAO = new XmlDAO();

		guessList = FXCollections.observableArrayList();
		listView = new ListView<>(guessList);
		guessDataList = new ArrayList<>();

		alert = null;
		alertScene = null;
		alertStage = null;

		//Set selectionmodel to singl selection
		listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

		//Add a listener to the observable list
		listView.getSelectionModel().selectedItemProperty().addListener(new ListViewHndlr());

		this.tfs.setOnKeyPressed(new TextFieldHndlr());

		addBtnHndlrs();

		newGameSetUp();
			
	}

	private void addBtnHndlrs(){
		btns[NEW_BTN].setOnAction(e->{newBtnClicked(e);});
		btns[LOAD_BTN].setOnAction(new LoadHndlr());
		btns[SAVE_BTN].setOnAction(new SaveHndlr());
		btns[ANSWER_BTN].setOnAction(e->{answerBtnClicked(e);});
		btns[GUESS_BTN].setOnAction(new GuessHndlr());
		btns[ANSWER_CONFIRM].setOnAction(e->{answerBtnClicked(e);});
		btns[NEW_CONFIRM].setOnAction(e->{newBtnClicked(e);});
	}

	public ListView<String> getListView(){
		return listView;
	}

	private void newGameSetUp(){
		answer = 0;

		createRandomNumber();

		hashRandomNumber();

		tfs.setText("");
		tfs.setEditable(true);

		//Clear the labels
		for(int i = 0; i < lbls.length; i++){
			lbls[i].setText("");
		}

		//clear observablelist
		guessList.clear();

		//Clear the datalist
		guessDataList.clear();

		//splash welcome screen in textArea
		textArea.clear();
		textArea.appendText("Welcome to Master Mind");
	}

	private void createRandomNumber(){
		for(int i = 0; i < answerHash.length; i++){
			answerHash[i] = (int)(Math.random() *10);
			if(answer == 0){
				answer = answerHash[0];
			}
			else{
				answer = (answer * 10 ) + answerHash[i];
			}
		}

	}

	private void hashRandomNumber(){
		//Read the guessed number backwards in to array from the end
		int temp = answer;
		for(int i = 3; i >= 0; i--){
			int remainder = temp %10;
			answerHash[i] = (remainder);
			temp/=10;
		}
	}
	
	private void checkGuess(String guessedNumber){
		int[] numbers = new int[4];
		int rightNumber = 0;
		int wrongPosition = 0;

		//Set labels with guess
		for(int i = 0; i < 4; i++){			
			lbls[i].setText(
				Character.toString(guessedNumber.charAt(i)));
		}

		//change characters to numbers
		for(int i = 0; i < 4; i++){
			numbers[i] = Integer.parseInt(
				Character.toString(guessedNumber.charAt(i)));
		}
		
		//check for correct number in correct position
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				if((numbers[i] == answerHash[j]) &&(i == j)){
					rightNumber++;
					answerHash[j] = -1;
					numbers[i] = -2;
				}
			}
		}
		
		//check for correct number in wrong position
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				if((numbers[i] == answerHash[j]) &&(i != j)){
					wrongPosition++;
					answerHash[j] = -1;
					numbers[i] = -2;
				}
			}
		}
		
		guessData = new GuessData(guessedNumber, rightNumber, wrongPosition);
		
		if(rightNumber == 4){
			textArea.clear();
			textArea.appendText("YOU WIN!!!!!");
			tfs.setEditable(false);
		}
		else{
			displayGuessData();
			hashRandomNumber();
		}		
		
	}//End checkGuess
	
	private void displayGuessData(){
		textArea.clear();
		textArea.appendText(guessData.toString());		
	}

	private void validateGuess(){
		if(tfs.getText().length() > 4){
			tfs.setText("");
			textArea.clear();
			textArea.appendText("Please enter a four digit number");
		}
		else{
			try{
				//int guessedNumber = Integer.parseInt(tfs.getText());
				//tfs.setText("");
				Integer.parseInt(tfs.getText()); //Error checking
				
				//Compare guessedNumber with actual number
				checkGuess(tfs.getText());
				
				//add guess to listView
				guessList.add(guessData.getGuessNumber());
					//add guessData to guessDataList
				guessDataList.add(guessData);
				tfs.setText("");
			}
			catch(NumberFormatException exception){
				tfs.setText("");
				textArea.clear();
				textArea.appendText("Please enter a numeric string");
			}
		}
	}

	private void answerBtnClicked(ActionEvent e){
		if(e.getSource() == btns[ANSWER_BTN]){
			//Create confirmation window
			alert = new AlertWindow(btns[ANSWER_CONFIRM], 
				"Show answer?");
			alertScene = new Scene(alert, WNDW_WIDTH, WNDW_HEIGHT);
			alertStage = new Stage();
			alertStage.setScene(alertScene);
			alertStage.initModality(Modality.APPLICATION_MODAL);
			alertStage.showAndWait();
		}
		else if(e.getSource() == btns[ANSWER_CONFIRM]){
			tfs.setText("");
			for(int i = 0; i < 4; i++){
				lbls[i].setText(Integer.toString(answerHash[i]));
			}

			tfs.setEditable(false);

			//close confirmation window
			alertStage.close();
		}
	}

	private void newBtnClicked(ActionEvent e){
		if(e.getSource() == btns[NEW_BTN]){
			System.out.println("Button " + NEW_BTN + " clicked");
			//Create and show new game confirmation window
			alert = new AlertWindow(btns[NEW_CONFIRM], "Start new game?");
			alertScene = new Scene(alert, WNDW_WIDTH, WNDW_HEIGHT);
			alertStage = new Stage();
			alertStage.setScene(alertScene);
			alertStage.initModality(Modality.APPLICATION_MODAL);
			alertStage.showAndWait();
		}
		else if(e.getSource() == btns[NEW_CONFIRM]){
			System.out.println("Button " + NEW_CONFIRM + " clicked");
			newGameSetUp();

			//close new game confirmation window
			alertStage.close();
		}
	}

	class LoadHndlr implements EventHandler<ActionEvent>{
		public void handle(ActionEvent e){
			System.out.println("Hello from LoadHndlr");

			//Save the game
			guessDataList = xmlDAO.getGame();
			if(guessDataList == null || guessDataList.isEmpty()){
				guessDataList = new ArrayList<>();
				textArea.clear();
				textArea.appendText("No previous game found.");
			}
			else{
				textArea.clear();
				textArea.appendText("Welcome back!!!");
				answer = xmlDAO.getAnswer();
				guessDataList = xmlDAO.getGame();

				hashRandomNumber();

				if(!guessDataList.isEmpty()){
					for(GuessData data: guessDataList){
						guessList.add(data.getGuessNumber());
					}
				}
			}
		}
	}

	class SaveHndlr implements EventHandler<ActionEvent>{
		public void handle(ActionEvent e){
			
			xmlDAO.saveGame(answer, guessDataList);

		}
	}

	class GuessHndlr implements EventHandler<ActionEvent>{
		public void handle(ActionEvent e){
			//String guessedNumber = tfs.getText();
			validateGuess();
			
			tfs.requestFocus();
		}
	}

	class ListViewHndlr implements InvalidationListener{
		public void invalidated(Observable ov){
			//Handle an thrown exception by 
			if(!listView.getSelectionModel().isEmpty()){
				int index = listView.getSelectionModel().getSelectedIndex();

				textArea.clear();
				if(!guessDataList.isEmpty()){
					textArea.appendText(guessDataList.get(index).toString());
				}
			}
		}
	}

	class TextFieldHndlr implements EventHandler<KeyEvent>{
		public void handle(KeyEvent ke){
			if(ke.getCode() == KeyCode.ENTER){
				validateGuess();
				tfs.requestFocus();
			}
		}
	}

}