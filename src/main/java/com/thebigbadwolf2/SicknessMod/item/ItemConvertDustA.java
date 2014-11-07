package com.thebigbadwolf2.SicknessMod.item;

import com.thebigbadwolf2.SicknessMod.block.BlockDecomp;
import com.thebigbadwolf2.SicknessMod.block.BlockEncrustedMycelium;
import com.thebigbadwolf2.SicknessMod.block.crops.BlockCropWSMod;
import com.thebigbadwolf2.SicknessMod.block.crops.weed.BlockWeed;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ItemConvertDustA extends ItemWSMod
{
	public ItemConvertDustA(){
		super();
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int meta, float hitX, float hitY, float hitZ)
	{
		for (int i = -5; i <= 5; i++)
			for (int j = -5; j <= 5; j++)
				for (int k = -5; k <=5 ; k++)
					convert(world, x+i, y+j, z+k);
		return true;
	}

	private boolean convert(World world, int x, int y, int z){
		Block blockA = world.getBlock(x,y,z);
		Block blockB = world.getBlock(x,y-1,z);
		Block block1 = null;
		Block block2 = null;
		if (!world.isRemote)
			if (blockA instanceof BlockCropWSMod){
				if ((blockA instanceof BlockWeed)){
					if (world.getBlockMetadata(x,y,z)==0)
					{
						block1 = convertPlant(world);
						if (block1 instanceof BlockCrops||block1 == Blocks.carrots||block1 == Blocks.potatoes)
						{
							if (blockB instanceof BlockEncrustedMycelium ||
							    blockB instanceof BlockDecomp ||
							    blockB instanceof BlockDirt ||
							    blockB instanceof BlockGrass ||
							    blockB instanceof BlockMycelium)
								block2 = Blocks.farmland;
							else block1 = Blocks.air;
						}
						else if (blockB instanceof BlockEncrustedMycelium || blockB instanceof BlockDecomp)
							block2 = convertSoil(world);
					}else block1 = Blocks.air;
				}else
				{
					block1 = convertPlant(world);
					if (block1 instanceof BlockCrops||block1 == Blocks.carrots||block1 == Blocks.potatoes)
					{
						if (blockB instanceof BlockEncrustedMycelium ||
						    blockB instanceof BlockDecomp ||
						    blockB instanceof BlockDirt ||
						    blockB instanceof BlockGrass ||
						    blockB instanceof BlockMycelium)
							block2 = Blocks.farmland;
						else block1 = Blocks.air;
					}
					else if (blockB instanceof BlockEncrustedMycelium || blockB instanceof BlockDecomp)
						block2 = convertSoil(world);
				}

			}else if (blockA instanceof BlockEncrustedMycelium||blockA instanceof BlockDecomp){
				block1 = convertSoil(world);
			}
		if (block1!=null){
			world.setBlock(x,y,z,block1);
			if (block2!=null)
				world.setBlock(x,y-1,z,block2);
			return true;
		}
		return false;
	}

	private Block convertSoil(World world){
		Block block = null;

		int r = world.rand.nextInt(3);
		if (r==0) block = Blocks.grass;
		else if (r==1) block = Blocks.dirt;
		else if (r==2) block = Blocks.mycelium;

		return block;
	}

	private Block convertPlant(World world){
		Block block = null;

		int r = world.rand.nextInt(12);
		if (r==0) block = Blocks.tallgrass;
		else if (r==1) block = Blocks.deadbush;
		else if (r==2) block = Blocks.yellow_flower;
		else if (r==3) block = Blocks.red_flower;
		else if (r==4) block = Blocks.brown_mushroom;
		else if (r==5) block = Blocks.red_mushroom;
		else if (r==6) block = Blocks.wheat;
		else if (r==7) block = Blocks.reeds;
		else if (r==8) block = Blocks.pumpkin;
		else if (r==9) block = Blocks.melon_block;
		else if (r==10) block = Blocks.carrots;
		else if (r==11) block = Blocks.potatoes;

		return block;
	}
}
