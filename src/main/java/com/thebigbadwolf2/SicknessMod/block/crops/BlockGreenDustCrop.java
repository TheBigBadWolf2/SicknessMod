package com.thebigbadwolf2.SicknessMod.block.crops;

import com.thebigbadwolf2.SicknessMod.init.ModBlocks;
import com.thebigbadwolf2.SicknessMod.init.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Random;

public class BlockGreenDustCrop extends BlockCropWSMod
{
	public BlockGreenDustCrop(){
		super();
	}

	/**
	 * Returns the quantity of items to drop on block destruction.
	 */
	@Override
	public int quantityDropped(int parMetadata, int parFortune, Random parRand)
	{
		return (parMetadata/2);
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
	{
		ArrayList<ItemStack> stacks = new ArrayList<ItemStack>();
		if (metadata==7)
			stacks.add(new ItemStack(ModItems.greenDust));
		if (metadata==7&&world.rand.nextInt(2)==0)
			stacks.add(new ItemStack(ModItems.greenDustSeed));
		stacks.add(new ItemStack(ModItems.greenDustSeed));
		return stacks;
	}

	@Override
	public void incrementGrowStage(World world, Random rand, int x, int y, int z)
	{
		super.incrementGrowStage(world, rand, x, y, z);
		if (world.getBlockMetadata(x,y,z)==7){
			if (rand.nextInt(20)==0){
				world.setBlock(x,y,z, ModBlocks.greenDustWeed,0,3);
				world.setBlock(x,y+1,z, ModBlocks.greenDustWeed,2,3);
			}
		}
	}
}
