package com.thebigbadwolf2.SicknessMod.block.ore;

import com.thebigbadwolf2.SicknessMod.init.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class BlockGreenOre extends BlockOre
{
	public BlockGreenOre(){
		super();
	}

	@Override
	public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int mata)
	{
		super.harvestBlock(world,x,y,z, ModItems.greenDust);
	}
}
