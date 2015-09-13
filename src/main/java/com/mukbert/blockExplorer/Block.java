package com.mukbert.blockExplorer;

import java.awt.Graphics2D;

import com.mukbert.framework.EntityColor;

public class Block extends EntityColor
{
	private static int size = 50;
	
	private int ID;
	private int xPos;
	private int yPos;
	
	public Block() 
	{
		super(RECTANGLE);
	}
	
	public void init(int ID, int xPos, int yPos)
	{
		setID(ID);
		setPosX(xPos);
		setPosY(yPos);
		setSize(size);
		setX(xPos * size);
		setY(yPos * size); 
	}
	
	@Override
	public void draw(Graphics2D g) 
	{
		if(ID == 0) return;

		super.draw(g);
	}
	
	public int getID()
	{
		return ID;
	}
	
	private void setID(int ID)
	{
		this.ID = ID;
	}
	
	public int getPosX()
	{
		return xPos;
	}
	
	public int getPosY()
	{
		return yPos;
	}
	
	private void setPosX(int xPos)
	{
		this.xPos = xPos;
	}
	
	private void setPosY(int yPos)
	{
		this.yPos = yPos;
	}
	
	public static int getSize()
	{
		return size;
	}
	
	public static void setBlockSize(int size)
	{
		Block.size = size;
	}
}
