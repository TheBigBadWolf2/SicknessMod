package com.thebigbadwolf2.SicknessMod.block.crops.weed;

import com.thebigbadwolf2.SicknessMod.init.ModBlocks;
import com.thebigbadwolf2.SicknessMod.init.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.ArrayList;

public class BlockBlueDustWeed extends BlockWeed
{
	public BlockBlueDustWeed()
	{
		super(ModBlocks.blueDustCrop);
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
	{
		ArrayList<ItemStack> stacks = new ArrayList<ItemStack>();
		stacks.add(new ItemStack(ModItems.blueDust));
		if (world.rand.nextInt(2)==0)
			stacks.add(new ItemStack(ModItems.blueDustSeed));
		stacks.add(new ItemStack(ModItems.blueDust));
		return stacks;
	}
}
