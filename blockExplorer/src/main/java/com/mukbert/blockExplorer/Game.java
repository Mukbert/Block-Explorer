package com.mukbert.blockExplorer;

import java.awt.Color;
import java.awt.Graphics2D;

import com.mukbert.framework.Framework;

public class Game extends Framework
{
	private Map map;
	private Player player;
	
	public Game() 
	{
		super("Game", 800, 600);
		
		setFPS(30);
		
		map = new Map();
		player = new Player();
	}
	
	public void init(int width, int height) 
	{
		map.init();
		player.init(this, 2, 2);
	}
	
	public void update(double t)
	{
		player.update();
	}
	
	public void draw(Graphics2D g) 
	{		
		map.draw(g);
		player.draw(g);
		
		Block block = map.getBlock(Game.getMouse().getPoint());
		if(block != null)
		{
			g.setColor(Color.gray);
			g.draw(block.getBounds());
		}
		
		g.setColor(Color.black);
		g.drawString("FPS " + Game.getFPS(), 10, 20);
	}
	
	public Map getMap()
	{
		return map;
	}
	
	public Player getPlayer()
	{
		return player;
	}
	
	public static void main(String[] args) 
	{
		Framework framework = new Game();
		framework.start();
	}
}
