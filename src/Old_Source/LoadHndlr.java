

//GuessDataList, xmlDAO, textArea, answer, hashRandomNumber, GuessList,
//GuessData
public class LoadHndlr implements EventHandler<ActionEvent>{
	private ObservableList<String> guessList;//Links directly with ListView
	private ArrayList<GuessData> guessDataList;
	private TextArea textArea;
	int answer;
	int[] answerHash = new int[4];

	private XmlDAO xmlDAO;

	public LoadHndlr(ObservableList<String> ov, ArrayList<GuessData> gdl,
						TextArea ta, int answer, int[] answerHash){
		xmlDAO = new XmlDAO();
		guessList = ov;
		guessDataList = gdl;
		textArea = ta;
		answer = xmlDAO.getAnswer();
		hashRandomNumber(answer, answerHash);
	}


	public void handle(ActionEvent e){
		System.out.println("Hello from LoadHndlr");

		//Save the game
		guessDataList = xmlDAO.getGame();
		if(guessDataList == null){
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

	private void hashRandomNumber(int answer, int[] answerHash){
		//Read the guessed number backwards in to array from the end
		int temp = answer;
		for(int i = 3; i >= 0; i--){
			int remainder = temp %10;
			answerHash[i] = (remainder);
			temp/=10;
		}
	}
}