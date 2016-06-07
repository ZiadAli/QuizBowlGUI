package application;

import java.util.ArrayList;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "Testing")
public class TournamentWrapper 
{
	private Tournament tournament;
	private ArrayList<Question> questions = new ArrayList<Question>();
	
	@XmlElement
	public Tournament getTournament()
	{
		return tournament;
	}
	
	public void setTournament(Tournament tournament2)
	{
		tournament = tournament2;
	}
}
