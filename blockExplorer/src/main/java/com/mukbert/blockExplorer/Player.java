package com.mukbert.blockExplorer;

import java.awt.Color;

import com.mukbert.framework.EntityColor;

public class Player extends EntityColor
{
	private Game game;
	
	private double xOld;
	private double yOld;
	
	public Player()
	{
		super(RECTANGLE);
	}
	
	public void init(Game game, int xPos, int yPos)
	{
		this.game = game;
		
		setWidth (0.8 * Block.getSize());
		setHeight(0.8 * Block.getSize());
		setX((xPos + 0.5) * Block.getSize() - getWidth()  / 2);
		setY((yPos + 0.5) * Block.getSize() - getHeight() / 2);
		setColor(Color.red);
	}
	
	public void update()
	{
		xOld = getX();
		yOld = getY();
		
		moveY(2D * (double)Block.getSize() * Game.getTimeDelta());
		
		collision(false, true);
	}
	
	private void collision(boolean x, boolean y)
	{
		Block button1 = game.getMap().getBlock(getLeft(), getTop());
		Block button2 = game.getMap().getBlock(getRight(), getBottom());
		
		
		int x1 = button1.getPosX();
		int x2 = button2.getPosX();
		int y1 = button1.getPosY();
		int y2 = button2.getPosY();
		
		for(int xPos = x1; xPos <= x2; xPos++)
		{
			for(int yPos = y1; yPos <= y2; yPos++)
			{
				Block block = game.getMap().getBlock(xPos, yPos);

				if(block.getID() != 0 && this.intersects(block))
				{
					if(x) setX(xOld);
					if(y) setY(yOld);
				}
			}
		}
	}
	
}
