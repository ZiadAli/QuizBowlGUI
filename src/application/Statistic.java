package application;

public class Statistic 
{
	public boolean statisticEnabled = true;
	public String statisticName = "";
	public boolean bonusStatistic = false;
	public boolean fractionOn = false;
	
	public Statistic()
	{
		
	}
	
	public Statistic(String name)
	{
		statisticName = name;
		ExcelWriter.statsCategories.add(this);
		Settings.bigStatisticList.add(this);
	}
	
	public Statistic(boolean fraction, String name)
	{
		statisticName = name;
		ExcelWriter.statsCategories.add(this);
		fractionOn = true;
		Settings.bigStatisticList.add(this);
	}
	
	public Statistic(String name, boolean bonusEnabled)
	{
		statisticName = name;
		bonusStatistic = bonusEnabled; 
		ExcelWriter.bonusStats.add(this);
		Settings.bigStatisticList.add(this);
	}
	
	public boolean isFractionEnabled()
	{
		return fractionOn;
	}
	
	public String getStatisticName()
	{
		return statisticName;
	}
	
	public void enableStatistic()
	{
		statisticEnabled = true;
	}
	
	public void disableStatistic()
	{
		statisticEnabled = false;
	}
	
	public void enableBonus()
	{
		bonusStatistic = true;
	}
	
	public void disableBonus()
	{
		bonusStatistic = false;
	}
	
	public boolean isEnabled()
	{
		return statisticEnabled;
	}
	
	public boolean isBonus()
	{
		return bonusStatistic;
	}
}
