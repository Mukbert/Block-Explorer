package com.mukbert.blockExplorer;

import java.awt.Color;
import java.awt.event.KeyEvent;

import com.mukbert.blockExplorer.data.Data;
import com.mukbert.framework.EntityColor;

public class Player extends EntityColor
{
	private Map map;
	
	private double frictionFactor;
	private double jumpPower;
	private double gravityPower;
	private double moveSpeed;
	private double xSpeed;
	private double ySpeed;
	
	private double xOld;
	private double yOld;
	
	private boolean canJump;
	private boolean hasCollisionX;
	private boolean hasCollisionY;
	
	public Player()
	{
		super(RECTANGLE);
	}
	
	public void init(Map map, int xPos, int yPos)
	{
		this.map = map;
		
		setWidth (0.8 * Block.getSize());
		setHeight(0.8 * Block.getSize());
		setX((xPos + 0.5) * Block.getSize() - getWidth()  / 2);
		setY((yPos + 0.5) * Block.getSize() - getHeight() / 2);
		setColor(Color.blue);
		setMoveSpeed(20);
		setGravityPower(20);
		setJumpPower(8);
		setFrictionFactor(0.8);
	}
	
	public void update()
	{
		hasCollisionX = false;
		hasCollisionY = false;
		xOld = getX();
		yOld = getY();
		
		collision(true, true);
		jump();
		gravity();
		move();
		
	}
	
	private void gravity()
	{
		ySpeed += gravityPower * (double)Block.getSize() * Game.getTimeDelta();
		
		moveY(ySpeed * Game.getTimeDelta());
		
		collision(false, true);
	}
	
	private void jump()
	{
		if(Game.getKeyboard().isKeyDown(KeyEvent.VK_SPACE) && canJump)
		{
			canJump = false;
			ySpeed = -jumpPower * Block.getSize();
		}
	}
	
	private void move()
	{
		boolean moveLeft  = Game.getKeyboard().isKeyDown(KeyEvent.VK_A) || Game.getKeyboard().isKeyDown(KeyEvent.VK_LEFT);
		boolean moveRight = Game.getKeyboard().isKeyDown(KeyEvent.VK_D) || Game.getKeyboard().isKeyDown(KeyEvent.VK_RIGHT);
		
		if(moveLeft && moveRight)
		{
			moveLeft  = false;
			moveRight = false;
		}
		
		if(moveLeft)
		{
			xSpeed -= moveSpeed * Block.getSize() * Game.getTimeDelta();
		}
		else if(moveRight)
		{
			xSpeed += moveSpeed * Block.getSize() * Game.getTimeDelta();
		}
		
		xSpeed *= frictionFactor;
		
		moveX(xSpeed * Game.getTimeDelta());
		
		if(getX() < 0) setX(0);
		else if(getRight() > map.getWidth()) setX(map.getWidth() - getWidth());
		
		collision(true, false);
	}
	
	private void collision(boolean x, boolean y)
	{		
		int x1 = (int) (getLeft  () / Block.getSize());
		int x2 = (int) (getRight () / Block.getSize());
		int y1 = (int) (getTop   () / Block.getSize());
		int y2 = (int) (getBottom() / Block.getSize());
		
		if(x1 < 0) x1 = 0;
		else if(x2 > map.getSizeX() - 1) x2 = map.getSizeX() - 1;
		
		if(y1 < 0) y1 = 0;
		else if(y2 > map.getSizeY() - 1) y2 = map.getSizeY() - 1;
		
		for(int xPos = x1; xPos <= x2; xPos++)
		{
			for(int yPos = y1; yPos <= y2; yPos++)
			{
				Block block = map.getBlock(xPos, yPos);
				
				if(block.getBlockData() != Data.Block.AIR && this.intersects(block))
				{
					if(x)
					{
						setX(xOld);
						xSpeed = 0;
						hasCollisionX = true;
					}
					if(y) 
					{
						setY(yOld);
						ySpeed = -ySpeed * 0.4;
						canJump = true;
						hasCollisionY = true;
					}
				}
			}
		}
	}
	
	private void setFrictionFactor(double factor)
	{
		this.frictionFactor = factor;
	}
	
	private void setJumpPower(double power)
	{
		this.jumpPower = power;
	}
	
	private void setGravityPower(double power)
	{
		this.gravityPower = power;
	}
	
	private void setMoveSpeed(double speed)
	{
		this.moveSpeed = speed;
	}
}
