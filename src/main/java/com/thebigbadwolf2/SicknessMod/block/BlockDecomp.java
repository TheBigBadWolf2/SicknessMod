package com.thebigbadwolf2.SicknessMod.block;

import com.thebigbadwolf2.SicknessMod.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

import java.util.Random;

public class BlockDecomp extends BlockWSMod
{
	public BlockDecomp(){
		super(Material.ground);
		this.setStepSound(Block.soundTypeGrass);
		this.setHardness(1.0F);
		this.setTickRandomly(true);
		this.setHarvestLevel("shovel",1);
	}

	private boolean canReplace(Block block)
	{
		return ModBlocks.isOrganic(block)&&
		       !(block==ModBlocks.decomp||
		         block==ModBlocks.encrustedMycelium);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand)
	{
		for (int i = -1; i <= 1; i++)
			for (int j = -1; j <= 0; j++)
				for (int k = -1; k <= 1; k++)
					if (canReplace(world.getBlock(x+i,y+j,z+k)))
						world.setBlock(x+i,y+j,z+k, ModBlocks.decomp);

	}
}
