package com.thebigbadwolf2.SicknessMod.init;

import com.thebigbadwolf2.SicknessMod.item.ItemBlueDust;
import com.thebigbadwolf2.SicknessMod.item.ItemGreenDust;
import com.thebigbadwolf2.SicknessMod.item.ItemRedDust;
import com.thebigbadwolf2.SicknessMod.item.ItemWSMod;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModItems
{
	public static final ItemWSMod redDust = new ItemRedDust();
	public static final ItemWSMod greenDust = new ItemGreenDust();
	public static final ItemWSMod blueDust = new ItemBlueDust();

	public static void init()
	{
		GameRegistry.registerItem(redDust,"redDust");
		GameRegistry.registerItem(greenDust,"greenDust");
		GameRegistry.registerItem(blueDust,"blueDust");
	}
}
