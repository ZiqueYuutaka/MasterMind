package handlers;



public class AnswerHndlr implements EventHandler<ActionEvent>{
	private 


	public AnswerHndlr(){


	}

	public void handle(ActionEvent e){
		System.out.println("Hello from AnswerHndlr");
		tfs.setText("");
		for(int i = 0; i < 4; i++){
			lbls[i].setText(Integer.toString(answerHash[i]));
		}

		tfs.setEditable(false);
	}//End handle
}//End EditHndlr