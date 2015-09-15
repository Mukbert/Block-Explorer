package com.mukbert.blockExplorer;

import java.awt.Graphics2D;

import com.mukbert.blockExplorer.data.Data;

public class Axe 
{
	private Block block;
	private Map map;
	
	private double hitTime;
	
	public Axe() 
	{
		
	}
	
	public void init(Map map)
	{
		this.map = map;
	}
	
	public void update()
	{
		block = map.getBlock(Game.getMouse().getPoint());
		
		if(block != null && block.getBlockData() != Data.Block.AIR && Game.getMouse().isLeftDown())
		{
			hitTime += Game.getTimeDelta();
			
			if(hitTime > block.getBlockData().getRemoveTime())
			{
				hitTime = 0;
				block.setBlockData(Data.Block.AIR);
			}
		}
		else
		{
			hitTime = 0;
		}
	}
	
	public void draw(Graphics2D g)
	{
		
	}
}
