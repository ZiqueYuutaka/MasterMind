package handlers;

import javafx.beans.Observable;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.util.ArrayList;
import javafx.scene.control.Label;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

import data.GuessData;

//TextArea, textfield, GuessList, guessdataList,
//answerHash, GuessData, 
public class GuessHndlr implements EventHandler<ActionEvent>{
	private TextArea textArea;
	private TextField tfs;
	private ObservableList<String> guessList;//Links directly with ListView
	private ArrayList<GuessData> guessDataList;
	private Label[] lbls;
	int answer;
	int[] answerHash;
	//String testGuess = "4444";
	
	GuessData guessData;

	public GuessHndlr(TextArea ta, TextField tf, ObservableList<String> ov,
						ArrayList<GuessData> gdl, Label[] lbls,
						int answer, int[] answerHash){
		textArea = ta;
		tfs = tf;
		guessList = ov;
		guessDataList = gdl;
		this.lbls = lbls;
		this.answer = answer;
		this.answerHash = answerHash;

	}

	public void handle(ActionEvent e){
		System.out.println("Hello from GuessHndlr");
		//String guessedNumber = tfs.getText();
		validateGuess();
		//add guess to listView
				guessList.add(guessData.getGuessNumber());
					//add guessData to guessDataList
				guessDataList.add(guessData);
		tfs.requestFocus();
	}

	private void validateGuess(){
		if(tfs.getText().length() > 4){
			tfs.setText("");
			textArea.clear();
			textArea.appendText("Please enter a four digit number");
		}
		else{
			try{
				int guessedNumber = Integer.parseInt(tfs.getText());
				tfs.setText("");
				
				//Compare guessedNumber with actual number
				checkGuess(guessedNumber);
				
				//add guess to listView
/*				guessList.add(guessData.getGuessNumber());
					//add guessData to guessDataList
				guessDataList.add(guessData);*/
			}
			catch(NumberFormatException exception){
				tfs.setText("");
				textArea.clear();
				textArea.appendText("Please enter a numeric string");
			}
		}
	}

	private void checkGuess(int guessedNumber){
		String numberData = Integer.toString(guessedNumber);
		int[] numbers = new int[4];
		int rightNumber = 0;
		int wrongPosition = 0;

		for(int i = 3; i >= 0; i--){
			numbers[i] = guessedNumber % 10;
			lbls[i].setText(Integer.toString(numbers[i]));
			guessedNumber/=10;
		}
		
		//check for correct number in correct position
		for(int guess = 0; guess < 4; guess++){
			for(int answer = 0; answer < 4; answer++){
				if((numbers[guess] == answerHash[answer]) &&(guess == answer)){
					rightNumber++;
					answerHash[answer] = -1;
					numbers[guess] = -2;
				}
			}
		}
		
		//check for correct number in wrong position
		for(int guess = 0; guess < 4; guess++){
			for(int answer = 0; answer < 4; answer++){
				if((numbers[guess] == answerHash[answer]) &&(guess != answer)){
					wrongPosition++;
					answerHash[answer] = -1;
					numbers[guess] = -2;
				}
			}
		}
		
		guessData = new GuessData(numberData, rightNumber, wrongPosition);
		
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

	private void hashRandomNumber(){
		//Read the guessed number backwards in to array from the end
		int temp = answer;
		for(int i = 3; i >= 0; i--){
			int remainder = temp %10;
			answerHash[i] = (remainder);
			temp/=10;
		}
	}
}