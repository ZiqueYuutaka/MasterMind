package data;

public class GuessData{
	private String numberStr;
	private int rightNumber;
	private int wrongPosition;
	
	public GuessData(){
		this("", 0, 0);
	}
	
	public GuessData(String numbers, int rightNumber, int wrongPosition){
		if(numbers.equals("")){
			numberStr = null;
		}
		else{
			this.numberStr = numbers;
		}//End if/else block
		
		this.rightNumber = rightNumber;
		this.wrongPosition = wrongPosition;
		
	}

	public void setGuessNumber(String nStr){
		this.numberStr = nStr;
	}
	
	public String getGuessNumber(){
		return numberStr;
	}

	public void setRightNumber(int rNum){
		this.rightNumber = rNum;
	}
	
	public int getRightNumber(){
		return rightNumber;
	}

	public void setWrongPosition(int wPos){
		this.wrongPosition = wPos;
	}
	
	public int getWrongPosition(){
		return wrongPosition;
	}
	
	@Override
	public String toString(){
		return "Guessed Number:  " + numberStr + "\n" +
				   "Correct Numbers:  " + rightNumber + "\n" +
				   "Wrong Position:     " + wrongPosition +"\n";
		
		
	}
}