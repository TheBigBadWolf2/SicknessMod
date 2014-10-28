package com.thebigbadwolf2.SicknessMod.block;

import com.thebigbadwolf2.SicknessMod.init.ModBlocks;
import com.thebigbadwolf2.SicknessMod.init.ModItems;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockCompactedDust extends BlockWSMod
{
	public BlockCompactedDust()
	{
		super();
	}


	@Override
	public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int p_149664_5_)
	{
		ItemStack stack = null;
		if (this.equals(ModBlocks.compactedRedDust))
			stack = new ItemStack(ModItems.redDust,1);
		else if (this.equals(ModBlocks.compactedGreenDust))
			stack = new ItemStack(ModItems.greenDust,1);
		else if (this.equals(ModBlocks.compactedBlueDust))
			stack = new ItemStack(ModItems.blueDust,1);

		if (stack!=null)
			world.spawnEntityInWorld(new EntityItem(world,x+0.5,y+0.5,z+0.5,stack));
	}
}
