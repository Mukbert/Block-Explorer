package com.mukbert.blockExplorer;

import java.awt.Graphics2D;

import com.mukbert.blockExplorer.data.BlockData;
import com.mukbert.blockExplorer.data.Data;
import com.mukbert.framework.EntityColor;

public class Block extends EntityColor
{
	private static int size = 50;
	
	private BlockData blockData;
	private int xPos;
	private int yPos;
	
	public Block() 
	{
		super(RECTANGLE);
	}
	
	public void init(int ID, int xPos, int yPos)
	{
		setBlockData(ID);
		setPosX(xPos);
		setPosY(yPos);
		setSize(size);
		setX(xPos * size);
		setY(yPos * size); 
	}
	
	public void update()
	{
		
	}
	
	@Override
	public void draw(Graphics2D g)
	{
		if(blockData == Data.Block.AIR) return;
		
		
		g.setColor(blockData.getColor());
		g.fill(getBounds());
	}
	
	public BlockData getBlockData()
	{
		return blockData;
	}
	
	public void setBlockData(int ID)
	{
		setBlockData(Data.getBlockData(ID));
	}
	
	public void setBlockData(BlockData blockData)
	{
		this.blockData = blockData;
		setColor(blockData.getColor());
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
