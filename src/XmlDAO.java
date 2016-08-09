package xmldao;

import java.util.*;
import java.io.*;
import javax.xml.stream.*;

import data.GuessData;

public class XmlDAO{

	private String xmlFile = "E:\\CIT285_Assignments\\Final\\class\\game.xml";
	private File gameFile = null;
	private int answer = 0;

	//Constructor
	public XmlDAO(){
		gameFile = new File(xmlFile);
	}

	private void checkFile() throws IOException{
		if(!gameFile.exists()){
			gameFile.createNewFile();
		}
	}

	public boolean saveGame(int answer, ArrayList<GuessData> guessDataList){

		//Creat an XMLOutputFactory object
		XMLOutputFactory xmlOutput = XMLOutputFactory.newInstance();

		try{
			//Check the file to make sure it exists
			this.checkFile();

			//Create an XMLStreamWriter object
			FileWriter fileWriter = new FileWriter(gameFile);
			XMLStreamWriter xmlWriter = xmlOutput.createXMLStreamWriter(fileWriter);

			//Begin writing the xml file
			//Start with the version and start tag
			xmlWriter.writeStartDocument("1.0");
			xmlWriter.writeStartElement("Games");
			xmlWriter.writeAttribute("Answer", Integer.toString(answer));

			if(!guessDataList.isEmpty()){
				for(GuessData data: guessDataList){
					xmlWriter.writeStartElement("Guess");
					
					xmlWriter.writeStartElement("GuessNumber");
					xmlWriter.writeCharacters(data.getGuessNumber());
					xmlWriter.writeEndElement();

					xmlWriter.writeStartElement("CorrectNumbers");
					xmlWriter.writeCharacters(Integer.toString(data.getRightNumber()));
					xmlWriter.writeEndElement();

					xmlWriter.writeStartElement("WrongPosition");
					xmlWriter.writeCharacters(Integer.toString(data.getWrongPosition()));
					xmlWriter.writeEndElement();

					//Write end element of Employee
					xmlWriter.writeEndElement();
				}
			}
			xmlWriter.writeEndElement();
			xmlWriter.flush();
			xmlWriter.close();
		}
		catch(IOException e){
			e.printStackTrace();
			return false;
		}
		catch(XMLStreamException e){
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public ArrayList<GuessData> getGame(){
		ArrayList<GuessData> dataList = new ArrayList<>();

		GuessData data = null;

		//Create an XMLInputFactory object
		XMLInputFactory xmlInput = XMLInputFactory.newInstance();

		try{
			//Check if the file exists
			this.checkFile();

			//Create a XMLStreamReader
			//First create a FileReader object
			FileReader fileReader = new FileReader(gameFile);
			//Second wrap the FileReader inside an XML reader
			XMLStreamReader xmlReader = xmlInput.createXMLStreamReader(fileReader);

			//read the XML file
			while(xmlReader.hasNext()){
				int eventType = xmlReader.getEventType();

				switch(eventType){
					case XMLStreamConstants.START_ELEMENT:
						String elementName = xmlReader.getLocalName();
						if(elementName.equals("Games")){
							String answerStr = xmlReader.getAttributeValue(0);
							answer = Integer.parseInt(answerStr);
						}
						if(elementName.equals("Guess")){
							data = new GuessData();
						}
						if(elementName.equals("GuessNumber")){
							String str = xmlReader.getElementText();
							data.setGuessNumber(str);
						}
						if(elementName.equals("CorrectNumbers")){
							String rn = xmlReader.getElementText();
							data.setRightNumber(Integer.parseInt(rn));
						}
						if(elementName.equals("WrongPosition")){
							String wp = xmlReader.getElementText();
							data.setWrongPosition(Integer.parseInt(wp));
						}
						break;
					case XMLStreamConstants.END_ELEMENT:
						elementName = xmlReader.getLocalName();
						if(elementName.equals("Guess")){
							dataList.add(data);
						}
						break;
					default:
						break;
				}
				xmlReader.next();	//move to the next line of xml text
			}
		}
		catch(IOException e){
			System.out.println("HELLO FROM IOException");
			e.printStackTrace();
			return null;	// b/c method returns an arraylist
		}
		catch(XMLStreamException e){
			System.out.println("HELLO FROM XMLStreamException");
			e.printStackTrace();
			System.out.println("RETURNING NULL");
			return null;
		}

		return dataList;
	}//End getEmployees

	public int getAnswer(){
		return answer;
	}
}