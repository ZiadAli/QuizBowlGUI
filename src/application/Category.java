package application;

public class Category 
{
	public boolean categoryEnabled = true;
	public String categoryName = "";
	public String parentCategory = "";
	public String categoryAbbreviation = "";
	
	public Category()
	{
		
	}
	
	public Category(String catName, String parentCat, String catAbbrev)
	{
		categoryName = catName;
		parentCategory = parentCat;
		categoryAbbreviation = catAbbrev;
		Settings.bigCategoryList.add(this);
	}
	
	public String getCategoryName()
	{
		return categoryName;
	}
	
	public String getCategoryAbbrev()
	{
		return categoryAbbreviation;
	}
	
	public void enableCategory()
	{
		categoryEnabled = true;
	}
	
	public void disableCategory()
	{
		categoryEnabled = false;
	}
	
	public boolean isEnabled()
	{
		return categoryEnabled;
	}
}
