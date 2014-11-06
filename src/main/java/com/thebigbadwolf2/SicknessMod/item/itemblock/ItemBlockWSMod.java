package com.thebigbadwolf2.SicknessMod.item.itemblock;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class ItemBlockWSMod extends ItemBlock
{
	public ItemBlockWSMod(Block block)
	{
		super(block);
		this.setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int meta)
	{
		return meta;
	}
}
