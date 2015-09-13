package com.mukbert.blockExplorer;

import java.awt.Color;
import java.awt.event.KeyEvent;

import com.mukbert.framework.EntityColor;

public class Player extends EntityColor
{
	private Game game;
	
	private double frictionFactor;
	private double jumpPower;
	private double gravityPower;
	private double moveSpeed;
	private double xSpeed;
	private double ySpeed;
	
	private double xOld;
	private double yOld;
	
	private boolean canJump;
	private boolean canMove;
	
	public Player()
	{
		super(ELLIPSE);
	}
	
	public void init(Game game, int xPos, int yPos)
	{
		this.game = game;
		
		setWidth (0.8 * Block.getSize());
		setHeight(0.8 * Block.getSize());
		setX((xPos + 0.5) * Block.getSize() - getWidth()  / 2);
		setY((yPos + 0.5) * Block.getSize() - getHeight() / 2);
		setColor(Color.red);
		setMoveSpeed(20);
		setGravityPower(20);
		setJumpPower(8);
		setFrictionFactor(0.8);
	}
	
	public void update()
	{
		xOld = getX();
		yOld = getY();
		
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
		if(Game.getKeyboard().isKeyDown(KeyEvent.VK_SPACE))
		{
			if(canJump)
			{
				canJump = false;
				ySpeed -= jumpPower * Block.getSize();
			}
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
			if(canMove)
			{
				xSpeed -= moveSpeed * Block.getSize() * Game.getTimeDelta();
			}
		}
		else if(moveRight)
		{
			if(canMove)
			{
				xSpeed += moveSpeed * Block.getSize() * Game.getTimeDelta();
			}
		}
		else
		{
			canMove = true;
		}
		
		xSpeed *= frictionFactor;
		
		moveX(xSpeed * Game.getTimeDelta());
		
		if(getX() < 0) setX(0);
		else if(getRight() > game.getMap().getWidth()) setX(game.getMap().getWidth() - getWidth());
		
		collision(true, false);
	}
	
	private void collision(boolean x, boolean y)
	{		
		int x1 = (int) (getLeft  () / Block.getSize());
		int x2 = (int) (getRight () / Block.getSize());
		int y1 = (int) (getTop   () / Block.getSize());
		int y2 = (int) (getBottom() / Block.getSize());
		
		if(x1 < 0) x1 = 0;
		else if(x2 > game.getMap().getSizeX() - 1) x2 = game.getMap().getSizeX() - 1;
		
		if(y1 < 0) y1 = 0;
		else if(y2 > game.getMap().getSizeY() - 1) y2 = game.getMap().getSizeY() - 1;
		
		for(int xPos = x1; xPos <= x2; xPos++)
		{
			for(int yPos = y1; yPos <= y2; yPos++)
			{
				Block block = game.getMap().getBlock(xPos, yPos);
				
				if(block.getID() != 0 && this.intersects(block))
				{
					if(x)
					{
						setX(xOld);
						xSpeed = -xSpeed;
						canMove = false;
					}
					if(y) 
					{
						setY(yOld);
						ySpeed = 0;
						canJump = true;
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
