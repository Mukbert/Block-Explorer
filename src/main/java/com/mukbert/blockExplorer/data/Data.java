package com.mukbert.blockExplorer.data;

import java.awt.Color;
import java.util.HashMap;

public class Data 
{
	
	public static BlockData getBlockData(int ID)
	{
		return Block.data.get(ID);
	}
	
	protected static BlockData setBlockData(BlockData blockData)
	{
		Block.data.put(blockData.getID(), blockData);
		return blockData;
	}
	
	public static class Block
	{		
		private static HashMap<Integer, BlockData> data = new HashMap<Integer, BlockData>();
		
		public static BlockData AIR 	= BlockData.create(0).setName("Air").setColor(null);
		public static BlockData DIRT 	= BlockData.create(1).setName("Dirt").setColor(Color.orange).setRemoveTime(0.2);
		public static BlockData STONE 	= BlockData.create(2).setName("Stone").setColor(Color.gray).setRemoveTime(0.5);
	}
}
