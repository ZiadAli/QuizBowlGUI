package application;

public class Setting 
{
	String settingName = "";
	String settingAbbrev = "";
	boolean teamSetting = false;
	
	public Setting()
	{
		
	}
	
	public Setting(String name, String abbrev, boolean team)
	{
		settingName = name;
		settingAbbrev = abbrev;
		teamSetting = team;
	}
	
	public String getSettingName()
	{
		return settingName;
	}
	
	public String getSettingAbbrev()
	{
		return settingAbbrev;
	}
}
