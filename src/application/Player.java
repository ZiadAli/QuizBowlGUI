package application;

import java.awt.Color;
import java.awt.Image;
import java.awt.Insets;
import java.io.File;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class Player 
{
	String name = "";
	String grade;
	JToggleButton toggleButton = new JToggleButton();
	String sheetData = "";
	public boolean dataWritten = false;
	int score;
	
	public Player()
	{
		this.name = "";
	}
	
	public Player(String playerName, String playerGrade, String data)
	{
		this.name = playerName;
		this.grade = playerGrade;
		this.sheetData = data;
	}
	
	public Player(String playerName, String playerGrade)
	{
		this.name = playerName;
		this.grade = playerGrade;
	}
	
	public void setPlayerName(String nameSet)
	{
		this.name = nameSet;
	}
	
	public void setPlayerGrade(String gradeSet)
	{
		this.grade = gradeSet;
	}
	
	public String getPlayerName()
	{
		return name;
	}
	
	public String getPlayerGrade()
	{
		return grade;
	}
	
	public void setPlayerImage(File imageFileName)
	{
		String imageFileName2 = imageFileName.toString();
		ImageIcon imageIcon1 = new ImageIcon(imageFileName2);
		Image image1 = imageIcon1.getImage();
		Image image2 = image1.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		ImageIcon playerImage = new ImageIcon(image2);
		toggleButton.setIcon(playerImage);	
		toggleButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		toggleButton.setHorizontalTextPosition(SwingConstants.CENTER);
		toggleButton.setMargin(new Insets(1,1,1,1));
		toggleButton.setBorder(new LineBorder(Color.WHITE,20));
		toggleButton.setBorderPainted(false);
		toggleButton.setVisible(true);
	}
	
	public void addScore()
	{
		score += 10;
	}
	
	public void setData(String data)
	{
		sheetData = data;
	}
	
	public String getData()
	{
		return sheetData;
	}
	
}
