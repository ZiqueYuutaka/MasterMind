package handlers;



public class NewHndlr implements EventHandler<ActionEvent>{
	public void handle(ActionEvent e){
		System.out.println("Hello from NewHndlr");

		newGameSetUp();
	}

	private void newGameSetUp(int answer, int[] answerHash, 
							Label[] lbls, TextField tfs, 
							ObservableList<String> ov, ArrayList<GuessData> gdl,
							TextArea textArea){
		answer = 0;

		createRandomNumber(answer, answerHash);

		//hashRandomNumber();

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
		System.out.println("Creating random number" + answer);

	}

	/*private void hashRandomNumber(){
		//Read the guessed number backwards in to array from the end
		int temp = answer;
		for(int i = 3; i >= 0; i--){
			int remainder = temp %10;
			answerHash[i] = (remainder);
			temp/=10;
		}
	}*/
}