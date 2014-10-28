package com.thebigbadwolf2.SicknessMod.init;

import com.thebigbadwolf2.SicknessMod.block.BlockCompactedBlueDust;
import com.thebigbadwolf2.SicknessMod.block.BlockCompactedGreenDust;
import com.thebigbadwolf2.SicknessMod.block.BlockCompactedRedDust;
import com.thebigbadwolf2.SicknessMod.block.BlockWSMod;
import com.thebigbadwolf2.SicknessMod.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks
{
	public static final BlockWSMod compactedRedDust = new BlockCompactedRedDust();
	public static final BlockWSMod compactedGreenDust = new BlockCompactedGreenDust();
	public static final BlockWSMod compactedBlueDust = new BlockCompactedBlueDust();

	public static void init()
	{
		GameRegistry.registerBlock(compactedRedDust,"compactedRedDust");
		GameRegistry.registerBlock(compactedGreenDust,"compactedGreenDust");
		GameRegistry.registerBlock(compactedBlueDust,"compactedBlueDust");
	}
}
