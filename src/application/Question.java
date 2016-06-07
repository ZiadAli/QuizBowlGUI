package application;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Question 
{
	int qNumber = 0;
	String tossupAbbrev = "";
	String bonusAbbrev = "";
	ArrayList<String> questionData = new ArrayList<String>();
	
	public Question()
	{

	}
	
	public Question(int questionNumber, String categoryAbbreviation, String bonusAbbreviation, ArrayList<String> questionData2)
	{
		qNumber = questionNumber;
		tossupAbbrev = categoryAbbreviation;
		bonusAbbrev = bonusAbbreviation;
		questionData = questionData2;
	}
	
	public void addData(String data)
	{
		questionData.add(data);
	}
	
	@XmlElement
	public void setData(ArrayList<String> data)
	{
		questionData = data;
	}
	
	public ArrayList<String> getData()
	{
		return questionData;
	}
	
	@XmlElement
	public void setQNumber(int questionNumber)
	{
		qNumber = questionNumber;
	}
	
	public int getQNumber()
	{
		return qNumber;
	}
	
	@XmlElement
	public void setTossupAbbrev (String categoryAbbreviation)
	{
		tossupAbbrev = categoryAbbreviation;
	}
	
	public String getTossupAbbrev()
	{
		return tossupAbbrev;
	}
	
	@XmlElement
	public void setBonusAbbrev(String bonusAbbreviation)
	{
		bonusAbbrev = bonusAbbreviation;
	}
	
	public String getBonusAbbrev()
	{
		return bonusAbbrev;
	}
}