package com.mukbert.blockExplorer;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Map 
{
	private List<Block> blocks;
	private HashMap<Point, Block> map;
	
	private int sizeX;
	private int sizeY;
	
	public Map() 
	{
		blocks = new ArrayList<Block>();
		map = new HashMap<Point, Block>();
		
		sizeX = 16;
		sizeY = 12;
	}
	
	public void init()
	{
		Random random = new Random();
		
		for(int x = 0; x < sizeX; x++)
		{
			int yStart = 3 + random.nextInt(3);
			
			for(int y = 0; y < sizeY; y++)
			{
				Point point = new Point(x, y);
				
				int ID = y < yStart ? 0 : 1;
				
				Block block = new Block();
				
				block.init(ID, x, y);
				
				blocks.add(block);
				map.put(point, block);
			}
		}
	}
	
	public void draw(Graphics2D g)
	{
		for(Block block : blocks)
		{
			block.draw(g);
		}
	}
	
	public int getSizeX()
	{
		return sizeX;
	}
	
	public int getSizeY()
	{
		return sizeY;
	}
	
	public int getWidth()
	{
		return sizeX * Block.getSize();
	}
	
	public int getHeight()
	{
		return sizeY * Block.getSize();
	}
	
	public Block getBlock(Point2D point)
	{
		return getBlock(point.getX(), point.getY());
	}
	
	public Block getBlock(double x, double y)
	{
		int xPos = (int) (x / Block.getSize());
		int yPos = (int) (y / Block.getSize());
		
		return getBlock(xPos, yPos);
	}
	
	public Block getBlock(Point point)
	{
		return map.get(point);
	}
	
	public Block getBlock(int xPos, int yPos)
	{
		return getBlock(new Point(xPos, yPos));
	}
	
	public List<Block> getBlocks()
	{
		return blocks;
	}
}
