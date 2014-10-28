package com.thebigbadwolf2.SicknessMod.init;

import com.thebigbadwolf2.SicknessMod.item.ItemBlueDust;
import com.thebigbadwolf2.SicknessMod.item.ItemGreenDust;
import com.thebigbadwolf2.SicknessMod.item.ItemRedDust;
import com.thebigbadwolf2.SicknessMod.item.ItemWSMod;
import com.thebigbadwolf2.SicknessMod.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
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
