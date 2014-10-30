package com.thebigbadwolf2.SicknessMod.init;

import com.thebigbadwolf2.SicknessMod.block.BlockCompactedBlueDust;
import com.thebigbadwolf2.SicknessMod.block.BlockCompactedGreenDust;
import com.thebigbadwolf2.SicknessMod.block.BlockCompactedRedDust;
import com.thebigbadwolf2.SicknessMod.block.BlockWSMod;
import com.thebigbadwolf2.SicknessMod.block.fireblock.BlockBlueFire;
import com.thebigbadwolf2.SicknessMod.block.fireblock.BlockColoredFire;
import com.thebigbadwolf2.SicknessMod.block.fireblock.BlockGreenFire;
import com.thebigbadwolf2.SicknessMod.block.fireblock.BlockRedFire;
import com.thebigbadwolf2.SicknessMod.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks
{
	public static final BlockWSMod compactedRedDust = new BlockCompactedRedDust();
	public static final BlockWSMod compactedGreenDust = new BlockCompactedGreenDust();
	public static final BlockWSMod compactedBlueDust = new BlockCompactedBlueDust();

	public static final BlockColoredFire redFire = new BlockRedFire();
	public static final BlockColoredFire greenFire = new BlockGreenFire();
	public static final BlockColoredFire blueFire = new BlockBlueFire();

	public static void init()
	{
		GameRegistry.registerBlock(compactedRedDust,"compactedRedDust");
		GameRegistry.registerBlock(compactedGreenDust,"compactedGreenDust");
		GameRegistry.registerBlock(compactedBlueDust,"compactedBlueDust");

		GameRegistry.registerBlock(redFire,"redFire");
		GameRegistry.registerBlock(greenFire,"greenFire");
		GameRegistry.registerBlock(blueFire,"blueFire");
	}
}
