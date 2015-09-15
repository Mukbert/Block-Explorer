package com.mukbert.blockExplorer.data;

import java.awt.Color;

public class BlockData 
{		
	private int ID;
	private String name;
	private Color color;
	
	public BlockData(int ID) 
	{
		setID(ID);
		setName("Empty");
		setColor(null);
		Data.setBlockData(this);
	}
	
	protected static BlockData create(int ID)
	{
		if(Data.getBlockData(ID) == null)
		{
			return new BlockData(ID);
		}
		else
		{
			throw new ExceptionInInitializerError("The BlockData for ID: '" + ID + "' already exists!");
		}
	}
	
	protected BlockData setName(String name)
	{
		this.name = name;
		return this;
	}
	
	protected BlockData setColor(Color color)
	{
		this.color = color;
		return this;
	}
	
	protected BlockData setID(int ID)
	{
		this.ID = ID;
		return this;
	}
	
	public int getID()
	{
		return ID;
	}
	
	public String getName()
	{
		return name;
	}
	
	public Color getColor()
	{
		return color;
	}
}
