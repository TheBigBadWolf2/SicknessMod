package com.thebigbadwolf2.SicknessMod.init;

import com.thebigbadwolf2.SicknessMod.reference.Reference;
import com.thebigbadwolf2.SicknessMod.tileentity.TileEntityDoorControl;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModTileEntity
{
	public static void init(){
		GameRegistry.registerTileEntity(TileEntityDoorControl.class,ModBlocks.doorControl.getName());
	}
}
