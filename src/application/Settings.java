package application;

import java.util.ArrayList;

public class Settings 
{
	protected static ArrayList<Category> bigCategoryList = new ArrayList<Category>();
	protected static ArrayList<Statistic> bigStatisticList = new ArrayList<Statistic>();
	public ArrayList<Category> categoryList = new ArrayList<Category>();
	public ArrayList<Statistic> statisticList = new ArrayList<Statistic>();
	protected ArrayList<Category> enabledCategoryList = new ArrayList<Category>();
	public static int playersPerTeam = 4;

	public Settings()
	{
		categoryList = bigCategoryList;
		statisticList = bigStatisticList;
	}
	
	@SuppressWarnings("unused")
	public void addAllStatistics()
	{
		Statistic pp20th = new Statistic(true, "PP20TH");
		Statistic ptuh = new Statistic(true, "P/TUH");
		Statistic thirties = new Statistic("30's");
		Statistic twenties = new Statistic("20's");
		Statistic powers = new Statistic("15's");
		Statistic tens = new Statistic("10's");
		Statistic negs = new Statistic("-5's");
		Statistic pn = new Statistic(true, "P/N");
		Statistic gn = new Statistic(true, "G/N");
		Statistic tuh = new Statistic("TUH");
		
		Statistic buh = new Statistic("BHrd", true);
		Statistic bpts = new Statistic("BPts", true);
		Statistic ppb = new Statistic("PPB", true);
		Statistic bbh = new Statistic("BBHrd", true); 
		Statistic bbpts = new Statistic("BBPts", true);
		Statistic ppbb =new Statistic("PPBB", true);
	}
	
	@SuppressWarnings("unused")
	public void addAllCategories()
	{
		Category all = new Category("ALL", "ALL", "All");

		Category history = new Category("HISTORY", "HISTORY", "Hist"); 
		Category aHistory = new Category("American", "HISTORY", "USHist"); 
		Category eHistory = new Category("European", "HISTORY", "EHist"); 
		Category wHistory = new Category("World", "HISTORY", "WHist"); 
		Category anHistory = new Category("Ancient", "HISTORY", "AnHist"); 
		Category oHistory = new Category("Other", "HISTORY", "OHist"); 
		
		Category lit = new Category("LITERATURE", "LITERATURE", "Lit"); 
		//Category worksLit = new Category("Works", "LITERATURE", "Work"); 
		Category usWorksLit = new Category("American", "LITERATURE", "USWork"); 
		Category britWorksLit = new Category("British", "LITERATURE", "UKWork"); 
		Category grWorksLit = new Category("Greco-Roman", "LITERATURE", "GRWork"); 
		Category worldWorksLit = new Category("World", "LITERATURE", "WWork");
		//Category authLit = new Category("Authors", "LITERATURE", "Auth");
		Category usAuthlit = new Category("American", "LITERATURE", "USAuth");
		Category brithAuthlit = new Category("British", "LITERATURE", "UKAuth");
		Category grAuthlit = new Category("Greco-Roman", "LITERATURE", "GRAuth");
		Category worldAuthlit = new Category("World", "LITERATURE", "WAuth");
		Category uslit = new Category("American", "LITERATURE", "USLit");
		Category britlit = new Category("British", "LITERATURE", "UKLit");
		Category grlit = new Category("Greco-Roman", "LITERATURE", "GRLit");
		Category worldlit = new Category("World", "LITERATURE", "WLit");
		Category otherlit = new Category("Other", "LITERATURE", "OLit");

		Category sci = new Category("SCIENCE", "SCIENCE", "Sci");
		Category astronomy = new Category("Astronomy", "SCIENCE", "Astro");
		Category biology = new Category("Biology", "SCIENCE", "Bio");
		Category chemistry = new Category("Chemistry", "SCIENCE", "Chem");
		Category physics = new Category("Physics", "SCIENCE", "Phys");
		Category math = new Category("Math", "SCIENCE", "Math");
		Category gSci = new Category("General", "SCIENCE", "GSci");
		Category otherSci = new Category("Other", "SCIENCE", "OSci");
		
		Category finearts = new Category("FINE ARTS", "FINE ARTS", "FiArt");
		Category music = new Category("Music", "FINE ARTS", "Music");
		Category composers = new Category("Musicians/Composers", "FINE ARTS", "MusComp");
		Category musPiece = new Category("Pieces", "FINE ARTS", "MPiece");
		Category musPerf = new Category("Performances", "FINE ARTS", "MPerf");
		Category otherMusic = new Category("Other", "FINE ARTS", "OMus");
		Category art = new Category("Art", "FINE ARTS", "Art");
		Category artists = new Category("Artists", "FINE ARTS", "Artist");
		Category artPiece = new Category("Piece", "FINE ARTS", "APiece");
		Category architecture = new Category("Architecture", "FINE ARTS", "Arch");
		Category otherArt = new Category("Other", "FINE ARTS", "OArt");
		
		Category geo = new Category("GEOGRAPHY", "GEOGRAPHY", "Geo");
		Category usGeo = new Category("American", "GEOGRAPHY", "USGeo");
		Category worldGeo = new Category("World", "GEOGRAPHY", "WGeo");
		Category otherGeo = new Category("Other", "GEOGRAPHY", "OGeo");
		
		Category phil = new Category("PHILOSOPHY", "PHILOSOPHY", "Phil");
		Category theo = new Category("Theology", "PHILOSOPHY", "Theo");
		Category classicalPhil = new Category("Classical", "PHILOSOPHY", "CPhil");
		Category modernPhil = new Category("Modern", "PHILOSOPHY", "MPhil");
		Category otherPhil = new Category("Other", "PHILOSOPHY", "OPhil");
		
		Category rel = new Category("RELIGION", "RELIGION", "Rel");
		Category christianity = new Category("Christianity", "RELIGION", "Christ");
		Category judaism = new Category("Judaism", "RELIGION", "Jud");
		Category worldRel = new Category("World", "RELIGION", "WRel");
		Category otherRel = new Category("Other", "RELIGION", "ORel");
		
		Category ssci = new Category("SOCIAL SCIENCE", "SOCIAL SCIENCE", "SSci");
		Category econ = new Category("Economics", "SOCIAL SCIENCE", "Econ");
		Category psych = new Category("Psychology", "SOCIAL SCIENCE", "Psych");
		Category govt = new Category("Government", "SOCIAL SCIENCE", "Govt");
		Category otherSSci = new Category("Other", "SOCIAL SCIENCE", "OSSci");
		
		Category current = new Category("CURRENT EVENTS", "CURRENT EVENTS", "CE");
		Category USCurrent = new Category("American", "CURRENT EVENTS", "USCE");
		Category WCurrent = new Category("World", "CURRENT EVENTS", "WCE");
		Category otherCurrent = new Category("Other", "CURRENT EVENTS", "OCE");
		
		Category trash = new Category("TRASH", "TRASH", "Trash");
		Category sports = new Category("Sports", "TRASH", "Sport");
		Category television = new Category("Television", "TRASH", "TV");
		Category film = new Category("Film", "TRASH", "Film");
		Category trashMusic = new Category("Music", "TRASH", "TMusic");
		Category otherTrash = new Category("Other", "TRASH", "OTrash");
		
		Category myth = new Category("MYTHOLOGY","MYTHOLOGY", "Myth");
		Category grMyth = new Category("Greco-Roman","MYTHOLOGY", "GRMyth");
		Category norseMyth = new Category("Norse","MYTHOLOGY", "NMyth");
		Category hinduMyth = new Category("Hindu","MYTHOLOGY", "HMyth");
		Category worldMyth = new Category("World","MYTHOLOGY", "WMyth");
		Category otherMyth = new Category("Other","MYTHOLOGY", "OMyth");
		
		Category misc = new Category("MISCELLANEOUS", "MISCELLANEOUS", "Misc");
		Category gk = new Category("General Knowledge", "MISCELLANEOUS", "GK");
		Category common = new Category("Common Link", "MISCELLANEOUS", "CLink");
		Category otherMisc = new Category("Other", "MISCELLANEOUS", "Other");
	}
}
