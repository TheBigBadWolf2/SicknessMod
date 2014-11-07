package com.thebigbadwolf2.SicknessMod.item;

import com.thebigbadwolf2.SicknessMod.block.crops.BlockCropWSMod;
import com.thebigbadwolf2.SicknessMod.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemConvertDustB extends ItemWSMod
{
	public ItemConvertDustB(){
		super();
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int meta, float hitX, float hitY, float hitZ)
	{
		Block blockA = world.getBlock(x,y,z);
		//Block block1 = null;
		for (int i = -5; i <= 5 ; i++)
			for (int j = -5; j <= 5; j++)
				for (int k = -5; k <= 5; k++)
					convert(world, x+i, y+j, z+k);

		return false;
	}

	private void convert(World world, int x, int y, int z){
		Block blockA = world.getBlock(x,y,z);
		if (blockA instanceof BlockBush&&!(blockA instanceof BlockCropWSMod))world.setBlock(x,y,z,convertPlant(world));
	}

	private Block convertPlant(World world){
		Block block = null;
		int r = world.rand.nextInt(3);
		if (r==0)block = ModBlocks.redDustCrop;
		else if (r==1)block = ModBlocks.greenDustCrop;
		else if (r==2)block = ModBlocks.blueDustCrop;
		return block;
	}
}
