package application;

import java.util.ArrayList;

public class Team 
{
	public ArrayList<Player> players = new ArrayList<Player>();
	public String name = "";
	public String teamCode = "";
	public String coach = "";
	public String city = "";
	public String state = "";
	public String division = "";
	public boolean dataWritten = false;
	
	public Team()
	{
		
	}
	
	public Team(String teamName)
	{
		name = teamName;
	}
	
	public Team(String teamName, String code)
	{
		name = teamName;
		teamCode = code;
	}
	
	public Team(String teamName, String teamCoach, String teamCity, String teamState, String teamDivision)
	{
		if(name != null)
		{
			name = teamName;
			System.out.println("Team name: " + name);
		}
		if(name != null)
			coach = teamCoach;
		if(name != null)
			city = teamCity;
		if(name != null)
			state = teamState;
		if(name != null)
			division = teamDivision;
	}
	
	public Team(ArrayList<Player> players2)
	{
		players = players2;
	}
	
	public void addPlayer(Player player)
	{
		players.add(player);
	}
	
	public void removePlayer(Player player)
	{
		players.remove(player);
	}
	
	public void setTeamName(String nameSet)
	{
		name = nameSet;
	}
	
	public String getTeamName()
	{
		return name;
	}
	
	public void setCode(String code)
	{
		teamCode = code;
	}
	
	public String getCode()
	{
		return teamCode;
	}
}
