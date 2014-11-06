package com.thebigbadwolf2.SicknessMod.creativetab;

import com.thebigbadwolf2.SicknessMod.init.ModBlocks;
import com.thebigbadwolf2.SicknessMod.init.ModItems;
import com.thebigbadwolf2.SicknessMod.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabWSMod
{
	public static final CreativeTabs WSMod_TAB_ITEMS = new CreativeTabs(Reference.MOD_ID.toLowerCase()+".items")
	{
		@Override
		public Item getTabIconItem()
		{
			return ModItems.redDust;
		}
	};

	public static final CreativeTabs WSMod_TAB_BLOCKS = new CreativeTabs(Reference.MOD_ID.toLowerCase()+".blocks")
	{
		@Override
		public Item getTabIconItem()
		{
			return ModBlocks.compactedRedDust.getItem(null,0,0,0);
		}
	};

	public static final CreativeTabs WSMod_TAB_MED = new CreativeTabs(Reference.MOD_ID.toLowerCase()+".medicine")
	{
		@Override
		public Item getTabIconItem()
		{
			return ModItems.pillBase;
		}
	};
}
