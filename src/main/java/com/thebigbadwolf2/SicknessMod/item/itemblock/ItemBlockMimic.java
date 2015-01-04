package com.thebigbadwolf2.SicknessMod.item.itemblock;

import com.thebigbadwolf2.SicknessMod.item.itemblock.ItemBlockWSMod;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class ItemBlockMimic extends ItemBlockWSMod
{
	private static final String[] subNames = {"red","green","blue","dirt"};

	public ItemBlockMimic(Block block)
	{
		super(block);
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		return super.getUnlocalizedName(itemStack)+"."+subNames[itemStack.getItemDamage()];
	}
}
