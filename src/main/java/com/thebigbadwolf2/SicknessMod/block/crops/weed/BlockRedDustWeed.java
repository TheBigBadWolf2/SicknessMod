package com.thebigbadwolf2.SicknessMod.block.crops.weed;

import com.thebigbadwolf2.SicknessMod.init.ModBlocks;
import com.thebigbadwolf2.SicknessMod.init.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.ArrayList;

public class BlockRedDustWeed extends BlockWeed
{
	public BlockRedDustWeed(){
		super(ModBlocks.redDustCrop);
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
	{
		ArrayList<ItemStack> stacks = new ArrayList<ItemStack>();
		stacks.add(new ItemStack(ModItems.redDust));
		if (world.rand.nextInt(2)==0)
			stacks.add(new ItemStack(ModItems.redDustSeed));
		stacks.add(new ItemStack(ModItems.redDustSeed));
		return stacks;
	}
}
